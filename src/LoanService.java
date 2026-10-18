
import java.util.ArrayList;

public class LoanService {

    private ArrayList<LoanApplication> applications;
    private int nextApplicationId;

    // Constructor
    public LoanService() {
        applications = new ArrayList<>();
        nextApplicationId = 1001;
    }

    // Create a new loan application
    public LoanApplication createApplication(
            Customer customer, Loan loan) {

        LoanApplication application = new LoanApplication(
                nextApplicationId, customer, loan
        );

        applications.add(application);
        nextApplicationId++;

        return application;
    }

    // Find an application using its ID
    public LoanApplication findApplication(int applicationId) {

        for (LoanApplication application : applications) {
            if (application.getApplicationId() == applicationId) {
                return application;
            }
        }

        return null;
    }

    // Display all applications
    public void displayAllApplications() {

        if (applications.isEmpty()) {
            System.out.println("No loan applications found.");
            return;
        }

        for (LoanApplication application : applications) {
            application.displayApplicationDetails();
            System.out.println("-------------------------");
        }
    }

    // Display applications by status
    public void displayApplicationsByStatus(String status) {

        boolean found = false;

        for (LoanApplication application : applications) {
            if (application.getStatus().equalsIgnoreCase(status)) {
                application.displayApplicationDetails();
                System.out.println("-------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No applications found with status: " + status
            );
        }
    }
}
