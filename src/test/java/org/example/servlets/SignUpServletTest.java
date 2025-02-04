package org.example.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import org.example.models.User;
import org.example.storage.InMemoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class SignUpServletTest {
    @Mock
    private SignUpServlet signUpServlet;
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
        signUpServlet = new SignUpServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        writer = mock(PrintWriter.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(writer);
        when(request.getRequestDispatcher("/views/signIn.jsp")).thenReturn(dispatcher);

        InMemoryDB.users = new HashMap<>();
    }

    @Test
    public void testDoPost_NewUser() throws ServletException, IOException {
        String login = "newUser";
        String password = "newPass";
        String fullName = "New User";
        String dateOfBirth = "1990-01-01";

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("fullName")).thenReturn(fullName);
        when(request.getParameter("dateOfBirth")).thenReturn(dateOfBirth);

        signUpServlet.doPost(request, response);

        User user = InMemoryDB.users.get(login);
        assertNotNull(user);
        assertEquals(fullName, user.getFullName());
        assertEquals(dateOfBirth, user.getDateOfBirth());
        verify(request.getRequestDispatcher("/views/signIn.jsp")).forward(request, response);
    }

    @Test
    public void testDoPost_ExistingUser() throws ServletException, IOException {
        String login = "existingUser";
        String password = "existingPass";
        String fullName = "Existing User";
        String dateOfBirth = "1990-01-01";

        User existingUser = new User(login, password, fullName, dateOfBirth);
        InMemoryDB.users.put(login, existingUser);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("fullName")).thenReturn(fullName);
        when(request.getParameter("dateOfBirth")).thenReturn(dateOfBirth);

        signUpServlet.doPost(request, response);

        verify(writer).write("User already exists!");
    }
}