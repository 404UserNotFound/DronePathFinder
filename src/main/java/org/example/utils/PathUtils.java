package org.example.utils;

import org.example.grid.Grid;
import org.example.node.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathUtils {
    /**
     * Reconstructs the path from the target node back to the start node.
     *
     * @param targetNode The node at which the path ends.
     * @return A list of coordinates representing the path from start to target.
     */
    public static List<int[]> reconstructPath(Node targetNode) {
        List<int[]> path = new ArrayList<>();
        Node current = targetNode;

        while (current != null) {
            path.add(new int[]{current.getX(), current.getY()});
            current = current.getParent();
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Calculates the total score collected along the path.
     *
     * @param path The list of coordinates representing the path.
     * @param grid The grid containing the scores for each cell.
     * @return The total score collected along the path.
     */
    public static int calculateTotalScore(List<int[]> path, Grid grid) {
        int totalScore = 0;
        for (int[] coordinate : path) {
            int x = coordinate[0];
            int y = coordinate[1];
            totalScore += grid.getGridCellScore(x, y);
        }
        return totalScore;
    }

    /**
     * Prints the collected score and the path taken.
     *
     * @param path The list of coordinates representing the path.
     * @param grid The grid containing the scores for each cell.
     */
    public static void printCollectedScoreAndPath(List<int[]> path, Grid grid) {
        System.out.println("Best path found: \n(x, y, score)");
        for (int[] coordinate : path) {
            int x = coordinate[0];
            int y = coordinate[1];
            int cellScore = grid.getGridCellScore(x, y);
            System.out.printf("(%d, %d) [%d]%n", x, y, cellScore);
        }
        int score = calculateTotalScore(path, grid);
        System.out.println("Total Score Collected: " + score);
    }
}