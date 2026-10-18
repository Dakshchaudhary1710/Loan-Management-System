
public class Loan {

    private int loanId;
    private double loanAmount;
    private double interestRate;
    private int tenureMonths;

    // Constructor
    public Loan(int loanId, double loanAmount,
                double interestRate, int tenureMonths) {

        if (loanAmount <= 0) {
            throw new IllegalArgumentException(
                    "Loan amount must be greater than zero."
            );
        }

        if (interestRate < 0) {
            throw new IllegalArgumentException(
                    "Interest rate cannot be negative."
            );
        }

        if (tenureMonths <= 0) {
            throw new IllegalArgumentException(
                    "Loan tenure must be greater than zero."
            );
        }

        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
    }

    // Getter methods
    public int getLoanId() {
        return loanId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    // Display loan details
    public void displayLoanDetails() {
        System.out.println("Loan ID: " + loanId);
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Annual Interest Rate: " + interestRate + "%");
        System.out.println("Tenure: " + tenureMonths + " months");
    }
}
