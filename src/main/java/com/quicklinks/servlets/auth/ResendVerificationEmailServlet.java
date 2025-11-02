package com.quicklinks.servlets.auth;

import com.quicklinks.dao.EmailVerificationTokenDAO;
import com.quicklinks.dao.UserDAO;
import com.quicklinks.model.EmailVerificationToken;
import com.quicklinks.model.User;
import com.quicklinks.utils.EmailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/auth/resend-verification-email")
public class ResendVerificationEmailServlet extends HttpServlet {

    private UserDAO userDAO;
    private EmailVerificationTokenDAO emailVerificationTokenDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        emailVerificationTokenDAO = new EmailVerificationTokenDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        User user = userDAO.getUserByEmail(email);

        if (user != null) {
            emailVerificationTokenDAO.deleteTokenByUserId(user.getId());

            String token = UUID.randomUUID().toString();
            EmailVerificationToken verificationToken = new EmailVerificationToken();
            verificationToken.setUserId(user.getId());
            verificationToken.setToken(token);

            emailVerificationTokenDAO.createToken(verificationToken);

            String verificationLink = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/auth/verify-email?token=" + token;
            EmailUtil.sendEmail(email, "Email Verification", "Please click the following link to verify your email: " + verificationLink);

            request.setAttribute("message", "A new verification email has been sent to your email address.");
        } else {
            request.setAttribute("error", "User with that email address does not exist.");
        }

        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
    }
}
