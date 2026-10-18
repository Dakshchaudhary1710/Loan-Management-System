# Loan Management System

A console-based Loan Management System developed in **Java** using Object-Oriented Programming (OOP) concepts. The application manages customers, loan applications, eligibility checks, EMI calculations, loan approvals, repayments, and reports.

## Project Overview

The Loan Management System simplifies basic loan processing by allowing customers to register, apply for loans, check eligibility, and track repayment records. It also provides administrative operations for approving or rejecting applications and generating reports.

## Features

- **Customer Management:** Register customers and view customer details.
- **Loan Applications:** Apply for personal loans and home loans.
- **Loan Eligibility:** Evaluate eligibility based on age, monthly income, and loan amount.
- **Loan Approval:** Approve or reject pending loan applications.
- **EMI Calculator:** Calculate monthly EMI, total repayment, and total interest.
- **Repayment Management:** Record repayments and view payment history.
- **Reports:** Generate application, repayment, and summary reports.
- **Input Validation:** Validate important customer, loan, and repayment data.

## Technologies Used

- **Programming Language:** Java
- **IDE:** IntelliJ IDEA
- **Data Structures:** `ArrayList`
- **Date Handling:** Java `LocalDate`
- **Mathematical Calculations:** Java `Math` and `printf`
- **Version Control:** Git and GitHub

## Object-Oriented Programming Concepts

The project demonstrates the following OOP concepts:

- **Encapsulation:** Private fields and public getter methods protect and control access to object data.
- **Inheritance:** `Customer` and `Admin` extend `Person`; `PersonalLoan` and `HomeLoan` extend `Loan`.
- **Polymorphism:** The loan subclasses override `displayLoanDetails()` to display loan-specific information.
- **Abstraction through separation of responsibilities:** Services and utility classes separate customer management, loan processing, eligibility checks, EMI calculations, repayments, and reporting.

## Project Structure

```text
LoanManagementSystem/
├── Main.java
├── Person.java
├── Customer.java
├── Admin.java
├── CustomerService.java
├── Loan.java
├── PersonalLoan.java
├── HomeLoan.java
├── LoanApplication.java
├── LoanService.java
├── LoanManager.java
├── LoanEligibility.java
├── EMICalculator.java
├── Repayment.java
├── RepaymentService.java
└── LoanReport.java
```

## How to Run

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Ensure a compatible JDK is installed and configured.
4. Open `Main.java`.
5. Run the `main()` method.
6. Use the console menu to access the available features.

## EMI Calculation

The system calculates Equated Monthly Instalments (EMI) using the standard reducing-balance formula:

\[
EMI = \frac{P \times r \times (1+r)^n}{(1+r)^n-1}
\]

Where:

- \(P\) = Principal loan amount
- \(r\) = Monthly interest rate expressed as a decimal
- \(n\) = Loan tenure in months

The application also calculates total repayment and total interest.

## Data Storage

The current version stores customer records, loan applications, and repayments in in-memory `ArrayList` collections.

**Note:** Data is not persisted between program runs. Database integration is a potential future enhancement.

## Testing

The project has been tested for its main workflows, including:

- Customer registration
- Loan application creation
- Loan eligibility checking
- Application approval and rejection
- EMI calculation
- Repayment recording
- Application and repayment reports
- Customer listing and repayment history

## Future Enhancements

- Integrate MySQL for persistent data storage.
- Add stronger input validation and exception handling.
- Implement login and role-based access for customers and administrators.
- Track outstanding balances and repayment schedules.
- Add a graphical user interface or web interface.
- Generate downloadable loan reports.

## Academic Context

Developed as an academic project to demonstrate Java programming, Object-Oriented Programming, collections, date handling, financial calculations, and modular application design.

---

**Author:** Daksh Chaudhary  
**Project:** Loan Management System  
**Language:** Java
