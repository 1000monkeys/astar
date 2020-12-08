package com.kjellvos.rea;

import com.kjellvos.rea.astar.Graph;
import com.kjellvos.rea.astar.Manhattan;
import com.kjellvos.rea.astar.Node;
import com.kjellvos.rea.astar.RouteFinder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        long setupNanoTime = System.nanoTime();
        Set<Node> nodes = new HashSet<>();
        Map<Integer, Set<Integer>> connections = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Rectangle rect = new Rectangle(10, 10);
                boolean traversable = !(new Random().nextInt(7) == 5);

                if (i == 99 && j == 99) {
                    traversable = true;
                }else if (i == 0 && j == 99) {
                    traversable = true;
                }else if (i == 0 && j == 0) {
                    traversable = true;
                }

                if (traversable) {
                    rect.setFill(Color.BLACK);
                }else{
                    rect.setFill(Color.RED);
                }

                Node node = new Node(i * 100 + j, i, j, rect, traversable);
                nodes.add(node);
                gridPane.add(rect, i, j);
            }
        }

        List<Node> tempNodes = new ArrayList(nodes);
        for (Node node : nodes) {
            List<Integer> tempConnections = new ArrayList<Integer>();

            if (0 < node.getX() - 1 && tempNodes.get((node.getX() - 1) * 100 + node.getY()).isTraversable()) {
                tempConnections.add((node.getX() - 1) * 100 + node.getY());
            }
            if (100 > node.getX() + 1 && tempNodes.get((node.getX() + 1) * 100 + node.getY()).isTraversable()) {
                tempConnections.add((node.getX() + 1) * 100 + node.getY());
            }
            if (0 < node.getY() - 1 && tempNodes.get(node.getX() * 100 + node.getY() - 1).isTraversable()) {
                tempConnections.add(node.getX() * 100 + node.getY() - 1);
            }
            if (100 > node.getY() + 1 && tempNodes.get(node.getX() * 100 + node.getY() + 1).isTraversable()) {
                tempConnections.add(node.getX() * 100 + node.getY() + 1);
            }

            if (0 < node.getX() - 1 && node.getY() - 1 > 0 && tempNodes.get((node.getX() - 1) * 100 + node.getY() - 1).isTraversable()) {
                tempConnections.add((node.getX() - 1) * 100 + node.getY() - 1);
            }
            if (100 > node.getX() + 1 && node.getY() + 1 < 100 && tempNodes.get((node.getX() + 1) * 100 + node.getY() + 1).isTraversable()) {
                tempConnections.add((node.getX() + 1) * 100 + node.getY() + 1);
            }
            if (0 < node.getX() - 1 && node.getY() + 1 < 100 && tempNodes.get((node.getX() - 1) * 100 + node.getY() + 1).isTraversable()) {
                tempConnections.add((node.getX() - 1) * 100 + node.getY() + 1);
            }
            if (100 > node.getX() + 1 && node.getY() - 1 > 0 && tempNodes.get((node.getX() + 1) * 100 + node.getY() - 1).isTraversable()) {
                tempConnections.add((node.getX() + 1) * 100 + node.getY() - 1);
            }

            connections.put(node.getX() * 100 + node.getY(), new HashSet<>(tempConnections));
        }

        Graph map = new Graph(nodes, connections);
        RouteFinder routeFinder = new RouteFinder<>(map, new Manhattan(), new Manhattan());

        long tempNanoTime = System.nanoTime();
        System.out.println("Setup time: " + (tempNanoTime - setupNanoTime) / 1_000_000);

        List<Node> route = routeFinder.findRoute(map.getNode(0), map.getNode( 99 * 100 + 1));
        System.out.println("Route finding time: " + (System.nanoTime() - tempNanoTime) / 1_000_000);

        for (Node node : route){
            node.getRect().setFill(Color.WHITE);
        }

    }
}
