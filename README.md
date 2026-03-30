# 🛒 RetailPilot (Spring Boot)

A scalable **E-Commerce Backend REST API** built using **Spring Boot**, designed with production-ready architecture practices including **JWT authentication**, **role-based authorization**, **DTO mapping**, and **relational entity modeling**.

This project demonstrates backend engineering skills required for modern commerce platforms such as **authentication, product management, order flow, and secure API design**.

---

## 🚀 Features

* 🔐 JWT-based Authentication & Authorization
* 👤 Role-based access control (Admin / User)
* 📦 Product Management APIs
* 🏷️ Category & Tag relationships
* 🛒 Order management structure
* 📄 DTO-based API response architecture
* 🧱 Layered architecture (Controller → Service → Repository)
* 🗃️ JPA/Hibernate relational mapping
* ⚠️ Global exception handling
* ✅ Validation using Jakarta Validation
* 🔄 Clean mapper-based entity conversions

---

## 🏗️ Tech Stack

| Layer           | Technology                        |
| --------------- | --------------------------------- |
| Backend         | Spring Boot                       |
| Security        | Spring Security + JWT             |
| ORM             | Hibernate / JPA                   |
| Database        | MySQL / PostgreSQL (configurable) |
| Build Tool      | Maven                             |
| Validation      | Jakarta Validation                |
| Mapping         | MapStruct                         |
| Version Control | Git + GitHub                      |

---

## 📂 Project Structure

```
src/main/java/org/authentication/ecommerce

├── config/        → Security configuration
├── controller/    → REST Controllers
├── dto/           → Request & Response DTOs
├── entity/        → Database entities
├── exception/     → Global exception handling
├── mapper/        → MapStruct mappers
├── repository/    → JPA repositories
├── service/       → Business logic layer
└── util/          → Utility classes
```

This layered architecture improves:

* Maintainability
* Testability
* Scalability
* Clean separation of concerns

---

## 🔐 Authentication Flow

```
User Login
   ↓
Spring Security Authentication Manager
   ↓
JWT Token Generated
   ↓
Token Sent in Response
   ↓
Used for future API requests
```

Example Header:

```
Authorization: Bearer <your_token>
```

---

## 📦 Sample APIs

### Auth APIs

```
POST /auth/register
POST /auth/login
POST /auth/refresh-token
```

### Product APIs

```
GET /products
POST /products
PUT /products/{id}
DELETE /products/{id}
```

### Category APIs

```
GET /categories
POST /categories
```

---

## ⚙️ Running the Project Locally

### 1️⃣ Clone Repository

```
git clone https://github.com/startthecode/ecommerce.git
```

### 2️⃣ Configure Database

Update:

```
application.properties
```

Example:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=password
```

---

### 3️⃣ Run Application

```
mvn spring-boot:run
```

OR

Run from IDE:

```
EcommerceApplication.java
```

Server starts at:

```
http://localhost:8080
```

---

## 🧪 Testing APIs

You can test endpoints using:

* Postman
* Thunder Client
* Curl

Example Login Request:

```
POST /auth/login
```

Body:

```
{
  "email": "user@example.com",
  "password": "password"
}
```

---

## 📊 Architecture Overview

```
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

DTO Mapping handled using:

```
MapStruct
```

Security handled using:

```
Spring Security + JWT Filters
```

---

## 🧱 Database Design Highlights

Includes relational mappings such as:

```
User → Roles
Product → Category
Product → Tags
User → Orders
Orders → Order Items
```

Designed using:

```
JPA Relationships
@OneToMany
@ManyToOne
@ManyToMany
```

---

## 🛡️ Security Features

* Password hashing (BCrypt)
* Stateless authentication
* JWT token validation filter
* Role-based route protection
* Refresh token support

---

## 📌 Future Improvements

Planned enhancements:

* Payment gateway integration
* Cart service
* Inventory tracking
* Email verification system
* Docker deployment support
* Swagger API documentation

---

## 👨‍💻 Author

**Ashish Gola**

Backend Developer (Spring Boot | React | REST APIs)

GitHub:

```
https://github.com/startthecode
```

---

## ⭐ Support

If you found this project useful:

```
Star ⭐ the repository
```
