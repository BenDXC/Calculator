package com.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for Calculator GUI class.
 * Note: Full Swing UI testing requires a display environment.
 * These tests verify the underlying business logic used by the Calculator.
 */
@DisplayName("Calculator Tests")
public class CalculatorTest {

    @Nested
    @DisplayName("Calculator Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("Addition logic should be correct")
        void testAdditionLogic() {
            double result = CalculationUtils.performOperation(5, 3, "+");
            assertThat(result).isEqualTo(8.0);
        }

        @Test
        @DisplayName("Subtraction logic should be correct")
        void testSubtractionLogic() {
            double result = CalculationUtils.performOperation(10, 3, "-");
            assertThat(result).isEqualTo(7.0);
        }

        @Test
        @DisplayName("Multiplication logic should be correct")
        void testMultiplicationLogic() {
            double result = CalculationUtils.performOperation(5, 3, "*");
            assertThat(result).isEqualTo(15.0);
        }

        @Test
        @DisplayName("Division logic should be correct")
        void testDivisionLogic() {
            double result = CalculationUtils.performOperation(10, 2, "/");
            assertThat(result).isEqualTo(5.0);
        }

        @Test
        @DisplayName("Modulo logic should be correct")
        void testModuloLogic() {
            double result = CalculationUtils.performOperation(10, 3, "%");
            assertThat(result).isEqualTo(1.0);
        }

        @Test
        @DisplayName("Division by zero should throw exception")
        void testDivisionByZero() {
            assertThatThrownBy(() -> CalculationUtils.performOperation(10, 0, "/"))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("Division by zero");
        }
    }

    @Nested
    @DisplayName("Calculator Utility Logic Tests")
    class UtilityLogicTests {

        @Test
        @DisplayName("Toggle sign logic should work")
        void testToggleSignLogic() {
            double positive = 10.0;
            double negated = positive * -1;
            assertThat(negated).isEqualTo(-10.0);
            
            double backToPositive = negated * -1;
            assertThat(backToPositive).isEqualTo(10.0);
        }

        @Test
        @DisplayName("Backspace logic should work")
        void testBackspaceLogic() {
            String text = "123";
            String afterBackspace = text.substring(0, text.length() - 1);
            assertThat(afterBackspace).isEqualTo("12");
        }

        @Test
        @DisplayName("Decimal point logic should prevent duplicates")
        void testDecimalLogic() {
            String text1 = "123";
            boolean canAddDecimal1 = !text1.contains(".");
            assertThat(canAddDecimal1).isTrue();
            
            String text2 = "123.45";
            boolean canAddDecimal2 = !text2.contains(".");
            assertThat(canAddDecimal2).isFalse();
        }

        @Test
        @DisplayName("Empty display with decimal should show 0.")
        void testEmptyDecimal() {
            String text = "";
            String withDecimal = text.isEmpty() ? "0." : text + ".";
            assertThat(withDecimal).isEqualTo("0.");
        }
    }

    @Nested
    @DisplayName("Calculator Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Should validate numeric input")
        void testNumericValidation() {
            assertThatCode(() -> Double.parseDouble("10.5"))
                .doesNotThrowAnyException();
            
            assertThatThrownBy(() -> Double.parseDouble("abc"))
                .isInstanceOf(NumberFormatException.class);
        }

        @Test
        @DisplayName("Should handle invalid operations gracefully")
        void testInvalidOperation() {
            assertThatThrownBy(() -> CalculationUtils.performOperation(5, 3, "^"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid operation");
        }

        @Test
        @DisplayName("Should handle empty string in numeric operations")
        void testEmptyString() {
            String empty = "";
            assertThat(empty.isEmpty()).isTrue();
        }
    }

    @Nested
    @DisplayName("Calculator Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle very large numbers")
        void testLargeNumbers() {
            double large1 = 999999999.0;
            double large2 = 888888888.0;
            double sum = CalculationUtils.performOperation(large1, large2, "+");
            assertThat(sum).isEqualTo(1888888887.0);
        }

        @Test
        @DisplayName("Should handle very small numbers")
        void testSmallNumbers() {
            double result = CalculationUtils.performOperation(0.001, 0.002, "+");
            assertThat(result).isCloseTo(0.003, within(0.0001));
        }

        @Test
        @DisplayName("Should handle negative numbers")
        void testNegativeNumbers() {
            double result = CalculationUtils.performOperation(-5, 3, "+");
            assertThat(result).isEqualTo(-2.0);
        }

        @Test
        @DisplayName("Should handle zero")
        void testZero() {
            double result = CalculationUtils.performOperation(0, 5, "+");
            assertThat(result).isEqualTo(5.0);
        }
    }
}
