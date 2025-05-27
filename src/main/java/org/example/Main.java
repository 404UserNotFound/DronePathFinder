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
            Grid newGrid = new Grid(GRID_FILE_20_LOCATION, DEFAULT_INCREMENT_RATE);

            int gridRowSize = (newGrid.getGridRowSize() - 1);
            int gridColSize = (newGrid.getGridColSize() - 1);

            int startX = UserInputUtils.getValidatedInt("Enter start position x from 0 to " + gridRowSize + "\n", 0, gridRowSize);
            int startY = UserInputUtils.getValidatedInt("Enter start position y from 0 to " + gridColSize + "\n", 0, gridColSize);
            int timeLimit = UserInputUtils.getValidatedInt("Enter max duration T (ms): \n", MIN_DURATION_MS, MAX_DURATION_MS);
            int timeSteps = UserInputUtils.getValidatedInt("Enter total number of time steps (t): \n", 1, Integer.MAX_VALUE);

            PathFinderImpl pathFinder = new PathFinderImpl();
            List<int[]> bestPath = pathFinder.findBestPath(newGrid, timeSteps, timeLimit, startX, startY);
            PathUtils.printCollectedScoreAndPath(bestPath, newGrid);

        } catch (IOException e) {
            System.out.println("Error loading grid" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Your input was invalid: " + e);
        }
    }
}