package org.example.pathfinding;

import org.example.grid.Grid;

import java.util.List;

public interface PathFinder {
    List<int[]> findBestPath(Grid grid, int t, int T, int startX, int startY);
}
