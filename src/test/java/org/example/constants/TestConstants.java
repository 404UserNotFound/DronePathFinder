package org.example.constants;

import org.example.node.Node;

import java.util.List;

public final class TestConstants {
    public static final String TEST_GRID_FILE = "src/test/resources/sample_test_grid.txt";

    public static final List<Node> TEST_VISITED_NODES_LIST = List.of(
            new Node(0, 0, 1, 0, null),
            new Node(1, 1, 2, 0, null),
            new Node(2, 2, 3, 0, null)
    );

    public static final List<int[]> TEST_OUTPUT_PATH_LIST = List.of(
            new int[]{0, 0},
            new int[]{1, 1},
            new int[]{2, 2}
    );

}
