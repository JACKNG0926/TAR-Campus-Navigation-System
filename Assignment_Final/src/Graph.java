import java.util.*;

/**
 * @ClassName Graph
 * @Description TODO
 * @Author hongs
 * @Date 2025/7/27 17:15
 */


public class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add vertex
    public void addVertex(String vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
            System.out.println("Vertex '" + vertex + "' added successfully!");
        } else {
            System.out.println("Vertex '" + vertex + "' already exists!");
        }
    }

    // Remove vertex
    public void removeVertex(String vertex) {
        if (adjacencyList.containsKey(vertex)) {
            // Remove all edges connected to this vertex
            for (List<String> neighbors : adjacencyList.values()) {
                neighbors.remove(vertex);
            }
            // Remove the vertex itself
            adjacencyList.remove(vertex);
            System.out.println("Vertex '" + vertex + "' removed successfully!");
        } else {
            System.out.println("Vertex '" + vertex + "' does not exist!");
        }
    }

    // Add edge
    public void addEdge(String vertex1, String vertex2) {
        if (!adjacencyList.containsKey(vertex1) || !adjacencyList.containsKey(vertex2)) {
            System.out.println("One or both vertices do not exist!");
            return;
        }

        // Add bidirectional edge (undirected graph)
        if (!adjacencyList.get(vertex1).contains(vertex2)) {
            adjacencyList.get(vertex1).add(vertex2);
            adjacencyList.get(vertex2).add(vertex1);
            System.out.println("Edge between '" + vertex1 + "' and '" + vertex2 + "' added successfully!");
        } else {
            System.out.println("Edge between '" + vertex1 + "' and '" + vertex2 + "' already exists!");
        }
    }

    // Remove edge
    public void removeEdge(String vertex1, String vertex2) {
        if (!adjacencyList.containsKey(vertex1) || !adjacencyList.containsKey(vertex2)) {
            System.out.println("One or both vertices do not exist!");
            return;
        }

        adjacencyList.get(vertex1).remove(vertex2);
        adjacencyList.get(vertex2).remove(vertex1);
        System.out.println("Edge between '" + vertex1 + "' and '" + vertex2 + "' removed successfully!");
    }

    // Display graph
    public void displayGraph() {
        System.out.println("\n=== TAR Campus Navigation Network ===");
        if (adjacencyList.isEmpty()) {
            System.out.println("Graph is empty!");
            return;
        }

        for (String vertex : adjacencyList.keySet()) {
            System.out.print(vertex + " -> ");
            List<String> neighbors = adjacencyList.get(vertex);
            if (neighbors.isEmpty()) {
                System.out.println("No connections");
            } else {
                System.out.println(String.join(", ", neighbors));
            }
        }
        System.out.println();
    }

    // BFS Traversal
    public void bfsTraversal(String startVertex) {
        if (!adjacencyList.containsKey(startVertex)) {
            System.out.println("Starting vertex '" + startVertex + "' does not exist!");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        List<String> traversalOrder = new ArrayList<>(); // Store the order of traversal

        queue.offer(startVertex);
        visited.add(startVertex);

        System.out.println("\nBFS Traversal starting from '" + startVertex + "':");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            traversalOrder.add(current);

            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println("Order of visited locations: \n" + String.join(" → ", traversalOrder));
    }

    // DFS Traversal
    public void dfsTraversal(String startVertex) {
        if (!adjacencyList.containsKey(startVertex)) {
            System.out.println("Starting vertex '" + startVertex + "' does not exist!");
            return;
        }

        Set<String> visited = new HashSet<>();
        List<String> traversalOrder = new ArrayList<>(); // Store the order of traversal

        System.out.println("\nDepth-First Search traversal starting from '" + startVertex + "':");
        dfsHelper(startVertex, visited, traversalOrder);

        // Display with arrows
        System.out.println("\nOrder of visited locations: " + String.join(" → ", traversalOrder));
    }

    private void dfsHelper(String vertex, Set<String> visited, List<String> traversalOrder) {
        visited.add(vertex);
        traversalOrder.add(vertex); // Add to traversal order

        for (String neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, traversalOrder);
            }
        }
    }

    // Get all vertices
    public Set<String> getAllVertices() {
        return adjacencyList.keySet();
    }

    // Get neighbors of a vertex
    public List<String> getNeighbors(String vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    // Check if vertex exists
    public boolean hasVertex(String vertex) {
        return adjacencyList.containsKey(vertex);
    }
}
