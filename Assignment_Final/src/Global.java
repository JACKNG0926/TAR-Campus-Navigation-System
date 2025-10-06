import java.util.ArrayList;

/**
 * @ClassName Global
 * @Description TODO
 * @Author hongs
 * @Date 2025/7/27 17:16
 */

public class Global {
    public static Graph campusGraph = new Graph();

    private Global() {}

    public static void initializeGraph() {
        // Initialize with some default campus locations
        campusGraph.addVertex("Block A");
        campusGraph.addVertex("Block B");
        campusGraph.addVertex("Block C");
        campusGraph.addVertex("DK ABA");
        campusGraph.addVertex("Library");
        campusGraph.addVertex("Swimming Pool");
        campusGraph.addVertex("Main Gate");

        // Add some initial connections
        campusGraph.addEdge("Main Gate", "Block A");
        campusGraph.addEdge("Block A", "Block B");
        campusGraph.addEdge("Block B", "Block C");
        campusGraph.addEdge("Block A", "Library");
        campusGraph.addEdge("Library", "DK ABA");
        campusGraph.addEdge("Block C", "Swimming Pool");

        System.out.println("Campus graph initialized with default locations and connections!");
    }
}
