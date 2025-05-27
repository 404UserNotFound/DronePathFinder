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
   git clone https://github.com/404UserNotFound/DronePathFinder.git
   ```

2. **Run the Application**:
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
   cd target
   ```

3. **Run the JAR File**:
   Execute the JAR file using:
   ```bash
   java -jar DronePathFinder-1.0-SNAPSHOT.jar
   ```
   
## How to Use the Application
1. When you run the application it will prompt you to select which grid you want to use.
2. You can choose from the following options:
   - **1**: 20x20 grid
   - **2**: 100x100 grid
   - **3**: 1000x1000 grid
3. After selecting a grid, you will be prompted to enter the starting x and y coordinates for the pathfinding algorithm.
4. Next you will enter the time limit in milliseconds.
5. Finally, you will enter the time-step limit, the app will run until time limit or step limit is reached.
6. The app will return the co-ordinates travelled, the value of each co-ordinate, along with the total accrued score.
---
