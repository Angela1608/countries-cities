# Countries and Cities Explorer

This is a test task that allows you to discover and interact with a diverse range of cities globally.

## Technologies Utilized
- **Java 21**
- **Keycloak**
- **AWS S3**
- **Spring Boot**
- **Spring Security**
- **Hibernate**
- **MySQL**
- **Liquibase**
- **Maven**
- **Mapstruct**
- **JUnit**
- **Mockito**

## Getting Started

To set up and run the application locally, follow these steps:

### Prerequisites

Ensure the following are installed on your system:

- Java Development Kit (JDK)
- Git
- Docker

### Installation

1. **Clone the Repository:**

  ```bash
  git clone https://github.com/Angela1608/countries-cities.git
  ```

2. **Create an AWS S3 Bucket and Folder for the Application:**

  - Sign in to the **AWS Management Console**.
  - Navigate to the **S3 service**.
  - Create a **bucket** with a unique, globally-unique name.
  - Choose a **region** for the bucket.
  - Inside the bucket, create a folder named **"logos"**.

3.**Run Docker Compose:**

  ```bash
  docker compose -f /absolute/path/to/your/docker-compose.yml up
  ```
  - MySQL DB will be started on port 3306 with admin credentials (`myuser/mypassword`).
  - Keycloak will be started on port 8080 with admin credentials (`admin/admin`).
  - Access Keycloak at [http://localhost:8080](http://localhost:8080) to configure realms, roles, and users.

4.**Configure Application Properties:**

  - Navigate to the project's resources folder.
  - Modify the `application-local.yaml` file to configure your MySQL database connection and AWS S3 credentials.

5.**Build the Project:**

  ```bash
  mvn clean install
  ```

6.**Run the Application**

  ```bash
  java -jar target/countries-cities-app.jar
  ```

7.**Use the provided [Postman Collection](https://www.postman.com/interstellar-robot-692985/workspace/angela-s-workspace/collection/21530729-0a8e2789-e4a2-4fe2-8601-85c7ea8339e4?action=share&creator=21530729&active-environment=21530729-e6944363-4c02-4110-ba8f-e39433544368) to test the application's API.**

## Endpoints

### 🌍 City Management

- **Search cities**
  - **Endpoint**: `GET /api/v1/cities/search`
  - **Privileges**: Requires 'USER' role.
  - **Description**: Search for cities by name and country.

- **Get all unique city names**
  - **Endpoint**: `GET /api/v1/cities/unique`
  - **Privileges**: Requires 'USER' role.
  - **Description**: Retrieve a list of all unique city names.

- **Create a new city**
  - **Endpoint**: `POST /api/v1/cities`
  - **Privileges**: Requires 'EDITOR' role.
  - **Description**: Create a new city.

- **Update a city by ID**
  - **Endpoint**: `PUT /api/v1/cities/{id}`
  - **Privileges**: Requires 'EDITOR' role.
  - **Description**: Update a city's name and logo by ID.

### 🌍 Country Management

- **Create a new country**
  - **Endpoint**: `POST /api/v1/countries`
  - **Privileges**: Requires 'EDITOR' role.
  - **Description**: Create a new country with specified details. Restricted to users with the 'EDITOR' role.
