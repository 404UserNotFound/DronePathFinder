package org.example.pathfinding;

import org.example.grid.Grid;
import org.example.node.Node;
import org.example.utils.PathUtils;

import java.util.*;

/**
 * Implementation of the PathFinder interface using a priority queue.
 */
public class PathFinderImpl implements PathFinder {
    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };
    // Tuning constant: higher = less likely to revisit
    private static final int REVISIT_PENALTY_WEIGHT = 10;

    /**
     * Finds the best path in a given grid within specified time and step limits.
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
        if (grid.getGridRowSize() == 0 || grid.getGridColSize() == 0) {
            return new ArrayList<>();
        }

        long startTime = System.currentTimeMillis();

        int rows = grid.getGridRowSize();
        int cols = grid.getGridColSize();
        int[][] visitCount = new int[rows][cols];
        List<Node> visitedNodes = new ArrayList<>();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));
        Node startNode = new Node(startX, startY, grid.getGridCellScore(startX, startY), 0, null);
        startNode.fScore = 0;
        openSet.add(startNode);

        Node bestNode = startNode;

        int lastProcessedTime = 0;

        while (!openSet.isEmpty()) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= timeLimit) {
                System.out.println("Time limit reached!");
                break;
            }

            Node current = openSet.poll();
            if (current == null || current.getTime() >= timeSteps) {
                System.out.println("Step limit reached!");
                break;
            }

            int cx = current.getX();
            int cy = current.getY();
            visitCount[cx][cy]++;
            grid.visitCell(cx, cy);
            visitedNodes.add(current);

            // Triggers score recovery once per time step
            if (current.getTime() > lastProcessedTime) {
                grid.updateScores(visitedNodes);
                lastProcessedTime = current.getTime();
            }

            if (current.getScore() > bestNode.getScore()) {
                bestNode = current;
            }

            for (int[] dir : DIRECTIONS) {
                int newX = cx + dir[0];
                int newY = cy + dir[1];
                int newTime = current.getTime() + 1;

                if (!isValidCell(grid, newX, newY, newTime, timeSteps)) {
                    continue;
                }

                int rawScore = grid.getGridCellScore(newX, newY);
                int newScore = current.getScore() + rawScore;

                int penalty = visitCount[newX][newY] * REVISIT_PENALTY_WEIGHT;
                int fScore = -newScore + penalty;

                Node next = new Node(newX, newY, newScore, newTime, current);
                next.fScore = fScore;

                openSet.add(next);
            }
        }

        return PathUtils.reconstructPath(bestNode);
    }

    /**
     * Checks if the cell is valid for movement.
     *
     * @param grid   The grid to check against.
     * @param x      The X co-ordinate of the cell.
     * @param y      The Y co-ordinate of the cell.
     * @param time   The current time-step.
     * @param maxTime The maximum allowed time steps.
     * @return True if the cell is valid, otherwise false.
     */
    private boolean isValidCell(Grid grid, int x, int y, int time, int maxTime) {
        return x >= 0 && x < grid.getGridRowSize()
                && y >= 0 && y < grid.getGridColSize()
                && time <= maxTime;
    }
}