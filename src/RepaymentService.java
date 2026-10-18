
import java.time.LocalDate;
import java.util.ArrayList;

public class RepaymentService {

    private ArrayList<Repayment> repayments;
    private int nextPaymentId;

    public RepaymentService() {
        repayments = new ArrayList<>();
        nextPaymentId = 5001;
    }

    public Repayment recordPayment(LoanApplication application, double amount) {
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

    public double getTotalRepaid(int applicationId) {
        double total = 0;

        for (Repayment repayment : repayments) {
            if (repayment.getApplicationId() == applicationId) {
                total += repayment.getAmount();
            }
        }

        return total;
    }

    // Returns the total of all repayment records.
    public double getTotalCollected() {
        double total = 0;

        for (Repayment repayment : repayments) {
            total += repayment.getAmount();
        }

        return total;
    }

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
