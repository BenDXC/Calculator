package com.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

/**
 * Integration tests for the Calculator application.
 * Tests the interaction between different components and workflows using CalculationUtils.
 */
@DisplayName("Calculator Integration Tests")
public class IntegrationTest {

    @Nested
    @DisplayName("Combined Operations and Conversions")
    class CombinedOperationsTests {

        @Test
        @DisplayName("Should combine arithmetic with temperature conversion")
        void testArithmeticWithTemperatureConversion() {
            // Calculate average temperature
            double temp1 = 32.0;  // Fahrenheit
            double temp2 = 212.0; // Fahrenheit
            double average = (temp1 + temp2) / 2;
            
            assertThat(average).isEqualTo(122.0);
            
            // Convert to Celsius
            double celsius = CalculationUtils.fahrenheitToCelsius(average);
            assertThat(celsius).isCloseTo(50.0, within(1.0));
        }

        @Test
        @DisplayName("Should combine arithmetic with mass conversion")
        void testArithmeticWithMassConversion() {
            // Calculate total ounces
            double ounces1 = 10.0;
            double ounces2 = 5.0;
            double totalOunces = ounces1 + ounces2;
            
            assertThat(totalOunces).isEqualTo(15.0);
            
            // Convert to grams
            double grams = CalculationUtils.ouncesToGrams(totalOunces);
            assertThat(grams).isCloseTo(425.25, within(0.1));
        }

        @Test
        @DisplayName("Should combine arithmetic with length conversion")
        void testArithmeticWithLengthConversion() {
            // Calculate total inches
            double length1 = 12.0;
            double length2 = 8.0;
            double totalInches = length1 + length2;
            
            assertThat(totalInches).isEqualTo(20.0);
            
            // Convert to centimeters
            double cm = CalculationUtils.inchesToCentimeters(totalInches);
            assertThat(cm).isCloseTo(50.8, within(0.1));
        }
    }

    @Nested
    @DisplayName("Number System Conversion Integration")
    class NumberSystemConversionTests {

        @Test
        @DisplayName("Should convert through multiple number systems")
        void testMultipleNumberSystemConversions() {
            int decimal = 255;
            
            // Decimal to Binary
            String binary = CalculationUtils.decimalToBinary(decimal);
            assertThat(binary).isEqualTo("11111111");
            
            // Decimal to Hexadecimal
            String hex = CalculationUtils.decimalToHexadecimal(decimal);
            assertThat(hex).isEqualTo("FF");
            
            // Back to decimal
            int fromBinary = CalculationUtils.binaryToDecimal(binary);
            int fromHex = CalculationUtils.hexadecimalToDecimal(hex);
            
            assertThat(fromBinary).isEqualTo(decimal);
            assertThat(fromHex).isEqualTo(decimal);
        }

        @Test
        @DisplayName("Should perform arithmetic and then convert number system")
        void testArithmeticThenConversion() {
            // Perform calculation
            double result = CalculationUtils.performOperation(10, 6, "+");
            assertThat(result).isEqualTo(16.0);
            
            // Convert to hex
            String hex = CalculationUtils.decimalToHexadecimal((int) result);
            assertThat(hex).isEqualTo("10");
            
            // Convert to binary
            String binary = CalculationUtils.decimalToBinary((int) result);
            assertThat(binary).isEqualTo("10000");
        }

        @ParameterizedTest
        @CsvSource({
            "10, 1010, A",
            "15, 1111, F",
            "255, 11111111, FF",
            "16, 10000, 10"
        })
        @DisplayName("Should verify consistency across number systems")
        void testNumberSystemConsistency(int decimal, String expectedBinary, String expectedHex) {
            String binary = CalculationUtils.decimalToBinary(decimal);
            String hex = CalculationUtils.decimalToHexadecimal(decimal);
            
            assertThat(binary).isEqualTo(expectedBinary);
            assertThat(hex).isEqualTo(expectedHex);
            
            // Verify reverse conversion
            assertThat(CalculationUtils.binaryToDecimal(binary)).isEqualTo(decimal);
            assertThat(CalculationUtils.hexadecimalToDecimal(hex)).isEqualTo(decimal);
        }
    }

    @Nested
    @DisplayName("Complex Scientific Calculations")
    class ScientificCalculationsTests {

        @Test
        @DisplayName("Should perform power and root operations in sequence")
        void testPowerAndRootSequence() {
            double number = 16.0;
            
            // Square the number: 16^2 = 256
            double squared = CalculationUtils.calculatePower(number, 2);
            assertThat(squared).isEqualTo(256.0);
            
            // Square root: √256 = 16
            double sqrt = CalculationUtils.calculateSquareRoot(squared);
            assertThat(sqrt).isEqualTo(number);
        }

        @Test
        @DisplayName("Should perform complex calculation with modulo")
        void testComplexModuloCalculation() {
            // Calculate: (100 + 50) % 7
            double sum = CalculationUtils.performOperation(100, 50, "+");
            double result = CalculationUtils.calculateModulo(sum, 7);
            
            assertThat(result).isEqualTo(3.0);
        }

        @Test
        @DisplayName("Should combine power, division, and square root")
        void testCombinedScientificOps() {
            // 2^8 = 256
            double power = CalculationUtils.calculatePower(2, 8);
            assertThat(power).isEqualTo(256.0);
            
            // 256 / 4 = 64
            double divided = CalculationUtils.performOperation(power, 4, "/");
            assertThat(divided).isEqualTo(64.0);
            
            // √64 = 8
            double sqrt = CalculationUtils.calculateSquareRoot(divided);
            assertThat(sqrt).isEqualTo(8.0);
        }
    }

    @Nested
    @DisplayName("Real-World Scenarios")
    class RealWorldScenariosTests {

        @Test
        @DisplayName("Should calculate recipe temperature conversion")
        void testRecipeTemperatureConversion() {
            // Recipe calls for 350°F, convert to Celsius
            double fahrenheit = 350.0;
            double celsius = CalculationUtils.fahrenheitToCelsius(fahrenheit);
            
            assertThat(celsius).isCloseTo(176.67, within(1.0));
        }

        @Test
        @DisplayName("Should calculate shipping weight conversion")
        void testShippingWeightConversion() {
            // Package weighs 5 lbs (80 oz), convert to grams
            double ounces = 80.0;
            double grams = CalculationUtils.ouncesToGrams(ounces);
            
            assertThat(grams).isCloseTo(2268.0, within(10.0));
        }

        @Test
        @DisplayName("Should calculate room dimensions conversion")
        void testRoomDimensionsConversion() {
            // Room is 120 inches wide, convert to centimeters
            double inches = 120.0;
            double cm = CalculationUtils.inchesToCentimeters(inches);
            
            assertThat(cm).isCloseTo(304.8, within(0.1));
        }

        @Test
        @DisplayName("Should calculate programming bit values")
        void testProgrammingBitValues() {
            // Calculate 2^8 - 1 for max 8-bit value
            double maxValue = CalculationUtils.calculatePower(2, 8) - 1;
            assertThat(maxValue).isEqualTo(255.0);
            
            // Convert to hex (should be FF)
            String hex = CalculationUtils.decimalToHexadecimal((int) maxValue);
            assertThat(hex).isEqualTo("FF");
            
            // Convert to binary (should be 11111111)
            String binary = CalculationUtils.decimalToBinary((int) maxValue);
            assertThat(binary).isEqualTo("11111111");
        }
    }

    @Nested
    @DisplayName("Error Recovery and Validation Integration")
    class ErrorRecoveryTests {

        @Test
        @DisplayName("Should validate input before calculation")
        void testInputValidationBeforeCalculation() {
            String validInput = "123.45";
            String invalidInput = "abc";
            
            assertThat(CalculationUtils.isNumeric(validInput)).isTrue();
            assertThat(CalculationUtils.isNumeric(invalidInput)).isFalse();
            
            // Only perform calculation with valid input
            if (CalculationUtils.isNumeric(validInput)) {
                double value = Double.parseDouble(validInput);
                double result = CalculationUtils.performOperation(value, 2, "*");
                assertThat(result).isCloseTo(246.9, within(0.1));
            }
        }

        @Test
        @DisplayName("Should validate binary input before conversion")
        void testBinaryValidationBeforeConversion() {
            String validBinary = "1010";
            String invalidBinary = "1012";
            
            assertThat(CalculationUtils.isBinary(validBinary)).isTrue();
            assertThat(CalculationUtils.isBinary(invalidBinary)).isFalse();
            
            // Only convert valid binary
            if (CalculationUtils.isBinary(validBinary)) {
                int decimal = CalculationUtils.binaryToDecimal(validBinary);
                assertThat(decimal).isEqualTo(10);
            }
        }

        @Test
        @DisplayName("Should validate hexadecimal input before conversion")
        void testHexValidationBeforeConversion() {
            String validHex = "FF";
            String invalidHex = "GG";
            
            assertThat(CalculationUtils.isHexadecimal(validHex)).isTrue();
            assertThat(CalculationUtils.isHexadecimal(invalidHex)).isFalse();
            
            // Only convert valid hex
            if (CalculationUtils.isHexadecimal(validHex)) {
                int decimal = CalculationUtils.hexadecimalToDecimal(validHex);
                assertThat(decimal).isEqualTo(255);
            }
        }

        @Test
        @DisplayName("Should handle division by zero gracefully")
        void testDivisionByZeroHandling() {
            assertThatThrownBy(() -> 
                CalculationUtils.performOperation(10, 0, "/")
            ).isInstanceOf(ArithmeticException.class)
             .hasMessageContaining("Division by zero");
        }
    }

    @Nested
    @DisplayName("Performance and Precision Tests")
    class PerformanceTests {

        @Test
        @DisplayName("Should handle large number calculations accurately")
        void testLargeNumberCalculations() {
            double large1 = 999999999.0;
            double large2 = 888888888.0;
            
            double sum = CalculationUtils.performOperation(large1, large2, "+");
            assertThat(sum).isEqualTo(1888888887.0);
            
            double product = CalculationUtils.performOperation(large1, 2, "*");
            assertThat(product).isEqualTo(1999999998.0);
        }

        @Test
        @DisplayName("Should maintain precision in conversion chains")
        void testConversionChainPrecision() {
            double originalCelsius = 100.0;
            
            // Celsius -> Fahrenheit -> Celsius
            double fahrenheit = CalculationUtils.celsiusToFahrenheit(originalCelsius);
            double backToCelsius = CalculationUtils.fahrenheitToCelsius(fahrenheit);
            
            assertThat(backToCelsius).isCloseTo(originalCelsius, within(0.01));
        }

        @Test
        @DisplayName("Should handle rapid sequential calculations")
        void testSequentialCalculations() {
            double result = 1.0;
            
            // Perform 10 sequential multiplications
            for (int i = 1; i <= 10; i++) {
                result = CalculationUtils.performOperation(result, 2, "*");
            }
            
            // 2^10 = 1024
            assertThat(result).isEqualTo(1024.0);
        }
    }
}
