package exception;

public class GlobalExceptionHandler {

    public static void handle(Exception e) {
        System.err.println("Error: " + e.getMessage());
        Throwable cause = e.getCause();
        if (cause != null) {
            System.err.println("Cause: " + cause.getMessage());
        }
    }
}