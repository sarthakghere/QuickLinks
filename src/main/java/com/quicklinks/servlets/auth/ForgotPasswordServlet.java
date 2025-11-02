package com.quicklinks.servlets.auth;

import com.quicklinks.dao.PasswordResetTokenDAO;
import com.quicklinks.dao.UserDAO;
import com.quicklinks.model.PasswordResetToken;
import com.quicklinks.model.User;
import com.quicklinks.utils.EmailUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/auth/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    private UserDAO userDAO;
    private PasswordResetTokenDAO passwordResetTokenDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        passwordResetTokenDAO = new PasswordResetTokenDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        User user = userDAO.getUserByEmail(email);

        if (user != null) {
            passwordResetTokenDAO.invalidateTokensByUser(user.getId());

            String token = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setUserId(user.getId());
            passwordResetToken.setToken(token);

            passwordResetTokenDAO.createToken(passwordResetToken);

            String resetLink = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/auth/reset-password?token=" + token;
            EmailUtil.sendEmail(email, "Password Reset",
                    "Please click the following link to reset your password: " + resetLink);

            request.setAttribute("message", "A password reset link has been sent to your email address.");
        } else {
            request.setAttribute("error", "No user found with that email address.");
        }

        request.getRequestDispatcher("/auth/forgot_password.jsp").forward(request, response);
    }
}