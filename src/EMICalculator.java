
public class EMICalculator {

    // Calculate monthly EMI
    public double calculateEMI(Loan loan) {

        double principal = loan.getLoanAmount();

        // Convert annual interest rate to monthly rate
        double monthlyRate = loan.getInterestRate() / 12 / 100;

        // Total number of monthly payments
        int months = loan.getTenureMonths();

        // Handle zero-interest loans
        if (monthlyRate == 0) {
            return principal / months;
        }

        // EMI formula
        double power = Math.pow(1 + monthlyRate, months);

        double emi = principal * monthlyRate * power
                / (power - 1);

        return emi;
    }

    // Calculate total repayment
    public double calculateTotalPayment(Loan loan) {
        return calculateEMI(loan) * loan.getTenureMonths();
    }

    // Calculate total interest
    public double calculateTotalInterest(Loan loan) {
        return calculateTotalPayment(loan) - loan.getLoanAmount();
    }

    // Display EMI summary
    public void displayEMISummary(Loan loan) {

        double emi = calculateEMI(loan);
        double totalPayment = calculateTotalPayment(loan);
        double totalInterest = calculateTotalInterest(loan);

        System.out.printf("Monthly EMI: ₹%.2f%n", emi);
        System.out.printf("Total Repayment: ₹%.2f%n", totalPayment);
        System.out.printf("Total Interest: ₹%.2f%n", totalInterest);
    }
}
