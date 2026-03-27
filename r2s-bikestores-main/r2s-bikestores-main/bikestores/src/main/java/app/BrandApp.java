package app;

import dao.BrandDAO;
import dao.BrandDAOImpl;
import entity.Brand;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.BrandForm;
import util.Constants;
import java.util.List;
import java.util.Scanner;

public class BrandApp {
    public static void run() {
        BrandDAO brandDAO = new BrandDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BRAND MENU =====");
            System.out.println("1. List all brands");
            System.out.println("2. Add new brand");
            System.out.println("3. Update brand");
            System.out.println("4. Delete brand");
            System.out.println("5. Find brand by ID");
            System.out.println("0. Back to main menu");
            System.out.print("Choose: ");
            String choiceInput = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        List<Brand> brands = brandDAO.findAll();
                        System.out.println(Constants.BRAND_HEADER);
                        for (Brand b : brands) {
                            System.out.printf(Constants.BRAND_ROW_FORMAT + "%n", b.getBrandId(), b.getBrandName());
                        }
                    }
                    case 2 -> brandDAO.insert(BrandForm.inputNewBrand());
                    case 3 -> brandDAO.update(BrandForm.inputUpdateBrand());
                    case 4 -> brandDAO.delete(BrandForm.inputBrandId("delete"));
                    case 5 -> {
                        Brand brand = brandDAO.findById(BrandForm.inputBrandId("find"));
                        System.out.println(brand != null ? brand : "Brand not found");
                    }
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (DAOException e) {
                GlobalExceptionHandler.handle(e);
            }
        }
    }
}