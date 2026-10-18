
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoanManager loanManager = new LoanManager();

        int choice = -1;

        do {
            System.out.println("\n================================");
            System.out.println("     LOAN MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Register Customer");
            System.out.println("2. Apply for Loan");
            System.out.println("3. Check Loan Eligibility");
            System.out.println("4. View Loan Applications");
            System.out.println("5. Approve or Reject Application");
            System.out.println("6. Calculate EMI");
            System.out.println("7. Record Repayment");
            System.out.println("8. View Reports");
            System.out.println("9. View All Customers");
            System.out.println("0. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        registerCustomer(scanner, loanManager);
                        break;

                    case 2:
                        applyForLoan(scanner, loanManager);
                        break;

                    case 3:
                        checkLoanEligibility(scanner, loanManager);
                        break;

                    case 4:
                        loanManager.getLoanService()
                                .displayAllApplications();
                        break;

                    case 5:
                        approveOrRejectApplication(scanner, loanManager);
                        break;

                    case 6:
                        calculateEMI(scanner, loanManager);
                        break;

                    case 7:
                        System.out.println(
                                "Repayment functionality is coming next."
                        );
                        break;

                    case 8:
                        loanManager.getLoanReport()
                                .displayApplicationReport();
                        loanManager.getLoanReport()
                                .displayRepaymentReport();
                        break;

                    case 9:
                        loanManager.getCustomerService()
                                .displayAllCustomers();
                        break;

                    case 0:
                        System.out.println(
                                "Exiting Loan Management System. Goodbye!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again."
                        );
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

        } while (choice != 0);

        scanner.close();
    }

    // 1. Register a customer
    private static void registerCustomer(
            Scanner scanner, LoanManager loanManager) {

        try {
            System.out.println("\n===== CUSTOMER REGISTRATION =====");

            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Enter monthly income: ");
            double monthlyIncome =
                    Double.parseDouble(scanner.nextLine());

            Customer customer =
                    loanManager.getCustomerService().registerCustomer(
                            name, age, phoneNumber, monthlyIncome
                    );

            System.out.println("\nCustomer registered successfully!");
            System.out.println(
                    "Generated Customer ID: " + customer.getCustomerId()
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Registration failed: " + e.getMessage()
            );
        }
    }

    // 2. Apply for a loan
    private static void applyForLoan(
            Scanner scanner, LoanManager loanManager) {

        try {
            System.out.println("\n===== APPLY FOR LOAN =====");

            System.out.print("Enter Customer ID: ");
            int customerId = Integer.parseInt(scanner.nextLine());

            Customer customer =
                    loanManager.getCustomerService()
                            .findCustomer(customerId);

            if (customer == null) {
                System.out.println(
                        "Customer not found. Register first."
                );
                return;
            }

            System.out.println("\nSelect Loan Type:");
            System.out.println("1. Personal Loan");
            System.out.println("2. Home Loan");
            System.out.print("Enter choice: ");

            int loanType = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter loan amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter annual interest rate (%): ");
            double interestRate =
                    Double.parseDouble(scanner.nextLine());

            System.out.print("Enter tenure in months: ");
            int tenureMonths =
                    Integer.parseInt(scanner.nextLine());

            int loanId = 2001 +
                    loanManager.getLoanService().getApplicationCount();

            Loan loan;

            if (loanType == 1) {
                System.out.print("Enter loan purpose: ");
                String purpose = scanner.nextLine();

                loan = new PersonalLoan(
                        loanId, amount, interestRate, tenureMonths, purpose
                );

            } else if (loanType == 2) {
                System.out.print("Enter property address: ");
                String address = scanner.nextLine();

                loan = new HomeLoan(
                        loanId, amount, interestRate, tenureMonths, address
                );

            } else {
                System.out.println("Invalid loan type.");
                return;
            }

            LoanApplication application =
                    loanManager.applyForLoan(customer, loan);

            System.out.println(
                    "\nLoan application submitted successfully!"
            );
            System.out.println(
                    "Application ID: " + application.getApplicationId()
            );
            System.out.println("Loan ID: " + loan.getLoanId());
            System.out.println("Status: " + application.getStatus());

            System.out.println("\n===== EMI ESTIMATE =====");
            loanManager.getEmiCalculator()
                    .displayEMISummary(loan);

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Loan application failed: " + e.getMessage()
            );
        }
    }

    // 3. Check loan eligibility
    private static void checkLoanEligibility(
            Scanner scanner, LoanManager loanManager) {

        try {
            System.out.println("\n===== LOAN ELIGIBILITY CHECK =====");

            System.out.print("Enter Customer ID: ");
            int customerId = Integer.parseInt(scanner.nextLine());

            Customer customer =
                    loanManager.getCustomerService()
                            .findCustomer(customerId);

            if (customer == null) {
                System.out.println(
                        "Customer not found. Register first."
                );
                return;
            }

            System.out.print("Enter requested loan amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter annual interest rate (%): ");
            double interestRate =
                    Double.parseDouble(scanner.nextLine());

            System.out.print("Enter tenure in months: ");
            int tenureMonths =
                    Integer.parseInt(scanner.nextLine());

            System.out.println("\nSelect Loan Type:");
            System.out.println("1. Personal Loan");
            System.out.println("2. Home Loan");
            System.out.print("Enter choice: ");

            int loanType = Integer.parseInt(scanner.nextLine());

            Loan loan;

            if (loanType == 1) {
                loan = new PersonalLoan(
                        9999, amount, interestRate, tenureMonths,
                        "Eligibility Check"
                );

            } else if (loanType == 2) {
                loan = new HomeLoan(
                        9999, amount, interestRate, tenureMonths,
                        "Eligibility Check"
                );

            } else {
                System.out.println("Invalid loan type.");
                return;
            }

            loanManager.getLoanEligibility()
                    .displayEligibility(customer, loan);

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Eligibility check failed: " + e.getMessage()
            );
        }
    }

    // 5. Approve or reject an application
    private static void approveOrRejectApplication(
            Scanner scanner, LoanManager loanManager) {

        try {
            System.out.println(
                    "\n===== APPROVE / REJECT APPLICATION ====="
            );

            System.out.print("Enter Application ID: ");
            int applicationId = Integer.parseInt(scanner.nextLine());

            LoanApplication application =
                    loanManager.getLoanService()
                            .findApplication(applicationId);

            if (application == null) {
                System.out.println("Application not found.");
                return;
            }

            System.out.println(
                    "Current Status: " + application.getStatus()
            );

            if (!application.getStatus().equals("PENDING")) {
                System.out.println(
                        "This application has already been processed."
                );
                return;
            }

            System.out.println("\n1. Approve");
            System.out.println("2. Reject");
            System.out.print("Enter choice: ");

            int action = Integer.parseInt(scanner.nextLine());

            if (action == 1) {
                application.approve();
                System.out.println(
                        "Loan application approved successfully."
                );

            } else if (action == 2) {
                application.reject();
                System.out.println(
                        "Loan application rejected successfully."
                );

            } else {
                System.out.println("Invalid choice.");
                return;
            }

            System.out.println(
                    "Updated Status: " + application.getStatus()
            );

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    // 6. Calculate EMI for an existing application
    private static void calculateEMI(
            Scanner scanner, LoanManager loanManager) {

        try {
            System.out.println("\n===== CALCULATE LOAN EMI =====");

            System.out.print("Enter Application ID: ");
            int applicationId = Integer.parseInt(scanner.nextLine());

            LoanApplication application =
                    loanManager.getLoanService()
                            .findApplication(applicationId);

            if (application == null) {
                System.out.println("Application not found.");
                return;
            }

            System.out.println(
                    "Application Status: " + application.getStatus()
            );

            loanManager.getEmiCalculator()
                    .displayEMISummary(application.getLoan());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid application ID.");
        }
    }
}
