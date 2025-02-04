package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;
import org.example.storage.InMemoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class SignInServletTest {
    @Mock
    private SignInServlet signInServlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws IOException {
        signInServlet = new SignInServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        writer = mock(PrintWriter.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(writer);
        when(request.getRequestDispatcher("/views/message.jsp")).thenReturn(dispatcher);

        InMemoryDB.users = new HashMap<>();
    }

    @Test
    public void testDoPost_ValidCredentials() throws ServletException, IOException {
        String login = "testUser";
        String password = "testPass";
        User user = new User(login, password, "test", "11/11/1111");
        InMemoryDB.users.put(login, user); // Assume InMemoryDB is properly set up

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);

        signInServlet.doPost(request, response);

        verify(session).setAttribute("user", user);
        verify(request.getRequestDispatcher("/views/message.jsp")).forward(request, response);
    }

    @Test
    public void testDoPost_InvalidCredentials() throws ServletException, IOException {
        when(request.getParameter("login")).thenReturn("invalidUser");
        when(request.getParameter("password")).thenReturn("invalidPass");

        signInServlet.doPost(request, response);

        verify(writer).write("Invalid login or password!");
    }
}
