
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static LoanManager loanManager = new LoanManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== LOAN MANAGEMENT SYSTEM =====");
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
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {

                    case 1:
                        registerCustomer();
                        break;

                    case 2:
                        applyForLoan();
                        break;

                    case 3:
                        checkLoanEligibility();
                        break;

                    case 4:
                        loanManager.getLoanService()
                                .displayAllApplications();
                        break;

                    case 5:
                        approveOrRejectApplication();
                        break;

                    case 6:
                        calculateEMI();
                        break;

                    case 7:
                        recordRepayment();
                        break;

                    case 8:
                        loanManager.getLoanReport()
                                .displaySummaryReport();

                        loanManager.getLoanReport()
                                .displayApplicationReport();

                        loanManager.getLoanReport()
                                .displayRepaymentReport();
                        break;

                    case 9:
                        loanManager.getCustomerService()
                                .displayAllCustomers();
                        break;

                    case 10:
                        System.out.print("Enter Application ID: ");
                        int applicationId = scanner.nextInt();
                        scanner.nextLine();

                        loanManager.getRepaymentService()
                                .displayRepaymentsByApplication(applicationId);
                        break;

                    case 0:
                        System.out.println(
                                "Exiting Loan Management System. Goodbye!"
                        );
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void registerCustomer() {

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter monthly income: ");
        double monthlyIncome = scanner.nextDouble();
        scanner.nextLine();

        Customer customer = loanManager.getCustomerService()
                .registerCustomer(name, age, phoneNumber, monthlyIncome);

        System.out.println("Customer registered successfully!");
        System.out.println("Customer ID: " + customer.getCustomerId());
    }

    private static void applyForLoan() {

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = loanManager.getCustomerService()
                .findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("\nSelect Loan Type:");
        System.out.println("1. Personal Loan");
        System.out.println("2. Home Loan");
        System.out.print("Enter choice: ");

        int loanType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter loan amount: ");
        double amount = scanner.nextDouble();

        System.out.print("Enter annual interest rate (%): ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter tenure in months: ");
        int tenure = scanner.nextInt();
        scanner.nextLine();

        int loanId = 2001
                + loanManager.getLoanService().getApplicationCount();

        Loan loan;

        if (loanType == 1) {

            System.out.print("Enter loan purpose: ");
            String purpose = scanner.nextLine();

            loan = new PersonalLoan(
                    loanId, amount, interestRate, tenure, purpose
            );

        } else if (loanType == 2) {

            System.out.print("Enter property address: ");
            String address = scanner.nextLine();

            loan = new HomeLoan(
                    loanId, amount, interestRate, tenure, address
            );

        } else {
            System.out.println("Invalid loan type.");
            return;
        }

        LoanApplication application =
                loanManager.applyForLoan(customer, loan);

        System.out.println("Loan application submitted successfully!");
        System.out.println(
                "Application ID: " + application.getApplicationId()
        );
        System.out.println("Loan ID: " + loan.getLoanId());
        System.out.println("Application Status: " + application.getStatus());
    }

    private static void checkLoanEligibility() {

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = loanManager.getCustomerService()
                .findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter proposed loan amount: ");
        double amount = scanner.nextDouble();

        System.out.print("Enter annual interest rate (%): ");
        double rate = scanner.nextDouble();

        System.out.print("Enter tenure in months: ");
        int tenure = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nSelect Loan Type:");
        System.out.println("1. Personal Loan");
        System.out.println("2. Home Loan");
        System.out.print("Enter choice: ");

        int type = scanner.nextInt();
        scanner.nextLine();

        int loanId = 9001;

        Loan loan;

        if (type == 1) {
            loan = new PersonalLoan(
                    loanId, amount, rate, tenure, "Eligibility Check"
            );
        } else if (type == 2) {
            loan = new HomeLoan(
                    loanId, amount, rate, tenure, "Eligibility Check"
            );
        } else {
            System.out.println("Invalid loan type.");
            return;
        }

        loanManager.getLoanEligibility()
                .displayEligibility(customer, loan);
    }

    private static void approveOrRejectApplication() {

        System.out.print("Enter Application ID: ");
        int applicationId = scanner.nextInt();
        scanner.nextLine();

        LoanApplication application = loanManager.getLoanService()
                .findApplication(applicationId);

        if (application == null) {
            System.out.println("Application not found.");
            return;
        }

        application.displayApplicationDetails();

        System.out.println("\n1. Approve");
        System.out.println("2. Reject");
        System.out.print("Enter choice: ");

        int decision = scanner.nextInt();
        scanner.nextLine();

        if (decision == 1) {
            application.approve();
            System.out.println("Application status: " + application.getStatus());
        } else if (decision == 2) {
            application.reject();
            System.out.println("Application status: " + application.getStatus());
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void calculateEMI() {

        System.out.print("Enter Application ID: ");
        int applicationId = scanner.nextInt();
        scanner.nextLine();

        LoanApplication application = loanManager.getLoanService()
                .findApplication(applicationId);

        if (application == null) {
            System.out.println("Application not found.");
            return;
        }

        loanManager.getEmiCalculator()
                .displayEMISummary(application.getLoan());
    }

    private static void recordRepayment() {

        System.out.print("Enter Application ID: ");
        int applicationId = scanner.nextInt();
        scanner.nextLine();

        LoanApplication application = loanManager.getLoanService()
                .findApplication(applicationId);

        if (application == null) {
            System.out.println("Application not found.");
            return;
        }

        if (!application.getStatus().equals("APPROVED")) {
            System.out.println(
                    "Repayment is allowed only for approved loans."
            );
            return;
        }

        double totalLoanAmount = application.getLoan().getLoanAmount();

        double totalRepaid = loanManager.getRepaymentService()
                .getTotalRepaid(applicationId);

        double remainingAmount = totalLoanAmount - totalRepaid;

        if (remainingAmount <= 0) {
            System.out.println("This loan has been fully repaid.");
            return;
        }

        System.out.printf("Remaining principal balance: ₹%.2f%n", remainingAmount);
        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0 || amount > remainingAmount) {
            System.out.println(
                    "Payment must be greater than zero and cannot exceed "
                            + "the remaining principal balance."
            );
            return;
        }

        Repayment repayment = loanManager.getRepaymentService()
                .recordPayment(application, amount);

        System.out.println("Payment recorded successfully!");
        System.out.println("Payment ID: " + repayment.getPaymentId());

        double updatedTotal = loanManager.getRepaymentService()
                .getTotalRepaid(applicationId);

        System.out.printf("Total repaid: ₹%.2f%n", updatedTotal);
        System.out.printf(
                "Remaining principal balance: ₹%.2f%n",
                totalLoanAmount - updatedTotal
        );
    }
}
