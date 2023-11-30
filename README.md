# Java Database Interaction

This Java application focuses on designing and interacting with databases. The functionalities include schema creation, database connectivity, CRUD (Create, Read, Update, Delete) operations, transaction handling, and metadata access.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Database Configuration](#database-configuration)

## Introduction

This Java application serves as a foundation for working with databases using JDBC (Java Database Connectivity). It includes modules for creating database schemas, establishing connections, and performing various database operations.

## Features

- **Database Connectivity:** Establish connections to supported databases.
- **Schema Creation:** Define and create database schemas.
- **CRUD Operations:** Perform Create, Read, Update, and Delete operations on the database.
- **Transaction Handling:** Ensure atomicity and consistency of transactions.
- **Metadata Access:** Retrieve metadata information about the database.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed.
- Supported database system installed (PostgreSQL).
- Database connection details (URL, username, password).

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/ey1az/assignment2_dbs.git
   ```

2. Navigate to the project directory

   ```bash
   cd assignment2_dbs
   ```

## Usage

## Database Configuration

Ensure to configure your database connection details in the appropriate configuration files or directly in the Java source code.

- Example:

    ```bash 
        URL = "jdbc:postgresql://localhost:5432/your_db_name";
        user = "your_username";
        password = "your_pass";
    ```
