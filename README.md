# Employee Payroll Management System
## Project Status

✅ Completed

✅ Full Stack Application

✅ React Frontend + Spring Boot Backend

✅ MySQL Database Integration

✅ REST API Implementation

✅ Login Authentication

✅ Dashboard & Payroll Management

## Overview

Employee Payroll Management System is a full-stack web application developed using **Java, Spring Boot, Spring Data JPA, MySQL, HTML, CSS, and JavaScript React.js**.

The application is designed to streamline employee and payroll management processes by providing functionalities such as employee record management, salary tracking, payroll processing, and payroll history maintenance.

The project follows a layered architecture approach consisting of Controller, Service, Repository, DTO, and Entity layers, ensuring scalability, maintainability, and clean code practices.

---

## Key Features

* Employee Registration and Management
* Add, View, Update, and Delete Employee Records
* Salary Management and Payroll Processing
* Payroll History Tracking
* Employee Search by ID
* RESTful API Development
* Database Integration with MySQL
* Exception Handling and Validation
* Responsive User Interface
* Maven-Based Project Management

---

## Technology Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* REST APIs
* Maven

### Frontend

* HTML5
* CSS3
* JavaScript
*  React.js 

### Database

* MySQL

### Tools & IDE

* Eclipse IDE / Spring Tool Suite (STS)
* Postman
* Git & GitHub

---

## Application Architecture

```text
Client (Browser)
       │
       ▼
Controller Layer
       │
       ▼
Service Layer
       │
       ▼
Repository Layer
       │
       ▼
MySQL Database
```

---

## Project Structure

```text
Employee-PayrollManagement-System
│
├── screenshots/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │       ├── static/
│   │       └── templates/
│   └── test/
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## Application Screenshots

### Login Page

The application provides a secure login interface for user authentication before accessing the dashboard.
![Login](screenshots/login.png)

### Dashboard Overview

The dashboard provides a quick summary of employees, salary budget, departments, and payroll-related activities.

<img width="1880" height="919" alt="image" src="https://github.com/user-attachments/assets/ae1303b7-ff45-43d5-9f06-86bdd05d6802" />


### Employee Management

Manage employee records with Add, View, Update, and Delete operations.

<img width="1899" height="918" alt="image" src="https://github.com/user-attachments/assets/f5384366-8913-4475-8a12-00e787f7cf58" />


### Payroll Management

Process salaries and maintain payroll records efficiently.

<img width="1900" height="523" alt="payrollManagement png" src="https://github.com/user-attachments/assets/e6d3f54b-8863-4602-879c-399f0a126c3c" />


### API Testing using Postman

REST APIs tested successfully using Postman

---

## REST API Endpoints

### Employee APIs

| Method | Endpoint          | Description             |
| ------ | ----------------- | ----------------------- |
| GET    | `/employees`      | Retrieve all employees  |
| GET    | `/employees/{id}` | Retrieve employee by ID |
| POST   | `/employees`      | Create new employee     |
| PUT    | `/employees/{id}` | Update employee details |
| DELETE | `/employees/{id}` | Delete employee         |

---

## Database Configuration

Configure MySQL database credentials inside:

```properties
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/payroll_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Installation & Setup

### 1. Clone Repository

```bash
git clone https://github.com/yourusername/Employee-PayrollManagement-System.git
```

### 2. Import Project

Import the project into Eclipse/STS as an Existing Maven Project.

### 3. Create Database

```sql
CREATE DATABASE payroll_db;
```

### 4. Configure Database

Update MySQL username and password in:

```properties
application.properties
```

### 5. Run Application

Run the application as:

```text
Spring Boot App
```

### 6. Access Application

```text
http://localhost:8080
```

---

## Learning Outcomes

Through this project, I gained practical experience in:

* Spring Boot Application Development
* REST API Design and Development
* CRUD Operations Implementation
* Spring Data JPA and Hibernate
* MySQL Database Integration
* Exception Handling
* Maven Project Management
* Layered Architecture Design
* Backend Development Best Practices
* Git and GitHub Version Control

---

## Future Enhancements

* JWT Authentication & Authorization
* Spring Security Integration
* Role-Based Access Control (RBAC)
* Payroll Report Generation (PDF/Excel)
* Email Notification Service
* Docker Containerization
* Cloud Deployment (AWS)
* React.js Frontend Integration

---

## Author

**Jagadeesh Vempuluru**

MCA Graduate | Java Full Stack Developer

---

## GitHub Repository

Repository Link:

```text
https://github.com/yourusername/Employee-PayrollManagement-System
```

---

## Project Highlights
✔ Developed a Full-Stack Employee Payroll Management System using React.js, Spring Boot, and MySQL.

✔ Implemented secure user authentication and dashboard-based navigation.

✔ Built RESTful APIs for employee and payroll management.

✔ Integrated React frontend with Spring Boot backend.

✔ Applied layered architecture (Controller, Service, Repository, DTO, Entity).

✔ Integrated MySQL database using Spring Data JPA and Hibernate.

✔ Tested APIs using Postman.

✔ Followed industry-standard software development practices.
