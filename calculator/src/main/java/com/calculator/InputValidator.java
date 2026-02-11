package com.calculator;

import javax.swing.JOptionPane;

/**
 * Utility class for validating user input in dialog boxes.
 * Consolidates validation logic to avoid code duplication.
 */
public class InputValidator {

    /**
     * Prompts user for numeric input and validates it.
     * Recursively re-prompts if input is invalid.
     * 
     * @param message The message to display in the input dialog
     * @return A valid numeric string
     */
    public static String getValidNumericInput(String message) {
        String input = JOptionPane.showInputDialog(message);
        if (input == null) {
            return null; // User cancelled
        }
        
        if (!CalculationUtils.isNumeric(input)) {
            JOptionPane.showMessageDialog(null, 
                "Please enter only numbers", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
            return getValidNumericInput(message);
        }
        return input;
    }

    /**
     * Prompts user for binary input and validates it.
     * 
     * @param message The message to display in the input dialog
     * @return A valid binary string
     */
    public static String getValidBinaryInput(String message) {
        String input = JOptionPane.showInputDialog(message);
        if (input == null) {
            return null;
        }
        
        if (!CalculationUtils.isBinary(input)) {
            JOptionPane.showMessageDialog(null, 
                "Please enter only 0 or 1", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
            return getValidBinaryInput(message);
        }
        return input;
    }

    /**
     * Prompts user for hexadecimal input and validates it.
     * 
     * @param message The message to display in the input dialog
     * @return A valid hexadecimal string
     */
    public static String getValidHexInput(String message) {
        String input = JOptionPane.showInputDialog(message);
        if (input == null) {
            return null;
        }
        
        if (!CalculationUtils.isHexadecimal(input)) {
            JOptionPane.showMessageDialog(null, 
                "Please enter valid hexadecimal characters (0-9, A-F)", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
            return getValidHexInput(message);
        }
        return input;
    }

    /**
     * Prompts user for integer input within a specific range.
     * 
     * @param message The message to display
     * @param min Minimum valid value
     * @param max Maximum valid value
     * @return A valid integer within the specified range
     */
    public static Integer getValidIntegerInRange(String message, int min, int max) {
        String input = getValidNumericInput(message);
        if (input == null) {
            return null;
        }
        
        try {
            int value = Integer.parseInt(input);
            if (value < min || value > max) {
                JOptionPane.showMessageDialog(null, 
                    String.format("Please enter a number between %d and %d", min, max), 
                    "Invalid Range", 
                    JOptionPane.ERROR_MESSAGE);
                return getValidIntegerInRange(message, min, max);
            }
            return value;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
                "Please enter a valid integer", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
            return getValidIntegerInRange(message, min, max);
        }
    }
}
