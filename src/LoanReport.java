
public class LoanReport {

    private LoanService loanService;
    private RepaymentService repaymentService;

    public LoanReport(
            LoanService loanService,
            RepaymentService repaymentService) {
        this.loanService = loanService;
        this.repaymentService = repaymentService;
    }

    public void displayApplicationReport() {
        System.out.println("\n===== LOAN APPLICATION REPORT =====");
        loanService.displayAllApplications();
    }

    public void displayStatusReport(String status) {
        System.out.println("\n===== " + status + " APPLICATIONS =====");
        loanService.displayApplicationsByStatus(status);
    }

    public void displayRepaymentReport() {
        System.out.println("\n===== REPAYMENT REPORT =====");
        repaymentService.displayAllRepayments();
    }

    public void displayTotalRepaid(int applicationId) {
        double total = repaymentService.getTotalRepaid(applicationId);

        System.out.printf(
                "Total amount repaid for application %d: ₹%.2f%n",
                applicationId, total
        );
    }

    public void displaySummaryReport() {
        int totalApplications = loanService.getApplicationCount();
        int pending = 0;
        int approved = 0;
        int rejected = 0;
        double approvedLoanAmount = 0;

        System.out.println("\n===== LOAN SUMMARY REPORT =====");

        for (LoanApplication application : loanService.getAllApplications()) {
            String status = application.getStatus();

            if (status.equals("PENDING")) {
                pending++;
            } else if (status.equals("APPROVED")) {
                approved++;
                approvedLoanAmount +=
                        application.getLoan().getLoanAmount();
            } else if (status.equals("REJECTED")) {
                rejected++;
            }
        }

        System.out.println("Total Applications: " + totalApplications);
        System.out.println("Pending Applications: " + pending);
        System.out.println("Approved Applications: " + approved);
        System.out.println("Rejected Applications: " + rejected);

        System.out.printf(
                "Total Amount of Approved Loans: ₹%.2f%n",
                approvedLoanAmount
        );

        System.out.printf(
                "Total Repayments Collected: ₹%.2f%n",
                repaymentService.getTotalCollected()
        );
    }
}
