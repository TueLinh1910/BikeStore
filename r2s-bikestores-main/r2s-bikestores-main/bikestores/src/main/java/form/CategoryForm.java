package form;

import entity.Category;
import util.ScannerUtil;
import util.ValidationUtil;

public class CategoryForm {

    public static Category inputNewCategory() {
        while (true) {
            String name = ScannerUtil.readNonEmptyString("Enter category name: ");
            if (ValidationUtil.isValidString(name)) {
                return new Category(0, name);
            } else {
                System.out.println("Category name must not be empty.");
            }
        }
    }

    public static Category inputUpdateCategory() {
        int id = ScannerUtil.readInt("Enter category ID to update: ");
        String name = ScannerUtil.readNonEmptyString("Enter new category name: ");
        return new Category(id, name);
    }

    public static int inputCategoryId(String action) {
        return ScannerUtil.readInt("Enter category ID to " + action + ": ");
    }
}