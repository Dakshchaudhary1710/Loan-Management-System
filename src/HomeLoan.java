
public class HomeLoan extends Loan {

    private String propertyAddress;

    // Constructor
    public HomeLoan(int loanId, double loanAmount,
                    double interestRate, int tenureMonths,
                    String propertyAddress) {

        super(loanId, loanAmount, interestRate, tenureMonths);

        this.propertyAddress = propertyAddress;
    }

    // Getter method
    public String getPropertyAddress() {
        return propertyAddress;
    }

    // Display home loan details
    @Override
    public void displayLoanDetails() {
        super.displayLoanDetails();

        System.out.println("Loan Type: Home Loan");
        System.out.println("Property Address: " + propertyAddress);
    }
}
