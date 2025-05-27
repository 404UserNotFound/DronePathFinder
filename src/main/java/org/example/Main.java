package org.example;

import org.example.grid.Grid;
import org.example.pathfinding.PathFinderImpl;
import org.example.utils.UserInputUtils;
import org.example.utils.PathUtils;

import java.io.IOException;
import java.util.List;

import static org.example.constants.Constants.*;

public class Main {
    public static void main(String[] args) {
        try {
            Grid grid = initialiseGrid();
            int[] startCoordinates = getStartCoordinates(grid);
            int timeLimit = UserInputUtils.getValidatedInt("Enter max duration T (ms): \n", MIN_DURATION_MS, MAX_DURATION_MS);
            int timeSteps = UserInputUtils.getValidatedInt("Enter total number of time steps (t): \n", 1, Integer.MAX_VALUE);

            PathFinderImpl pathFinder = new PathFinderImpl();
            List<int[]> bestPath = pathFinder.findBestPath(grid, timeSteps, timeLimit, startCoordinates[0], startCoordinates[1]);

            PathUtils.printCollectedScoreAndPath(bestPath, grid);
        } catch (IOException e) {
            System.out.println("Error loading grid: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Your input was invalid: " + e.getMessage());
        }
    }

    private static Grid initialiseGrid() throws IOException {
        int gridSizeChoice = UserInputUtils.getValidatedInt(
                "Choose grid size:\n1. 20x20\n2. 100x100\n3. 1000x1000\n", 1, 3
        );
        String gridFile = UserInputUtils.getGridFile(gridSizeChoice);
        return new Grid(gridFile);
    }

    private static int[] getStartCoordinates(Grid grid) {
        int startX = UserInputUtils.getValidatedInt(
                "Enter start position x from 0 to " + (grid.getGridRowSize() - 1) + "\n", 0, grid.getGridRowSize() - 1
        );
        int startY = UserInputUtils.getValidatedInt(
                "Enter start position y from 0 to " + (grid.getGridColSize() - 1) + "\n", 0, grid.getGridColSize() - 1
        );
        return new int[]{startX, startY};
    }
}