<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.quicklinks.model.User" %>
<%@ page import="com.quicklinks.model.ShortLink" %>
<%@ page import="com.quicklinks.dao.ShortLinkDAO" %>

<%
    User user = (session != null) ? (User) session.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("auth/login.jsp");
        return;
    }

    String linkIdStr = request.getParameter("id");
    if (linkIdStr == null || linkIdStr.isEmpty()) {
        response.sendRedirect("dashboard.jsp");
        return;
    }

    ShortLinkDAO linkDAO = new ShortLinkDAO();
    ShortLink link = linkDAO.getById(Integer.parseInt(linkIdStr));

    if (link == null || link.getUserId() != user.getId()) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>

<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<style>
    body {
        background: linear-gradient(135deg, #f0f4ff, #eaf7ff);
        font-family: "Segoe UI", Roboto, sans-serif;
        color: #222;
    }

    .edit-card {
        background: #fff;
        border-radius: 16px;
        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.08);
        padding: 2rem 2.5rem;
        margin-top: 4rem;
        max-width: 600px;
        margin-left: auto;
        margin-right: auto;
        animation: fadeIn 0.4s ease;
    }

    .edit-card h3 {
        font-weight: 700;
        text-align: center;
        color: #1a1a1a;
        margin-bottom: 1.8rem;
    }

    label {
        font-weight: 600;
        color: #333;
        margin-bottom: 0.4rem;
        display: block;
    }

    input[type="url"] {
        width: 100%;
        padding: 0.8rem 1rem;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 1rem;
        transition: all 0.25s ease;
    }

    input[type="url"]:focus {
        border-color: #0078d4;
        box-shadow: 0 0 0 3px rgba(0,120,212,0.15);
        outline: none;
    }

    .btn-primary {
        display: inline-block;
        width: 100%;
        padding: 0.8rem 1rem;
        font-size: 1rem;
        font-weight: 600;
        border-radius: 8px;
        border: none;
        color: #fff;
        background: linear-gradient(90deg, #0078d4, #00b4d8);
        transition: all 0.3s ease;
        cursor: pointer;
        margin-top: 1rem;
    }

    .btn-primary:hover {
        background: linear-gradient(90deg, #005fa3, #0092b3);
        transform: translateY(-1px);
        box-shadow: 0 4px 10px rgba(0, 95, 163, 0.25);
    }

    .back-link {
        display: block;
        text-align: center;
        margin-top: 1.5rem;
        color: #0078d4;
        text-decoration: none;
        font-weight: 500;
        transition: 0.2s ease;
    }

    .back-link:hover {
        text-decoration: underline;
        color: #005fa3;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(10px); }
        to { opacity: 1; transform: translateY(0); }
    }

    @media (max-width: 576px) {
        .edit-card {
            padding: 1.5rem;
            margin-top: 3rem;
        }
    }
</style>

<div class="container">
    <div class="edit-card">
        <h3>Edit Short Link ✏️</h3>
        <form action="<%= request.getContextPath() %>/editShortLink" method="post">
            <input type="hidden" name="link_id" value="<%= link.getId() %>">

            <label for="original_url">Original URL</label>
            <input type="url" id="original_url" name="original_url" 
                   value="<%= link.getOriginalUrl() %>" required>

            <button type="submit" class="btn-primary">Update Link</button>
        </form>

        <a href="dashboard.jsp" class="back-link">← Back to Dashboard</a>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
