package org.example.utils;

import org.example.grid.Grid;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.example.constants.Constants.DEFAULT_INCREMENT_RATE;
import static org.junit.jupiter.api.Assertions.*;

class PathUtilsTest {

    @Test
    void testFormatPath() {
        List<int[]> path = List.of(
                new int[]{0, 0},
                new int[]{1, 1},
                new int[]{2, 2}
        );

        String formattedPath = PathUtils.formatPath(path);

        assertTrue(formattedPath.contains("(0, 0) ->"));
        assertTrue(formattedPath.contains("(1, 1) ->"));
        assertTrue(formattedPath.contains("(2, 2)"));
    }

    @Test
    void testCalculateTotalScore() throws IOException {
        Grid grid = new Grid("src/test/resources/sample_test_grid.txt", DEFAULT_INCREMENT_RATE);

        List<int[]> path = List.of(
                new int[]{0, 0},
                new int[]{1, 1},
                new int[]{2, 2}
        );

        int totalScore = PathUtils.calculateTotalScore(path, grid);
        assertEquals(15, totalScore);
    }
}