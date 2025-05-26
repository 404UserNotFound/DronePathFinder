package org.example.utils;
import java.util.Scanner;

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
}
