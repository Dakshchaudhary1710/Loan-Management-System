
public class LoanApplication {

    private int applicationId;
    private Customer customer;
    private Loan loan;
    private String status;

    // Constructor
    public LoanApplication(int applicationId,
                           Customer customer,
                           Loan loan) {

        if (customer == null || loan == null) {
            throw new IllegalArgumentException(
                    "Customer and loan cannot be null."
            );
        }

        this.applicationId = applicationId;
        this.customer = customer;
        this.loan = loan;
        this.status = "PENDING";
    }

    // Getter methods
    public int getApplicationId() {
        return applicationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Loan getLoan() {
        return loan;
    }

    public String getStatus() {
        return status;
    }

    // Approve application
    public void approve() {
        if (status.equals("PENDING")) {
            status = "APPROVED";
        } else {
            System.out.println(
                    "Only pending applications can be approved."
            );
        }
    }

    // Reject application
    public void reject() {
        if (status.equals("PENDING")) {
            status = "REJECTED";
        } else {
            System.out.println(
                    "Only pending applications can be rejected."
            );
        }
    }

    // Display application details
    public void displayApplicationDetails() {
        System.out.println("Application ID: " + applicationId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Loan ID: " + loan.getLoanId());
        System.out.println("Loan Amount: " + loan.getLoanAmount());
        System.out.println("Status: " + status);
    }
}
