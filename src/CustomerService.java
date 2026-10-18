
import java.util.ArrayList;

public class CustomerService {

    private ArrayList<Customer> customers;
    private int nextCustomerId;

    // Constructor
    public CustomerService() {
        customers = new ArrayList<>();
        nextCustomerId = 101;
    }

    // Register a new customer
    public Customer registerCustomer(
            String name, int age, String phoneNumber,
            double monthlyIncome) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty."
            );
        }

        if (age <= 0) {
            throw new IllegalArgumentException(
                    "Age must be greater than zero."
            );
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Phone number cannot be empty."
            );
        }

        if (monthlyIncome < 0) {
            throw new IllegalArgumentException(
                    "Monthly income cannot be negative."
            );
        }

        Customer customer = new Customer(
                nextCustomerId,
                name,
                age,
                phoneNumber,
                monthlyIncome
        );

        customers.add(customer);
        nextCustomerId++;

        return customer;
    }

    // Find customer by ID
    public Customer findCustomer(int customerId) {

        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    // Display all registered customers
    public void displayAllCustomers() {

        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }

        for (Customer customer : customers) {
            customer.displayDetails();
            System.out.println("-------------------------");
        }
    }
}
