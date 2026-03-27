package app;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import entity.Category;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.CategoryForm;
import util.Constants;
import java.util.List;
import java.util.Scanner;

public class CategoryApp {
    public static void run() {
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CATEGORY MENU =====");
            System.out.println("1. List all categories");
            System.out.println("2. Add new category");
            System.out.println("3. Update category");
            System.out.println("4. Delete category");
            System.out.println("5. Find category by ID");
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
                        List<Category> list = categoryDAO.findAll();
                        System.out.println("Category ID | Category Name");
                        for (Category c : list) {
                            System.out.printf("%-12d | %-30s%n", c.getCategoryId(), c.getCategoryName());
                        }
                    }
                    case 2 -> categoryDAO.insert(CategoryForm.inputNewCategory());
                    case 3 -> categoryDAO.update(CategoryForm.inputUpdateCategory());
                    case 4 -> categoryDAO.delete(CategoryForm.inputCategoryId("delete"));
                    case 5 -> {
                        Category category = categoryDAO.findById(CategoryForm.inputCategoryId("find"));
                        System.out.println(category != null ? category : "Category not found");
                    }
                    case 0 -> {
                        System.out.println("Back to main menu...");
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