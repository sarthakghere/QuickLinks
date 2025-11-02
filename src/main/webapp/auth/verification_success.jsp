<%@ include file="../includes/header.jsp" %>

<style>
    body {
        background: linear-gradient(135deg, #e3f2fd, #ffffff);
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: "Poppins", sans-serif;
    }

    .verification-card {
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
        padding: 40px 30px;
        text-align: center;
        max-width: 480px;
        width: 100%;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    .verification-card:hover {
        transform: translateY(-4px);
        box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
    }

    .checkmark {
        font-size: 70px;
        color: #28a745;
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

    .btn-login {
        background: #007bff;
        border: none;
        padding: 10px 22px;
        font-weight: 500;
        border-radius: 50px;
        transition: background 0.3s ease, transform 0.2s ease;
    }

    .btn-login:hover {
        background: #0056b3;
        transform: translateY(-2px);
    }
</style>

<div class="verification-card">
    <div class="checkmark">&#10003;</div>
    <h3 class="card-title">Email Verified Successfully</h3>
    <p class="card-text">
        Your email has been successfully verified.<br>
        You can now log in to your account.
    </p>
    <a href="<%= request.getContextPath() %>/auth/login.jsp" class="btn btn-login">Go to Login</a>
</div>

<%@ include file="../includes/footer.jsp" %>
