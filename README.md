# Student Management System

A RESTful Student Management System built using Spring Boot. This project provides secure APIs for managing student records with JWT Authentication and Swagger API documentation.

## Features

* Student CRUD Operations
* JWT Authentication & Authorization
* Role-Based Access Control (Admin/User)
* Search Students by Name
* Pagination Support
* Input Validation
* Global API Response Format
* Swagger/OpenAPI Documentation
* MySQL Database Integration

## Technologies Used

* Java 17
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* MySQL
* Maven
* Swagger/OpenAPI

## Project Structure

```text
controller
service
repository
entity
dto
config
security
```

## API Endpoints

### Authentication

| Method | Endpoint       |
| ------ | -------------- |
| POST   | /auth/register |
| POST   | /auth/login    |

### Students

| Method | Endpoint                          |
| ------ | --------------------------------- |
| POST   | /students                         |
| GET    | /students?page=0&size=5           |
| GET    | /students/search?name=studentName |
| PUT    | /students/{id}                    |
| DELETE | /students/{id}                    |
| GET    | /students/admin/all               |

## Swagger Documentation

After starting the application, open:

```text
http://localhost:8080/swagger-ui/index.html
```

## Database Configuration

Update the database details in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=root
spring.datasource.password=yourpassword
```

## Running the Project

Clone the repository:

```bash
git clone <repository-url>
```

Navigate to the project folder:

```bash
cd StudentManagement_demo
```

Run the application:

```bash
mvn spring-boot:run
```

## Author

Aryan Patil
