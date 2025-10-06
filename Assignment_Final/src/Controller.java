

/**
 * @ClassName Controller
 * @Description TODO
 * @Author hongs
 * @Date 2025/7/27 17:16
 */

public class Controller {
    private Model model = new Model();

    public void action(int choose) {
        switch (choose) {
            case 1: // Create Graph
                int sa = Page.selectOperation();
                selectAction(sa);
                break;
            case 2: // Traversal of the graph
                model.performTraversal();
                break;
            case 3: // View the TAR Campus Navigation Network
                model.displayGraph();
                break;
            case 0: // Exit
                System.out.println("Thank you for using TAR Campus Navigation System!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    private void selectAction(int sa) {
        switch (sa) {
            case 1: // Add a vertex
                model.addVertex();
                break;
            case 2: // Remove a vertex
                model.removeVertex();
                break;
            case 3: // Add an edge
                model.addEdge();
                break;
            case 4: // Remove an edge
                model.removeEdge();
                break;
            case 5: // Return to the main menu
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
}
