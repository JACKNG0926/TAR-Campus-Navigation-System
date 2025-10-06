import java.util.Scanner;

/**
 * @ClassName Page
 * @Description TODO
 * @Author hongs
 * @Date 2025/7/27 17:16
 */

public class Page {
    private static Scanner scanner = new Scanner(System.in);

    private Page() {}

    public static int welcome() {
        System.out.println("================================================================================");
        System.out.println("                    Welcome to TAR Campus Navigation System!                   ");
        System.out.println("================================================================================");
        System.out.println("\nEnter your option:");
        System.out.println("1. Create/Edit Graph");
        System.out.println("2. Traversal of the Graph");
        System.out.println("3. View the TAR Campus Navigation Network");
        System.out.println("0. Exit");

        int choose;
        do {
            System.out.print("Please enter correct number (0-3): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter a number (0-3): ");
                scanner.next();
            }
            choose = scanner.nextInt();
        } while (choose < 0 || choose > 3);

        return choose;
    }

    public static int selectOperation() {
        System.out.println("\n=== Graph Operations ===");
        System.out.println("Enter \"1\" to \"5\" for updating the graph:");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("1. Add a vertex");
        System.out.println("2. Remove a vertex");
        System.out.println("3. Add an edge");
        System.out.println("4. Remove an edge");
        System.out.println("5. Return to the main menu");

        int choose;
        do {
            System.out.print("Please enter correct number (1-5): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter a number (1-5): ");
                scanner.next();
            }
            choose = scanner.nextInt();
        } while (choose < 1 || choose > 5);

        return choose;
    }
}