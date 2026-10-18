
import java.time.LocalDate;

public class Repayment {

    private int paymentId;
    private int applicationId;
    private double amount;
    private LocalDate paymentDate;
    private String status;

    // Constructor
    public Repayment(int paymentId, int applicationId,
                     double amount, LocalDate paymentDate) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be greater than zero."
            );
        }

        if (paymentDate == null) {
            throw new IllegalArgumentException(
                    "Payment date cannot be null."
            );
        }

        this.paymentId = paymentId;
        this.applicationId = applicationId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = "PAID";
    }

    // Getter methods
    public int getPaymentId() {
        return paymentId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getStatus() {
        return status;
    }

    // Display payment details
    public void displayPaymentDetails() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Application ID: " + applicationId);
        System.out.printf("Payment Amount: ₹%.2f%n", amount);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Payment Status: " + status);
    }
}
