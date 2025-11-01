<%@ page import="com.quicklinks.model.User" %>
<%
    User currentUser = (User) session.getAttribute("user");
%>

<div class="navbar">
    <h1>QuickLinks</h1>

    <% if (currentUser != null) { %>
        <form action="<%= request.getContextPath() %>/LogoutServlet" method="get">
            <button type="submit" class="logout-btn">Logout</button>
        </form>
    <% } else { %>
        <div class="auth-links">
            <a href="<%= request.getContextPath() %>/auth/login.jsp" class="login-btn">Login</a>
        </div>
    <% } %>
</div>
