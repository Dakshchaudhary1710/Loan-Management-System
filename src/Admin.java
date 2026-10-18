
public class Admin extends Person {

    private int adminId;

    // Constructor
    public Admin(int adminId, String name, int age, String phoneNumber) {
        super(name, age, phoneNumber);

        this.adminId = adminId;
    }

    // Getter method
    public int getAdminId() {
        return adminId;
    }

    // Display admin details
    public void displayDetails() {
        System.out.println("Admin ID: " + adminId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Phone: " + getPhoneNumber());
    }
}
