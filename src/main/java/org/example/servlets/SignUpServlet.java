package org.example.servlets;

import org.example.models.User;
import org.example.storage.InMemoryDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.User;

import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String dateOfBirth = req.getParameter("dateOfBirth");

        if (!InMemoryDB.users.containsKey(login)) {
            User user = new User(login, password, fullName, dateOfBirth);
            InMemoryDB.users.put(login, user);

            //resp.sendRedirect(req.getContextPath() + "/views/signIn.jsp"); doesnt work changed to RequestDispatcher

            req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
        } else {
            resp.getWriter().write("User already exists!");
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }
}

