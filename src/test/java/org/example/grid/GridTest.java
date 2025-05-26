package org.example.grid;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void shouldInitialiseGridCorrectly() throws IOException {
        Grid grid = new Grid("src/test/resources/sample_test_grid.txt", 1);

        assertEquals(3, grid.getGridRowSize());
        assertEquals(3, grid.getGridColSize());
        assertEquals(1, grid.getGridCellScore(0, 0));
        assertEquals(9, grid.getGridCellScore(2, 2));
    }

    @Test
    void shouldSetCellScoreToZeroWhenVisited() throws IOException {
        Grid grid = new Grid("src/test/resources/sample_test_grid.txt", 1);

        grid.visitCell(1, 1);
        assertEquals(0, grid.getGridCellScore(1, 1));
    }

    @Test
    void shouldUpdateScoresCorrectly() throws IOException {
        Grid grid = new Grid("src/test/resources/sample_test_grid.txt", 1);

        grid.visitCell(1, 1);
        grid.updateScores();
        assertEquals(1, grid.getGridCellScore(1, 1));
    }
}