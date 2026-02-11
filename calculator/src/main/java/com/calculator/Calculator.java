package com.calculator;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Main calculator application with GUI using Java Swing.
 * Provides basic arithmetic operations and advanced conversions.
 * 
 * <p>Thread-safety: This class should only be accessed from the EDT (Event Dispatch Thread).</p>
 * 
 * @since 1.0
 */
public class Calculator {

    // UI dimension constants
    private static final int FRAME_WIDTH = 539;
    private static final int FRAME_HEIGHT = 389;
    private static final int DISPLAY_HEIGHT = 60;
    private static final int BUTTON_HEIGHT = 43;
    
    // Font constants
    private static final Font OPERATOR_FONT = new Font("Tahoma", Font.BOLD, 11);
    private static final Font DISPLAY_FONT = new Font("Tahoma", Font.PLAIN, 18);
    
    // Display character limit to prevent overflow
    private static final int MAX_DISPLAY_LENGTH = 20;
    
    private final JFrame frame;
    private final JTextField display;
    private double firstNumber;
    private String currentOperation;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator calculator = new Calculator();
                calculator.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        this.frame = new JFrame("Calculator");
        this.display = new JTextField();
        initialize();
    }

    private void initialize() {
        frame.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Explicit for fixed layout
        frame.getContentPane().setLayout(null);

        // Display configuration
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(DISPLAY_FONT);
        display.setEditable(false); // Prevent manual editing - calculator buttons only
        display.setBounds(10, 11, 503, DISPLAY_HEIGHT);
        frame.getContentPane().add(display);

        // Control buttons (Row 1)
        createButton("←", 10, 95, 55, BUTTON_HEIGHT, e -> backspace());
        createButton("C", 65, 95, 64, BUTTON_HEIGHT, e -> clearDisplay());
        createButton("%", 126, 95, 64, BUTTON_HEIGHT, e -> setOperation("%"));
        createButton("±", 189, 95, 64, BUTTON_HEIGHT, e -> toggleSign());

        // Number buttons (Rows 2-4)
        createNumberButton("7", 10, 139, 55, BUTTON_HEIGHT);
        createNumberButton("8", 65, 139, 64, BUTTON_HEIGHT);
        createNumberButton("9", 126, 139, 64, BUTTON_HEIGHT);
        
        createNumberButton("4", 10, 179, 55, BUTTON_HEIGHT);
        createNumberButton("5", 65, 179, 64, BUTTON_HEIGHT);
        createNumberButton("6", 126, 179, 64, BUTTON_HEIGHT);
        
        createNumberButton("1", 10, 221, 64, BUTTON_HEIGHT);
        createNumberButton("2", 65, 221, 64, BUTTON_HEIGHT);
        createNumberButton("3", 126, 221, 64, BUTTON_HEIGHT);
        
        // Bottom row
        createNumberButton("0", 10, 265, 70, BUTTON_HEIGHT);
        createButton(".", 75, 265, 54, BUTTON_HEIGHT, e -> addDecimal());
        createButton("=", 126, 265, 64, BUTTON_HEIGHT, e -> calculate());

        // Operator buttons
        createButton("+", 189, 139, 64, BUTTON_HEIGHT, OPERATOR_FONT, e -> setOperation("+"));
        createButton("-", 189, 183, 64, BUTTON_HEIGHT, OPERATOR_FONT, e -> setOperation("-"));
        createButton("*", 189, 221, 64, BUTTON_HEIGHT, OPERATOR_FONT, e -> setOperation("*"));
        createButton("/", 189, 265, 64, BUTTON_HEIGHT, OPERATOR_FONT, e -> setOperation("/"));

        // Advanced function buttons
        createButton("Power, Root & Modulo", 257, 82, 275, 23, 
            e -> ConversionDialog.showComplexCalculations());
        createButton("Temperature Conversion", 257, 115, 275, 23, 
            e -> ConversionDialog.showTemperatureConversions());
        createButton("Metric/Imperial Units", 263, 149, 269, 23, 
            e -> ConversionDialog.showMetricImperialConversions());
        createButton("Decimal → Binary", 263, 179, 269, 23, 
            e -> ConversionDialog.showDecimalToBinary());
        createButton("Binary → Decimal", 263, 213, 269, 23, 
            e -> ConversionDialog.showBinaryToDecimal());
        createButton("Decimal → Hexadecimal", 263, 241, 269, 23, 
            e -> ConversionDialog.showDecimalToHexadecimal());
        createButton("Hexadecimal → Decimal", 263, 275, 250, 23, 
            e -> ConversionDialog.showHexadecimalToDecimal());
    }

    /**
     * Helper method to create a number button with reduced duplication.
     * Includes length limit to prevent display overflow.
     */
    private void createNumberButton(String text, int x, int y, int width, int height) {
        createButton(text, x, y, width, height, e -> appendToDisplay(text));
    }
    
    /**
     * Appends text to display with length limit check.
     */
    private void appendToDisplay(String text) {
        String currentText = display.getText();
        if (currentText.length() < MAX_DISPLAY_LENGTH) {
            display.setText(currentText + text);
        }
    }
    
    /**
     * Clears the display and resets state.
     */
    private void clearDisplay() {
        display.setText("");
        currentOperation = null;
        firstNumber = 0.0;
    }

    /**
     * Helper method to create a button with an action listener
     */
    private void createButton(String text, int x, int y, int width, int height, 
                            java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(action);
        frame.getContentPane().add(button);
    }

    /**
     * Overloaded helper to create button with custom font
     */
    private void createButton(String text, int x, int y, int width, int height, 
                            Font font, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(font);
        button.addActionListener(action);
        frame.getContentPane().add(button);
    }

    /**
     * Sets the current operation and stores the first number
     */
    private void setOperation(String operation) {
        if (!display.getText().isEmpty()) {
            try {
                firstNumber = Double.parseDouble(display.getText());
                currentOperation = operation;
                display.setText("");
            } catch (NumberFormatException e) {
                display.setText("Error");
            }
        }
    }

    /**
     * Calculates the result based on the stored operation.
     * Displays error messages for invalid operations.
     */
    private void calculate() {
        if (currentOperation == null || display.getText().isEmpty()) {
            return;
        }

        try {
            double secondNumber = Double.parseDouble(display.getText());
            double result = CalculationUtils.performOperation(
                firstNumber, secondNumber, currentOperation);
            
            // Format result to remove trailing zeros
            String resultStr = formatResult(result);
            display.setText(resultStr);
            currentOperation = null;
        } catch (NumberFormatException e) {
            display.setText("Invalid Number");
        } catch (ArithmeticException e) {
            // Show specific error for division/modulo by zero
            display.setText("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            display.setText("Invalid Operation");
        }
    }
    
    /**
     * Formats result to remove unnecessary trailing zeros.
     * E.g., 5.0 becomes "5", but 5.5 stays "5.5"
     */
    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        }
        return String.valueOf(result);
    }

    /**
     * Toggles the sign of the current number.
     * Uses negation instead of multiplication for clarity.
     */
    private void toggleSign() {
        String text = display.getText();
        if (text.isEmpty()) {
            return;
        }
        
        try {
            double value = Double.parseDouble(text);
            String result = formatResult(-value);
            display.setText(result);
        } catch (NumberFormatException e) {
            display.setText("Invalid Number");
        }
    }

    /**
     * Removes the last character from the display.
     * More efficient than substring when removing single character.
     */
    private void backspace() {
        String text = display.getText();
        if (!text.isEmpty()) {
            display.setText(text.substring(0, text.length() - 1));
        }
    }

    /**
     * Adds a decimal point if one doesn't already exist
     */
    private void addDecimal() {
        String text = display.getText();
        if (!text.contains(".")) {
            display.setText(text.isEmpty() ? "0." : text + ".");
        }
    }
}
