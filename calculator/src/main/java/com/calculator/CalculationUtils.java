package com.calculator;

import java.util.regex.Pattern;

/**
 * Utility class containing pure calculation logic separated from UI.
 * This allows for easier unit testing of calculation functionality.
 * 
 * <p>This class is thread-safe as all methods are stateless.</p>
 * <p>This class cannot be instantiated as it contains only static utility methods.</p>
 * 
 * @since 1.0
 */
public final class CalculationUtils {

    // Conversion constants
    private static final double FAHRENHEIT_OFFSET = 32.0;
    private static final double FAHRENHEIT_RATIO = 5.0 / 9.0;
    private static final double CELSIUS_TO_FAHRENHEIT_RATIO = 9.0 / 5.0;
    private static final double INCHES_TO_CM = 2.54;
    private static final double CM_TO_INCHES = 1.0 / INCHES_TO_CM;
    private static final double OUNCES_TO_GRAMS = 28.35;
    private static final double GRAMS_TO_OUNCES = 1.0 / OUNCES_TO_GRAMS;
    private static final int BINARY_BASE = 2;
    private static final int HEX_BASE = 16;
    
    // Floating-point comparison threshold
    private static final double EPSILON = 1e-10;
    
    // Compiled regex patterns for performance (avoid recompiling on every call)
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static final Pattern BINARY_PATTERN = Pattern.compile("[01]+");
    private static final Pattern HEX_PATTERN = Pattern.compile("[0-9A-Fa-f]+");
    
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private CalculationUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Calculate power of a number.
     * 
     * @param base The base number
     * @param exponent The exponent
     * @return base raised to the power of exponent
     * @throws ArithmeticException if result overflows or is undefined (e.g., 0^negative)
     */
    public static double calculatePower(double base, double exponent) {
        double result = Math.pow(base, exponent);
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new ArithmeticException("Power calculation resulted in undefined or infinite value");
        }
        return result;
    }

    /**
     * Calculate modulo of two numbers.
     * 
     * @param num1 First number
     * @param num2 Second number (divisor)
     * @return num1 modulo num2
     * @throws ArithmeticException if num2 is zero
     */
    public static double calculateModulo(double num1, double num2) {
        if (Math.abs(num2) < EPSILON) {
            throw new ArithmeticException("Modulo by zero");
        }
        return num1 % num2;
    }

    /**
     * Calculate square root of a number.
     * 
     * @param number The number to find square root of
     * @return square root of the number
     * @throws IllegalArgumentException if number is negative
     */
    public static double calculateSquareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number: " + number);
        }
        return Math.sqrt(number);
    }

    /**
     * Calculate cube root of a number.
     * 
     * @param number The number to find cube root of
     * @return cube root of the number
     */
    public static double calculateCubeRoot(double number) {
        return Math.cbrt(number);
    }

    /**
     * Convert Fahrenheit to Celsius
     * @param fahrenheit Temperature in Fahrenheit
     * @return Temperature in Celsius
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - FAHRENHEIT_OFFSET) * FAHRENHEIT_RATIO;
    }

    /**
     * Convert Celsius to Fahrenheit
     * @param celsius Temperature in Celsius
     * @return Temperature in Fahrenheit
     */
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * CELSIUS_TO_FAHRENHEIT_RATIO) + FAHRENHEIT_OFFSET;
    }

    /**
     * Convert inches to centimeters
     * @param inches Length in inches
     * @return Length in centimeters
     */
    public static double inchesToCentimeters(double inches) {
        return inches * INCHES_TO_CM;
    }

    /**
     * Convert centimeters to inches
     * @param centimeters Length in centimeters
     * @return Length in inches
     */
    public static double centimetersToInches(double centimeters) {
        return centimeters * CM_TO_INCHES;
    }

    /**
     * Convert ounces to grams
     * @param ounces Mass in ounces
     * @return Mass in grams
     */
    public static double ouncesToGrams(double ounces) {
        return ounces * OUNCES_TO_GRAMS;
    }

    /**
     * Convert grams to ounces
     * @param grams Mass in grams
     * @return Mass in ounces
     */
    public static double gramsToOunces(double grams) {
        return grams * GRAMS_TO_OUNCES;
    }

    /**
     * Convert decimal to binary.
     * 
     * <p>Performance: O(log n) using built-in Integer.toBinaryString()
     * instead of O(n²) with StringBuilder.insert(0, ...)</p>
     * 
     * @param decimal Decimal number (non-negative)
     * @return Binary representation as string
     * @throws IllegalArgumentException if decimal is negative
     */
    public static String decimalToBinary(int decimal) {
        if (decimal < 0) {
            throw new IllegalArgumentException("Cannot convert negative number to binary: " + decimal);
        }
        return Integer.toBinaryString(decimal);
    }

    /**
     * Convert binary to decimal.
     * 
     * <p>Performance: O(n) using built-in Integer.parseInt() with radix
     * instead of custom implementation with Math.pow() calls.</p>
     * 
     * @param binary Binary number as string (must contain only 0s and 1s)
     * @return Decimal representation
     * @throws IllegalArgumentException if input is null, empty, or contains non-binary digits
     * @throws NumberFormatException if binary string represents number too large for int
     */
    public static int binaryToDecimal(String binary) {
        if (binary == null || binary.isEmpty()) {
            throw new IllegalArgumentException("Binary string cannot be null or empty");
        }
        if (!BINARY_PATTERN.matcher(binary).matches()) {
            throw new IllegalArgumentException("Input must contain only 0s and 1s: " + binary);
        }
        try {
            return Integer.parseInt(binary, BINARY_BASE);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Binary string too large to convert to int: " + binary);
        }
    }

    /**
     * Convert decimal to hexadecimal.
     * 
     * <p>Performance: O(log n) using built-in Integer.toHexString()
     * instead of O(n²) with StringBuilder.insert(0, ...)</p>
     * 
     * @param decimal Decimal number (non-negative)
     * @return Hexadecimal representation as uppercase string
     * @throws IllegalArgumentException if decimal is negative
     */
    public static String decimalToHexadecimal(int decimal) {
        if (decimal < 0) {
            throw new IllegalArgumentException("Cannot convert negative number to hexadecimal: " + decimal);
        }
        return Integer.toHexString(decimal).toUpperCase();
    }

    /**
     * Convert hexadecimal to decimal.
     * 
     * <p>Performance: O(n) using built-in Integer.parseInt() with radix
     * instead of custom string manipulation.</p>
     * 
     * @param hex Hexadecimal string (may contain 0-9, A-F, case-insensitive)
     * @return Decimal representation
     * @throws IllegalArgumentException if input is null, empty, or contains invalid hex characters
     * @throws NumberFormatException if hex string represents number too large for int
     */
    public static int hexadecimalToDecimal(String hex) {
        if (hex == null || hex.isEmpty()) {
            throw new IllegalArgumentException("Hexadecimal string cannot be null or empty");
        }
        if (!HEX_PATTERN.matcher(hex).matches()) {
            throw new IllegalArgumentException("Input must contain only valid hexadecimal characters (0-9, A-F): " + hex);
        }
        try {
            return Integer.parseInt(hex, HEX_BASE);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Hexadecimal string too large to convert to int: " + hex);
        }
    }

    /**
     * Perform basic arithmetic operations.
     * 
     * @param num1 First operand
     * @param num2 Second operand
     * @param operation Operation symbol (+, -, *, /, %)
     * @return Result of the operation
     * @throws IllegalArgumentException if operation is null or invalid
     * @throws ArithmeticException if division or modulo by zero
     */
    public static double performOperation(double num1, double num2, String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (Math.abs(num2) < EPSILON) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            case "%":
                if (Math.abs(num2) < EPSILON) {
                    throw new ArithmeticException("Modulo by zero");
                }
                return num1 % num2;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    /**
     * Validate if string represents a valid numeric value.
     * 
     * <p>Performance: Uses pre-compiled Pattern to avoid regex compilation overhead.</p>
     * 
     * @param input String to validate
     * @return true if input is a valid number (integer or decimal, may be negative), false otherwise
     */
    public static boolean isNumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(input).matches();
    }

    /**
     * Validate if string contains only binary digits (0 and 1).
     * 
     * <p>Performance: Uses pre-compiled Pattern to avoid regex compilation overhead.</p>
     * 
     * @param input String to validate
     * @return true if input contains only 0s and 1s, false otherwise
     */
    public static boolean isBinary(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return BINARY_PATTERN.matcher(input).matches();
    }

    /**
     * Validate if string contains only hexadecimal characters (0-9, A-F, case-insensitive).
     * 
     * <p>Performance: Uses pre-compiled Pattern to avoid regex compilation overhead.</p>
     * 
     * @param input String to validate
     * @return true if input contains only valid hex characters, false otherwise
     */
    public static boolean isHexadecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return HEX_PATTERN.matcher(input).matches();
    }
}
