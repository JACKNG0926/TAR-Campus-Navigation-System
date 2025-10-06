import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class GraphVisualizer extends Application {

    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 600;
    private static final double NODE_RADIUS = 30;
    private static boolean isJavaFXInitialized = false;
    private static CountDownLatch initializationLatch = new CountDownLatch(1);
    private static Stage primaryStage;

    public static void showGraph() {
        if (!isJavaFXInitialized) {
            // Initialize JavaFX only once
            initializeJavaFX();
        }

        // Wait for JavaFX to be initialized
        try {
            initializationLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        // Create and show the visualization
        Platform.runLater(() -> {
            try {
                createAndShowVisualization();
            } catch (Exception e) {
                System.err.println("Error creating visualization: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private static void initializeJavaFX() {
        Thread javaFXThread = new Thread(() -> {
            try {
                Application.launch(GraphVisualizer.class);
            } catch (IllegalStateException e) {
                System.out.println("JavaFX runtime already initialized");
                initializationLatch.countDown();
            }
        });
        javaFXThread.setDaemon(true);
        javaFXThread.start();
        isJavaFXInitialized = true;
    }

    private static void createAndShowVisualization() {
        // Create a new stage for each visualization
        Stage stage = new Stage();

        Pane root = new Pane();
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Title
        Label title = new Label("TAR Campus Navigation Network");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.DARKBLUE);
        title.setLayoutX(SCENE_WIDTH / 2 - 150);
        title.setLayoutY(20);
        root.getChildren().add(title);

        // Get vertices and create positions
        Set<String> vertices = Global.campusGraph.getAllVertices();

        if (vertices.isEmpty()) {
            Label emptyLabel = new Label("Graph is empty! Please add vertices first.");
            emptyLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            emptyLabel.setLayoutX(SCENE_WIDTH / 2 - 150);
            emptyLabel.setLayoutY(SCENE_HEIGHT / 2);
            emptyLabel.setTextFill(Color.RED);
            root.getChildren().add(emptyLabel);
        } else {
            Map<String, double[]> positions = calculatePositions(vertices);
            // Draw edges first (so they appear behind nodes)
            drawEdges(root, positions);
            // Draw vertices
            drawVertices(root, positions);

            // Add vertex count info
            Label infoLabel = new Label("Vertices: " + vertices.size());
            infoLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            infoLabel.setLayoutX(20);
            infoLabel.setLayoutY(SCENE_HEIGHT - 30);
            infoLabel.setTextFill(Color.DARKGRAY);
            root.getChildren().add(infoLabel);
        }

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("TAR Campus Navigation - Graph Visualization");
        stage.setScene(scene);

        // Position the window (optional - you can remove this if you don't want positioning)
        stage.setX(100);
        stage.setY(100);

        // Show the stage
        stage.show();

        // Bring window to front
        stage.toFront();
        stage.requestFocus();

        System.out.println("Graph visualization displayed successfully!");
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        // Don't show the primary stage, just use it to keep JavaFX running
        initializationLatch.countDown();

        // Keep the JavaFX application running but hide the primary stage
        Platform.setImplicitExit(false);  //This prevents JavaFX from shutting down when all stages are closed.
    }

    private static Map<String, double[]> calculatePositions(Set<String> vertices) {
        Map<String, double[]> positions = new HashMap<>();
        List<String> vertexList = new ArrayList<>(vertices);

        if (vertexList.isEmpty()) {
            return positions;
        }

        // Calculate positions in a circular layout
        double centerX = SCENE_WIDTH / 2;
        double centerY = SCENE_HEIGHT / 2 + 20; // Offset for title
        double radius = Math.min(SCENE_WIDTH, SCENE_HEIGHT) / 3.5;

        if (vertexList.size() == 1) {
            positions.put(vertexList.get(0), new double[]{centerX, centerY});
        } else {
            for (int i = 0; i < vertexList.size(); i++) {
                double angle = 2 * Math.PI * i / vertexList.size() - Math.PI / 2; // Start from top
                double x = centerX + radius * Math.cos(angle);
                double y = centerY + radius * Math.sin(angle);
                positions.put(vertexList.get(i), new double[]{x, y});
            }
        }

        return positions;
    }

    private static void drawEdges(Pane root, Map<String, double[]> positions) {
        Set<String> drawnEdges = new HashSet<>();

        for (String vertex : Global.campusGraph.getAllVertices()) {
            double[] pos1 = positions.get(vertex);
            if (pos1 == null) continue;

            for (String neighbor : Global.campusGraph.getNeighbors(vertex)) {
                double[] pos2 = positions.get(neighbor);
                if (pos2 == null) continue;

                // Avoid drawing the same edge twice
                String edgeKey = vertex.compareTo(neighbor) < 0 ?
                        vertex + "-" + neighbor : neighbor + "-" + vertex;

                if (!drawnEdges.contains(edgeKey)) {
                    Line line = new Line(pos1[0], pos1[1], pos2[0], pos2[1]);
                    line.setStroke(Color.DARKBLUE);
                    line.setStrokeWidth(2);
                    // Add some transparency
                    line.setOpacity(0.7);
                    root.getChildren().add(line);
                    drawnEdges.add(edgeKey);
                }
            }
        }
    }

    private static void drawVertices(Pane root, Map<String, double[]> positions) {
        for (Map.Entry<String, double[]> entry : positions.entrySet()) {
            String vertex = entry.getKey();
            double[] pos = entry.getValue();

            // Create circle for vertex
            Circle circle = new Circle(pos[0], pos[1], NODE_RADIUS);
            circle.setFill(Color.LIGHTBLUE);
            circle.setStroke(Color.DARKBLUE);
            circle.setStrokeWidth(3);

            // Create label for vertex
            Label label = new Label(vertex);
            label.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            // Better centering for the label
            double labelWidth = vertex.length() * 4; // Approximate width
            label.setLayoutX(pos[0] - labelWidth / 2);
            label.setLayoutY(pos[1] - 5);
            label.setTextFill(Color.DARKBLUE);

            root.getChildren().addAll(circle, label);
        }
    }
}