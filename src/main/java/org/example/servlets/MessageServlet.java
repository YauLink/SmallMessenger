package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.models.Message;
import org.example.models.User;
import org.example.storage.InMemoryDB;

import java.io.IOException;

public class MessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = ((User) req.getSession().getAttribute("user")).getLogin();
        String to = req.getParameter("to");
        String text = req.getParameter("text");
        String timestamp = java.time.LocalDateTime.now().toString();

        if (InMemoryDB.users.containsKey(to)) {
            Message message = new Message(from, to, text, timestamp);
            InMemoryDB.messages.add(message);
            resp.getWriter().write("Message sent!");
        } else {
            resp.getWriter().write("Recipient not found!");
        }
    }
}
