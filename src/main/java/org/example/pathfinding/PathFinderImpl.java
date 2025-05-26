package org.example.pathfinding;

import org.example.grid.Grid;
import org.example.node.Node;
import org.example.utils.PathUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of the PathFinder interface using a priority queue.
 */
public class PathFinderImpl implements PathFinder {
    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    /**
     * Finds the best path in the grid using a priority queue.
     *
     * @param grid   The grid to search in.
     * @param t      Total number of time-steps.
     * @param T      The total time limit for the search.
     * @param startX The starting X coordinate.
     * @param startY The starting Y coordinate.
     * @return A list of coordinates representing the best path found.
     */
    @Override
    public List<int[]> findBestPath(Grid grid, int t, int T, int startX, int startY) {
        if (grid.getGridRowSize() == 0 || grid.getGridColSize() == 0) {
            return new ArrayList<>();
        }

        long startTime = System.currentTimeMillis();
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));

        Node startNode = new Node(startX, startY, grid.getGridCellScore(startX, startY), 0, null);
        startNode.fScore = -startNode.getScore();
        openSet.add(startNode);

        Node bestNode = startNode;

        while (!openSet.isEmpty() && System.currentTimeMillis() - startTime < T) {
            Node current = openSet.poll();
            if (current == null || current.getTime() >= t) continue;

            if (current.getScore() > bestNode.getScore()) {
                bestNode = current;
            }

            grid.visitCell(current.getX(), current.getY());

            for (int[] dir : DIRECTIONS) {
                int newX = current.getX() + dir[0];
                int newY = current.getY() + dir[1];
                int newTime = current.getTime() + 1;

                if (isValidCell(grid, newX, newY, newTime, t)) {
                    int newScore = current.getScore() + grid.getGridCellScore(newX, newY);
                    Node next = new Node(newX, newY, newScore, newTime, current);
                    next.fScore = -newScore;
                    openSet.add(next);
                }
            }

            grid.updateScores();
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
        return x >= 0 && x < grid.getGridRowSize() && y >= 0 && y < grid.getGridColSize() && time <= maxTime;
    }
}