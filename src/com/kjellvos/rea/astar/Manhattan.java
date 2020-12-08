package com.kjellvos.rea.astar;

public class Manhattan implements Scorer<Node> {
    @Override
    public double computeCost(Node from, Node to) {
        int dx = Math.abs(from.getX() - to.getX());
        if (from.getX() != to.getX()){
            dx -= 1;
        }

        int dy = Math.abs(from.getY() - to .getY());
        if (from.getY() != to.getY()){
            dy -= 1;
        }

        return 1 * (dx + dy);
    }
}
