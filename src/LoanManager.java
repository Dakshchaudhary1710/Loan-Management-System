
public class LoanManager {

    private LoanService loanService;
    private RepaymentService repaymentService;
    private LoanEligibility loanEligibility;
    private EMICalculator emiCalculator;
    private LoanReport loanReport;

    // Constructor
    public LoanManager() {

        loanService = new LoanService();
        repaymentService = new RepaymentService();
        loanEligibility = new LoanEligibility();
        emiCalculator = new EMICalculator();

        loanReport = new LoanReport(
                loanService,
                repaymentService
        );
    }

    // Getter methods for services
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
