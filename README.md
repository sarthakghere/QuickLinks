# QuickLinks

QuickLinks is a web application for creating and managing short URLs. It provides features for user authentication, link tracking, and sharing.

## Features

- **URL Shortening**: Create short, memorable links from long URLs.
- **User Authentication**: Secure user registration, login, and logout.
- **Email Verification**: Account verification via email.
- **Password Reset**: Secure password reset functionality.
- **Link Management**: View, edit, and remove your shortened links.
- **Link Analytics**: Track the number of visits for each short link.
- **Email Sharing**: Share short links directly via email.

## Technologies Used

- **Backend**: Java (Jakarta Servlet, JSP)
- **Database**: MySQL
- **Build Tool**: Maven
- **Web Server**: Jetty (for development)
- **Frontend**: HTML, CSS (with Bootstrap principles), JSP
- **Email**: Jakarta Mail API

## Setup Instructions

### Prerequisites

- Java Development Kit (JDK) 21 or higher
- Apache Maven 3.x
- MySQL Server

### Database Setup

1. Create a MySQL database named `quicklinks`.
2. Import the provided SQL schema:
   ```bash
   mysql -u your_username -p quicklinks < quicklinks.sql
   ```

### Application Configuration

1. Copy `src/main/resources/db.properties.example` to `src/main/resources/db.properties` and update the database connection details.
2. Copy `src/main/resources/email.properties.example` to `src/main/resources/email.properties` and update the email server details for sending verification and password reset emails.

### Building and Running the Application

1. Navigate to the project root directory:
   ```bash
   cd QuickLinks
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application using the Jetty Maven plugin:
   ```bash
   mvn jetty:run
   ```
4. Access the application in your web browser at `http://localhost:8080/QuickLinks/` (or `http://localhost:8080/` if deployed to root context).

## Usage

1. **Register**: Create a new user account.
2. **Verify Email**: Follow the link in the verification email to activate your account.
3. **Login**: Access your dashboard.
4. **Create Short Link**: Enter a long URL to generate a short link.
5. **Manage Links**: View, edit, or delete your short links from the dashboard.
6. **Track Visits**: See the total visits for each of your short links.
