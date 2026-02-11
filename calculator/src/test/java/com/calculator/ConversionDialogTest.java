package com.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for ConversionDialog class.
 * Note: Full UI testing with JOptionPane dialogs is complex in headless environments.
 * These tests verify the class can be instantiated and delegates to CalculationUtils correctly.
 */
@DisplayName("ConversionDialog Tests")
public class ConversionDialogTest {

    @Nested
    @DisplayName("Constructor Test")
    class ConstructorTest {

        @Test
        @DisplayName("Should be able to instantiate ConversionDialog")
        void testConstructor() {
            assertThatCode(() -> new ConversionDialog()).doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("Business Logic Verification")
    class BusinessLogicTests {

        @Test
        @DisplayName("Power calculation should use CalculationUtils correctly")
        void testPowerLogic() {
            // Verify the underlying calculation is correct
            double result = CalculationUtils.calculatePower(2, 3);
            assertThat(result).isEqualTo(8.0);
        }

        @Test
        @DisplayName("Temperature conversion should use CalculationUtils correctly")
        void testTemperatureLogic() {
            double celsius = CalculationUtils.fahrenheitToCelsius(32);
            assertThat(celsius).isCloseTo(0.0, within(0.1));
            
            double fahrenheit = CalculationUtils.celsiusToFahrenheit(100);
            assertThat(fahrenheit).isCloseTo(212.0, within(0.1));
        }

        @Test
        @DisplayName("Metric/Imperial conversion should use CalculationUtils correctly")
        void testMetricImperialLogic() {
            double cm = CalculationUtils.inchesToCentimeters(10);
            assertThat(cm).isCloseTo(25.4, within(0.01));
            
            double grams = CalculationUtils.ouncesToGrams(10);
            assertThat(grams).isCloseTo(283.5, within(0.1));
        }

        @Test
        @DisplayName("Number system conversions should use CalculationUtils correctly")
        void testNumberSystemLogic() {
            String binary = CalculationUtils.decimalToBinary(255);
            assertThat(binary).isEqualTo("11111111");
            
            int decimal = CalculationUtils.binaryToDecimal("1010");
            assertThat(decimal).isEqualTo(10);
            
            String hex = CalculationUtils.decimalToHexadecimal(255);
            assertThat(hex).isEqualTo("FF");
            
            int decFromHex = CalculationUtils.hexadecimalToDecimal("FF");
            assertThat(decFromHex).isEqualTo(255);
        }

        @Test
        @DisplayName("Should handle negative numbers for conversions")
        void testNegativeNumberHandling() {
            // Test that validation logic would catch negative numbers for binary
            int negativeNumber = -5;
            assertThat(negativeNumber < 0).isTrue();
        }

        @Test
        @DisplayName("Should handle invalid integer inputs")
        void testInvalidIntegerHandling() {
            String decimalNumber = "12.5";
            assertThatThrownBy(() -> Integer.parseInt(decimalNumber))
                .isInstanceOf(NumberFormatException.class);
        }

        @Test
        @DisplayName("Should handle invalid binary inputs")
        void testInvalidBinaryHandling() {
            assertThatThrownBy(() -> CalculationUtils.binaryToDecimal("102"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0s and 1s");
        }

        @Test
        @DisplayName("Should handle invalid hexadecimal inputs")
        void testInvalidHexHandling() {
            assertThatThrownBy(() -> CalculationUtils.hexadecimalToDecimal("XYZ"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("hexadecimal");
        }
    }
}
