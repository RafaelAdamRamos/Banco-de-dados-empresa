# Company Database

> 🔧 **Extensible Project**  
> This project presents a relational database structure for a company and can be expanded or improved according to new requirements, studies, or application integrations.

## Description

This repository contains a **Java project** focused on **data persistence**, using a relational database to represent a company's structure.

The project was developed as a base for studying **CRUD**, data modeling, and integration between Java and databases, and can be expanded with new features, business rules, or interfaces.

## Database Structure

The main tables in this database include:

- **EMPLOYEE** – employee records with attributes such as name, CPF, salary, and relationships with projects and supervisors.
- **DEPARTMENT** – company departments with manager and start dates.
- **DEPARTMENT_LOCATION** – department locations.
- **PROJECT** – projects with name, number, and related department.
- **WORKS_ON** – associative table between employees and projects.
- **DEPENDENT** – employee dependents.

*(The tables are defined in the `banco.sql` file.)*

## Files

- `banco.sql` – contains the SQL commands for creating the tables and database structure.

## Technologies Used
- Java
- SQL
- Relational database
- JDBC (or direct database access)

## How to use

1. **Import the SQL script**  
   - Open your DBMS (MySQL, PostgreSQL, etc.)  
   - Create a database (for example `empresa`)  
   - Execute the contents of the `banco.sql` file to create the tables  

2. **Connect with your application**  
   - Use JDBC or an ORM (for example Hibernate) in your Java application  
   - Adjust the connection configuration (URL, user, password) as needed

## Requirements

- Relational database server (MySQL, PostgreSQL, or another compatible one)
- Tool to execute SQL scripts (Workbench, DBeaver, PgAdmin, etc.)
- If connecting with Java code: JDK installed and JDBC driver configured

## Notes

- Project focused on **database modeling studies** and examples of relational structure  
- Can be expanded with sample data, additional relationships, views, indexes, etc.
- It is constantly evolving as new requirements or applications are integrated
