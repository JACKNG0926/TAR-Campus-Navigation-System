
/**
 * @ClassName Main
 * @Description TODO
 * @Author hongs
 * @Date 2025/7/27 17:16
 */

public class Main {
    private static Controller controller = new Controller();

    public static void main(String[] args) {
        // Initialize the campus graph with default locations
        Global.initializeGraph();

        // Display welcome layout and handle user interactions
        while (true) {
            int choose = Page.welcome();
            controller.action(choose);

            // Add a small pause for better user experience
            System.out.println("\nPress Enter to continue...");
            try {
                System.in.read();
            } catch (Exception e) {
                // Ignore
            }
        }
    }
}
