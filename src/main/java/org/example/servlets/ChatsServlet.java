package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import org.example.models.Message;
import org.example.models.User;
import org.example.storage.InMemoryDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("signIn");
            return;
        }

        User currentUser = (User) req.getSession().getAttribute("user");
        if (currentUser == null) {
            resp.sendRedirect("signIn");
            return;
        }
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
