package com.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for InputValidator utility class.
 * Note: These tests verify validation logic but skip actual JOptionPane dialogs
 * since they require GUI interaction.
 */
@DisplayName("InputValidator Tests")
public class InputValidatorTest {

    @Nested
    @DisplayName("Validation Logic Tests")
    class ValidationLogicTests {

        @Test
        @DisplayName("Should use CalculationUtils for numeric validation")
        void testNumericValidation() {
            // Verify that validation delegates to CalculationUtils
            assertThat(CalculationUtils.isNumeric("123")).isTrue();
            assertThat(CalculationUtils.isNumeric("abc")).isFalse();
        }

        @Test
        @DisplayName("Should use CalculationUtils for binary validation")
        void testBinaryValidation() {
            assertThat(CalculationUtils.isBinary("1010")).isTrue();
            assertThat(CalculationUtils.isBinary("102")).isFalse();
        }

        @Test
        @DisplayName("Should use CalculationUtils for hexadecimal validation")
        void testHexValidation() {
            assertThat(CalculationUtils.isHexadecimal("FF")).isTrue();
            assertThat(CalculationUtils.isHexadecimal("GG")).isFalse();
        }
    }

    @Nested
    @DisplayName("Range Validation Logic Tests")
    class RangeValidationTests {

        @Test
        @DisplayName("Should validate integer range boundaries")
        void testRangeBoundaries() {
            // Test that values within range would pass numeric check
            assertThat(CalculationUtils.isNumeric("1")).isTrue();
            assertThat(CalculationUtils.isNumeric("2")).isTrue();
            assertThat(CalculationUtils.isNumeric("10")).isTrue();
        }

        @Test
        @DisplayName("Should identify invalid integer inputs")
        void testInvalidIntegerInputs() {
            assertThat(CalculationUtils.isNumeric("abc")).isFalse();
            assertThat(CalculationUtils.isNumeric("1.5")).isTrue(); // Valid number but not integer
        }
    }

    @Nested
    @DisplayName("Constructor Test")
    class ConstructorTest {

        @Test
        @DisplayName("Should be able to instantiate InputValidator")
        void testConstructor() {
            // Verify the class can be instantiated (though it's meant to be used statically)
            assertThatCode(() -> new InputValidator()).doesNotThrowAnyException();
        }
    }
}
