# 🍽️ Restaurant Booking System

A Java Spring Boot application designed for restaurant table reservations. The system provides secure user management, data validation, and background processing, alongside frontend views for the restaurant's menu and location.

## 🚀 Features

* **Table Booking:** (In Development) Core functionality for users to reserve tables.
* **Restaurant Showcase:** Main page displaying the restaurant's menu and location details.
* **User Authentication:** Complete user registration and login processes.
* **Secure Password Handling:** Passwords are cryptographically hashed using Spring Security's `PasswordEncoder` before being saved to the database.
* **Data Validation:**
  * Custom age verification checks.
  * Password strength enforcement (requires special characters).
  * Password matching confirmation.
  * Duplicate email prevention.
* **Asynchronous Email Notifications:** Uses `@Async` to send welcome emails in the background via `JavaMailSender` and Mailtrap.
* **Custom Exception Handling:** Clean error management for edge cases like `PasswordNotMatchException`, `WeekPasswordException`, and `EmailAlreadyRegisteredException`.

## 🛠️ Technologies Used

* **Backend:** Java, Spring Boot (Web, Data JPA, Mail, Security)
* **Frontend:** HTML/Thymeleaf 
* **Database:** Relational Database (via Spring Data JPA)
* **Development Tools:** * Spring Boot DevTools
  * Mailtrap 
  * Maven
 
  * It is still under development. 
