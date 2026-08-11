# My Greetings App

A RESTful Greeting Management application built using **Java, Spring MVC, PostgreSQL, JDBC, XML-based Spring configuration, and Jackson**.

The application allows users to create, retrieve, update, delete, and search greeting messages. It follows a layered architecture to maintain separation of concerns between the controller, service, repository, and database layers.

---

## 📌 Project Overview

**Project Name:** My Greetings App

**Project Type:** RESTful Web Application

**Backend:** Java + Spring MVC

**Database:** PostgreSQL

**Server:** Apache Tomcat 10.1+

**Build Tool:** Maven

The application provides REST APIs for managing greeting messages stored in a PostgreSQL database.

---

## 🎯 Objectives

The primary objectives of this project are:

* Build a REST API using Spring MVC.
* Understand Spring MVC without Spring Boot.
* Configure Spring using XML configuration.
* Understand the role of `DispatcherServlet`.
* Use annotation-based controller mappings.
* Perform database operations using Core JDBC.
* Connect Java applications with PostgreSQL.
* Use Jackson for JSON serialization and deserialization.
* Implement a layered architecture.
* Understand dependency injection using Spring.
* Handle CRUD and search operations.

---

# 🏗️ Architecture

The application follows a layered architecture:

```text
                    Client / Postman
                           |
                           | HTTP Request
                           v
                  +-------------------+
                  |  DispatcherServlet |
                  +-------------------+
                           |
                           v
                  +-------------------+
                  | GreetingController|
                  +-------------------+
                           |
                           v
                  +-------------------+
                  |  GreetingService  |
                  +-------------------+
                           |
                           v
                  +-------------------+
                  |   GreetingDAO     |
                  +-------------------+
                           |
                           v
                  +-------------------+
                  | GreetingRepository|
                  +-------------------+
                           |
                           v
                  +-------------------+
                  |      DBUtil       |
                  +-------------------+
                           |
                           v
                  +-------------------+
                  |     PostgreSQL    |
                  +-------------------+
```

### Request Flow

For example:

```text
POST /greetings
```

The request flows through:

```text
JSON Request
     ↓
Tomcat
     ↓
DispatcherServlet
     ↓
GreetingController
     ↓
Jackson
     ↓
Greeting Object
     ↓
GreetingService
     ↓
GreetingDAO
     ↓
GreetingRepository
     ↓
DBUtil
     ↓
JDBC Connection
     ↓
PostgreSQL
```

The response follows the reverse path:

```text
PostgreSQL
     ↓
Repository
     ↓
DAO
     ↓
Service
     ↓
Controller
     ↓
Greeting Object
     ↓
Jackson
     ↓
JSON Response
```

---

# 🛠️ Technologies Used

| Technology    | Purpose                            |
| ------------- | ---------------------------------- |
| Java          | Application development            |
| Spring MVC    | Web MVC framework                  |
| Spring Core   | Dependency Injection and IoC       |
| Servlet API   | Web request handling               |
| Apache Tomcat | Application server                 |
| PostgreSQL    | Relational database                |
| JDBC          | Database connectivity              |
| Jackson       | JSON serialization/deserialization |
| Maven         | Dependency management and build    |
| XML           | Spring and Servlet configuration   |
| Postman       | API testing                        |

---

# 📂 Project Structure

```text
MyGreetingsApp
│
├── pom.xml
│
└── src
    └── main
        ├── java
        │   └── com.bridgelabz
        │       │
        │       ├── controller
        │       │   └── GreetingController.java
        │       │
        │       ├── service
        │       │   └── GreetingService.java
        │       │
        │       ├── dao
        │       │   └── GreetingDAO.java
        │       │
        │       ├── repository
        │       │   └── GreetingRepository.java
        │       │
        │       ├── model
        │       │   └── Greeting.java
        │       │
        │       └── util
        │           └── DBUtil.java
        │
        └── webapp
            └── WEB-INF
                ├── web.xml
                └── dispatcher-servlet.xml
```

---

# 🗄️ Database Design

## Database

```sql
CREATE DATABASE greetings_db;
```

Connect to the database and create the table:

```sql
CREATE TABLE greetings (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## Table Structure

| Column       | Type         | Constraint  | Description            |
| ------------ | ------------ | ----------- | ---------------------- |
| id           | SERIAL       | PRIMARY KEY | Unique greeting ID     |
| user_name    | VARCHAR(100) | NOT NULL    | Name of the user       |
| message      | TEXT         | NOT NULL    | Greeting message       |
| created_date | TIMESTAMP    | DEFAULT     | Greeting creation date |

---

# 📋 Business Requirements

The application supports the following operations:

1. Create a greeting.
2. Retrieve a greeting by ID.
3. Retrieve all greetings.
4. Update an existing greeting.
5. Delete a greeting.
6. Search greetings by user name.
7. Retrieve all greetings created by a specific user.

---

# 📌 Business Rules

* User name cannot be empty.
* Greeting message cannot be empty.
* Every greeting has a unique ID.
* A user can create multiple greetings.
* A greeting can be updated using its ID.
* A greeting can be deleted using its ID.
* If a greeting does not exist, an appropriate error should be returned.
* Database operations are performed using parameterized SQL queries.

---

# 🔗 REST API Endpoints

Base URL:

```text
http://localhost:8080/MyGreetingsApp
```

## API Summary

| Method | Endpoint                        | Description          |
| ------ | ------------------------------- | -------------------- |
| POST   | `/greetings`                    | Create greeting      |
| GET    | `/greetings`                    | Get all greetings    |
| GET    | `/greetings/{id}`               | Get greeting by ID   |
| PUT    | `/greetings/{id}`               | Update greeting      |
| DELETE | `/greetings/{id}`               | Delete greeting      |
| GET    | `/greetings/search?name={name}` | Search greetings     |
| GET    | `/greetings/user/{name}`        | Get user's greetings |

---

# 1️⃣ Create Greeting

### Endpoint

```http
POST /greetings
```

### Full URL

```text
http://localhost:8080/MyGreetingsApp/greetings
```

### Headers

```text
Content-Type: application/json
```

### Request Body

```json
{
    "userName": "Saravanan",
    "message": "Hello Spring MVC"
}
```

### Example Response

```json
{
    "id": 1,
    "userName": "Saravanan",
    "message": "Hello Spring MVC",
    "createdDate": "2026-08-11 11:30:00"
}
```

---

# 2️⃣ Get All Greetings

### Endpoint

```http
GET /greetings
```

### Full URL

```text
http://localhost:8080/MyGreetingsApp/greetings
```

### Example Response

```json
[
    {
        "id": 1,
        "userName": "Saravanan",
        "message": "Hello Spring MVC",
        "createdDate": "2026-08-11 11:30:00"
    },
    {
        "id": 2,
        "userName": "Rahul",
        "message": "Good Morning",
        "createdDate": "2026-08-11 11:35:00"
    }
]
```

---

# 3️⃣ Get Greeting By ID

### Endpoint

```http
GET /greetings/{id}
```

### Example

```text
GET http://localhost:8080/MyGreetingsApp/greetings/1
```

### Example Response

```json
{
    "id": 1,
    "userName": "Saravanan",
    "message": "Hello Spring MVC",
    "createdDate": "2026-08-11 11:30:00"
}
```

---

# 4️⃣ Update Greeting

### Endpoint

```http
PUT /greetings/{id}
```

### Example

```text
PUT http://localhost:8080/MyGreetingsApp/greetings/1
```

### Headers

```text
Content-Type: application/json
```

### Request Body

```json
{
    "userName": "Saravanan",
    "message": "Hello Spring MVC - Updated"
}
```

### Example Response

```json
{
    "id": 1,
    "userName": "Saravanan",
    "message": "Hello Spring MVC - Updated",
    "createdDate": "2026-08-11 11:30:00"
}
```

---

# 5️⃣ Delete Greeting

### Endpoint

```http
DELETE /greetings/{id}
```

### Example

```text
DELETE http://localhost:8080/MyGreetingsApp/greetings/1
```

### Example Response

```json
[
    {
        "id": 2,
        "userName": "Rahul",
        "message": "Good Morning",
        "createdDate": "2026-08-11 11:35:00"
    }
]
```

The current implementation returns the remaining greetings after deletion.

---

# 6️⃣ Search Greetings

### Endpoint

```http
GET /greetings/search?name={name}
```

### Example

```text
GET http://localhost:8080/MyGreetingsApp/greetings/search?name=Saravanan
```

### Example Response

```json
[
    {
        "id": 1,
        "userName": "Saravanan",
        "message": "Hello Spring MVC",
        "createdDate": "2026-08-11 11:30:00"
    }
]
```

The repository uses PostgreSQL `ILIKE`, allowing case-insensitive searching.

For example:

```text
Saravanan
saravanan
SARAVANAN
```

can match the same user.

---

# 7️⃣ Get Greetings By User

### Endpoint

```http
GET /greetings/user/{name}
```

### Example

```text
GET http://localhost:8080/MyGreetingsApp/greetings/user/Saravanan
```

### Example Response

```json
[
    {
        "id": 1,
        "userName": "Saravanan",
        "message": "Hello Spring MVC",
        "createdDate": "2026-08-11 11:30:00"
    },
    {
        "id": 3,
        "userName": "Saravanan",
        "message": "Have a nice day",
        "createdDate": "2026-08-11 11:45:00"
    }
]
```

---

# ⚙️ Configuration

## `web.xml`

The `web.xml` registers the Spring `DispatcherServlet`.

```text
Client
  ↓
Tomcat
  ↓
web.xml
  ↓
DispatcherServlet
  ↓
dispatcher-servlet.xml
```

The DispatcherServlet acts as the **Front Controller** for the application.

---

## `dispatcher-servlet.xml`

The Spring MVC configuration is handled using XML.

Important configuration includes:

* Component scanning
* Annotation-driven MVC
* Spring MVC configuration
* Bean configuration

Example:

```xml
<context:component-scan
        base-package="com.bridgelabz"/>

<mvc:annotation-driven/>
```

The component scan allows Spring to detect classes annotated with:

```text
@Controller
@RestController
@Service
@Repository
@Component
```

---

# 🔌 Database Connection

The application intentionally uses **Core JDBC instead of JdbcTemplate**.

Database connection details are centralized inside:

```text
com.bridgelabz.util.DBUtil
```

The repository obtains a connection using:

```java
Connection connection = DBUtil.getConnection();
```

Database operations use:

```text
Connection
    ↓
PreparedStatement
    ↓
ResultSet
```

This approach helps understand the underlying JDBC workflow.

---

# 🔐 SQL Injection Prevention

The application uses `PreparedStatement` instead of concatenating user input into SQL queries.

For example:

```java
String sql =
    "SELECT * FROM greetings WHERE id = ?";

PreparedStatement statement =
    connection.prepareStatement(sql);

statement.setInt(1, id);
```

This helps prevent SQL injection and is the recommended way to pass user-provided values to SQL statements.

---

# 🌐 Spring MVC Annotations Used

The controller uses annotation-based request mapping.

### `@RestController`

Marks the class as a REST controller.

```java
@RestController
public class GreetingController {
}
```

It combines controller functionality with response-body handling.

---

### `@RequestMapping`

Defines the common URL prefix.

```java
@RequestMapping("/greetings")
```

---

### `@PostMapping`

Handles HTTP POST requests.

```java
@PostMapping
```

Used for creating greetings.

---

### `@GetMapping`

Handles HTTP GET requests.

```java
@GetMapping
```

Used for retrieving greetings.

---

### `@PutMapping`

Handles HTTP PUT requests.

```java
@PutMapping("/{id}")
```

Used for updating greetings.

---

### `@DeleteMapping`

Handles HTTP DELETE requests.

```java
@DeleteMapping("/{id}")
```

Used for deleting greetings.

---

### `@RequestBody`

Converts incoming JSON into a Java object using Jackson.

```java
@RequestBody Greeting greeting
```

---

### `@PathVariable`

Reads a value from the URL.

```java
@GetMapping("/{id}")
public Greeting getGreeting(
        @PathVariable("id") int id) {
}
```

---

### `@RequestParam`

Reads a query parameter.

```java
@GetMapping("/search")
public List<Greeting> search(
        @RequestParam("name") String name) {
}
```

---

# 🔄 Jackson JSON Processing

Jackson is responsible for converting between JSON and Java objects.

### Request

```json
{
    "userName": "Saravanan",
    "message": "Hello"
}
```

Jackson converts it into:

```text
Greeting Java Object
```

The reverse also happens.

```text
Greeting Java Object
        ↓
Jackson
        ↓
JSON Response
```

This allows the REST controller to directly accept and return Java objects.

---

# 🧩 Dependency Injection

Spring manages the application objects as beans.

For example:

```java
@Autowired
private GreetingService greetingService;
```

Spring automatically injects the `GreetingService` object.

Similarly:

```java
@Autowired
private GreetingDAO greetingDAO;
```

Spring injects the implementation:

```text
GreetingDAO
     ↑
GreetingRepository
```

because `GreetingRepository` is annotated with:

```java
@Repository
```

---

# 🧪 Testing

The APIs can be tested using **Postman**.

Recommended testing order:

```text
1. POST /greetings
       ↓
2. GET /greetings
       ↓
3. GET /greetings/{id}
       ↓
4. GET /greetings/search?name=Saravanan
       ↓
5. GET /greetings/user/Saravanan
       ↓
6. PUT /greetings/{id}
       ↓
7. DELETE /greetings/{id}
       ↓
8. GET /greetings
```

---

# 🚀 Setup Instructions

## Step 1: Clone the Project

```bash
git clone <your-repository-url>
```

Navigate into the project:

```bash
cd MyGreetingsApp
```

---

## Step 2: Configure PostgreSQL

Create the database:

```sql
CREATE DATABASE greetings_db;
```

Create the table:

```sql
CREATE TABLE greetings (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## Step 3: Configure Database Credentials

Open:

```text
src/main/java/com/bridgelabz/util/DBUtil.java
```

Configure:

```java
private static final String URL =
        "jdbc:postgresql://localhost:5432/greetings_db";

private static final String USERNAME =
        "postgres";

private static final String PASSWORD =
        "your_password";
```

Replace the password with your PostgreSQL password.

> For production applications, credentials should not be hard-coded. They should be externalized using environment variables or a secure configuration mechanism.

---

## Step 4: Configure Maven

Make sure Maven is installed:

```bash
mvn -version
```

Build the project:

```bash
mvn clean install
```

---

## Step 5: Deploy to Tomcat

Deploy the generated WAR file to:

```text
Tomcat/webapps/
```

Start Tomcat.

The application should then be available at:

```text
http://localhost:8080/MyGreetingsApp
```

---

# 📦 Maven Dependencies

The major dependencies used by the project are:

```text
Spring MVC
Spring Core
PostgreSQL JDBC Driver
Jackson Databind
Jakarta Servlet API
```

Maven manages these dependencies through:

```text
pom.xml
```

---

# 🛡️ Error Handling

The application should handle cases such as:

### Greeting Not Found

```text
GET /greetings/999
```

If ID `999` does not exist, the application should return an appropriate HTTP error response.

### Empty User Name

```json
{
    "userName": "",
    "message": "Hello"
}
```

Should be rejected.

### Empty Message

```json
{
    "userName": "Saravanan",
    "message": ""
}
```

Should be rejected.

A future enhancement can introduce:

```java
@ControllerAdvice
```

and:

```java
@ExceptionHandler
```

for centralized exception handling.

---

# 📈 Future Enhancements

The project can be extended with:

* Global exception handling
* `ResponseEntity`
* Proper HTTP status codes
* Bean Validation
* `@Valid`
* Custom exceptions
* DTO classes
* Pagination
* Sorting
* Logging
* Unit testing
* Mockito
* JUnit
* Database transactions
* Connection pooling
* Externalized database configuration
* API documentation using Swagger/OpenAPI
* Authentication and authorization

---

# 📚 Learning Outcomes

After completing this project, you should understand:

* Spring MVC architecture
* Front Controller pattern
* DispatcherServlet
* XML-based Spring configuration
* Component scanning
* Dependency Injection
* IoC container
* REST API development
* HTTP methods
* Controller mappings
* `@RequestBody`
* `@PathVariable`
* `@RequestParam`
* Jackson
* Core JDBC
* `Connection`
* `PreparedStatement`
* `ResultSet`
* PostgreSQL integration
* DAO pattern
* Repository pattern
* Service layer
* CRUD operations
* SQL parameterization
* Maven dependency management
* Tomcat deployment

---

# 👨‍💻 Author

**Saravanan S**

Java Developer | Spring MVC | JDBC | PostgreSQL

---

# 📄 License

This project is created for educational and learning purposes.
