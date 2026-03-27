package app;

import static util.ScannerUtil.readInt;
import app.CategoryApp;
import app.CustomerApp;

public class DashboardUI {

    public static void main(String[] args) {
        DashboardUI dashboard = new DashboardUI();
        dashboard.showMainMenu();
    }

    public void showMainMenu() {
        int choice;
        do {
            System.out.println("\n====== BIKE STORES DASHBOARD ======");
            System.out.println("1. Brand Management");
            System.out.println("2. Staff Management");
            System.out.println("3. Store Management");
            System.out.println("4. Customer & Category");
            System.out.println("5. Orders & Order Items");
            System.out.println("6. Products & Stock");
            System.out.println("7. Reports / Statistics");
            System.out.println("8. User Authentication");
            System.out.println("9. Cart & Payment");
            System.out.println("10. Shipping & Review");
            System.out.println("11. Wishlist & Discount");
            System.out.println("0. Exit");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> handleBrandModule();
                case 2 -> handleStaff();
                case 3 -> handleStore();
                case 4 -> handleCustomerCategory();
                case 5 -> handleOrderModule();
                case 6 -> handleProductStock();
                case 7 -> handleReports();
                case 8 -> handleUserAuth();
                case 9 -> handleCartPayment();
                case 10 -> handleShippingReview();
                case 11 -> handleWishlistDiscount();
                case 0 -> System.out.println("Exiting. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void handleBrandModule() {
        System.out.println("\nNavigating to Brand Module...");
        BrandApp.run();
    }

    private void handleStaff() {
        System.out.println("\nNavigating to Staff Module...");
    }

    private void handleStore() {
        System.out.println("\nNavigating to Store Module...");
    }

    private void handleCustomerCategory() {
    System.out.println("\n1. Category Management");
    System.out.println("2. Customer Management");

    int choice = readInt("Choose: ");

    switch (choice) {
        case 1 -> CategoryApp.run();
        case 2 -> CustomerApp.run();
        default -> System.out.println("Invalid choice.");
    }
    }

    private void handleOrderModule() {
        System.out.println("\nNavigating to Order & OrderItem Module...");
    }

    private void handleProductStock() {
        System.out.println("\nNavigating to Product & Stock Module...");
    }

    private void handleReports() {
        System.out.println("\nNavigating to Reports / Stats Module...");
    }

    private void handleUserAuth() {
        System.out.println("\nNavigating to Authentication Module...");
    }

    private void handleCartPayment() {
        System.out.println("\nNavigating to Cart & Payment Module...");
    }

    private void handleShippingReview() {
        System.out.println("\nNavigating to Shipping & Review Module...");
    }

    private void handleWishlistDiscount() {
        System.out.println("\nNavigating to Wishlist & Discount Module...");
    }
}