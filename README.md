Spring Boot User API

REST API สำหรับระบบจัดการผู้ใช้ พร้อม JWT Authentication และ Role-based Authorization

Tech Stack

Java 17
Spring Boot
Spring Security
JWT (JSON Web Token)
MySQL
Maven
Features
User Registration
Login with JWT
Role-based Authorization (ADMIN / USER)
Protected APIs
Global Exception Handling
Input Validation
Swagger API Documentation

API Endpoints
Authentication
Method	Endpoint	Description
POST	/auth/register	Register new user
POST	/auth/login	Login and receive JWT
User APIs (Protected)
Method	Endpoint	Role
GET	/users	ADMIN
GET	/users/{id}	ADMIN
POST	/users	ADMIN
PUT	/users/{id}	ADMIN
DELETE	/users/{id}	ADMIN
Swagger UI

หลังจากรันโปรเจกต์:

http://localhost:8080/swagger-ui/index.html
How to Run
1. Create Database
   CREATE DATABASE demo_api;
2. Configure application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/demo_api
   spring.datasource.username=root
   spring.datasource.password=yourpassword
3. Run Application
   mvn spring-boot:run
   Project Structure
   controller
   service
   repository
   entity
   dto
   config
   filter
   exception
   util
   Example Login Request
   POST /auth/login

{
"username": "admin",
"password": "admin123"
}
Response
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
Author

Backend Developer – Spring Boot & REST API