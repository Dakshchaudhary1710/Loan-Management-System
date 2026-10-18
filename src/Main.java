
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
            System.out.println("10. View Repayment History");
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
                        loanManager.getLoanService().displayAllApplications();
                        break;

                    case 5:
                        approveOrRejectApplication(scanner, loanManager);
                        break;

                    case 6:
                        calculateEMI(scanner, loanManager);
                        break;

                    case 7:
                        recordRepayment(scanner, loanManager);
                        break;

                    case 8:
                        loanManager.getLoanReport().displayApplicationReport();
                        loanManager.getLoanReport().displayRepaymentReport();
                        break;

                    case 9:
                        loanManager.getCustomerService().displayAllCustomers();
                        break;

                    case 10:
                        loanManager.getRepaymentService().displayAllRepayments();
                        break;

                    case 0:
                        System.out.println("Exiting Loan Management System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

        } while (choice != 0);

        scanner.close();
    }

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
            double monthlyIncome = Double.parseDouble(scanner.nextLine());

            Customer customer = loanManager.getCustomerService()
                    .registerCustomer(name, age, phoneNumber, monthlyIncome);

            System.out.println("Customer registered successfully!");
            System.out.println("Generated Customer ID: " + customer.getCustomerId());

        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void applyForLoan(
            Scanner scanner, LoanManager loanManager) {
        try {
            System.out.println("\n===== APPLY FOR LOAN =====");

            System.out.print("Enter Customer ID: ");
            int customerId = Integer.parseInt(scanner.nextLine());

            Customer customer = loanManager.getCustomerService()
                    .findCustomer(customerId);

            if (customer == null) {
                System.out.println("Customer not found. Register first.");
                return;
            }

            System.out.println("1. Personal Loan");
            System.out.println("2. Home Loan");
            System.out.print("Select loan type: ");
            int loanType = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter loan amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter annual interest rate (%): ");
            double rate = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter tenure in months: ");
            int months = Integer.parseInt(scanner.nextLine());

            int loanId = 2001 + loanManager.getLoanService().getApplicationCount();
            Loan loan;

            if (loanType == 1) {
                System.out.print("Enter loan purpose: ");
                String purpose = scanner.nextLine();
                loan = new PersonalLoan(loanId, amount, rate, months, purpose);

            } else if (loanType == 2) {
                System.out.print("Enter property address: ");
                String address = scanner.nextLine();
                loan = new HomeLoan(loanId, amount, rate, months, address);

            } else {
                System.out.println("Invalid loan type.");
                return;
            }

            LoanApplication application = loanManager.applyForLoan(customer, loan);

            System.out.println("Application submitted!");
            System.out.println("Application ID: " + application.getApplicationId());
            System.out.println("Loan ID: " + loan.getLoanId());
            System.out.println("Status: " + application.getStatus());

            loanManager.getEmiCalculator().displayEMISummary(loan);

        } catch (IllegalArgumentException e) {
            System.out.println("Loan application failed: " + e.getMessage());
        }
    }

    private static void checkLoanEligibility(
            Scanner scanner, LoanManager loanManager) {
        try {
            System.out.println("\n===== LOAN ELIGIBILITY CHECK =====");

            System.out.print("Enter Customer ID: ");
            int customerId = Integer.parseInt(scanner.nextLine());

            Customer customer = loanManager.getCustomerService()
                    .findCustomer(customerId);

            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            System.out.print("Enter requested loan amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter annual interest rate (%): ");
            double rate = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter tenure in months: ");
            int months = Integer.parseInt(scanner.nextLine());

            System.out.println("1. Personal Loan");
            System.out.println("2. Home Loan");
            System.out.print("Select loan type: ");
            int loanType = Integer.parseInt(scanner.nextLine());

            Loan loan;

            if (loanType == 1) {
                loan = new PersonalLoan(9999, amount, rate, months, "Eligibility Check");
            } else if (loanType == 2) {
                loan = new HomeLoan(9999, amount, rate, months, "Eligibility Check");
            } else {
                System.out.println("Invalid loan type.");
                return;
            }

            loanManager.getLoanEligibility().displayEligibility(customer, loan);

        } catch (IllegalArgumentException e) {
            System.out.println("Eligibility check failed: " + e.getMessage());
        }
    }

    private static void approveOrRejectApplication(
            Scanner scanner, LoanManager loanManager) {
        try {
            System.out.println("\n===== APPROVE / REJECT APPLICATION =====");

            System.out.print("Enter Application ID: ");
            int applicationId = Integer.parseInt(scanner.nextLine());

            LoanApplication application = loanManager.getLoanService()
                    .findApplication(applicationId);

            if (application == null) {
                System.out.println("Application not found.");
                return;
            }

            System.out.println("Current Status: " + application.getStatus());

            if (!application.getStatus().equals("PENDING")) {
                System.out.println("This application has already been processed.");
                return;
            }

            System.out.println("1. Approve");
            System.out.println("2. Reject");
            System.out.print("Choose action: ");
            int action = Integer.parseInt(scanner.nextLine());

            if (action == 1) {
                application.approve();
                System.out.println("Application approved.");
            } else if (action == 2) {
                application.reject();
                System.out.println("Application rejected.");
            } else {
                System.out.println("Invalid choice.");
                return;
            }

            System.out.println("Updated Status: " + application.getStatus());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void calculateEMI(
            Scanner scanner, LoanManager loanManager) {
        try {
            System.out.println("\n===== CALCULATE EMI =====");

            System.out.print("Enter Application ID: ");
            int applicationId = Integer.parseInt(scanner.nextLine());

            LoanApplication application = loanManager.getLoanService()
                    .findApplication(applicationId);

            if (application == null) {
                System.out.println("Application not found.");
                return;
            }

            loanManager.getEmiCalculator()
                    .displayEMISummary(application.getLoan());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid application ID.");
        }
    }

    private static void recordRepayment(
            Scanner scanner, LoanManager loanManager) {
        try {
            System.out.println("\n===== RECORD REPAYMENT =====");

            System.out.print("Enter Application ID: ");
            int applicationId = Integer.parseInt(scanner.nextLine());

            LoanApplication application = loanManager.getLoanService()
                    .findApplication(applicationId);

            if (application == null) {
                System.out.println("Application not found.");
                return;
            }

            if (!application.getStatus().equals("APPROVED")) {
                System.out.println("Repayment is allowed only for approved loans.");
                return;
            }

            System.out.printf("Loan amount: ₹%.2f%n",
                    application.getLoan().getLoanAmount());

            System.out.print("Enter payment amount: ₹");
            double amount = Double.parseDouble(scanner.nextLine());

            double alreadyPaid = loanManager.getRepaymentService()
                    .getTotalRepaid(applicationId);

            double remaining = application.getLoan().getLoanAmount() - alreadyPaid;

            if (amount <= 0) {
                System.out.println("Payment amount must be greater than zero.");
                return;
            }

            if (amount > remaining) {
                System.out.printf("Payment exceeds remaining principal of ₹%.2f%n",
                        remaining);
                return;
            }

            Repayment repayment = loanManager.getRepaymentService()
                    .recordPayment(application, amount);

            System.out.println("\nPayment recorded successfully!");
            System.out.println("Payment ID: " + repayment.getPaymentId());
            System.out.println("Payment Date: " + repayment.getPaymentDate());
            System.out.printf("Total repaid: ₹%.2f%n",
                    loanManager.getRepaymentService().getTotalRepaid(applicationId));
            System.out.printf("Remaining principal: ₹%.2f%n",
                    remaining - amount);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Repayment failed: " + e.getMessage());
        }
    }
}
