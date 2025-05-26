package org.example.pathfinding;

import org.example.grid.Grid;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PathFinderImplTest {

    @Test
    void testFindBestPath_ValidGrid() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.getGridRowSize()).thenReturn(3);
        when(mockGrid.getGridColSize()).thenReturn(3);
        when(mockGrid.getGridCellScore(anyInt(), anyInt())).thenReturn(1);

        PathFinderImpl pathFinder = new PathFinderImpl();
        List<int[]> path = pathFinder.findBestPath(mockGrid, 5, 1000, 0, 0);

        assertNotNull(path);
        assertFalse(path.isEmpty());
    }

    @Test
    void testFindBestPath_EmptyGrid() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.getGridRowSize()).thenReturn(0);

        List<int[]> path = new PathFinderImpl().findBestPath(mockGrid, 5, 1000, 0, 0);

        assertTrue(path.isEmpty());
    }
}