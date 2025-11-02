package com.quicklinks.servlets;

import com.quicklinks.dao.ShortLinkDAO;
import com.quicklinks.model.ShortLink;
import com.quicklinks.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editShortLink")
public class EditShortLinkServlet extends HttpServlet {
    private ShortLinkDAO shortLinkDAO;

    @Override
    public void init() throws ServletException {
        shortLinkDAO = new ShortLinkDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("auth/login.jsp");
            return;
        }

        int linkId = Integer.parseInt(req.getParameter("link_id"));
        String originalUrl = req.getParameter("original_url");

        ShortLink link = shortLinkDAO.getById(linkId);

        if (link != null && link.getUserId() == user.getId()) {
            link.setOriginalUrl(originalUrl);
            shortLinkDAO.update(link);
        }

        resp.sendRedirect("dashboard.jsp");
    }
}
