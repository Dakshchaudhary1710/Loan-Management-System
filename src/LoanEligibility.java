
public class LoanEligibility {

    private static final int MINIMUM_AGE = 21;
    private static final double MINIMUM_MONTHLY_INCOME = 15000;
    private static final double MAX_LOAN_INCOME_MULTIPLIER = 20;

    // Check whether a customer is eligible for a loan
    public boolean isEligible(Customer customer, Loan loan) {

        if (customer == null || loan == null) {
            return false;
        }

        if (customer.getAge() < MINIMUM_AGE) {
            return false;
        }

        if (customer.getMonthlyIncome() < MINIMUM_MONTHLY_INCOME) {
            return false;
        }

        double maximumLoanAmount =
                customer.getMonthlyIncome() * MAX_LOAN_INCOME_MULTIPLIER;

        return loan.getLoanAmount() <= maximumLoanAmount;
    }

    // Display eligibility result
    public void displayEligibility(Customer customer, Loan loan) {

        if (isEligible(customer, loan)) {
            System.out.println("Customer is eligible for this loan.");
        } else {
            System.out.println("Customer is not eligible for this loan.");
        }
    }
}
