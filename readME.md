# Pathfinding Algorithm Project

## Overview
This project implements a pathfinding algorithm using Java. It includes utilities for grid-based pathfinding, user input validation, and path formatting. The project is built using Maven and includes unit tests for key components.

---

## Prerequisites
- **Java**: JDK 21 (tested with Corretto 21.0.7)
- **Maven**: Version 3.6 or higher

---

## How to Run the Project

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/404UserNotFound/pathfinding-algorithm.git
   cd pathfinding-algorithm
   ```

2. **Build the Project**:
   Run the following Maven command to compile the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   Execute the main class using Maven:
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```

---

## How to Run Tests

1. **Run All Tests**:
   Use Maven to execute all unit tests:
   ```bash
   mvn test
   ```

2. **Run a Specific Test**:
   To run a specific test class, use:
   ```bash
   mvn -Dtest=PathFinderImplTest test
   ```
---

## How to Build the Project

1. **Clean and Build**:
   Run the following command to clean and build the project:
   ```bash
   mvn clean package
   ```

2. **Generate a JAR File**:
   The packaged JAR file will be located in the `target` directory:
   ```bash
   target/pathfinding-algorithm-1.0-SNAPSHOT.jar
   ```

3. **Run the JAR File**:
   Execute the JAR file using:
   ```bash
   java -jar target/pathfinding-algorithm-1.0-SNAPSHOT.jar
   ```

## Additional Notes
- Ensure the grid file is correctly formatted and accessible.
- Modify the `Constants` class to adjust default parameters like increment rates.
---
