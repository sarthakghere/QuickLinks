<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.quicklinks.model.User" %>

<%
    User user = (session != null) ? (User) session.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("auth/login.jsp");
        return;
    }
%>

<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<style>
    body {
        background-color: #f8f9fa;
    }

    .dashboard-card {
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        padding: 2rem;
        margin-top: 2rem;
    }

    /* --- Shorten Form --- */
    .shorten-form form {
    display: flex;
    align-items: center;
    gap: 10px;
    flex-wrap: wrap;
}

.shorten-form input {
    flex-grow: 1;
    min-width: 250px;
    padding: 0.6rem 1rem;
    height: 44px;
}

.shorten-btn {
    padding: 0.5rem 1.2rem;
    height: 44px;
    font-size: 0.95rem;
    border-radius: 8px;
    font-weight: 500;
    background: linear-gradient(90deg, #007bff, #6f42c1);
    border: none;
    transition: background 0.3s ease, transform 0.2s ease;
}

.shorten-btn:hover {
    background: linear-gradient(90deg, #0056b3, #5a32a3);
    transform: translateY(-1px);
}


    /* --- Table --- */
    table {
        border-radius: 10px;
        overflow: hidden;
    }

    thead {
        background: #007bff;
        color: white;
    }

    td a {
        text-decoration: none;
        color: #007bff;
    }

    td a:hover {
        text-decoration: underline;
    }

    .verified {
        color: #28a745;
        font-weight: bold;
    }

    .not-verified {
        color: #dc3545;
        font-weight: bold;
    }
</style>

<div class="container">
    <div class="dashboard-card">
        <h2 class="fw-bold mb-3">Welcome, <%= user.getName() %> 👋</h2>
        <p class="text-muted mb-1"><strong>Email:</strong> <%= user.getEmail() %></p>
        <p><strong>Account Verified:</strong>
            <span class="<%= user.isVerified() ? "verified" : "not-verified" %>">
                <%= user.isVerified() ? "Yes ✅" : "No ❌" %>
            </span>
        </p>

        <hr class="my-4">

        <div class="shorten-form mt-4">
            <h4 class="fw-semibold mb-3">Create a Short Link 🔗</h4>
            <form action="<%= request.getContextPath() %>/createShortLink" method="post" class="d-flex flex-wrap align-items-center gap-2">
                <input type="url" name="original_url" class="form-control flex-grow-1" placeholder="Paste your long URL here..." required>
                <button type="submit" class="btn btn-primary shorten-btn">Shorten</button>
            </form>
        </div>


        <%
            com.quicklinks.dao.ShortLinkDAO linkDAO = new com.quicklinks.dao.ShortLinkDAO();
            java.util.List<com.quicklinks.model.ShortLink> links = linkDAO.getByUserId(user.getId());
            if (!links.isEmpty()) {
        %>

        <h4 class="fw-semibold mt-5 mb-3">Your Shortened Links</h4>
        <div class="table-responsive">
            <table class="table align-middle table-bordered">
                <thead>
                    <tr>
                        <th>Original URL</th>
                        <th>Short URL</th>
                        <th>Visits</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (com.quicklinks.model.ShortLink l : links) { %>
                        <tr>
                            <td style="word-break: break-all;"><%= l.getOriginalUrl() %></td>
                            <td>
                                <a href="<%= request.getContextPath() + "/s/" + l.getShortCode() %>" target="_blank">
                                    <%= request.getContextPath() + "/s/" + l.getShortCode() %>
                                </a>
                            </td>
                            <td class="text-center"><%= l.getTotalVisits() %></td>
                            <td>
                                <a href="edit_link.jsp?id=<%= l.getId() %>" class="btn btn-primary btn-sm">Edit</a>
                                <form action="removeShortLink" method="post" style="display:inline;">
                                    <input type="hidden" name="link_id" value="<%= l.getId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <% } else { %>
            <p class="text-muted mt-4">No links created yet. Start by shortening one above!</p>
        <% } %>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
