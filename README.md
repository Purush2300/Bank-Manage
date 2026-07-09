# Banking Management System (JDBC)

## Overview

The Banking Management System is a console-based Java application that demonstrates database connectivity using JDBC and MySQL. It allows users to perform basic banking operations such as withdrawing money and transferring funds while ensuring transaction consistency using database transactions.

## Features

* View all customer records
* Withdraw money
* Transfer money between accounts
* Transaction management using Commit and Rollback
* PIN verification before withdrawal
* PreparedStatement for secure SQL execution
* Console-based menu system

## Technologies Used

* Java
* JDBC
* MySQL
* Eclipse IDE

## Database

Database Name:

```
Employees
```

Table Name:

```
User
```

Example Columns:

| Column     | Type    |
| ---------- | ------- |
| id         | INT     |
| name       | VARCHAR |
| email      | VARCHAR |
| department | VARCHAR |
| balance    | INT     |
| pin        | INT     |

## Project Structure

```
BankManagementSystem/
│
├── src/
│   └── com/
│       └── bank/
│           └── Bank.java
│
├── README.md
└── mysql-connector-j.jar
```

## Prerequisites

* Java 17 or later
* Eclipse IDE
* MySQL Server
* MySQL Connector/J

## JDBC Connection

```java
String url = "jdbc:mysql://localhost:3306/Employees";
String username = "root";
String password = "1234";
```

Update the username and password according to your local MySQL configuration.

## How to Run

1. Clone the repository.
2. Import the project into Eclipse.
3. Add the MySQL Connector/J JAR to the project's Build Path.
4. Create the `Employees` database.
5. Create the `User` table.
6. Insert sample records.
7. Run the `Bank` class.

## Sample Menu

```
withdraw/transfer
```

### Withdraw

```
Enter Name
Enter PIN
Enter Amount

Withdrawal Successful
Remaining Balance : 35000
```

### Transfer

```
Enter Sender Name
Enter Receiver Name
Enter Amount
Do you want to proceed to payment? yes/no
```

## JDBC Concepts Demonstrated

* DriverManager
* Connection
* Statement
* PreparedStatement
* ResultSet
* SQL Transactions
* Commit
* Rollback

## Future Enhancements

* Create Account
* Deposit Money
* Check Balance
* Change PIN
* Delete Account
* Account Number based transactions
* Transaction History
* Login Authentication
* Exception Handling Improvements
* Password/PIN Encryption
* Spring Boot REST API
* Hibernate / JPA Integration

## Learning Outcomes

This project demonstrates:

* CRUD operations using JDBC
* Secure SQL using PreparedStatement
* Transaction management
* Console application development
* MySQL integration with Java

## Author

Purushotham Reddy
