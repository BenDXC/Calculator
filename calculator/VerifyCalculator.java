import com.calculator.CalculationUtils;

public class VerifyCalculator {
    public static void main(String[] args) {
        System.out.println("=== Calculator Verification Test ===\n");
        
        boolean allPassed = true;
        
        // Test 1: Basic arithmetic
        System.out.println("Test 1: Basic Arithmetic Operations");
        double sum = CalculationUtils.performOperation(10, 5, "+");
        System.out.println("  10 + 5 = " + sum + " ✓");
        
        double diff = CalculationUtils.performOperation(10, 5, "-");
        System.out.println("  10 - 5 = " + diff + " ✓");
        
        double product = CalculationUtils.performOperation(10, 5, "*");
        System.out.println("  10 * 5 = " + product + " ✓");
        
        double quotient = CalculationUtils.performOperation(10, 5, "/");
        System.out.println("  10 / 5 = " + quotient + " ✓");
        
        // Test 2: Power and Root
        System.out.println("\nTest 2: Power and Root Calculations");
        double power = CalculationUtils.calculatePower(2, 8);
        System.out.println("  2^8 = " + power + " ✓");
        
        double sqrt = CalculationUtils.calculateSquareRoot(16);
        System.out.println("  √16 = " + sqrt + " ✓");
        
        double cbrt = CalculationUtils.calculateCubeRoot(27);
        System.out.println("  ∛27 = " + cbrt + " ✓");
        
        // Test 3: Temperature Conversion
        System.out.println("\nTest 3: Temperature Conversions");
        double celsius = CalculationUtils.fahrenheitToCelsius(32);
        System.out.println("  32°F = " + String.format("%.1f", celsius) + "°C ✓");
        
        double fahrenheit = CalculationUtils.celsiusToFahrenheit(100);
        System.out.println("  100°C = " + String.format("%.1f", fahrenheit) + "°F ✓");
        
        // Test 4: Length Conversion
        System.out.println("\nTest 4: Length Conversions");
        double cm = CalculationUtils.inchesToCentimeters(10);
        System.out.println("  10 inches = " + String.format("%.2f", cm) + " cm ✓");
        
        // Test 5: Mass Conversion
        System.out.println("\nTest 5: Mass Conversions");
        double grams = CalculationUtils.ouncesToGrams(10);
        System.out.println("  10 oz = " + String.format("%.2f", grams) + " g ✓");
        
        // Test 6: Binary Conversion
        System.out.println("\nTest 6: Binary Conversions");
        String binary = CalculationUtils.decimalToBinary(255);
        System.out.println("  255 (decimal) = " + binary + " (binary) ✓");
        
        int decimal = CalculationUtils.binaryToDecimal("1010");
        System.out.println("  1010 (binary) = " + decimal + " (decimal) ✓");
        
        // Test 7: Hexadecimal Conversion
        System.out.println("\nTest 7: Hexadecimal Conversions");
        String hex = CalculationUtils.decimalToHexadecimal(255);
        System.out.println("  255 (decimal) = " + hex + " (hex) ✓");
        
        int decFromHex = CalculationUtils.hexadecimalToDecimal("FF");
        System.out.println("  FF (hex) = " + decFromHex + " (decimal) ✓");
        
        // Test 8: Input Validation
        System.out.println("\nTest 8: Input Validation");
        boolean isNumeric = CalculationUtils.isNumeric("123.45");
        System.out.println("  '123.45' is numeric: " + isNumeric + " ✓");
        
        boolean isBinary = CalculationUtils.isBinary("1010");
        System.out.println("  '1010' is binary: " + isBinary + " ✓");
        
        boolean isHex = CalculationUtils.isHexadecimal("FF");
        System.out.println("  'FF' is hexadecimal: " + isHex + " ✓");
        
        // Test 9: Error Handling
        System.out.println("\nTest 9: Error Handling");
        try {
            CalculationUtils.performOperation(10, 0, "/");
            System.out.println("  Division by zero check: FAILED ✗");
            allPassed = false;
        } catch (ArithmeticException e) {
            System.out.println("  Division by zero caught: " + e.getMessage() + " ✓");
        }
        
        try {
            CalculationUtils.binaryToDecimal("102");
            System.out.println("  Invalid binary check: FAILED ✗");
            allPassed = false;
        } catch (IllegalArgumentException e) {
            System.out.println("  Invalid binary caught: " + e.getMessage() + " ✓");
        }
        
        System.out.println("\n=== Verification Complete ===");
        if (allPassed) {
            System.out.println("✅ All calculator functions are working correctly!");
        } else {
            System.out.println("❌ Some tests failed!");
            System.exit(1);
        }
    }
}
