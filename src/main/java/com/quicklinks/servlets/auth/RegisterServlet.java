package com.quicklinks.servlets.auth;

import com.quicklinks.utils.EmailUtil;
import com.quicklinks.dao.EmailVerificationTokenDAO;
import com.quicklinks.dao.UserDAO;
import com.quicklinks.model.EmailVerificationToken;
import com.quicklinks.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/auth/register")
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
            User user = userDAO.getUserByEmail(email);
            String token = UUID.randomUUID().toString();
            EmailVerificationToken verificationToken = new EmailVerificationToken();
            verificationToken.setUserId(user.getId());
            verificationToken.setToken(token);

            EmailVerificationTokenDAO emailVerificationTokenDAO = new EmailVerificationTokenDAO();
            emailVerificationTokenDAO.createToken(verificationToken);

            String verificationLink = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/auth/verify-email?token=" + token;
            EmailUtil.sendEmail(email, "Email Verification", "Please click the following link to verify your email: " + verificationLink);

            response.sendRedirect(request.getContextPath() + "/auth/login.jsp?registered=true");
        } else {
            request.setAttribute("error", "Registration failed. Try again!");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
        }
    }
}
