<%@ include file="../includes/header.jsp" %>

<style>
    body {
        background: linear-gradient(135deg, #ffe6e6, #fff);
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: "Poppins", sans-serif;
    }

    .error-card {
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
        padding: 40px 30px;
        text-align: center;
        max-width: 480px;
        width: 100%;
    }

    .error-icon {
        font-size: 70px;
        color: #dc3545;
        margin-bottom: 15px;
        animation: popIn 0.6s ease;
    }

    @keyframes popIn {
        from { transform: scale(0); opacity: 0; }
        to { transform: scale(1); opacity: 1; }
    }

    .card-title {
        font-weight: 600;
        font-size: 1.5rem;
        margin-bottom: 10px;
        color: #333;
    }

    .card-text {
        color: #555;
        font-size: 1rem;
        margin-bottom: 25px;
    }

    .btn-home {
        background: #007bff;
        border: none;
        padding: 10px 22px;
        font-weight: 500;
        border-radius: 50px;
        transition: background 0.3s ease, transform 0.2s ease;
        text-decoration: none;
        color: white;
    }

    .btn-home:hover {
        background: #0056b3;
        transform: translateY(-2px);
    }
</style>

<div class="error-card">
    <div class="error-icon">&#10060;</div>
    <h3 class="card-title">Verification Failed</h3>
    <p class="card-text">
        <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "Token Expired or Invalid." %>
    </p>
    <a href="<%= request.getContextPath() %>/auth/login.jsp" class="btn-home">Back to Login</a>
</div>

<%@ include file="../includes/footer.jsp" %>
