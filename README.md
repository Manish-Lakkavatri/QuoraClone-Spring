# 🧠 QuoraClone-Spring

A backend-only **Quora clone** built with **Spring Boot** and **Gradle**, following a clean layered architecture *(Controller → Service → Repository)*.  
Supports **users**, **questions**, **answers**, **comments**, and **tags** via RESTful APIs.

---

## 🚀 Quickstart

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Manish-Lakkavatri/QuoraClone-Spring.git
cd QuoraClone-Spring

````

---

### 2️⃣ Open the Project in Your IDE

* Recommended: **IntelliJ IDEA** (alternatively, **Eclipse**)
* Ensure the IDE recognizes it as a **Spring Boot project using Gradle**.
* Gradle sync should complete without errors.
* Verify Gradle wrapper (`gradlew`, `gradlew.bat`) exists.
  If missing, generate and commit it from your machine.

---

## ⚙️ Database Configuration

Edit your `src/main/resources/application.yml` (or `application.properties`) file:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/quora_clone
    username: your_user
    password: your_pass
  jpa:
    hibernate:
      ddl-auto: update
```

💡 **Tip:** Create `application-example.yml` (without credentials) to document all required keys.

You can use **PostgreSQL**, **MySQL**, or another relational database by updating `spring.datasource.*`.

---

## ▶️ Run the Application

### Linux/Mac:

```bash
./gradlew bootRun
```

### Windows:

```bash
gradlew.bat bootRun
```

Now open [http://localhost:8080/](http://localhost:8080/) in your browser.

The base API path is **`/api/v1`**.
You can test endpoints using **Postman**, **Insomnia**, or `curl`.

---

## 🧩 API Endpoints

### 📚 Questions

| Method | Endpoint                                    | Description                       |
| ------ | ------------------------------------------- | --------------------------------- |
| GET    | `/api/v1/questions?page={page}&size={size}` | List questions (paginated)        |
| GET    | `/api/v1/questions/{id}`                    | Get a question by ID              |
| POST   | `/api/v1/questions`                         | Create a question *(QuestionDTO)* |
| DELETE | `/api/v1/questions/{id}`                    | Delete a question                 |

---

### 💬 Comments

| Method | Endpoint                                                       | Description                              |
| ------ | -------------------------------------------------------------- | ---------------------------------------- |
| GET    | `/api/v1/comments/answer/{answerId}?page={page}&size={size}`   | List comments for an answer              |
| GET    | `/api/v1/comments/comment/{commentId}?page={page}&size={size}` | List replies for a comment               |
| GET    | `/api/v1/comments/{id}`                                        | Get a comment by ID                      |
| POST   | `/api/v1/comments`                                             | Create a comment or reply *(CommentDTO)* |
| DELETE | `/api/v1/comments/{id}`                                        | Delete a comment                         |

---

### 🏷️ Tags

| Method | Endpoint            | Description             |
| ------ | ------------------- | ----------------------- |
| GET    | `/api/v1/tags`      | List all tags           |
| GET    | `/api/v1/tags/{id}` | Get a tag by ID         |
| POST   | `/api/v1/tags`      | Create a tag *(TagDTO)* |
| DELETE | `/api/v1/tags/{id}` | Delete a tag            |

---

### 👤 Users

| Method | Endpoint                                   | Description               |
| ------ | ------------------------------------------ | ------------------------- |
| GET    | `/api/v1/users`                            | List users                |
| GET    | `/api/v1/users/{id}`                       | Get a user by ID          |
| POST   | `/api/v1/users`                            | Create a user *(UserDTO)* |
| DELETE | `/api/v1/users/{id}`                       | Delete a user             |
| POST   | `/api/v1/users/{userId}/followTag/{tagId}` | Follow a tag              |

---

## 🧱 DTOs (Request Bodies)

### **QuestionDTO**

```json
{
  "id": 1,
  "title": "Dependency Injection in Spring",
  "content": "Explain DI with examples",
  "userId": 1,
  "tagIds": [1, 2]
}
```

### **CommentDTO**

```json
{
  "id": 1,
  "content": "Nice explanation!",
  "answerId": 42,
  "parentCommentId": null
}
```

### **TagDTO**

```json
{
  "id": 1,
  "name": "spring-boot"
}
```

### **UserDTO**

```json
{
  "id": 1,
  "username": "alice",
  "password": "secret"
}
```

### **AnswerDTO (Future Implementation)**

```json
{
  "id": 1,
  "content": "This is my answer.",
  "userId": 1,
  "questionId": 2
}
```

---

## 🧪 Sample API Requests

### **Create a User**

```http
POST /api/v1/users
Content-Type: application/json

{
  "username": "alice",
  "password": "secret"
}
```

### **Create a Tag**

```http
POST /api/v1/tags
Content-Type: application/json

{
  "name": "spring-boot"
}
```

### **Create a Question**

```http
POST /api/v1/questions
Content-Type: application/json

{
  "title": "DI in Spring",
  "content": "Explain dependency injection",
  "userId": 1,
  "tagIds": [1]
}
```

### **List Questions**

```http
GET /api/v1/questions?page=0&size=10
```

### **Comment on an Answer**

```http
POST /api/v1/comments
Content-Type: application/json

{
  "content": "Nice!",
  "answerId": 42,
  "parentCommentId": null
}
```

### **Reply to a Comment**

```http
POST /api/v1/comments
Content-Type: application/json

{
  "content": "Can you elaborate?",
  "answerId": 42,
  "parentCommentId": 77
}
```

---

## 🌐 Web Directories

This repository is **backend-only** — no JSP or frontend views required.

If adding a static frontend, place it under:

```
src/main/resources/static
```

To use **React** or **Vite** as a separate frontend, run it on another port (e.g., 3000) and call:

```
http://localhost:8080/api/v1
```

using **CORS** or a **proxy** setup.

---

## 🧭 Project Structure

| Layer            | Responsibility                         |
| ---------------- | -------------------------------------- |
| **Controller**   | Defines REST endpoints under `/api/v1` |
| **Service**      | Contains business logic                |
| **Repository**   | Handles database operations            |
| **Entity/Model** | Maps objects to database tables        |
| **DTO**          | Defines request/response payloads      |

---

## ⚡ Endpoints Summary (Quick Copy)

```bash
/api/v1/questions
/api/v1/questions/{id}
/api/v1/comments/answer/{answerId}
/api/v1/comments/comment/{commentId}
/api/v1/comments/{id}
/api/v1/comments
/api/v1/tags
/api/v1/tags/{id}
/api/v1/users
/api/v1/users/{id}
/api/v1/users/{userId}/followTag/{tagId}
```

---

## 🧰 Useful Commands

| Task              | Command                               |
| ----------------- | ------------------------------------- |
| Build the project | `./gradlew build`                     |
| Run the jar file  | `java -jar build/libs/<artifact>.jar` |
| Clean build files | `./gradlew clean`                     |

> ⚙️ Consider adding **springdoc-openapi** for live API docs at `/swagger-ui.html`.

> ✅ Commit Gradle wrapper files so others can build without installing Gradle.

---

## 👨‍💻 Author

**Manish Lakkavatri**
🔗 [GitHub Profile](https://github.com/Manish-Lakkavatri)
