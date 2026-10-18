
public class PersonalLoan extends Loan {

    private String purpose;

    // Constructor
    public PersonalLoan(int loanId, double loanAmount,
                        double interestRate, int tenureMonths,
                        String purpose) {

        super(loanId, loanAmount, interestRate, tenureMonths);

        this.purpose = purpose;
    }

    // Getter method
    public String getPurpose() {
        return purpose;
    }

    // Display personal loan details
    public void displayLoanDetails() {
        super.displayLoanDetails();
        System.out.println("Loan Type: Personal Loan");
        System.out.println("Purpose: " + purpose);
    }
}
