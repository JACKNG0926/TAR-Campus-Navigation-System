import java.util.Scanner;
/**
 * @ClassName Model
 * @Description TODO
 * @Author hongs
 * @Date 2025/7/27 17:16
 */

public class Model {
    private Scanner scanner = new Scanner(System.in);

    public void addVertex() {
        System.out.print("Enter vertex name to add: ");
        String vertex = scanner.nextLine();
        Global.campusGraph.addVertex(vertex);
    }

    public void removeVertex() {
        System.out.println("Current vertices: " + Global.campusGraph.getAllVertices());
        System.out.print("Enter vertex name to remove: ");
        String vertex = scanner.nextLine();
        Global.campusGraph.removeVertex(vertex);
    }

    public void addEdge() {
        System.out.println("Current vertices: " + Global.campusGraph.getAllVertices());
        System.out.print("Enter first vertex: ");
        String vertex1 = scanner.nextLine();
        System.out.print("Enter second vertex: ");
        String vertex2 = scanner.nextLine();
        Global.campusGraph.addEdge(vertex1, vertex2);
    }

    public void removeEdge() {
        System.out.println("Current vertices: " + Global.campusGraph.getAllVertices());
        System.out.print("Enter first vertex: ");
        String vertex1 = scanner.nextLine();
        System.out.print("Enter second vertex: ");
        String vertex2 = scanner.nextLine();
        Global.campusGraph.removeEdge(vertex1, vertex2);
    }

    public void performTraversal() {
        if (Global.campusGraph.getAllVertices().isEmpty()) {
            System.out.println("Graph is empty! Please add vertices first.");
            return;
        }

        System.out.println("Current vertices: " + Global.campusGraph.getAllVertices());
        System.out.print("Enter starting vertex for traversal: ");
        String startVertex = scanner.nextLine();

        System.out.println("Choose traversal type:");
        System.out.println("1. BFS (Breadth-First Search)");
        System.out.println("2. DFS (Depth-First Search)");
        System.out.print("Enter choice (1-2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                Global.campusGraph.bfsTraversal(startVertex);
                break;
            case 2:
                Global.campusGraph.dfsTraversal(startVertex);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public void displayGraph() {
        System.out.println("Choose display option:");
        System.out.println("1. Console Display");
        System.out.println("2. JavaFX Visual Display");
        System.out.print("Enter choice (1-2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                Global.campusGraph.displayGraph();
                break;
            case 2:
                GraphVisualizer.showGraph();
                break;
            default:
                System.out.println("Invalid choice! Showing console display...");
                Global.campusGraph.displayGraph();
        }
    }
}
