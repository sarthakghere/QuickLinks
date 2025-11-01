<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>QuickLinks</title>
    <style>
        body {
            font-family: "Segoe UI", Roboto, sans-serif;
            background: #f7f9fc;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background: #0052cc;
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar h1 {
            margin: 0;
            font-size: 20px;
            letter-spacing: 0.5px;
        }

        .logout-btn {
            background: #ff4d4d;
            color: white;
            border: none;
            padding: 8px 18px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.2s;
        }

        .logout-btn:hover {
            background: #d93636;
        }

        .container {
            max-width: 600px;
            margin: 60px auto;
            background: white;
            padding: 40px 50px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #222;
        }

        p {
            font-size: 15px;
            color: #555;
            margin: 8px 0;
            text-align: center;
        }

.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #1a73e8;
    padding: 12px 24px;
    color: white;
    font-family: Arial, sans-serif;
}

.navbar h1 {
    margin: 0;
    font-size: 22px;
}

.auth-links a, .logout-btn {
    color: white;
    text-decoration: none;
    margin-left: 15px;
    background: #0b57cf;
    border: none;
    padding: 8px 14px;
    border-radius: 5px;
    font-weight: 500;
    cursor: pointer;
}

.auth-links a:hover, .logout-btn:hover {
    background: #084eb0;
}

/* ===== AUTH PAGE STYLING ===== */
.auth-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: calc(100vh - 140px);
    background: linear-gradient(135deg, #0078d4, #00b4d8);
    padding: 40px 20px;
}

.auth-card {
    background: rgba(255, 255, 255, 0.95);
    backdrop-filter: blur(10px);
    border-radius: 16px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.15);
    padding: 2.5rem 3rem;
    width: 100%;
    max-width: 420px;
    text-align: center;
    animation: fadeIn 0.5s ease;
}

.auth-card h2 {
    color: #222;
    font-size: 1.8rem;
    margin-bottom: 0.3rem;
}

.auth-card .subtext {
    color: #555;
    font-size: 0.95rem;
    margin-bottom: 1.8rem;
}

.auth-form .input-group {
    margin-bottom: 1.3rem;
    text-align: left;
}

.auth-form label {
    display: block;
    font-weight: 600;
    color: #333;
    margin-bottom: 6px;
}

.auth-form input {
    width: 100%;
    padding: 12px 14px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
    transition: all 0.3s ease;
}

.auth-form input:focus {
    border-color: #0078d4;
    background-color: #fff;
    box-shadow: 0 0 0 3px rgba(0,120,212,0.15);
    outline: none;
}

/* Primary Button */
.btn-primary {
    width: 100%;
    padding: 12px;
    background-color: #0078d4;
    color: #fff;
    border: none;
    border-radius: 8px;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.25s ease;
}

.btn-primary:hover {
    background-color: #005fa3;
    transform: translateY(-1px);
    box-shadow: 0 4px 10px rgba(0, 95, 163, 0.25);
}

.msg {
    font-size: 0.9rem;
    margin-top: 1rem;
}

.msg.error {
    color: #d9534f;
}

.msg.success {
    color: #28a745;
}

.alt-text {
    margin-top: 1.5rem;
    font-size: 0.95rem;
    color: #444;
}

.alt-text a {
    color: #0078d4;
    font-weight: 600;
    text-decoration: none;
    transition: color 0.2s ease;
}

.alt-text a:hover {
    color: #005fa3;
    text-decoration: underline;
}

/* Subtle fade animation */
@keyframes fadeIn {
    from { opacity: 0; transform: translateY(15px); }
    to { opacity: 1; transform: translateY(0); }
}

    </style>
</head>
<body>
