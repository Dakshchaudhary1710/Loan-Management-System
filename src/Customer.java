
public class Customer extends Person {

    private int customerId;
    private double monthlyIncome;

    // Constructor
    public Customer(int customerId, String name, int age,
                    String phoneNumber, double monthlyIncome) {

        super(name, age, phoneNumber);

        this.customerId = customerId;
        this.monthlyIncome = monthlyIncome;
    }

    // Getter methods
    public int getCustomerId() {
        return customerId;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    // Update monthly income
    public void setMonthlyIncome(double monthlyIncome) {
        if (monthlyIncome < 0) {
            throw new IllegalArgumentException(
                    "Monthly income cannot be negative."
            );
        }

        this.monthlyIncome = monthlyIncome;
    }

    // Display customer details
    public void displayDetails() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Monthly Income: " + monthlyIncome);
    }
}
