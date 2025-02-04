package org.example.servlets;

import static org.mockito.Mockito.*;

import jakarta.servlet.RequestDispatcher;
import org.example.models.Message;
import org.example.models.User;
import org.example.storage.InMemoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChatsServletTest {

    private ChatsServlet servlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new ChatsServlet();
        InMemoryDB.users.clear();
        InMemoryDB.messages.clear();

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGetWithMessages() throws Exception {
        User user = new User("testUser", "password", "Test User", "1990-01-01");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        InMemoryDB.messages.add(new Message("sender1", "testUser", "Hello!", "2025-02-04T10:00:00"));
        InMemoryDB.messages.add(new Message("sender2", "otherUser", "Hi!", "2025-02-04T10:05:00"));

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("messages"), anyList());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetWithNoMessages() throws Exception {
        User user = new User("testUser", "password", "Test User", "1990-01-01");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("messages"), anyList());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetWithoutUser() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);

        servlet.doGet(request, response);

        verify(response).sendRedirect("signIn");
    }
}