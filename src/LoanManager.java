
public class LoanManager {

    private CustomerService customerService;
    private LoanService loanService;
    private RepaymentService repaymentService;
    private LoanEligibility loanEligibility;
    private EMICalculator emiCalculator;
    private LoanReport loanReport;

    public LoanManager() {
        customerService = new CustomerService();
        loanService = new LoanService();
        repaymentService = new RepaymentService();
        loanEligibility = new LoanEligibility();
        emiCalculator = new EMICalculator();

        loanReport = new LoanReport(
                loanService,
                repaymentService
        );
    }

    // Submit a loan application after checking eligibility
    public LoanApplication applyForLoan(Customer customer, Loan loan) {

        if (customer == null || loan == null) {
            throw new IllegalArgumentException(
                    "Customer and loan are required."
            );
        }

        if (!loanEligibility.isEligible(customer, loan)) {
            throw new IllegalArgumentException(
                    "Customer does not meet the loan eligibility criteria."
            );
        }

        return loanService.createApplication(customer, loan);
    }

    // Getter methods
    public CustomerService getCustomerService() {
        return customerService;
    }

    public LoanService getLoanService() {
        return loanService;
    }

    public RepaymentService getRepaymentService() {
        return repaymentService;
    }

    public LoanEligibility getLoanEligibility() {
        return loanEligibility;
    }

    public EMICalculator getEmiCalculator() {
        return emiCalculator;
    }

    public LoanReport getLoanReport() {
        return loanReport;
    }
}
