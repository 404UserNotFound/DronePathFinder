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
     * Formats the path into a readable string.
     *
     * @param path The list of coordinates representing the path.
     * @return A formatted string representation of the path.
     */
    public static String formatPath(List<int[]> path) {
        if (path.isEmpty()) {
            return "No path found.";
        }

        System.out.println("Best path found: ");
        StringBuilder pathOutput = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            int[] step = path.get(i);
            pathOutput.append("(").append(step[0]).append(", ").append(step[1]).append(")");
            if (i < path.size() - 1) {
                pathOutput.append(" -> \n");
            }
        }
        return pathOutput.toString();
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
        for (int[] step : path) {
            totalScore += grid.getGridCellScore(step[0], step[1]);
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
        System.out.println(formatPath(path));
        int score = calculateTotalScore(path, grid);
        System.out.println("Total Score Collected: " + score);
    }
}