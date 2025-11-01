package com.quicklinks.servlets.auth;

import com.quicklinks.dao.UserDAO;
import com.quicklinks.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userDAO.getUserByEmail(email) != null) {
            request.setAttribute("error", "Email already registered!");
            request.getRequestDispatcher("auth/register.jsp").forward(request, response);
            return;
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        boolean inserted = userDAO.addUser(newUser);

        if (inserted) {
            request.setAttribute("message", "Registration successful! Please log in.");
            request.getRequestDispatcher("auth/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Try again!");
            request.getRequestDispatcher("auth/register.jsp").forward(request, response);
        }
    }
}
