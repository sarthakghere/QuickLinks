package com.quicklinks.servlets.auth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.quicklinks.dao.EmailVerificationTokenDAO;
import com.quicklinks.dao.UserDAO;
import com.quicklinks.model.EmailVerificationToken;
import com.quicklinks.model.User;

@WebServlet("/auth/verify-email")
public class EmailVerificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EmailVerificationTokenDAO emailVerificationTokenDAO;
    private UserDAO userDAO;

    public void init() {
        emailVerificationTokenDAO = new EmailVerificationTokenDAO();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");

        if (token == null || token.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Verification token is missing.");
            return;
        }

        try {
            EmailVerificationToken verificationToken = emailVerificationTokenDAO.findByToken(token);

            if (verificationToken == null || verificationToken.isExpired()) {
                request.setAttribute("errorMessage", "Invalid or expired verification token.");
                request.getRequestDispatcher("/auth/verification_error.jsp").forward(request, response);
                return;
            }

            User user = userDAO.findById(verificationToken.getUserId());
            if (user != null) {
                user.setVerified(true);
                userDAO.updateUser(user);
                emailVerificationTokenDAO.deleteToken(verificationToken.getId());
                response.sendRedirect(request.getContextPath() + "/auth/verification_success.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
            }

        } catch (Exception e) {
            throw new ServletException("Database error during email verification.", e);
        }
    }
}
