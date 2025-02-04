package org.example.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import org.example.models.Message;
import org.example.models.User;
import org.example.storage.InMemoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageServletTest {
    @Mock
    private MessageServlet messageServlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;

    @BeforeEach
    public void setUp() throws IOException {
        messageServlet = new MessageServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/message.jsp")).thenReturn(dispatcher);


        InMemoryDB.users = new HashMap<>();
        InMemoryDB.messages = new ArrayList<>();
    }

    @Test
    public void testDoPost_ValidRecipient() throws ServletException, IOException {
        User sender = new User("senderUser", "password", "Sender User", "1990-01-01");
        User recipient = new User("recipientUser", "password", "Recipient User", "1990-01-01");

        InMemoryDB.users.put(sender.getLogin(), sender);
        InMemoryDB.users.put(recipient.getLogin(), recipient);

        when(session.getAttribute("user")).thenReturn(sender);
        when(request.getParameter("to")).thenReturn(recipient.getLogin());
        when(request.getParameter("text")).thenReturn("Hello!");

        messageServlet.doPost(request, response);

        assertEquals(1, InMemoryDB.messages.size());
        Message message = InMemoryDB.messages.get(0);
        assertEquals(sender.getLogin(), message.getFrom());
        assertEquals(recipient.getLogin(), message.getTo());
        assertEquals("Hello!", message.getText());
        verify(request).setAttribute("responseMessage", "Message sent!");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_InvalidRecipient() throws ServletException, IOException {
        User sender = new User("senderUser", "password", "Sender User", "1990-01-01");

        InMemoryDB.users.put(sender.getLogin(), sender);

        when(session.getAttribute("user")).thenReturn(sender);
        when(request.getParameter("to")).thenReturn("nonExistentUser");
        when(request.getParameter("text")).thenReturn("Hello!");

        messageServlet.doPost(request, response);

        assertEquals(0, InMemoryDB.messages.size());
        verify(request).setAttribute("responseMessage", "Recipient not found!");
        verify(dispatcher).forward(request, response);
    }
}