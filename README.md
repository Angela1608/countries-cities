# Countries and Cities Explorer

Discover and interact with a diverse array of cities globally through our Countries and Cities Application.
 ## Endpoints

🌍 City Management

- **Search cities**
    - Endpoint: `GET /api/v1/cities/search`
    - Privileges: Requires 'USER' role.
    - Description: Search for cities by name and country.

- **Get all unique city names**
    - Endpoint: `GET /api/v1/cities/unique`
    - Privileges: Requires 'USER' role.
    - Description: Retrieve a list of all unique city names.

- - **Create a new city**
    - Endpoint: `POST /api/v1/cities`
    - Privileges: Requires 'EDITOR' role.
    - Description: Create a new city. 

- **Update a city by ID**
    - Endpoint: `PUT /api/v1/cities/{id}`
    - Privileges: Requires 'EDITOR' role.
    - Description: Update a city's name and logo by ID.

🌍 Country Management

- **Create a new country**
    - Endpoint: `POST /api/v1/countries`
    - Privileges: Requires 'EDITOR' role.
    - Description: Create a new country with specified details. Restricted to users with the 'EDITOR' role.

🌍 Authentication

User Registration

- Endpoint: `POST /api/v1/auth/register`
- Description: Register a new user.

User Authentication

- Endpoint: `POST /api/v1/auth/login`
- Description: Authenticate a user.

## Technologies Utilized

This application harnesses the power of the following technologies:

- **Java 17**
- **AWS S3 (Bucket and Folder Creation)**
- **Maven**
- **Spring Boot**
- **Spring Security**
- **Mapstruct**
- **Hibernate**
- **JUnit**
- **Mockito**
- **MySQL Database**
- **Liquibase**
- **Checkstyle plugin**

## Getting Started

To set up and run the application locally, follow these steps:

### Prerequisites

Ensure the following are installed on your system:

- Java Development Kit (JDK)
- MySQL Database
- Git

### Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Angela1608/countries-cities.git
2. **Create an AWS S3 Bucket and Folder for the Application:**
- Sign in to the AWS Management Console.
- Navigate to the S3 service.
- Create a bucket with a unique, globally-unique name.
- Choose a region for the bucket.
- Inside the bucket, create a folder named "logos."
3. **Configure Application Properties:**
- Navigate to the project's configuration folder.
- Modify the application.properties file to set up your MySQL database connection and AWS S3 credentials
4. **Build the Project:**
   ```bash
   mvn clean install
5. **Run the Application:**
   ```bash
   java -jar target/countries-cities-app.jar
6. **Access the Application:**
- Open your web browser and go to http://localhost:8080.
7. **API Testing with Postman:**
   - Use the provided [Postman Collection](https://www.postman.com/interstellar-robot-692985/workspace/angela-s-workspace/collection/21530729-0a8e2789-e4a2-4fe2-8601-85c7ea8339e4?action=share&creator=21530729&active-environment=21530729-e6944363-4c02-4110-ba8f-e39433544368) to test the application's API.
