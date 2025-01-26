package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.models.Message;
import org.example.models.User;
import org.example.storage.InMemoryDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            resp.sendRedirect("signIn"); // Redirect to login page if not logged in
            return;
        }

        User currentUser = (User) req.getSession().getAttribute("user");
        String currentUserLogin = currentUser.getLogin();
        List<Message> userMessages = new ArrayList<>();


        for (Message message : InMemoryDB.messages) {
            if (message.getTo().equals(currentUserLogin)) {
                userMessages.add(message);
            }
        }

        req.setAttribute("messages", userMessages);
        req.getRequestDispatcher("/views/chats.jsp").forward(req, resp);
    }
}
