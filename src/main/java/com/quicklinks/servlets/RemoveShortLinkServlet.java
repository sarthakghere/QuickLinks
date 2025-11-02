package com.quicklinks.servlets;

import com.quicklinks.dao.ShortLinkDAO;
import com.quicklinks.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/removeShortLink")
public class RemoveShortLinkServlet extends HttpServlet {
    private ShortLinkDAO shortLinkDAO;

    @Override
    public void init() {
        shortLinkDAO = new ShortLinkDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("auth/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int linkId = Integer.parseInt(request.getParameter("link_id"));

        // Optional: Check if the link belongs to the user before deleting
        // This is a good security practice
        if (shortLinkDAO.isOwner(user.getId(), linkId)) {
            shortLinkDAO.delete(linkId);
        }

        response.sendRedirect("dashboard.jsp");
    }
}
