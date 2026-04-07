# flipkart-automation-java
Java Automation Framework  Flipkart Assignment
# Flipkart Automation Framework (Java)

## Overview
This project is an end-to-end test automation framework developed using Java.  
It validates key user flows such as product search, comparison, cart operations, and quantity updates.

## Tech Stack
- Java  
- JUnit 5  
- Maven  

## Framework Design
- Page Object Model (POM)  
- Modular and scalable structure  
- Separation of test logic and page actions  
- Reusable methods for maintainability  

## Project Structure
flipkart-automation-java
│
├── pom.xml
│
├── src/main/java/pages/
│   ├── HomePage.java
│   ├── SearchPage.java
│   ├── ProductPage.java
│   └── CartPage.java
│
└── src/test/java/tests/
    └── FlipkartTest.java
