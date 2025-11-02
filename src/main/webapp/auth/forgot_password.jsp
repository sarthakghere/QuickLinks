<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
    <div class="auth-container">
        <h2>Forgot Password</h2>
        <p>Enter your email address and we will send you a link to reset your password.</p>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if (request.getAttribute("message") != null) { %>
            <p style="color: green;"><%= request.getAttribute("message") %></p>
        <% } %>
        <form action="<%= request.getContextPath() %>/auth/forgot-password" method="post">
            <input type="email" name="email" placeholder="Email" required>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
