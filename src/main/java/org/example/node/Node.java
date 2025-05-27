package org.example.node;

public class Node {
    private final int x;
    private final int y;
    private final int score;
    private final int time;
    public int fScore;
    private final Node parent;

    public Node(int x, int y, int score, int time, Node parent) {
        this.x = x;
        this.y = y;
        this.score = score;
        this.time = time;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }

    public Node getParent() {
        return parent;
    }
}