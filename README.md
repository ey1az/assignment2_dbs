# Java Database Interaction

This Java application focuses on designing and interacting with databases. The functionalities include database connectivity, CRUD (Create, Read, Update, Delete) operations, transaction handling, and metadata access.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Database Configuration](#database-configuration)
- [ER Diagram and Table Schema](#er-diagram-and-table-schema)

## Introduction

This Java application serves as a foundation for working with databases using JDBC (Java Database Connectivity). It includes modules for establishing connections, and performing various database operations.

## Features

- **Database Connectivity:** Establish connections to supported databases.
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
   git clone https://github.com/ey1az/bookstore_jdbc.git
   ```

2. Navigate to the project directory

   ```bash
   cd bookstore_jdbc
   ```

3. Add PostgreSQL JDBC Driver to the Project:

   1. Go to the "lib" folder in the cloned repository.
   2. Add the "postgresql-42.7.0.jar" into the "Referenced Libraries" section of the Java Project.

## Usage

1. Run the Main class

   - Execute the Main.java class to run the application.
   - This will demonstrate various functionalities, including database connectivity, CRUD operations, and metadata access.

2. Explore Functionalities:

   - The application covers features such as database connectivity, CRUD operations, transaction handling, and metadata access.
   - Check the specific Java classes for each functionality in the crud, metadata, and other relevant packages.

## Database Configuration

Ensure to configure your database connection details in the appropriate configuration files or directly in the Java source code.

- Example:

    ```bash 
        URL = "jdbc:postgresql://localhost:5432/your_db_name";
        user = "your_username";
        password = "your_pass";
    ```

## ER Diagram and Table Schema

- Explore the ER diagram and table schema in the "ERDiagram-TableSchema" folder. This includes visual representations of the database structure and an Excel file detailing table schema with primary and foreign keys.
