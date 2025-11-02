package com.quicklinks.servlets;

import java.io.IOException;
import java.util.UUID;

import com.quicklinks.dao.ShortLinkDAO;
import com.quicklinks.model.ShortLink;
import com.quicklinks.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createShortLink")
public class CreateShortLinkServlet extends jakarta.servlet.http.HttpServlet {
    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("auth/login.jsp");
            return;
        }

        String originalUrl = request.getParameter("original_url");
        String shortCode = generateShortCode();

        ShortLink link = new ShortLink();
        link.setUserId(user.getId());
        link.setOriginalUrl(originalUrl);
        link.setShortCode(shortCode);

        ShortLinkDAO dao = new ShortLinkDAO();
        if (dao.addShortLink(link)) {
            response.sendRedirect("dashboard.jsp?success=1");
        } else {
            response.sendRedirect("dashboard.jsp?error=1");
        }
    }
}
