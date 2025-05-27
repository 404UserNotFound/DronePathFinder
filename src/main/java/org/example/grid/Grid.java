package org.example.grid;

import org.example.node.Node;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents a grid for pathfinding with scores and increments.
 */
public class Grid {
    private final int[][] grid;
    private final int[][] originalScore;
    private final int[][] increment;

    /**
     * Constructs a Grid object from a file and initialises scores and increments.
     *
     * @param sourceFile   Path to the grid file.
     * @throws IOException If the file cannot be read or is empty.
     */
    public Grid(String sourceFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(sourceFile));

        if (lines.isEmpty()) {
            throw new IOException("Grid file is empty.");
        }

        int rows = lines.size();
        int cols = lines.get(0).split("\\s+").length;

        this.grid = new int[rows][cols];
        this.originalScore = new int[rows][cols];
        this.increment = new int[rows][cols];

        int defaultIncrementRate = 1;
        for (int i = 0; i < rows; i++) {
            String[] values = lines.get(i).split("\\s+");
            for (int j = 0; j < cols; j++) {
                int cellValue = Integer.parseInt(values[j]);
                grid[i][j] = cellValue;
                originalScore[i][j] = cellValue;
                increment[i][j] = defaultIncrementRate;
            }
        }
    }

    /**
     * Returns the score of a specific cell.
     *
     * @param x Row index.
     * @param y Column index.
     * @return The score of the cell.
     * @throws IllegalArgumentException If the coordinates are out of bounds.
     */
    public int getGridCellScore(int x, int y) {
        validateCoordinates(x, y);
        return grid[x][y];
    }

    /**
     * Returns the number of rows in the grid.
     *
     * @return Number of rows.
     */
    public int getGridRowSize() {
        return grid.length;
    }

    /**
     * Returns the number of columns in the grid.
     *
     * @return Number of columns.
     */
    public int getGridColSize() {
        return grid[0].length;
    }

    /**
     * Marks a cell as visited by setting its score to 0.
     *
     * @param x Row index.
     * @param y Column index.
     * @throws IllegalArgumentException If the coordinates are out of bounds.
     */
    public void visitCell(int x, int y) {
        validateCoordinates(x, y);
        grid[x][y] = 0;
    }

    /**
     * Updates the scores of all cells based on their original scores and increment rates.
     */
    public void updateScores(List<Node> visitedNodes) {
        for (Node node : visitedNodes) {
            int x = node.getX();
            int y = node.getY();
            if (grid[x][y] < originalScore[x][y]) {
                grid[x][y] = Math.min(originalScore[x][y], grid[x][y] + increment[x][y]);
            }
        }
    }

    /**
     * Validates that the given coordinates are within the grid bounds.
     *
     * @param x Row index.
     * @param y Column index.
     * @throws IllegalArgumentException If the coordinates are out of bounds.
     */
    private void validateCoordinates(int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            throw new IllegalArgumentException(String.format("Invalid cell coordinates: (%d, %d).", x, y));
        }
    }
}