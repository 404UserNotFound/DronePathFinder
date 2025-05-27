package org.example.pathfinding;

import org.example.grid.Grid;
import org.example.node.Node;
import org.example.utils.PathUtils;

import java.util.*;

import static org.example.constants.Constants.REVISIT_PENALTY_WEIGHT;

public class PathFinderImpl implements PathFinder {
    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    /**
     * Entry point for finding the best scoring path within specified time and step limits.
     *
     * @param grid           The grid to search.
     * @param timeSteps      The maximum number of time-steps allowed.
     * @param timeLimit      The maximum time allowed in milliseconds.
     * @param startX         The starting X coordinate.
     * @param startY         The starting Y coordinate.
     * @return               A list of coordinates representing the best path found.
     */
    @Override
    public List<int[]> findBestPath(Grid grid, int timeSteps, int timeLimit, int startX, int startY) {
        if (grid.getGridRowSize() == 0 || grid.getGridColSize() == 0) return List.of();

        long startTime = System.currentTimeMillis();
        int[][] visitCount = new int[grid.getGridRowSize()][grid.getGridColSize()];
        List<Node> visitedNodes = new ArrayList<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));

        Node bestNode = initialiseSearch(grid, startX, startY, openSet);
        int lastProcessedTime = 0;

        while (!openSet.isEmpty()) {
            if (shouldTerminate(startTime, timeLimit)) break;

            Node current = openSet.poll();
            if (current == null || current.getTime() >= timeSteps) {
                System.out.println("Step limit reached!");
                break;
            }

            updateVisited(current, grid, visitCount, visitedNodes);

            if (current.getTime() > lastProcessedTime) {
                grid.updateScores(visitedNodes);
                lastProcessedTime = current.getTime();
            }

            if (current.getScore() > bestNode.getScore()) bestNode = current;

            expandNeighbours(current, grid, visitCount, timeSteps, openSet);
        }

        return PathUtils.reconstructPath(bestNode);
    }

    /**
     * Initialises the starting node and adds it to the open set.
     */
    private Node initialiseSearch(Grid grid, int startX, int startY, PriorityQueue<Node> openSet) {
        int initialScore = grid.getGridCellScore(startX, startY);
        Node startNode = new Node(startX, startY, initialScore, 0, null);
        startNode.fScore = 0;
        openSet.add(startNode);
        return startNode;
    }

    /**
     * Checks if the time limit has been reached.
     */
    private boolean shouldTerminate(long startTime, int timeLimit) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= timeLimit) {
            System.out.println("Time limit reached!");
            return true;
        }
        return false;
    }

    /**
     * Updates the visited node list and marks the cell as visited.
     */
    private void updateVisited(Node current, Grid grid, int[][] visitCount, List<Node> visitedNodes) {
        int x = current.getX();
        int y = current.getY();
        visitCount[x][y]++;
        grid.visitCell(x, y);
        visitedNodes.add(current);
    }

    /**
     * Expands all valid neighbouring nodes and adds them to the open set.
     */
    private void expandNeighbours(Node current, Grid grid, int[][] visitCount, int timeSteps, PriorityQueue<Node> openSet) {
        for (int[] dir : DIRECTIONS) {
            int newX = current.getX() + dir[0];
            int newY = current.getY() + dir[1];
            int newTime = current.getTime() + 1;

            if (!isValidCell(grid, newX, newY, newTime, timeSteps)) continue;

            int rawScore = grid.getGridCellScore(newX, newY);
            int newScore = current.getScore() + rawScore;
            int penalty = visitCount[newX][newY] * REVISIT_PENALTY_WEIGHT;
            int fScore = calculateFScore(newScore, penalty);

            Node next = new Node(newX, newY, newScore, newTime, current);
            next.fScore = fScore;

            openSet.add(next);
        }
    }

    /**
     * Computes the fScore based on score and revisit penalty.
     */
    private int calculateFScore(int score, int penalty) {
        return -score + penalty;
    }

    /**
     * Ensures the cell is within bounds and under the time-step limit.
     */
    private boolean isValidCell(Grid grid, int x, int y, int time, int maxTime) {
        return x >= 0 && x < grid.getGridRowSize()
                && y >= 0 && y < grid.getGridColSize()
                && time <= maxTime;
    }
}