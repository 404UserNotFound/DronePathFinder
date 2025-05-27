package org.example.utils;

import org.example.grid.Grid;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.example.constants.TestConstants.TEST_GRID_FILE;
import static org.example.constants.TestConstants.TEST_OUTPUT_PATH_LIST;
import static org.junit.jupiter.api.Assertions.*;

class PathUtilsTest {

    @Test
    void testFormatPath() {

        String formattedPath = PathUtils.formatPath(TEST_OUTPUT_PATH_LIST);

        assertTrue(formattedPath.contains("(0, 0) ->"));
        assertTrue(formattedPath.contains("(1, 1) ->"));
        assertTrue(formattedPath.contains("(2, 2)"));
    }

    @Test
    void testCalculateTotalScore() throws IOException {
        Grid grid = new Grid(TEST_GRID_FILE, 1);

        int totalScore = PathUtils.calculateTotalScore(TEST_OUTPUT_PATH_LIST, grid);
        assertEquals(15, totalScore);
    }
}