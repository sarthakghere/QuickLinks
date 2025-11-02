package com.quicklinks.servlets;

import com.quicklinks.dao.ShortLinkDAO;
import com.quicklinks.dao.LinkVisitDAO;
import com.quicklinks.model.ShortLink;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/s/*")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String shortCode = request.getPathInfo().substring(1); // e.g. /abc123 → abc123

        ShortLinkDAO dao = new ShortLinkDAO();
        ShortLink link = dao.getByShortCode(shortCode);

        if (link != null) {
            dao.incrementVisitCount(link.getId());
            new LinkVisitDAO().recordVisit(link.getId());
            response.sendRedirect(link.getOriginalUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Short link not found");
        }
    }
}
