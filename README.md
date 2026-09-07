# 🐾 PetHub – Pet Adoption Management System

PetHub is a Java-based web application that simplifies the pet adoption process by connecting users with pets available for adoption.

## ✨ Features

### 👤 User Side
- User Registration & Login
- Browse, Search & Filter Pets
- View Pet Details
- Submit Adoption Requests
- Track Adoption Request Status

### 🔐 Admin Side
- Admin Login & Dashboard
- Add, Update & Delete Pets
- Upload & Manage Pet Images
- View Adoption Requests
- Approve or Reject Requests
- Manage Pet Adoption Status

## 🛠️ Technologies

- Java
- JSP & Servlets
- JDBC
- MySQL
- HTML5
- CSS3
- JavaScript
- Apache Tomcat 10.1
- Jakarta Servlet API

## 🏗️ Architecture

```text
JSP
 ↓
Servlet
 ↓
Service
 ↓
DAO / DAOImpl
 ↓
MySQL


🔄 Application Flow

User → Register/Login → Browse Pets → View Details
     → Submit Adoption Request → Track Status

Admin → Login → Dashboard → Manage Pets
      → Manage Adoption Requests → Approve/Reject


 🖼️ Pet Images
 Pet images are stored in:
 src/main/webapp/images/

 The database stores only the image filename, and images are loaded dynamically.

🚀 How to Run
Clone the repository.
Configure MySQL database credentials in DBConnection.java.
Add the required pet images to src/main/webapp/images/.
Deploy the project on Apache Tomcat 10.1.
Open:
http://localhost:8080/PetHub/

🎯 Objective

To provide a simple and user-friendly platform for pet adoption while allowing administrators to efficiently manage pets and adoption requests.

👩‍💻 Author

Kshama
