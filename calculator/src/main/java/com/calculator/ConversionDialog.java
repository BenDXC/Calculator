package com.calculator;

import javax.swing.JOptionPane;

/**
 * Handles advanced calculation dialogs and conversion operations.
 * Separated from UI to improve modularity and testability.
 */
public class ConversionDialog {

    /**
     * Shows dialog for power, root, and modulo calculations
     */
    public static void showComplexCalculations() {
        String input1 = InputValidator.getValidNumericInput("Please enter the first number:");
        if (input1 == null) return;
        double num1 = Double.parseDouble(input1);

        String input2 = InputValidator.getValidNumericInput(
            "Please enter second number (if root: 2 - square, 3 - cube):");
        if (input2 == null) return;
        double num2 = Double.parseDouble(input2);

        String operatorInput = JOptionPane.showInputDialog(
            "Please enter operator:\n" +
            "p - Power\n" +
            "r - Root\n" +
            "% - Modulo");
        if (operatorInput == null || operatorInput.isEmpty()) return;
        
        char operator = operatorInput.charAt(0);
        double result = 0.0;

        switch (operator) {
            case 'p':
            case 'P':
                result = CalculationUtils.calculatePower(num1, num2);
                break;
            case '%':
                result = CalculationUtils.calculateModulo(num1, num2);
                break;
            case 'r':
            case 'R':
                if (num2 == 2.0) {
                    result = CalculationUtils.calculateSquareRoot(num1);
                } else if (num2 == 3.0) {
                    result = CalculationUtils.calculateCubeRoot(num1);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "For root operation, please enter 2 (square root) or 3 (cube root)", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    showComplexCalculations();
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, 
                    "Invalid operator. Please enter 'p', 'r', or '%'", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                showComplexCalculations();
                return;
        }
        
        JOptionPane.showMessageDialog(null, "The result is: " + result);
    }

    /**
     * Shows dialog for temperature conversions
     */
    public static void showTemperatureConversions() {
        Integer choice = InputValidator.getValidIntegerInRange(
            "Temperature Conversion:\n1 - Fahrenheit to Celsius\n2 - Celsius to Fahrenheit", 
            1, 2);
        if (choice == null) return;

        if (choice == 1) {
            String input = InputValidator.getValidNumericInput("Enter temperature in Fahrenheit:");
            if (input == null) return;
            double fahrenheit = Double.parseDouble(input);
            double celsius = CalculationUtils.fahrenheitToCelsius(fahrenheit);
            JOptionPane.showMessageDialog(null, 
                String.format("%.2f°F = %.2f°C", fahrenheit, celsius));
        } else {
            String input = InputValidator.getValidNumericInput("Enter temperature in Celsius:");
            if (input == null) return;
            double celsius = Double.parseDouble(input);
            double fahrenheit = CalculationUtils.celsiusToFahrenheit(celsius);
            JOptionPane.showMessageDialog(null, 
                String.format("%.2f°C = %.2f°F", celsius, fahrenheit));
        }
    }

    /**
     * Shows dialog for metric and imperial unit conversions
     */
    public static void showMetricImperialConversions() {
        Integer choice = InputValidator.getValidIntegerInRange(
            "Unit Conversion:\n" +
            "1 - Inches to Centimeters\n" +
            "2 - Centimeters to Inches\n" +
            "3 - Ounces to Grams\n" +
            "4 - Grams to Ounces", 
            1, 4);
        if (choice == null) return;

        String input;
        double value, result;

        switch (choice) {
            case 1:
                input = InputValidator.getValidNumericInput("Enter length in Inches:");
                if (input == null) return;
                value = Double.parseDouble(input);
                result = CalculationUtils.inchesToCentimeters(value);
                JOptionPane.showMessageDialog(null, 
                    String.format("%.2f inches = %.2f cm", value, result));
                break;

            case 2:
                input = InputValidator.getValidNumericInput("Enter length in Centimeters:");
                if (input == null) return;
                value = Double.parseDouble(input);
                result = CalculationUtils.centimetersToInches(value);
                JOptionPane.showMessageDialog(null, 
                    String.format("%.2f cm = %.2f inches", value, result));
                break;

            case 3:
                input = InputValidator.getValidNumericInput("Enter mass in Ounces:");
                if (input == null) return;
                value = Double.parseDouble(input);
                result = CalculationUtils.ouncesToGrams(value);
                JOptionPane.showMessageDialog(null, 
                    String.format("%.2f oz = %.2f g", value, result));
                break;

            case 4:
                input = InputValidator.getValidNumericInput("Enter mass in Grams:");
                if (input == null) return;
                value = Double.parseDouble(input);
                result = CalculationUtils.gramsToOunces(value);
                JOptionPane.showMessageDialog(null, 
                    String.format("%.2f g = %.2f oz", value, result));
                break;
        }
    }

    /**
     * Shows dialog for decimal to binary conversion
     */
    public static void showDecimalToBinary() {
        String input = InputValidator.getValidNumericInput("Enter a decimal number:");
        if (input == null) return;
        
        try {
            int decimal = Integer.parseInt(input);
            if (decimal < 0) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a non-negative integer", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            String binary = CalculationUtils.decimalToBinary(decimal);
            JOptionPane.showMessageDialog(null, 
                String.format("Decimal %d = Binary %s", decimal, binary));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
                "Please enter a valid integer", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows dialog for binary to decimal conversion
     */
    public static void showBinaryToDecimal() {
        String input = InputValidator.getValidBinaryInput("Enter a binary number:");
        if (input == null) return;
        
        try {
            int decimal = CalculationUtils.binaryToDecimal(input);
            JOptionPane.showMessageDialog(null, 
                String.format("Binary %s = Decimal %d", input, decimal));
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, 
                "Invalid binary number: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows dialog for decimal to hexadecimal conversion
     */
    public static void showDecimalToHexadecimal() {
        String input = InputValidator.getValidNumericInput("Enter a decimal number:");
        if (input == null) return;
        
        try {
            int decimal = Integer.parseInt(input);
            if (decimal < 0) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a non-negative integer", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            String hex = CalculationUtils.decimalToHexadecimal(decimal);
            JOptionPane.showMessageDialog(null, 
                String.format("Decimal %d = Hexadecimal %s", decimal, hex));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
                "Please enter a valid integer", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows dialog for hexadecimal to decimal conversion
     */
    public static void showHexadecimalToDecimal() {
        String input = InputValidator.getValidHexInput("Enter a hexadecimal number:");
        if (input == null) return;
        
        try {
            int decimal = CalculationUtils.hexadecimalToDecimal(input);
            JOptionPane.showMessageDialog(null, 
                String.format("Hexadecimal %s = Decimal %d", input.toUpperCase(), decimal));
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, 
                "Invalid hexadecimal number: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
