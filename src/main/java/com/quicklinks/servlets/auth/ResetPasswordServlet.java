package com.quicklinks.servlets.auth;

import com.quicklinks.dao.PasswordResetTokenDAO;
import com.quicklinks.dao.UserDAO;
import com.quicklinks.model.PasswordResetToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet("/auth/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    private UserDAO userDAO;
    private PasswordResetTokenDAO passwordResetTokenDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        passwordResetTokenDAO = new PasswordResetTokenDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        PasswordResetToken passwordResetToken = passwordResetTokenDAO.getByToken(token);

        if (passwordResetToken == null || passwordResetToken.getExpiry().before(Timestamp.from(Instant.now()))) {
            request.setAttribute("message", "Invalid or expired password reset link.");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }

        request.setAttribute("token", token);
        request.getRequestDispatcher("/auth/reset_password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        if (password == null || !password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.setAttribute("token", token);
            request.getRequestDispatcher("/auth/reset_password.jsp").forward(request, response);
            return;
        }

        PasswordResetToken passwordResetToken = passwordResetTokenDAO.getByToken(token);

        if (passwordResetToken == null || passwordResetToken.getExpiry().before(Timestamp.from(Instant.now()))) {
            request.setAttribute("message", "Invalid or expired password reset link.");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }

        userDAO.updatePassword(passwordResetToken.getUserId(), password);
        passwordResetTokenDAO.deleteToken(token);

        request.setAttribute("message", "Password has been reset successfully. Please login.");
        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
    }
}