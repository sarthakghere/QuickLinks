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

<div class="container">
    <h2>Welcome, <%= user.getName() %> 👋</h2>
    <p><strong>Email:</strong> <%= user.getEmail() %></p>
    <p><strong>Account Verified:</strong> <%= user.isVerified() ? "Yes ✅" : "No ❌" %></p>
</div>

<%@ include file="includes/footer.jsp" %>
