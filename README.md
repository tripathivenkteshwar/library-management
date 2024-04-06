# Library Management System

This is a library management system implemented in Java using Spring Boot.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [Interacting with API Endpoints](#interacting-with-api-endpoints)
- [Basic Authentication](#basic-authentication)

## Features

- CRUD operations for managing books, patrons, and borrowing records.
- Basic authentication for securing API endpoints.
- Logging of method calls, exceptions, and performance metrics using Aspect-Oriented Programming (AOP).

## Prerequisites

Before running the application, make sure you have the following installed:

- Java JDK (version 17 or later)
- Postman or any REST API client for testing endpoints

## Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/tripathivenkteshwar/library-management.git
   ```
2. cd library-management
3. ./gradlew clean build
4.  java -jar build\libs\library-management-0.0.1-SNAPSHOT.jar


## interacting-with-api-endpoints

  - GET /api/books: Retrieve all books or GET /api/books/{id}: Retrieve a specific book by ID
    ```
    curl --location 'http://localhost:8080/api/books' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='

    SAMPLE OUTPUT FOR GET BY ID
    {
    "id": 2,
    "title": "New Book",
    "author": "Author Name",
    "publicationYear": 2022,
    "isbn": "1234567890",
    "availableForBorrowing": true,
    "borrowingRecords": [
        {
            "borrowingRecordId": 1,
            "borrowDate": "2024-04-06",
            "returnDate": "2024-04-06"
        }
    ]
    }
    ```
  
  - POST /api/books: Add a new book
    ```
    curl --location 'http://localhost:8080/api/books' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
    --data '{
        "title": "New Book",
        "author": "Author Name",
        "publicationYear": 2022,
        "isbn": "1234567890"
    }'
    ```
  
  - PUT /api/books/{id}: Update an existing book
    ```
    curl --location --request PUT 'http://localhost:8080/api/books/1' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
    --data '{
        "title": "Updated Title",
        "author": "Updated Author",
        "publicationYear": 2023,
        "isbn": "0987654321"
    }'
    ```
  
  - DELETE /api/books/{id}: Delete a book by ID
    ```
    curl --location --request DELETE 'http://localhost:8080/api/books/1' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
    ```
  
  - GET /api/patrons: Retrieve all patrons
  
  - GET /api/patrons/{id}: Retrieve a specific patron by ID
  
  - POST /api/patrons: Add a new patron
  
  - PUT /api/patrons/{id}: Update an existing patron
  
  - DELETE /api/patrons/{id}: Delete a patron by ID
  
  - POST /api/borrow/{bookId}/{patronId}: Borrow a book
  ```
  curl --location --request POST 'http://localhost:8080/api/borrow/2/patron/3' \
  --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
  ```
  
  - PUT /api/return/{bookId}/{patronId}: Return a borrowed book
  ```
  curl --location --request PUT 'http://localhost:8080/api/return/2/patron/3' \
  --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
  ```

## Basic Authentication
  This application uses basic authentication for securing endpoints.

  To interact with secured endpoints, include the following headers in your requests:
  
  Authorization: Basic <base64_encoded_username_password>
  Replace <base64_encoded_username_password> with the base64 encoded value of your username and password. For example:
  
  Username: user
  Password: password
  Base64 encoded value: dXNlcjpwYXNzd29yZA==
