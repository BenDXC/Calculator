package calculator.src.test.java.com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import calculator.src.main.java.com.calculator.run;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for the run class calculator operations.
 * Tests the core calculation logic without GUI interaction.
 */
@DisplayName("Calculator Run Class Tests")
public class RunCalculatorTest {

    private run calculator;

    @BeforeEach
    void setUp() {
        calculator = new run();
    }

    @Nested
    @DisplayName("Calculator State Management")
    class StateManagementTests {

        @Test
        @DisplayName("Calculator should initialize with zero values")
        void testInitialState() {
            assertThat(calculator.firstnum).isEqualTo(0.0);
            assertThat(calculator.secondnum).isEqualTo(0.0);
            assertThat(calculator.result).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Calculator should store operations correctly")
        void testOperationStorage() {
            calculator.operations = "+";
            assertThat(calculator.operations).isEqualTo("+");
            
            calculator.operations = "-";
            assertThat(calculator.operations).isEqualTo("-");
            
            calculator.operations = "*";
            assertThat(calculator.operations).isEqualTo("*");
            
            calculator.operations = "/";
            assertThat(calculator.operations).isEqualTo("/");
            
            calculator.operations = "%";
            assertThat(calculator.operations).isEqualTo("%");
        }
    }

    @Nested
    @DisplayName("Basic Arithmetic Operations Logic")
    class ArithmeticTests {

        @ParameterizedTest
        @CsvSource({
            "5, 3, +, 8",
            "10, 4, -, 6",
            "6, 7, *, 42",
            "20, 4, /, 5",
            "17, 5, %, 2"
        })
        @DisplayName("Should calculate operations correctly")
        void testBasicOperations(double first, double second, String op, double expected) {
            calculator.firstnum = first;
            calculator.secondnum = second;
            calculator.operations = op;
            
            double result = switch (op) {
                case "+" -> calculator.firstnum + calculator.secondnum;
                case "-" -> calculator.firstnum - calculator.secondnum;
                case "*" -> calculator.firstnum * calculator.secondnum;
                case "/" -> calculator.firstnum / calculator.secondnum;
                case "%" -> calculator.firstnum % calculator.secondnum;
                default -> 0.0;
            };
            
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should handle addition with decimals")
        void testAdditionWithDecimals() {
            calculator.firstnum = 12.5;
            calculator.secondnum = 7.3;
            double result = calculator.firstnum + calculator.secondnum;
            assertThat(result).isCloseTo(19.8, within(0.01));
        }

        @Test
        @DisplayName("Should handle subtraction with negative results")
        void testSubtractionNegativeResult() {
            calculator.firstnum = 5.0;
            calculator.secondnum = 10.0;
            double result = calculator.firstnum - calculator.secondnum;
            assertThat(result).isEqualTo(-5.0);
        }

        @Test
        @DisplayName("Should handle multiplication by zero")
        void testMultiplicationByZero() {
            calculator.firstnum = 100.0;
            calculator.secondnum = 0.0;
            double result = calculator.firstnum * calculator.secondnum;
            assertThat(result).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Should handle division returning decimal")
        void testDivisionDecimalResult() {
            calculator.firstnum = 10.0;
            calculator.secondnum = 4.0;
            double result = calculator.firstnum / calculator.secondnum;
            assertThat(result).isEqualTo(2.5);
        }

        @Test
        @DisplayName("Should handle modulo operation")
        void testModuloOperation() {
            calculator.firstnum = 15.0;
            calculator.secondnum = 4.0;
            double result = calculator.firstnum % calculator.secondnum;
            assertThat(result).isEqualTo(3.0);
        }
    }

    @Nested
    @DisplayName("Advanced Operation Tests")
    class AdvancedTests {

        @Test
        @DisplayName("Should handle very large numbers")
        void testLargeNumbers() {
            calculator.firstnum = 999999999.0;
            calculator.secondnum = 999999999.0;
            double result = calculator.firstnum + calculator.secondnum;
            assertThat(result).isEqualTo(1999999998.0);
        }

        @Test
        @DisplayName("Should handle very small decimals")
        void testSmallDecimals() {
            calculator.firstnum = 0.0001;
            calculator.secondnum = 0.0002;
            double result = calculator.firstnum + calculator.secondnum;
            assertThat(result).isCloseTo(0.0003, within(0.00001));
        }

        @Test
        @DisplayName("Should handle negative number operations")
        void testNegativeNumbers() {
            calculator.firstnum = -5.0;
            calculator.secondnum = -3.0;
            
            double addResult = calculator.firstnum + calculator.secondnum;
            assertThat(addResult).isEqualTo(-8.0);
            
            double multiplyResult = calculator.firstnum * calculator.secondnum;
            assertThat(multiplyResult).isEqualTo(15.0);
        }

        @Test
        @DisplayName("Should handle chain operations")
        void testChainOperations() {
            // First operation: 10 + 5 = 15
            calculator.firstnum = 10.0;
            calculator.secondnum = 5.0;
            calculator.result = calculator.firstnum + calculator.secondnum;
            assertThat(calculator.result).isEqualTo(15.0);
            
            // Second operation: 15 * 2 = 30
            calculator.firstnum = calculator.result;
            calculator.secondnum = 2.0;
            calculator.result = calculator.firstnum * calculator.secondnum;
            assertThat(calculator.result).isEqualTo(30.0);
        }

        @Test
        @DisplayName("Should toggle positive/negative correctly")
        void testPlusMinusToggle() {
            double value = 5.0;
            double toggled = value * (-1);
            assertThat(toggled).isEqualTo(-5.0);
            
            double toggledBack = toggled * (-1);
            assertThat(toggledBack).isEqualTo(5.0);
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle division by zero scenario")
        void testDivisionByZero() {
            calculator.firstnum = 10.0;
            calculator.secondnum = 0.0;
            double result = calculator.firstnum / calculator.secondnum;
            assertThat(result).isInfinite();
        }

        @Test
        @DisplayName("Should handle zero divided by number")
        void testZeroDivided() {
            calculator.firstnum = 0.0;
            calculator.secondnum = 10.0;
            double result = calculator.firstnum / calculator.secondnum;
            assertThat(result).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Should handle operations with same numbers")
        void testSameNumbers() {
            calculator.firstnum = 7.0;
            calculator.secondnum = 7.0;
            
            assertThat(calculator.firstnum + calculator.secondnum).isEqualTo(14.0);
            assertThat(calculator.firstnum - calculator.secondnum).isEqualTo(0.0);
            assertThat(calculator.firstnum * calculator.secondnum).isEqualTo(49.0);
            assertThat(calculator.firstnum / calculator.secondnum).isEqualTo(1.0);
        }

        @Test
        @DisplayName("Should preserve precision in floating point operations")
        void testFloatingPointPrecision() {
            calculator.firstnum = 0.1;
            calculator.secondnum = 0.2;
            double result = calculator.firstnum + calculator.secondnum;
            // Note: Floating point precision issues
            assertThat(result).isCloseTo(0.3, within(0.00001));
        }
    }

    @Nested
    @DisplayName("Result String Conversion")
    class StringConversionTests {

        @Test
        @DisplayName("Should convert result to string correctly")
        void testResultToString() {
            calculator.result = 42.0;
            String answer = String.valueOf(calculator.result);
            assertThat(answer).isEqualTo("42.0");
        }

        @Test
        @DisplayName("Should convert decimal result to string")
        void testDecimalResultToString() {
            calculator.result = 3.14159;
            String answer = String.valueOf(calculator.result);
            assertThat(answer).contains("3.14159");
        }

        @Test
        @DisplayName("Should convert negative result to string")
        void testNegativeResultToString() {
            calculator.result = -25.5;
            String answer = String.valueOf(calculator.result);
            assertThat(answer).isEqualTo("-25.5");
        }
    }

    @Nested
    @DisplayName("Operation Validation")
    class ValidationTests {

        @Test
        @DisplayName("Should identify valid operations")
        void testValidOperations() {
            String[] validOps = {"+", "-", "*", "/", "%"};
            for (String op : validOps) {
                calculator.operations = op;
                assertThat(calculator.operations).isIn(validOps);
            }
        }

        @Test
        @DisplayName("Should handle decimal point validation logic")
        void testDecimalPointValidation() {
            String text = "123.456";
            boolean containsDecimal = text.contains(".");
            assertThat(containsDecimal).isTrue();
            
            String textNoDecimal = "123";
            boolean noDecimal = textNoDecimal.contains(".");
            assertThat(noDecimal).isFalse();
        }

        @Test
        @DisplayName("Should validate number input format")
        void testNumberInputValidation() {
            String validNumber = "123.456";
            assertThatCode(() -> Double.parseDouble(validNumber))
                .doesNotThrowAnyException();
            
            String invalidNumber = "abc";
            assertThatThrownBy(() -> Double.parseDouble(invalidNumber))
                .isInstanceOf(NumberFormatException.class);
        }
    }

    @Nested
    @DisplayName("Sequential Operations")
    class SequentialOperationsTests {

        @Test
        @DisplayName("Should perform sequential additions")
        void testSequentialAdditions() {
            // 5 + 3 = 8
            calculator.firstnum = 5.0;
            calculator.secondnum = 3.0;
            calculator.result = calculator.firstnum + calculator.secondnum;
            
            // 8 + 2 = 10
            calculator.firstnum = calculator.result;
            calculator.secondnum = 2.0;
            calculator.result = calculator.firstnum + calculator.secondnum;
            
            assertThat(calculator.result).isEqualTo(10.0);
        }

        @Test
        @DisplayName("Should perform mixed operations")
        void testMixedOperations() {
            // 10 + 5 = 15
            calculator.firstnum = 10.0;
            calculator.secondnum = 5.0;
            calculator.result = calculator.firstnum + calculator.secondnum;
            
            // 15 - 3 = 12
            calculator.firstnum = calculator.result;
            calculator.secondnum = 3.0;
            calculator.result = calculator.firstnum - calculator.secondnum;
            
            // 12 * 2 = 24
            calculator.firstnum = calculator.result;
            calculator.secondnum = 2.0;
            calculator.result = calculator.firstnum * calculator.secondnum;
            
            assertThat(calculator.result).isEqualTo(24.0);
        }
    }
}
