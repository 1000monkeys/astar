package com.kjellvos.rea.astar;

import javafx.scene.shape.Rectangle;

public class Node implements GraphNode {
    private final int id;
    private final int x;
    private final int y;
    private Rectangle rect;
    private boolean traversable;

    public Node(int id, int x, int y, Rectangle rect, boolean traversable) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.rect = rect;
        this.traversable = traversable;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isTraversable() {
        return traversable;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
