
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LoanManager loanManager = new LoanManager();

        int choice;

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
            System.out.println("0. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Customer registration selected.");
                    break;

                case 2:
                    System.out.println("Loan application selected.");
                    break;

                case 3:
                    System.out.println("Eligibility check selected.");
                    break;

                case 4:
                    System.out.println("View applications selected.");
                    break;

                case 5:
                    System.out.println("Approval/rejection selected.");
                    break;

                case 6:
                    System.out.println("EMI calculation selected.");
                    break;

                case 7:
                    System.out.println("Repayment selected.");
                    break;

                case 8:
                    System.out.println("Reports selected.");
                    break;

                case 0:
                    System.out.println("Exiting Loan Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
