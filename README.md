# 🎓 Student Management System

This is a simple Spring Boot backend project to manage student data.
It allows users to register, login, and perform CRUD operations on students.

---

## 🚀 Features

* User Registration & Login 🔐
* JWT Authentication
* Add Student
* View Students
* Update Student
* Delete Student

---

## 🛠️ Technologies Used

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL
* Maven

---

## 📁 Project Structure (Simple)

* controller → Handles API requests
* service → Business logic
* repository → Database operations
* entity → Database tables
* dto → Data transfer objects
* security → JWT & authentication

---

## 🔑 API Endpoints

### Auth APIs

* POST /auth/register → Register user
* POST /auth/login → Login user

### Student APIs

* POST /students → Add student
* GET /students → Get all students
* GET /students/{id} → Get student by ID
* PUT /students/{id} → Update student
* DELETE /students/{id} → Delete student

---

## ⚙️ How to Run

1. Clone the project:

git clone https://github.com/aryanpm28/student-management-system.git

2. Open project in IDE

3. Configure database in application.properties

4. Run the project:

mvn spring-boot:run

---

## 🧪 Testing

* Use Postman
* First register and login
* Copy JWT token
* Add in header:

Authorization: Bearer <token>

---

## 👨‍💻 Author

Aryan Patil

---

## ⭐ Note

This project is made for learning Java Backend Development using Spring Boot.
