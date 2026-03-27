package app;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import entity.Customer;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.CustomerForm;
import java.util.List;
import java.util.Scanner;

public class CustomerApp {
    public static void run() {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. List all customers");
            System.out.println("2. Add new customer");
            System.out.println("3. Update customer");
            System.out.println("4. Delete customer");
            System.out.println("5. Find customer by ID");
            System.out.println("0. Back to main menu");
            System.out.print("Choose: ");
            String choiceInput = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        List<Customer> list = customerDAO.findAll();
                        System.out.println("ID | Name | Gender | Phone | Email");
                        for (Customer c : list) {
                            System.out.printf("%-3d | %-15s | %-7s | %-12s | %-20s%n",
                                    c.getCustomerId(),
                                    c.getName(),
                                    c.getGender(),
                                    c.getPhone(),
                                    c.getEmail());
                        }
                    }
                    case 2 -> customerDAO.insert(CustomerForm.inputNewCustomer());
                    case 3 -> customerDAO.update(CustomerForm.inputUpdateCustomer());
                    case 4 -> customerDAO.delete(CustomerForm.inputCustomerId("delete"));
                    case 5 -> {
                        Customer c = customerDAO.findById(CustomerForm.inputCustomerId("find"));
                        System.out.println(c != null ? c : "Customer not found");
                    }
                    case 0 -> {
                        System.out.println("Back to main menu...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (DAOException e) {
                GlobalExceptionHandler.handle(e);
            }
        }
    }
}