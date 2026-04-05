# 💰 Finance Dashboard Backend

A **production-ready backend system** for managing financial records with **role-based access control, JWT authentication, refresh tokens, and advanced data querying** using Spring Boot.

---

# 🚀 Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.x
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Security:** Spring Security + JWT
* **API Docs:** Swagger (SpringDoc OpenAPI)
* **Build Tool:** Gradle
* **Utilities:** Lombok

---

# 🧠 Features

## 🔐 Authentication & Security

* JWT-based authentication
* Refresh token mechanism
* Password encryption using BCrypt
* Stateless session management

---

## 👥 Role-Based Access Control (RBAC)

| Role    | Permissions                |
| ------- | -------------------------- |
| VIEWER  | View dashboard data        |
| ANALYST | View + analytics           |
| ADMIN   | Full access (CRUD + users) |

---

## 💰 Financial Records Management

* Create financial records (income/expense)
* Update records
* Delete records
* View records

Each record contains:

* Amount
* Type (INCOME / EXPENSE)
* Category
* Date
* Notes

---

## 🔍 Advanced Querying

* Pagination
* Sorting
* Dynamic filtering using JPA Specifications

---

## 📊 Dashboard Analytics

* Total Income
* Total Expense
* Net Balance
* Aggregated financial insights

---

## 🧾 API Documentation

* Swagger UI available
* Test APIs directly from browser

---

# 🏗️ Project Structure

```
finance-dashboard-backend
│
├── config          # Security & Swagger config
├── controller      # REST APIs
├── dto             # Request/Response models
├── entity          # Database entities
├── repository      # JPA repositories
├── security        # JWT & filters
├── service         # Business logic
├── specification   # Filtering logic
├── exception       # Global error handling
```

---

# ⚙️ Setup Instructions

## 🧾 Prerequisites

* Java 17 installed
* MySQL installed and running
* Gradle installed (or use wrapper)

---

## 🗄️ Step 1: Setup Database

Login to MySQL:

```sql
CREATE DATABASE finance_db;
```

---

## ⚙️ Step 2: Configure Application

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ Step 3: Run Application

```bash
./gradlew bootRun
```

---

## 🌐 Step 4: Access Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

# 🔑 API Usage Flow

## 1️⃣ Register User

```http
POST /auth/register
```

---

## 2️⃣ Login

```http
POST /auth/login
```

Response:

```json
{
  "accessToken": "...",
  "refreshToken": "..."
}
```

---

## 3️⃣ Use Access Token

```
Authorization: Bearer <token>
```

---

## 4️⃣ Refresh Token

```http
POST /auth/refresh
```

---

# 🧪 Sample curl Commands

## Register

```bash
curl -X POST http://localhost:8080/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"admin","password":"admin123","role":"ADMIN"}'
```

---

## Login

```bash
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"admin","password":"admin123"}'
```

---

## Create Record

```bash
curl -X POST http://localhost:8080/admin/records \
-H "Authorization: Bearer <token>" \
-H "Content-Type: application/json" \
-d '{"amount":5000,"type":"INCOME","category":"Salary"}'
```

---

# 🔍 Service Layer Responsibilities

## AuthService

* User registration
* Login validation
* JWT generation
* Refresh token handling

---

## FinancialService

* CRUD operations on records
* Pagination & sorting
* Filtering using Specifications

---

## DashboardService

* Aggregation logic
* Income vs Expense calculation
* Net balance

---

## RefreshTokenService

* Generate refresh tokens
* Validate expiration
* Token lifecycle management

---

# ⚠️ Error Handling

* Global exception handler
* Proper HTTP status codes
* Meaningful error messages

---

# 🔐 Security Highlights

* Role-based endpoint restrictions
* JWT validation filter
* Stateless authentication

---

# 🚀 Future Improvements

* Redis caching
* Docker deployment
* CI/CD pipeline
* Cloud deployment (AWS / Render)
* Frontend integration (React)

---

# 👨‍💻 Author

**Saurabh Pal**

---

# ⭐ Key Takeaways

* Clean architecture
* Scalable backend design
* Production-level authentication
* Efficient data handling

---
