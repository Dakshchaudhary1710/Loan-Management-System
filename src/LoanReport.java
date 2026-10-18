
public class LoanReport {

    private LoanService loanService;
    private RepaymentService repaymentService;

    // Constructor
    public LoanReport(LoanService loanService,
                      RepaymentService repaymentService) {

        this.loanService = loanService;
        this.repaymentService = repaymentService;
    }

    // Display all loan applications
    public void displayApplicationReport() {
        System.out.println("\n===== LOAN APPLICATION REPORT =====");
        loanService.displayAllApplications();
    }

    // Display applications by status
    public void displayStatusReport(String status) {
        System.out.println("\n===== " + status + " APPLICATIONS =====");
        loanService.displayApplicationsByStatus(status);
    }

    // Display repayment records
    public void displayRepaymentReport() {
        System.out.println("\n===== REPAYMENT REPORT =====");
        repaymentService.displayAllRepayments();
    }

    // Display total amount repaid for an application
    public void displayTotalRepaid(int applicationId) {
        double total = repaymentService.getTotalRepaid(applicationId);

        System.out.printf(
                "Total amount repaid for application %d: ₹%.2f%n",
                applicationId,
                total
        );
    }
}
