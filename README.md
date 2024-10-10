*** ASSIGNMENT PORTAL BACKEND ***

This project is a backend application for managing user authentication and assignments, developed as part of a backend developer intern assignment. 
It is built using Spring Boot, with role-based access control for users and admins.

FEATURES: 
* User Authentication: Register, log in, and authenticate users with Token. 
* Admin Access: Admin users can manage assignments and view user data.
* Assignment Management: Users can upload assignments, which admins can manage.
* Role-Based Access Control: Different permissions for users and admins.
  
TECHNOLOGIES USED:
* Spring Boot (REST API)
* Spring Security (Token based authentication)
* MongoDB (Database)
* Spring Data MongoDB (for MongoDB integration)
* Maven (Build tool)

PROJECT STRUCTURE:
src
 ├── controllers      # REST Controllers
 ├── models           # Entities (User, Assignment)
 ├── services         # Business logic
 ├── repositories     # Database operations
 ├── dtos             # Data Transfer Objects
 └── configurations   # Security and App configurations

 END POINTS:
* Authentication
    POST /auth/register: Register a new user
    POST /auth/login: Log in and get a token
    GET /auth/validate: Validate the token
* User
    GET /users/upload: Upload an assignment
    GET /users/admins: Fetch all the admins
* Admin (Admin-only)
    POST /admins/assignments/{adminId}: Fetch all tagged assignments
    PUT /admins/assignments/{id}/{status}: Validate the assignment (ACCEPT/REJECT)

* SETUP:
    Prerequisites
      Java 17+
      MongoDb
      Maven
* CLONE & RUN:    
    git clone https://github.com/bhaskarsunchu/Assignment-Portal-Backend-.git
    cd Assignment-Portal-Backend-
    mvn clean install
    mvn spring-boot:run

AUTHOR
Developed by Bhaskar Sunchu for a backend developer intern assignment.

LinkedIn : https://www.linkedin.com/in/bhaskar-sunchu/ 
Resume : https://drive.google.com/file/d/16tjoRnkQ4JhFIkE89JQKKgFkCn3N0ZHw/view 
Email : sunchubhaskar19@gmail.com 
