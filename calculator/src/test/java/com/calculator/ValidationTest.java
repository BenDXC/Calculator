package com.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

/**
 * Comprehensive validation tests for input validation and edge cases.
 */
@DisplayName("Input Validation Tests")
public class ValidationTest {

    @Nested
    @DisplayName("Numeric Input Validation")
    class NumericValidationTests {

        @ParameterizedTest
        @ValueSource(strings = {"0", "1", "123", "123.456", "-123", "-123.456", "0.0", "999999999"})
        @DisplayName("Should accept valid numeric strings")
        void testValidNumericStrings(String input) {
            assertThat(CalculationUtils.isNumeric(input)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"abc", "12a3", "12.34.56", "12 34", "1,234", "12e3", "NaN", "Infinity"})
        @DisplayName("Should reject invalid numeric strings")
        void testInvalidNumericStrings(String input) {
            assertThat(CalculationUtils.isNumeric(input)).isFalse();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Should reject null and empty strings as numeric")
        void testNullAndEmptyNumeric(String input) {
            assertThat(CalculationUtils.isNumeric(input)).isFalse();
        }

        @ParameterizedTest
        @ValueSource(strings = {" ", "  ", "\t", "\n"})
        @DisplayName("Should reject whitespace-only strings as numeric")
        void testWhitespaceNumeric(String input) {
            assertThat(CalculationUtils.isNumeric(input)).isFalse();
        }
    }

    @Nested
    @DisplayName("Binary Input Validation")
    class BinaryValidationTests {

        @ParameterizedTest
        @ValueSource(strings = {"0", "1", "10", "11", "101010", "11111111", "1000000000"})
        @DisplayName("Should accept valid binary strings")
        void testValidBinaryStrings(String input) {
            assertThat(CalculationUtils.isBinary(input)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"2", "10a", "102", "abc", "1 0", "1,0", ""})
        @DisplayName("Should reject invalid binary strings")
        void testInvalidBinaryStrings(String input) {
            assertThat(CalculationUtils.isBinary(input)).isFalse();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Should reject null and empty strings as binary")
        void testNullAndEmptyBinary(String input) {
            assertThat(CalculationUtils.isBinary(input)).isFalse();
        }

        @ParameterizedTest
        @ValueSource(strings = {"01010101", "10101010", "11110000", "00001111"})
        @DisplayName("Should accept long binary strings")
        void testLongBinaryStrings(String input) {
            assertThat(CalculationUtils.isBinary(input)).isTrue();
        }
    }

    @Nested
    @DisplayName("Hexadecimal Input Validation")
    class HexadecimalValidationTests {

        @ParameterizedTest
        @ValueSource(strings = {"0", "9", "A", "F", "a", "f", "FF", "ABC", "abc123", "DEADBEEF"})
        @DisplayName("Should accept valid hexadecimal strings")
        void testValidHexStrings(String input) {
            assertThat(CalculationUtils.isHexadecimal(input)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"G", "Z", "FFG", "XYZ", "FF GG", "FF,AA", ""})
        @DisplayName("Should reject invalid hexadecimal strings")
        void testInvalidHexStrings(String input) {
            assertThat(CalculationUtils.isHexadecimal(input)).isFalse();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Should reject null and empty strings as hexadecimal")
        void testNullAndEmptyHex(String input) {
            assertThat(CalculationUtils.isHexadecimal(input)).isFalse();
        }

        @Test
        @DisplayName("Should accept mixed case hexadecimal")
        void testMixedCaseHex() {
            assertThat(CalculationUtils.isHexadecimal("AbCdEf")).isTrue();
            assertThat(CalculationUtils.isHexadecimal("1a2B3c")).isTrue();
        }
    }

    @Nested
    @DisplayName("Conversion Edge Cases")
    class ConversionEdgeCaseTests {

        @Test
        @DisplayName("Should handle zero in all conversions")
        void testZeroConversions() {
            assertThat(CalculationUtils.decimalToBinary(0)).isEqualTo("0");
            assertThat(CalculationUtils.decimalToHexadecimal(0)).isEqualTo("0");
            assertThat(CalculationUtils.binaryToDecimal("0")).isEqualTo(0);
            assertThat(CalculationUtils.hexadecimalToDecimal("0")).isEqualTo(0);
        }

        @Test
        @DisplayName("Should handle maximum 8-bit value (255)")
        void testMaximum8Bit() {
            assertThat(CalculationUtils.decimalToBinary(255)).isEqualTo("11111111");
            assertThat(CalculationUtils.decimalToHexadecimal(255)).isEqualTo("FF");
        }

        @Test
        @DisplayName("Should handle powers of 2")
        void testPowersOfTwo() {
            assertThat(CalculationUtils.decimalToBinary(1)).isEqualTo("1");
            assertThat(CalculationUtils.decimalToBinary(2)).isEqualTo("10");
            assertThat(CalculationUtils.decimalToBinary(4)).isEqualTo("100");
            assertThat(CalculationUtils.decimalToBinary(8)).isEqualTo("1000");
            assertThat(CalculationUtils.decimalToBinary(16)).isEqualTo("10000");
        }

        @Test
        @DisplayName("Should handle powers of 16")
        void testPowersOfSixteen() {
            assertThat(CalculationUtils.decimalToHexadecimal(1)).isEqualTo("1");
            assertThat(CalculationUtils.decimalToHexadecimal(16)).isEqualTo("10");
            assertThat(CalculationUtils.decimalToHexadecimal(256)).isEqualTo("100");
            assertThat(CalculationUtils.decimalToHexadecimal(4096)).isEqualTo("1000");
        }
    }

    @Nested
    @DisplayName("Operation Validation")
    class OperationValidationTests {

        @ParameterizedTest
        @ValueSource(strings = {"+", "-", "*", "/", "%"})
        @DisplayName("Should accept valid operations")
        void testValidOperations(String operation) {
            assertThatCode(() -> 
                CalculationUtils.performOperation(10, 5, operation)
            ).doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(strings = {"^", "**", "//", "x", "÷", "", " "})
        @DisplayName("Should reject invalid operations")
        void testInvalidOperations(String operation) {
            assertThatThrownBy(() -> 
                CalculationUtils.performOperation(10, 5, operation)
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("Invalid operation");
        }
    }

    @Nested
    @DisplayName("Boundary Value Tests")
    class BoundaryValueTests {

        @Test
        @DisplayName("Should handle reasonably large integers in binary conversion")
        void testLargeBinaryConversion() {
            int large = 1023; // 10-bit value (binary: 1111111111)
            String binary = CalculationUtils.decimalToBinary(large);
            int back = CalculationUtils.binaryToDecimal(binary);
            assertThat(back).isEqualTo(large);
        }

        @Test
        @DisplayName("Should handle very large integers in hex conversion")
        void testLargeHexConversion() {
            int large = 1000000;
            String hex = CalculationUtils.decimalToHexadecimal(large);
            int back = CalculationUtils.hexadecimalToDecimal(hex);
            assertThat(back).isEqualTo(large);
        }

        @Test
        @DisplayName("Should handle negative temperatures")
        void testNegativeTemperatures() {
            double celsius = -40.0;
            double fahrenheit = CalculationUtils.celsiusToFahrenheit(celsius);
            assertThat(fahrenheit).isCloseTo(-40.0, within(0.1));
            
            double backToCelsius = CalculationUtils.fahrenheitToCelsius(fahrenheit);
            assertThat(backToCelsius).isCloseTo(-40.0, within(0.1));
        }

        @Test
        @DisplayName("Should handle very small decimal values")
        void testSmallDecimals() {
            double result = CalculationUtils.performOperation(0.0001, 0.0002, "+");
            assertThat(result).isCloseTo(0.0003, within(0.00001));
        }

        @Test
        @DisplayName("Should handle very large decimal values")
        void testLargeDecimals() {
            double result = CalculationUtils.performOperation(999999.99, 0.01, "+");
            assertThat(result).isCloseTo(1000000.0, within(0.01));
        }
    }

    @Nested
    @DisplayName("Exception Handling Tests")
    class ExceptionHandlingTests {

        @Test
        @DisplayName("Should throw exception for invalid binary input")
        void testInvalidBinaryException() {
            assertThatThrownBy(() -> 
                CalculationUtils.binaryToDecimal("102")
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("only 0s and 1s");
        }

        @Test
        @DisplayName("Should throw exception for invalid hex input")
        void testInvalidHexException() {
            assertThatThrownBy(() -> 
                CalculationUtils.hexadecimalToDecimal("XYZ")
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("valid hexadecimal");
        }

        @Test
        @DisplayName("Should throw exception for division by zero")
        void testDivisionByZeroException() {
            assertThatThrownBy(() -> 
                CalculationUtils.performOperation(10, 0, "/")
            ).isInstanceOf(ArithmeticException.class)
             .hasMessageContaining("Division by zero");
        }

        @Test
        @DisplayName("Should throw exception for invalid operation")
        void testInvalidOperationException() {
            assertThatThrownBy(() -> 
                CalculationUtils.performOperation(10, 5, "invalid")
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("Invalid operation");
        }
    }

    @Nested
    @DisplayName("Special Character and Format Tests")
    class SpecialCharacterTests {

        @Test
        @DisplayName("Should reject strings with special characters as numeric")
        void testSpecialCharactersNumeric() {
            assertThat(CalculationUtils.isNumeric("12@34")).isFalse();
            assertThat(CalculationUtils.isNumeric("12#34")).isFalse();
            assertThat(CalculationUtils.isNumeric("12$34")).isFalse();
            assertThat(CalculationUtils.isNumeric("12%34")).isFalse();
        }

        @Test
        @DisplayName("Should handle leading zeros in conversions")
        void testLeadingZeros() {
            assertThat(CalculationUtils.binaryToDecimal("0001")).isEqualTo(1);
            assertThat(CalculationUtils.binaryToDecimal("0010")).isEqualTo(2);
            assertThat(CalculationUtils.hexadecimalToDecimal("00FF")).isEqualTo(255);
        }

        @Test
        @DisplayName("Should handle all hex digits correctly")
        void testAllHexDigits() {
            assertThat(CalculationUtils.hexadecimalToDecimal("0")).isEqualTo(0);
            assertThat(CalculationUtils.hexadecimalToDecimal("1")).isEqualTo(1);
            assertThat(CalculationUtils.hexadecimalToDecimal("9")).isEqualTo(9);
            assertThat(CalculationUtils.hexadecimalToDecimal("A")).isEqualTo(10);
            assertThat(CalculationUtils.hexadecimalToDecimal("B")).isEqualTo(11);
            assertThat(CalculationUtils.hexadecimalToDecimal("C")).isEqualTo(12);
            assertThat(CalculationUtils.hexadecimalToDecimal("D")).isEqualTo(13);
            assertThat(CalculationUtils.hexadecimalToDecimal("E")).isEqualTo(14);
            assertThat(CalculationUtils.hexadecimalToDecimal("F")).isEqualTo(15);
        }
    }

    @Nested
    @DisplayName("Precision and Rounding Tests")
    class PrecisionTests {

        @Test
        @DisplayName("Should maintain precision in temperature conversions")
        void testTemperaturePrecision() {
            double[] testValues = {0.0, 100.0, -40.0, 37.0, 98.6};
            for (double celsius : testValues) {
                double fahrenheit = CalculationUtils.celsiusToFahrenheit(celsius);
                double back = CalculationUtils.fahrenheitToCelsius(fahrenheit);
                assertThat(back).isCloseTo(celsius, within(0.01));
            }
        }

        @Test
        @DisplayName("Should maintain precision in length conversions")
        void testLengthPrecision() {
            double[] testValues = {1.0, 10.0, 100.0, 0.5, 2.54};
            for (double inches : testValues) {
                double cm = CalculationUtils.inchesToCentimeters(inches);
                double back = CalculationUtils.centimetersToInches(cm);
                assertThat(back).isCloseTo(inches, within(0.01));
            }
        }

        @Test
        @DisplayName("Should handle floating point arithmetic edge cases")
        void testFloatingPointEdgeCases() {
            // 0.1 + 0.2 = 0.3 (with floating point precision issues)
            double result = CalculationUtils.performOperation(0.1, 0.2, "+");
            assertThat(result).isCloseTo(0.3, within(0.00001));
        }
    }
}
