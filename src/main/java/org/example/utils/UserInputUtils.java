package org.example.utils;
import java.util.Map;
import java.util.Scanner;

import static org.example.constants.Constants.GRID_FILE_20_LOCATION;
import static org.example.constants.Constants.GRID_FILE_100_LOCATION;
import static org.example.constants.Constants.GRID_FILE_1000_LOCATION;

public class UserInputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user for an integer input and validates it against a specified range.
     *
     * @param prompt The message to display to the user.
     * @param min The minimum acceptable value.
     * @param max The maximum acceptable value.
     * @return A valid integer input from the user within the specified range.
     */
    public static int getValidatedInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Value must be between " + min + " and " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    /**
     * Maps grid size choices to their corresponding file paths.
     */
    private static final Map<Integer, String> GRID_FILE_MAP = Map.of(
            1, GRID_FILE_20_LOCATION,
            2, GRID_FILE_100_LOCATION,
            3, GRID_FILE_1000_LOCATION
    );

    /**
     * Retrieves the grid file path based on the user's choice.
     * @param choice The user's choice for grid size.
     * @return The file path for the selected grid size, or null if the choice is invalid.
     */
    public static String getGridFile(int choice) {
        return GRID_FILE_MAP.getOrDefault(choice, null);
    }
}
