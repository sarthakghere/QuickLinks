<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/navbar.jsp" %>

<div class="auth-container">
    <div class="auth-card">
        <h2>Create an Account</h2>
        <p class="subtext">Join QuickLinks and manage your resources easily.</p>

        <form action="<%= request.getContextPath() %>/RegisterServlet" method="post" class="auth-form">
            <div class="input-group">
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" placeholder="John Doe" required>
            </div>

            <div class="input-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="john@example.com" required>
            </div>

            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="••••••••" required>
            </div>

            <button type="submit" class="btn-primary">Register</button>

            <% if (request.getAttribute("error") != null) { %>
                <p class="msg error"><%= request.getAttribute("error") %></p>
            <% } %>
            <% if (request.getAttribute("message") != null) { %>
                <p class="msg success"><%= request.getAttribute("message") %></p>
            <% } %>

            <p class="alt-text">
                Already have an account? 
                <a href="<%= request.getContextPath() %>/auth/login.jsp">Login here</a>
            </p>
        </form>
    </div>
</div>

<%@ include file="../includes/footer.jsp" %>
