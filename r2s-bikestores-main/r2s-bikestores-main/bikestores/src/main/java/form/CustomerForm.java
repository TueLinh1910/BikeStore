package form;

import entity.Customer;
import util.ScannerUtil;
import util.ValidationUtil;

public class CustomerForm {

    public static Customer inputNewCustomer() {
        String name = ScannerUtil.readNonEmptyString("Enter name: ");

        String gender;
        while (true) {
            gender = ScannerUtil.readNonEmptyString("Enter gender (Male/Female/Other): ");
            if (gender.equalsIgnoreCase("Male") ||
                gender.equalsIgnoreCase("Female") ||
                gender.equalsIgnoreCase("Other")) {
                break;
            } else {
                System.out.println("Invalid gender. Please enter Male, Female or Other.");
            }
        }

        String phone = ScannerUtil.readNonEmptyString("Enter phone: ");
        String email = ScannerUtil.readNonEmptyString("Enter email: ");

        return new Customer(0, name, gender, phone, email);
    }

    public static Customer inputUpdateCustomer() {
        int id = ScannerUtil.readInt("Enter customer ID to update: ");

        String name = ScannerUtil.readNonEmptyString("Enter new name: ");

        String gender;
        while (true) {
            gender = ScannerUtil.readNonEmptyString("Enter gender (Male/Female/Other): ");
            if (gender.equalsIgnoreCase("Male") ||
                gender.equalsIgnoreCase("Female") ||
                gender.equalsIgnoreCase("Other")) {
                break;
            } else {
                System.out.println("Invalid gender.");
            }
        }

        String phone = ScannerUtil.readNonEmptyString("Enter new phone: ");
        String email = ScannerUtil.readNonEmptyString("Enter new email: ");

        return new Customer(id, name, gender, phone, email);
    }

    public static int inputCustomerId(String action) {
        return ScannerUtil.readInt("Enter customer ID to " + action + ": ");
    }
}