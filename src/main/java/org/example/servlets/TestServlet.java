package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Test Servlet is working!</h1>");
    }
}