
import java.time.LocalDate;
import java.util.ArrayList;

public class RepaymentService {

    private ArrayList<Repayment> repayments;
    private int nextPaymentId;

    // Constructor
    public RepaymentService() {
        repayments = new ArrayList<>();
        nextPaymentId = 5001;
    }

    // Record a repayment
    public Repayment recordPayment(
            LoanApplication application, double amount) {

        if (application == null) {
            throw new IllegalArgumentException(
                    "Loan application cannot be null."
            );
        }

        if (!application.getStatus().equals("APPROVED")) {
            throw new IllegalStateException(
                    "Repayment is allowed only for approved loans."
            );
        }

        Repayment repayment = new Repayment(
                nextPaymentId,
                application.getApplicationId(),
                amount,
                LocalDate.now()
        );

        repayments.add(repayment);
        nextPaymentId++;

        return repayment;
    }

    // Calculate total repayments for an application
    public double getTotalRepaid(int applicationId) {

        double total = 0;

        for (Repayment repayment : repayments) {
            if (repayment.getApplicationId() == applicationId) {
                total += repayment.getAmount();
            }
        }

        return total;
    }

    // Display all repayments
    public void displayAllRepayments() {

        if (repayments.isEmpty()) {
            System.out.println("No repayment records found.");
            return;
        }

        for (Repayment repayment : repayments) {
            repayment.displayPaymentDetails();
            System.out.println("-------------------------");
        }
    }

    // Display repayments for a particular application
    public void displayRepaymentsByApplication(int applicationId) {

        boolean found = false;

        for (Repayment repayment : repayments) {
            if (repayment.getApplicationId() == applicationId) {
                repayment.displayPaymentDetails();
                System.out.println("-------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No payments found for application ID: " + applicationId
            );
        }
    }
}
