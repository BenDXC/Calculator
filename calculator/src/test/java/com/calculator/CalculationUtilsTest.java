package com.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CalculationUtils Tests")
public class CalculationUtilsTest {

    @Nested
    @DisplayName("Power and Root Calculations")
    class PowerAndRootTests {

        @Test
        @DisplayName("Should calculate power correctly")
        void testCalculatePower() {
            assertThat(CalculationUtils.calculatePower(2, 3)).isEqualTo(8.0);
            assertThat(CalculationUtils.calculatePower(5, 2)).isEqualTo(25.0);
            assertThat(CalculationUtils.calculatePower(10, 0)).isEqualTo(1.0);
            assertThat(CalculationUtils.calculatePower(2, -1)).isEqualTo(0.5);
        }

        @ParameterizedTest
        @CsvSource({
            "2, 3, 8.0",
            "5, 2, 25.0",
            "10, 0, 1.0",
            "3, 4, 81.0",
            "7, 2, 49.0"
        })
        @DisplayName("Should calculate power with multiple inputs")
        void testCalculatePowerParameterized(double base, double exponent, double expected) {
            assertThat(CalculationUtils.calculatePower(base, exponent)).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should calculate square root correctly")
        void testCalculateSquareRoot() {
            assertThat(CalculationUtils.calculateSquareRoot(4)).isEqualTo(2.0);
            assertThat(CalculationUtils.calculateSquareRoot(9)).isEqualTo(3.0);
            assertThat(CalculationUtils.calculateSquareRoot(16)).isEqualTo(4.0);
            assertThat(CalculationUtils.calculateSquareRoot(25)).isEqualTo(5.0);
            assertThat(CalculationUtils.calculateSquareRoot(0)).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Should calculate cube root correctly")
        void testCalculateCubeRoot() {
            assertThat(CalculationUtils.calculateCubeRoot(8)).isEqualTo(2.0);
            assertThat(CalculationUtils.calculateCubeRoot(27)).isEqualTo(3.0);
            assertThat(CalculationUtils.calculateCubeRoot(64)).isEqualTo(4.0);
            assertThat(CalculationUtils.calculateCubeRoot(0)).isEqualTo(0.0);
        }
    }

    @Nested
    @DisplayName("Modulo Operations")
    class ModuloTests {

        @ParameterizedTest
        @CsvSource({
            "10, 3, 1.0",
            "15, 4, 3.0",
            "20, 6, 2.0",
            "7, 5, 2.0",
            "100, 9, 1.0"
        })
        @DisplayName("Should calculate modulo correctly")
        void testCalculateModulo(double num1, double num2, double expected) {
            assertThat(CalculationUtils.calculateModulo(num1, num2)).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("Temperature Conversions")
    class TemperatureTests {

        @Test
        @DisplayName("Should convert Fahrenheit to Celsius correctly")
        void testFahrenheitToCelsius() {
            assertThat(CalculationUtils.fahrenheitToCelsius(32)).isCloseTo(0.0, within(0.1));
            assertThat(CalculationUtils.fahrenheitToCelsius(212)).isCloseTo(100.0, within(0.1));
            assertThat(CalculationUtils.fahrenheitToCelsius(98.6)).isCloseTo(37.0, within(0.5));
            assertThat(CalculationUtils.fahrenheitToCelsius(0)).isCloseTo(-17.78, within(0.1));
        }

        @Test
        @DisplayName("Should convert Celsius to Fahrenheit correctly")
        void testCelsiusToFahrenheit() {
            assertThat(CalculationUtils.celsiusToFahrenheit(0)).isCloseTo(32.0, within(0.1));
            assertThat(CalculationUtils.celsiusToFahrenheit(100)).isCloseTo(212.0, within(0.1));
            assertThat(CalculationUtils.celsiusToFahrenheit(37)).isCloseTo(98.6, within(0.1));
            assertThat(CalculationUtils.celsiusToFahrenheit(-40)).isCloseTo(-40.0, within(0.1));
        }

        @Test
        @DisplayName("Temperature conversions should be reversible")
        void testTemperatureConversionReversibility() {
            double celsius = 25.0;
            double fahrenheit = CalculationUtils.celsiusToFahrenheit(celsius);
            double backToCelsius = CalculationUtils.fahrenheitToCelsius(fahrenheit);
            assertThat(backToCelsius).isCloseTo(celsius, within(0.01));
        }
    }

    @Nested
    @DisplayName("Length Conversions")
    class LengthTests {

        @ParameterizedTest
        @CsvSource({
            "1, 2.54",
            "10, 25.4",
            "5, 12.7",
            "0, 0"
        })
        @DisplayName("Should convert inches to centimeters correctly")
        void testInchesToCentimeters(double inches, double expectedCm) {
            assertThat(CalculationUtils.inchesToCentimeters(inches)).isCloseTo(expectedCm, within(0.01));
        }

        @ParameterizedTest
        @CsvSource({
            "2.54, 1.0",
            "10, 3.937",
            "5, 1.9685",
            "0, 0"
        })
        @DisplayName("Should convert centimeters to inches correctly")
        void testCentimetersToInches(double cm, double expectedInches) {
            assertThat(CalculationUtils.centimetersToInches(cm)).isCloseTo(expectedInches, within(0.01));
        }
    }

    @Nested
    @DisplayName("Mass Conversions")
    class MassTests {

        @ParameterizedTest
        @CsvSource({
            "1, 28.35",
            "10, 283.5",
            "5, 141.75",
            "0, 0"
        })
        @DisplayName("Should convert ounces to grams correctly")
        void testOuncesToGrams(double ounces, double expectedGrams) {
            assertThat(CalculationUtils.ouncesToGrams(ounces)).isCloseTo(expectedGrams, within(0.01));
        }

        @ParameterizedTest
        @CsvSource({
            "28.35, 0.99225",
            "100, 3.5273",
            "50, 1.7637",
            "0, 0"
        })
        @DisplayName("Should convert grams to ounces correctly")
        void testGramsToOunces(double grams, double expectedOunces) {
            assertThat(CalculationUtils.gramsToOunces(grams)).isCloseTo(expectedOunces, within(0.01));
        }
    }

    @Nested
    @DisplayName("Binary Conversions")
    class BinaryTests {

        @ParameterizedTest
        @CsvSource({
            "0, 0",
            "1, 1",
            "2, 10",
            "10, 1010",
            "15, 1111",
            "255, 11111111",
            "1024, 10000000000"
        })
        @DisplayName("Should convert decimal to binary correctly")
        void testDecimalToBinary(int decimal, String expectedBinary) {
            assertThat(CalculationUtils.decimalToBinary(decimal)).isEqualTo(expectedBinary);
        }

        @ParameterizedTest
        @CsvSource({
            "0, 0",
            "1, 1",
            "10, 2",
            "1010, 10",
            "1111, 15",
            "11111111, 255"
        })
        @DisplayName("Should convert binary to decimal correctly")
        void testBinaryToDecimal(String binary, int expectedDecimal) {
            assertThat(CalculationUtils.binaryToDecimal(binary)).isEqualTo(expectedDecimal);
        }

        @Test
        @DisplayName("Should throw exception for invalid binary input")
        void testBinaryToDecimalInvalidInput() {
            assertThatThrownBy(() -> CalculationUtils.binaryToDecimal("102"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("only 0s and 1s");
            
            assertThatThrownBy(() -> CalculationUtils.binaryToDecimal("ABC"))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Binary conversions should be reversible")
        void testBinaryConversionReversibility() {
            int[] testNumbers = {0, 1, 5, 10, 100, 255, 1000};
            for (int num : testNumbers) {
                String binary = CalculationUtils.decimalToBinary(num);
                int backToDecimal = CalculationUtils.binaryToDecimal(binary);
                assertThat(backToDecimal).isEqualTo(num);
            }
        }
    }

    @Nested
    @DisplayName("Hexadecimal Conversions")
    class HexadecimalTests {

        @ParameterizedTest
        @CsvSource({
            "0, 0",
            "1, 1",
            "10, A",
            "15, F",
            "16, 10",
            "255, FF",
            "256, 100",
            "4096, 1000"
        })
        @DisplayName("Should convert decimal to hexadecimal correctly")
        void testDecimalToHexadecimal(int decimal, String expectedHex) {
            assertThat(CalculationUtils.decimalToHexadecimal(decimal)).isEqualTo(expectedHex);
        }

        @ParameterizedTest
        @CsvSource({
            "0, 0",
            "1, 1",
            "A, 10",
            "F, 15",
            "10, 16",
            "FF, 255",
            "100, 256",
            "1000, 4096"
        })
        @DisplayName("Should convert hexadecimal to decimal correctly")
        void testHexadecimalToDecimal(String hex, int expectedDecimal) {
            assertThat(CalculationUtils.hexadecimalToDecimal(hex)).isEqualTo(expectedDecimal);
        }

        @Test
        @DisplayName("Should handle lowercase hexadecimal input")
        void testHexadecimalToDecimalLowercase() {
            assertThat(CalculationUtils.hexadecimalToDecimal("ff")).isEqualTo(255);
            assertThat(CalculationUtils.hexadecimalToDecimal("abc")).isEqualTo(2748);
        }

        @Test
        @DisplayName("Should throw exception for invalid hexadecimal input")
        void testHexadecimalToDecimalInvalidInput() {
            assertThatThrownBy(() -> CalculationUtils.hexadecimalToDecimal("G"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("valid hexadecimal");
            
            assertThatThrownBy(() -> CalculationUtils.hexadecimalToDecimal("XYZ"))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Hexadecimal conversions should be reversible")
        void testHexadecimalConversionReversibility() {
            int[] testNumbers = {0, 1, 10, 15, 16, 255, 256, 1000, 4096};
            for (int num : testNumbers) {
                String hex = CalculationUtils.decimalToHexadecimal(num);
                int backToDecimal = CalculationUtils.hexadecimalToDecimal(hex);
                assertThat(backToDecimal).isEqualTo(num);
            }
        }
    }

    @Nested
    @DisplayName("Basic Operations")
    class BasicOperationsTests {

        @Test
        @DisplayName("Should perform addition correctly")
        void testAddition() {
            assertThat(CalculationUtils.performOperation(5, 3, "+")).isEqualTo(8.0);
            assertThat(CalculationUtils.performOperation(10.5, 2.5, "+")).isEqualTo(13.0);
            assertThat(CalculationUtils.performOperation(-5, 3, "+")).isEqualTo(-2.0);
        }

        @Test
        @DisplayName("Should perform subtraction correctly")
        void testSubtraction() {
            assertThat(CalculationUtils.performOperation(5, 3, "-")).isEqualTo(2.0);
            assertThat(CalculationUtils.performOperation(10.5, 2.5, "-")).isEqualTo(8.0);
            assertThat(CalculationUtils.performOperation(-5, 3, "-")).isEqualTo(-8.0);
        }

        @Test
        @DisplayName("Should perform multiplication correctly")
        void testMultiplication() {
            assertThat(CalculationUtils.performOperation(5, 3, "*")).isEqualTo(15.0);
            assertThat(CalculationUtils.performOperation(10.5, 2, "*")).isEqualTo(21.0);
            assertThat(CalculationUtils.performOperation(-5, 3, "*")).isEqualTo(-15.0);
        }

        @Test
        @DisplayName("Should perform division correctly")
        void testDivision() {
            assertThat(CalculationUtils.performOperation(6, 3, "/")).isEqualTo(2.0);
            assertThat(CalculationUtils.performOperation(10, 2, "/")).isEqualTo(5.0);
            assertThat(CalculationUtils.performOperation(-10, 2, "/")).isEqualTo(-5.0);
        }

        @Test
        @DisplayName("Should throw exception for division by zero")
        void testDivisionByZero() {
            assertThatThrownBy(() -> CalculationUtils.performOperation(10, 0, "/"))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("Division by zero");
        }

        @Test
        @DisplayName("Should perform modulo correctly")
        void testModuloOperation() {
            assertThat(CalculationUtils.performOperation(10, 3, "%")).isEqualTo(1.0);
            assertThat(CalculationUtils.performOperation(15, 4, "%")).isEqualTo(3.0);
        }

        @Test
        @DisplayName("Should throw exception for invalid operation")
        void testInvalidOperation() {
            assertThatThrownBy(() -> CalculationUtils.performOperation(5, 3, "^"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid operation");
        }
    }

    @Nested
    @DisplayName("Validation Methods")
    class ValidationTests {

        @ParameterizedTest
        @ValueSource(strings = {"123", "123.456", "-123", "-123.456", "0", "0.0"})
        @DisplayName("Should validate numeric strings correctly")
        void testIsNumericValid(String input) {
            assertThat(CalculationUtils.isNumeric(input)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"abc", "12a3", "12.34.56", "", " ", "12 34"})
        @DisplayName("Should reject non-numeric strings")
        void testIsNumericInvalid(String input) {
            assertThat(CalculationUtils.isNumeric(input)).isFalse();
        }

        @Test
        @DisplayName("Should reject null as numeric")
        void testIsNumericNull() {
            assertThat(CalculationUtils.isNumeric(null)).isFalse();
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "1", "101010", "11111111"})
        @DisplayName("Should validate binary strings correctly")
        void testIsBinaryValid(String input) {
            assertThat(CalculationUtils.isBinary(input)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"2", "102", "abc", "10 10", ""})
        @DisplayName("Should reject non-binary strings")
        void testIsBinaryInvalid(String input) {
            assertThat(CalculationUtils.isBinary(input)).isFalse();
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "F", "FF", "123ABC", "abcdef"})
        @DisplayName("Should validate hexadecimal strings correctly")
        void testIsHexadecimalValid(String input) {
            assertThat(CalculationUtils.isHexadecimal(input)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"G", "XYZ", "FF GG", "", " "})
        @DisplayName("Should reject non-hexadecimal strings")
        void testIsHexadecimalInvalid(String input) {
            assertThat(CalculationUtils.isHexadecimal(input)).isFalse();
        }
    }

    @Nested
    @DisplayName("Constructor Test")
    class ConstructorTest {

        @Test
        @DisplayName("Should prevent instantiation of utility class")
        void testConstructor() throws Exception {
            // Utility class should not be instantiable
            var constructor = CalculationUtils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            assertThatThrownBy(() -> constructor.newInstance())
                .isInstanceOf(java.lang.reflect.InvocationTargetException.class)
                .hasCauseInstanceOf(UnsupportedOperationException.class)
                .hasRootCauseMessage("Utility class cannot be instantiated");
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle very large numbers")
        void testLargeNumbers() {
            double largeNum = 1000000.0;
            assertThat(CalculationUtils.performOperation(largeNum, 2, "*")).isEqualTo(2000000.0);
            assertThat(CalculationUtils.calculatePower(10, 6)).isEqualTo(1000000.0);
        }

        @Test
        @DisplayName("Should handle very small decimal numbers")
        void testSmallDecimals() {
            assertThat(CalculationUtils.performOperation(0.001, 0.002, "+"))
                .isCloseTo(0.003, within(0.0001));
        }

        @Test
        @DisplayName("Should handle negative numbers in conversions")
        void testNegativeConversions() {
            assertThat(CalculationUtils.celsiusToFahrenheit(-40)).isCloseTo(-40.0, within(0.1));
            assertThat(CalculationUtils.fahrenheitToCelsius(-40)).isCloseTo(-40.0, within(0.1));
        }

        @Test
        @DisplayName("Should handle zero in all operations")
        void testZeroHandling() {
            assertThat(CalculationUtils.performOperation(0, 5, "+")).isEqualTo(5.0);
            assertThat(CalculationUtils.performOperation(0, 5, "*")).isEqualTo(0.0);
            assertThat(CalculationUtils.calculateSquareRoot(0)).isEqualTo(0.0);
            assertThat(CalculationUtils.decimalToBinary(0)).isEqualTo("0");
            assertThat(CalculationUtils.decimalToHexadecimal(0)).isEqualTo("0");
        }
    }
}
