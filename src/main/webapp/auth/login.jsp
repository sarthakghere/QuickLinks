<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
    <div class="auth-container">
        <h2>Login</h2>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
            <% if ("Please verify your email before logging in.".equals(request.getAttribute("error"))) { %>
                <p><a href="<%= request.getContextPath() %>/auth/resend-verification-email?email=<%= request.getAttribute("email") %>">Resend verification email</a></p>
            <% } %>
        <% } %>
        <form action="<%= request.getContextPath() %>/auth/login" method="post">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
        <p>Don’t have an account? <a href="register.jsp">Register</a></p>
        <p><a href="forgot_password.jsp">Forgot Password?</a></p>
        <% if ("true".equals(request.getParameter("registered"))) { %>
            <p style="color: green;">Registration successful! Please check your email to verify your account.</p>
        <% } %>
        <% if (request.getAttribute("message") != null) { %>
            <p style="color: green;"><%= request.getAttribute("message") %></p>
        <% } %>
    </div>
</body>
</html>
