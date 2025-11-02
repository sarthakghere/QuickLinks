<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quicklinks.model.User" %>
<%@ page import="com.quicklinks.dao.ShortLinkDAO" %>
<%@ page import="com.quicklinks.model.ShortLink" %>

<%
    User user = (session != null) ? (User) session.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("auth/login.jsp");
        return;
    }

    ShortLinkDAO linkDAO = new ShortLinkDAO();
    java.util.List<ShortLink> links = linkDAO.getByUserId(user.getId());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - QuickLinks</title>
    <style>
        /* === GLOBAL RESET & BASE === */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: "Segoe UI", Roboto, sans-serif;
            background: linear-gradient(135deg, #f0f4ff, #eaf7ff);
            color: #222;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        /* === NAVBAR (from header.jsp) === */
        .navbar {
            background: #1a73e8;
            color: white;
            padding: 12px 24px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        .navbar h1 {
            margin: 0;
            font-size: 22px;
            font-weight: 600;
        }

        .logout-btn {
            background: #0b57cf;
            color: white;
            border: none;
            padding: 8px 14px;
            border-radius: 5px;
            font-weight: 500;
            cursor: pointer;
            transition: background 0.2s;
        }

        .logout-btn:hover {
            background: #084eb0;
        }

        /* === MAIN CONTENT === */
        .main-content {
            flex: 1;
            padding: 20px;
            max-width: 1000px;
            margin: 0 auto;
            width: 100%;
        }

        .dashboard-card {
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.08);
            padding: 2rem;
            animation: fadeIn 0.4s ease;
        }

        .dashboard-card h2 {
            font-size: 1.8rem;
            font-weight: 700;
            color: #1a1a1a;
            margin-bottom: 0.5rem;
        }

        .user-info p {
            font-size: 1rem;
            color: #555;
            margin-bottom: 0.4rem;
        }

        .verified { color: #28a745; font-weight: 600; }
        .not-verified { color: #dc3545; font-weight: 600; }

        hr {
            margin: 1.5rem 0;
            border: none;
            border-top: 1px solid #eee;
        }

        /* === SHORTEN FORM === */
        .shorten-form h4 {
            font-weight: 600;
            color: #222;
            margin-bottom: 0.8rem;
        }

        .shorten-form form {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
            margin-bottom: 1.5rem;
        }

        .shorten-form input {
            flex: 1;
            min-width: 260px;
            padding: 0.75rem 1rem;
            font-size: 1rem;
            border: 1px solid #ccc;
            border-radius: 8px;
            transition: all 0.2s;
        }

        .shorten-form input:focus {
            outline: none;
            border-color: #0078d4;
            box-shadow: 0 0 0 3px rgba(0,120,212,0.15);
        }

        .shorten-btn {
            padding: 0.75rem 1.5rem;
            background: linear-gradient(90deg, #0078d4, #00b4d8);
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.25s;
        }

        .shorten-btn:hover {
            background: linear-gradient(90deg, #005fa3, #0092b3);
            transform: translateY(-1px);
            box-shadow: 0 4px 10px rgba(0,95,163,0.25);
        }

        /* === TABLE === */
        .table-container {
            overflow-x: auto;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 0.95rem;
            background: #fff;
        }

        thead {
            background: #0078d4;
            color: white;
        }

        th, td {
            padding: 10px 12px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }

        td a {
            color: #0078d4;
            text-decoration: none;
            font-weight: 500;
            word-break: break-all;
        }

        td a:hover {
            text-decoration: underline;
        }

        .btn-sm {
            padding: 5px 10px;
            font-size: 0.8rem;
            border-radius: 6px;
            cursor: pointer;
            transition: 0.2s;
            border: none;
        }

        .btn-primary.btn-sm {
            background: #0078d4;
            color: #fff;
        }

        .btn-primary.btn-sm:hover {
            background: #005fa3;
        }

        .btn-danger.btn-sm {
            background: #d9534f;
            color: #fff;
        }

        .btn-danger.btn-sm:hover {
            background: #c12e2a;
        }

        .no-links {
            color: #777;
            font-style: italic;
            text-align: center;
            padding: 1.5rem 0;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }

        @media (max-width: 576px) {
            .main-content { padding: 15px; }
            .dashboard-card { padding: 1.5rem; }
            .shorten-form form { flex-direction: column; }
            .shorten-form input { min-width: 100%; }
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <h1>QuickLinks</h1>
        <form action="<%= request.getContextPath() %>/logout" method="post">
            <button type="submit" class="logout-btn">Logout</button>
        </form>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <div class="dashboard-card">
            <h2>Welcome, <%= user.getName() %>!</h2>
            <div class="user-info">
                <p><strong>Email:</strong> <%= user.getEmail() %></p>
                <p><strong>Account Verified:</strong>
                    <span class="<%= user.isVerified() ? "verified" : "not-verified" %>">
                        <%= user.isVerified() ? "Yes" : "No" %>
                    </span>
                </p>
            </div>

            <hr>

            <!-- Shorten Form -->
            <div class="shorten-form">
                <h4>Create Short Link</h4>
                <form action="<%= request.getContextPath() %>/createShortLink" method="post">
                    <input type="url" name="original_url" placeholder="Paste long URL here..." required>
                    <button type="submit" class="shorten-btn">Shorten</button>
                </form>
            </div>

            <!-- Links Table -->
            <% if (!links.isEmpty()) { %>
                <div class="table-container">
                    <h4 style="margin: 1.5rem 0 0.8rem;">Your Links</h4>
                    <table>
                        <thead>
                            <tr>
                                <th>Original URL</th>
                                <th>Short URL</th>
                                <th>Visits</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (ShortLink l : links) { %>
                                <tr>
                                    <td title="<%= l.getOriginalUrl() %>">
                                        <%= l.getOriginalUrl().length() > 50 ? l.getOriginalUrl().substring(0, 50) + "..." : l.getOriginalUrl() %>
                                    </td>
                                    <td>
                                        <a href="<%= request.getContextPath() + "/s/" + l.getShortCode() %>" target="_blank">
                                            <%= request.getContextPath() + "/s/" + l.getShortCode() %>
                                        </a>
                                    </td>
                                    <td><%= l.getTotalVisits() %></td>
                                    <td>
                                        <a href="edit_link.jsp?id=<%= l.getId() %>" class="btn-sm btn-primary">Edit</a>
                                        <form action="removeShortLink" method="post" style="display:inline;">
                                            <input type="hidden" name="link_id" value="<%= l.getId() %>">
                                            <button type="submit" class="btn-sm btn-danger">Remove</button>
                                        </form>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            <% } else { %>
                <p class="no-links">No links yet. Shorten any URL above!</p>
            <% } %>
        </div>
    </div>

<%@ include file="includes/footer.jsp" %>