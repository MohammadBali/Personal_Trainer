# Personal Trainer Application

## Overview
The Personal Trainer Application is a Spring Boot-based project designed to manage fitness-related data such as users, items, transactions, and more. It features JWT-based authentication, RESTful APIs, and database interactions.

## Features
- **User Management**: CRUD operations for user data.
- **Item and Category Management**: APIs to handle items, categories, and their relationships.
- **Transactions**: Manage and track user transactions involving items.
- **Security**: Secure endpoints with JWT-based authentication and authorization.
- **Database Initialization**: Predefined schemas and seed data.

## Project Structure
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.bali.personal_trainer
│   │   │       ├── components
│   │   │       ├── controllers
│   │   │       ├── models
│   │   │       │   ├── DTO
│   │   │       │   ├── Entities
│   │   │       │   └── ManyToMany
│   │   │       ├── repositories
│   │   │       └── services
│   │   └── resources
│   │       ├── application.properties
│   │       ├── application.yml
│   │       ├── schema.sql
│   │       └── data.sql
├── pom.xml
```

## Prerequisites

To run the application, ensure you have the following installed:

- Java 17 or later
- Maven 3.6+
- MySQL Server

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd personal_trainer
   ```

2. **Configure the database**:
   Update the `application.properties` or `application.yml` file with your MySQL database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/personal_trainer_db
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build the application**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application**:
   The API will be available at `http://localhost:8080`.

## Key Endpoints

- **Authentication**:
    - `POST /auth/login` - Generate a JWT token.

- **Users**:
    - `GET /users` - Retrieve all users.
    - `POST /users` - Create a new user.

- **Items and Categories**:
    - `GET /categories` - List all categories.
    - `POST /items` - Add a new item.

- **Transactions**:
    - `GET /transactions` - List all transactions.
    - `POST /transactions` - Create a new transaction.

## Security
This project uses JWT for securing API endpoints. Ensure you include the token in the `Authorization` header for secured routes:

```
Authorization: Bearer <your-jwt-token>
```

## Database Initialization
The application uses SQL scripts for schema creation and data seeding:
- `schema.sql`: Defines the database schema.
- `data.sql`: Seeds the database with initial data.

## Contact
For any queries or support, contact:
- **Email**: eng.mohammadbali@gmail.com

