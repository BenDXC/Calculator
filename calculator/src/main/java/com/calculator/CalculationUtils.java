package calculator.src.main.java.com.calculator;

/**
 * Utility class containing pure calculation logic separated from UI.
 * This allows for easier unit testing of calculation functionality.
 */
public class CalculationUtils {

    /**
     * Calculate power of a number
     * @param base The base number
     * @param exponent The exponent
     * @return base raised to the power of exponent
     */
    public static double calculatePower(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Calculate modulo of two numbers
     * @param num1 First number
     * @param num2 Second number
     * @return num1 modulo num2
     */
    public static double calculateModulo(double num1, double num2) {
        return num1 % num2;
    }

    /**
     * Calculate square root of a number
     * @param number The number to find square root of
     * @return square root of the number
     */
    public static double calculateSquareRoot(double number) {
        return Math.sqrt(number);
    }

    /**
     * Calculate cube root of a number
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
        return (fahrenheit - 32) * 0.5556;
    }

    /**
     * Convert Celsius to Fahrenheit
     * @param celsius Temperature in Celsius
     * @return Temperature in Fahrenheit
     */
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }

    /**
     * Convert inches to centimeters
     * @param inches Length in inches
     * @return Length in centimeters
     */
    public static double inchesToCentimeters(double inches) {
        return inches * 2.54;
    }

    /**
     * Convert centimeters to inches
     * @param centimeters Length in centimeters
     * @return Length in inches
     */
    public static double centimetersToInches(double centimeters) {
        return centimeters * 0.3937;
    }

    /**
     * Convert ounces to grams
     * @param ounces Mass in ounces
     * @return Mass in grams
     */
    public static double ouncesToGrams(double ounces) {
        return ounces * 28.35;
    }

    /**
     * Convert grams to ounces
     * @param grams Mass in grams
     * @return Mass in ounces
     */
    public static double gramsToOunces(double grams) {
        return grams * 0.035;
    }

    /**
     * Convert decimal to binary
     * @param decimal Decimal number
     * @return Binary representation as string
     */
    public static String decimalToBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        
        StringBuilder binary = new StringBuilder();
        int num = decimal;
        
        while (num != 0) {
            binary.insert(0, num % 2);
            num = num / 2;
        }
        
        return binary.toString();
    }

    /**
     * Convert binary to decimal
     * @param binary Binary number as string
     * @return Decimal representation
     * @throws IllegalArgumentException if input contains non-binary digits
     */
    public static int binaryToDecimal(String binary) {
        if (!binary.matches("[01]+")) {
            throw new IllegalArgumentException("Input must contain only 0s and 1s");
        }
        
        int decimal = 0;
        int power = 0;
        int binaryNum = Integer.parseInt(binary);
        
        while (binaryNum != 0) {
            int temp = binaryNum % 10;
            decimal += temp * Math.pow(2, power);
            binaryNum = binaryNum / 10;
            power++;
        }
        
        return decimal;
    }

    /**
     * Convert decimal to hexadecimal
     * @param decimal Decimal number
     * @return Hexadecimal representation as string
     */
    public static String decimalToHexadecimal(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        
        StringBuilder result = new StringBuilder();
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int number = decimal;
        
        while (number > 0) {
            int remainder = number % 16;
            result.insert(0, hex[remainder]);
            number = number / 16;
        }
        
        return result.toString();
    }

    /**
     * Convert hexadecimal to decimal
     * @param hex Hexadecimal string
     * @return Decimal representation
     * @throws IllegalArgumentException if input contains invalid hex characters
     */
    public static int hexadecimalToDecimal(String hex) {
        if (!hex.matches("[0-9A-Fa-f]+")) {
            throw new IllegalArgumentException("Input must contain only valid hexadecimal characters (0-9, A-F)");
        }
        
        String digits = "0123456789ABCDEF";
        String hexUpper = hex.toUpperCase();
        int val = 0;
        
        for (int i = 0; i < hexUpper.length(); i++) {
            char c = hexUpper.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        
        return val;
    }

    /**
     * Perform basic arithmetic operations
     * @param num1 First number
     * @param num2 Second number
     * @param operation Operation (+, -, *, /, %)
     * @return Result of the operation
     * @throws IllegalArgumentException if operation is invalid
     * @throws ArithmeticException if division by zero
     */
    public static double performOperation(double num1, double num2, String operation) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            case "%":
                return num1 % num2;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    /**
     * Validate if string contains only numeric characters
     * @param input String to validate
     * @return true if input is numeric, false otherwise
     */
    public static boolean isNumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Validate if string contains only binary digits
     * @param input String to validate
     * @return true if input is binary, false otherwise
     */
    public static boolean isBinary(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("[01]+");
    }

    /**
     * Validate if string contains only hexadecimal characters
     * @param input String to validate
     * @return true if input is hexadecimal, false otherwise
     */
    public static boolean isHexadecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("[0-9A-Fa-f]+");
    }
}
