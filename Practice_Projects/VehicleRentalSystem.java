package Practice_Projects;

import java.util.*;

abstract class Person {
    protected String name;
    protected String username;
    protected String password;

    public Person(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getName() {
        return name;
    }

    public abstract void showMenu(VehicleRentalSystem system, Scanner scanner);
}

class Admin extends Person {
    public Admin(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public void showMenu(VehicleRentalSystem system, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. View All Rentals");
            System.out.println("4. View All Customers");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: system.addVehicle(scanner); break;
                case 2: system.viewAllVehicles(); break;
                case 3: system.viewAllRentals(); break;
                case 4: system.viewAllCustomers(); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}

class Customer extends Person {
    public Customer(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public void showMenu(VehicleRentalSystem system, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Return a Vehicle");
            System.out.println("4. View My Rentals");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: system.viewAvailableVehicles(); break;
                case 2: system.rentVehicle(this, scanner); break;
                case 3: system.returnVehicle(this, scanner); break;
                case 4: system.viewCustomerRentals(this); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}

class Vehicle {
    private String vehicleId;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
    }

    public String getVehicleId() { return vehicleId; }
    public String getModel() { return model; }
    public double getRentPerDay() { return rentPerDay; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return vehicleId + " - " + model + " ($" + rentPerDay + "/day), Available: " + isAvailable;
    }
}

class Rental {
    private String customerName;
    private String vehicleId;
    private int days;
    private double totalCost;

    public Rental(String customerName, String vehicleId, int days, double totalCost) {
        this.customerName = customerName;
        this.vehicleId = vehicleId;
        this.days = days;
        this.totalCost = totalCost;
    }

    public String getCustomerName() { return customerName; }
    public String getVehicleId() { return vehicleId; }

    @Override
    public String toString() {
        return customerName + " rented " + vehicleId + " for " + days + " days, Cost: ₹" + totalCost;
    }
}

public class VehicleRentalSystem {
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    public void initialize() {
        admins.add(new Admin("Amit Patel", "St4rkAmit", "amit#3000"));
    }

    public void addVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Rent per Day: ");
        double rent = scanner.nextDouble();
        scanner.nextLine();
        vehicles.add(new Vehicle(id, model, rent));
        System.out.println("Vehicle added!");
    }

    public void viewAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    public void viewAvailableVehicles() {
        boolean anyAvailable = false;
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                System.out.println(v);
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No vehicles currently available.");
        }
    }

    public void rentVehicle(Customer customer, Scanner scanner) {
        viewAvailableVehicles();
        System.out.print("Enter Vehicle ID to rent: ");
        String id = scanner.nextLine();
        Vehicle selected = null;
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equals(id) && v.isAvailable()) {
                selected = v;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Vehicle not available or invalid ID.");
            return;
        }
        System.out.print("Enter number of days: ");
        int days = scanner.nextInt();
        scanner.nextLine();
        double cost = days * selected.getRentPerDay();
        rentals.add(new Rental(customer.getName(), id, days, cost));
        selected.setAvailable(false);
        System.out.println("Vehicle rented successfully!");
    }

    public void returnVehicle(Customer customer, Scanner scanner) {
        System.out.print("Enter Vehicle ID to return: ");
        String id = scanner.nextLine();
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equals(id)) {
                v.setAvailable(true);
                System.out.println("Vehicle returned successfully!");
                return;
            }
        }
        System.out.println("Invalid Vehicle ID.");
    }

    public void viewCustomerRentals(Customer customer) {
        boolean found = false;
        for (Rental r : rentals) {
            if (r.getCustomerName().equals(customer.getName())) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rental history found.");
        }
    }

    public void viewAllRentals() {
        if (rentals.isEmpty()) {
            System.out.println("No rentals made yet.");
        } else {
            for (Rental r : rentals) {
                System.out.println(r);
            }
        }
    }

    public void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No registered customers.");
        } else {
            for (Customer c : customers) {
                System.out.println("Customer: " + c.getName());
            }
        }
    }

    public void adminLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();
        for (Admin a : admins) {
            if (a.login(user, pass)) {
                a.showMenu(this, scanner);
                return;
            }
        }
        System.out.println("Invalid admin credentials.");
    }

    public void customerLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();
        for (Customer c : customers) {
            if (c.login(user, pass)) {
                c.showMenu(this, scanner);
                return;
            }
        }
        System.out.println("Invalid customer credentials.");
    }

    public void customerRegister(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();
        customers.add(new Customer(name, user, pass));
        System.out.println("Registration successful!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleRentalSystem system = new VehicleRentalSystem();
        system.initialize();

        while (true) {
            System.out.println("\n=== Vehicle Rental System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Registration");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: system.adminLogin(scanner); break;
                case 2: system.customerLogin(scanner); break;
                case 3: system.customerRegister(scanner); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}