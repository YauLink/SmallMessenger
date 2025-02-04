package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.models.User;
import org.example.storage.InMemoryDB;

import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = InMemoryDB.users.get(login);
        if (user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);

            req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
        } else {
            resp.getWriter().write("Invalid login or password!");
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
    }
}
