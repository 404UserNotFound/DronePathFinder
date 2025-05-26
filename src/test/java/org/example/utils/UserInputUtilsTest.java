package org.example.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputUtilsTest {
    @Test
    void testGetValidatedInt_OutOfRangeThenValidInput() {
        String simulatedInput = "0\n11\n3\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            int result = UserInputUtils.getValidatedInt("Enter a number: ", 1, 10);
            assertEquals(3, result);
        } finally {
            System.setIn(originalIn);
        }
    }
}