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
 */
public class Calculator {

    private JFrame frame;
    private JTextField display;
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
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Calculator");
        frame.setBounds(100, 100, 539, 389);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Display
        display = new JTextField();
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Tahoma", Font.PLAIN, 18));
        display.setBounds(10, 11, 503, 60);
        frame.getContentPane().add(display);
        display.setColumns(10);

        // Control buttons (Row 1)
        createButton("←", 10, 95, 55, 43, e -> backspace());
        createButton("C", 65, 95, 64, 43, e -> display.setText(""));
        createButton("%", 126, 95, 64, 43, e -> setOperation("%"));
        createButton("±", 189, 95, 64, 43, e -> toggleSign());

        // Number buttons (Rows 2-4)
        createNumberButton("7", 10, 139, 55, 43);
        createNumberButton("8", 65, 139, 64, 43);
        createNumberButton("9", 126, 139, 64, 43);
        
        createNumberButton("4", 10, 179, 55, 43);
        createNumberButton("5", 65, 179, 64, 43);
        createNumberButton("6", 126, 179, 64, 43);
        
        createNumberButton("1", 10, 221, 64, 43);
        createNumberButton("2", 65, 221, 64, 43);
        createNumberButton("3", 126, 221, 64, 43);
        
        // Bottom row
        createNumberButton("0", 10, 265, 70, 43);
        createButton(".", 75, 265, 54, 43, e -> addDecimal());
        createButton("=", 126, 265, 64, 43, e -> calculate());

        // Operator buttons
        Font operatorFont = new Font("Tahoma", Font.BOLD, 11);
        createButton("+", 189, 139, 64, 43, operatorFont, e -> setOperation("+"));
        createButton("-", 189, 183, 64, 43, operatorFont, e -> setOperation("-"));
        createButton("*", 189, 221, 64, 43, operatorFont, e -> setOperation("*"));
        createButton("/", 189, 265, 64, 43, operatorFont, e -> setOperation("/"));

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
     * Helper method to create a number button with reduced duplication
     */
    private void createNumberButton(String text, int x, int y, int width, int height) {
        createButton(text, x, y, width, height, e -> {
            display.setText(display.getText() + text);
        });
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
     * Calculates the result based on the stored operation
     */
    private void calculate() {
        if (currentOperation == null || display.getText().isEmpty()) {
            return;
        }

        try {
            double secondNumber = Double.parseDouble(display.getText());
            double result = CalculationUtils.performOperation(
                firstNumber, secondNumber, currentOperation);
            display.setText(String.valueOf(result));
            currentOperation = null;
        } catch (NumberFormatException e) {
            display.setText("Error");
        } catch (ArithmeticException e) {
            display.setText("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            display.setText("Error");
        }
    }

    /**
     * Toggles the sign of the current number
     */
    private void toggleSign() {
        if (!display.getText().isEmpty()) {
            try {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(value * -1));
            } catch (NumberFormatException e) {
                display.setText("Error");
            }
        }
    }

    /**
     * Removes the last character from the display
     */
    private void backspace() {
        String text = display.getText();
        if (text.length() > 0) {
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
