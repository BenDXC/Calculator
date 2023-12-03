package calculator.src.main.java.com.calculator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class calculations extends run {
    static char reset; /* Used for Task 2 */

    /** -------Calculation-Tasks 2-8--------------- */
    public static void complexCalculations() {
        double num1 = 0.0;
        double num2 = 0.0;
        double result = 0.0;
        char operator;

        String message = ("Please Enter a number");
        String Input = JOptionPane.showInputDialog(message);

        /** Validation of User Input */
        Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
        Matcher m = p.matcher(Input);
        if (m.find()) {
            JOptionPane.showMessageDialog(null, "Please enter only numbers");
            complexCalculations();
        }
        num1 = Double.parseDouble(Input);

        String message1 = ("Please enter second number (if root: 2 - square, 3 - cube):");
        String Input1 = JOptionPane.showInputDialog(message1);

        /** Validation of User Input */
        Pattern p1 = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
        Matcher m1 = p1.matcher(Input);
        if (m1.find()) {
            JOptionPane.showMessageDialog(null, "Please enter only 2 and 3");
            complexCalculations();
        }
        num2 = Double.parseDouble(Input1);

        String message2 = ("Please enter operator (Power - p, root - r, Modulo - %): ");
        String Input2 = JOptionPane.showInputDialog(message2);
        operator = Input2.charAt(0); /* Ensures that p, r and % are true */

        switch (operator) {

            case 'p':
                result = Math.pow(num1, num2); /* Ensures that num1 and num2 are used together for Power */
            case '%':
                result = num1 % num2;
                break;
            case 'r':
                if (num2 == 2.0) {
                    result = Math.sqrt(num1); /* Square roots a number */
                } else if (num2 == 3.0) {
                    result = Math.cbrt(num1); /* Cube roots a number */
                } else {
                    JOptionPane.showMessageDialog(null, "You have to input numbers", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    complexCalculations(); /* Goes back to User Input */
                }
        }
        JOptionPane.showMessageDialog(null, "The result is: " + result); /* Outputs Answer */
        exitFunction.exitfunction();
    }

    public static void conversionsofTemperatureUnits() {
        String Input = JOptionPane.showInputDialog("Enter 1: Celsuis or 2: Fahrenheit");

        /** Validation of User Input */
        Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
        Matcher m = p.matcher(Input);
        if (m.find()) {
            JOptionPane.showMessageDialog(null, "Please enter only numbers");
            conversionsofTemperatureUnits();
        }
        int number = Integer.valueOf(Input);

        if (number == 1) {
            float C_Answer; // variable for answer
            int C_number;

            String User_Input = (JOptionPane.showInputDialog("Enter a number in Fahrenheit"));

            /** Validation of User Input */
            Pattern U1 = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
            Matcher U2 = U1.matcher(User_Input);
            if (U2.find()) {
                JOptionPane.showMessageDialog(null, "Please enter only numbers");
                conversionsofTemperatureUnits();
            }
            C_number = Integer.valueOf(User_Input);
            C_Answer = (float) ((C_number - 32) * (0.5556)); /* Casts into a float and computes calculation */
            JOptionPane.showMessageDialog(null, "The temperature in Celsuis is: " + C_Answer);
        } else if (number == 2) {
            float F_Answer; // variable for answer
            int F_number;

            String User_Input = (JOptionPane.showInputDialog("Enter a number in Celsuis"));

            /** Validation of User Input */
            Pattern U3 = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
            Matcher U4 = U3.matcher(User_Input);
            if (U4.find()) {
                JOptionPane.showMessageDialog(null, "Please enter only numbers");
                conversionsofTemperatureUnits();
            }
            F_number = Integer.valueOf(User_Input);
            F_Answer = (float) ((F_number * 9) / 5) + 32; /* Casts into a float and computes calculation */
            JOptionPane.showMessageDialog(null, "The temperature in Fahreneheit is: " + F_Answer);
        } else {
            JOptionPane.showMessageDialog(null, "You have to input numbers 1 or 2", "Error", JOptionPane.ERROR_MESSAGE);
            conversionsofTemperatureUnits(); // Loops until correct input is given
        }
        exitFunction.exitfunction();
    }

    public static void conversionsOfMassandImperialUnits() {
        int number; // Declares the variable number
        String message = ("1:Centimetres, 2:Inches, 3:Grams, 4:Ounces ");
        number = Integer.parseInt(JOptionPane.showInputDialog(message)); // grabs input and places into variable number

        if (number == 1) {
            final double to_CM = 2.54; // Conversion rate as a constant
            double Answer; // variable for answer
            double number_CM; // variable for user input

            String Input = JOptionPane.showInputDialog("Enter a number in Inches to convert to Centimetres");
            Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
            Matcher m = p.matcher(Input);
            if (m.find()) {
                JOptionPane.showMessageDialog(null, "Please enter only numbers");
                conversionsOfMassandImperialUnits();
            }
            number_CM = Double.parseDouble(Input);
            Answer = number_CM * to_CM; // it computes the conversion from Inches to CM
            JOptionPane.showMessageDialog(null,
                    "The number is " + number_CM + " Inches" + " Converted into Centimetres is " + Answer + "CM");

            exitFunction.exitfunction();
        }

        else if (number == 2) {
            final double to_Inches = 0.3937; // Conversion rate as a constant
            double Answer;
            double inches_number;

            String Input = JOptionPane.showInputDialog("Enter a number in Centimetres to convert to Inches");
            Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
            Matcher m = p.matcher(Input);
            if (m.find()) {
                JOptionPane.showMessageDialog(null, "Please enter only numbers");
                conversionsOfMassandImperialUnits();
            }

            inches_number = Double.parseDouble(Input);
            Answer = inches_number * to_Inches; // it computes the conversion from CM to Inches
            JOptionPane.showMessageDialog(null, "The number is " + inches_number + " Centimetres"
                    + " Converted into Inches is " + Answer + "Inches");
            exitFunction.exitfunction();
        } else if (number == 3) {

            final double to_Grams = 28.35; // Conversion rate as a constant
            double Answer;
            double number_Grams;

            String Input = JOptionPane.showInputDialog("Enter a number in Ounces to convert to Grams");
            Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
            Matcher m = p.matcher(Input);
            if (m.find()) {
                JOptionPane.showMessageDialog(null, "Please enter only numbers");
                conversionsOfMassandImperialUnits();
            }
            number_Grams = Double.parseDouble(Input);
            Answer = number_Grams * to_Grams; // Computes Answer into Grams
            JOptionPane.showMessageDialog(null,
                    "The number is " + number_Grams + " Ounces" + " Converted into Grams is " + Answer + "Grams");
            exitFunction.exitfunction();
        } else if (number == 4) {
            final double to_Ounces = 0.035; // Conversion rate as a constant
            double Answer;
            double number_Ounces;

            String Input = JOptionPane.showInputDialog("Enter a number in Ounces to convert to Grams");// grabs user //
                                                                                                       // input
            Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
            Matcher m = p.matcher(Input);
            if (m.find()) {
                JOptionPane.showMessageDialog(null, "Please enter only numbers");
                conversionsOfMassandImperialUnits();
            }
            number_Ounces = Double.parseDouble(Input);
            Answer = number * to_Ounces;// Computes Answer to Ounces
            JOptionPane.showMessageDialog(null,
                    "The number is " + number_Ounces + " Grams" + " Converted into Ounces is " + Answer + "Ounces");
            exitFunction.exitfunction();
        } else {
            JOptionPane.showMessageDialog(null, "You have to input numbers between 1 to 4", "Error",
                    JOptionPane.ERROR_MESSAGE);
            conversionsOfMassandImperialUnits(); // Loops until correct input is given
        }
    }

    public static void converttoBinary() {

        int num; // Stores Decimal number
        String Binary = ""; // Stores Binary Number

        String User_Input = JOptionPane.showInputDialog("Enter a Decimal Number"); // changes String into a integer

        /** Validation of User Input */
        Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
        Matcher m = p.matcher(User_Input);
        if (m.find()) {
            JOptionPane.showMessageDialog(null, "Please enter only numbers");
            converttoBinary();
        }

        num = Integer.parseInt(User_Input);
        for (; num != 0; num = num / 2) /* Number is not 0 and divided by 2 */
        {
            Binary = num % 2 + Binary; /* Number has found the remainder and added to Binary */
        }
        JOptionPane.showMessageDialog(null, "The Binary Number is: " + Binary);
        exitFunction.exitfunction();
    }

    public static void converttoDenary()

    {
        int Binary; // Stores Binary number
        int power = 0; /* Integer for power */
        int Decimal = 0; // Stores Binary Number

        String Input = JOptionPane.showInputDialog("Enter a binary number");

        /** Validation of User Input */
        Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^, 2-9]");
        Matcher m = p.matcher(Input);
        if (m.find()) {
            JOptionPane.showMessageDialog(null, "Please enter only 0 or 1");
            converttoDenary();
        }
        Binary = Integer.parseInt(Input); // changes String into a integer

        if (Binary >= 0) {
            while (true) {
                if (Binary == 0) {
                    break;
                } else {
                    int temp = Binary % 10; /* Binary Number is casted into Base 10 */
                    Decimal += temp * Math.pow(2, power);
                    Binary = Binary / 10;
                    power++; /* Power increases after each column */
                }
            }
            JOptionPane.showMessageDialog(null, "The Decimal Number is: " + Decimal);
            exitFunction.exitfunction();
        } else {
            JOptionPane.showMessageDialog(null, "You can only input numbers 0 and 1", "Error",
                    JOptionPane.ERROR_MESSAGE);
            converttoDenary();
        }

    }

    public static void converttoHexadecimal() {
        int number, remainder; // Stores Input Number and Remainder
        String result = ""; // Stores Hexadecimal number
        char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        String User_Input = JOptionPane.showInputDialog("Input a decimal number: ");

        /** Validation of User Input */
        Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
        Matcher m = p.matcher(User_Input);
        if (m.find()) {
            JOptionPane.showMessageDialog(null, "Please enter only numbers");
            converttoHexadecimal();
        }
        number = Integer.valueOf(User_Input);
        while (number > 0) {
            remainder = number % 16; /* When number is above 0, the number finds the remainder of 16 */
            result = hex[remainder] + result; /* The result is combined every time with the Char Array */
            number = number / 16; /* Then the number is divided by 16 to find the last digit */
        }
        JOptionPane.showMessageDialog(null, "The Hexadecimal number is: " + result + "\n");
        exitFunction.exitfunction();
    }

    public static void converttoDenaryH() {

        String digits = "0123456789ABCDEF";
        String hex;
        String message = "Input a Hexadecimal number:";
        hex = JOptionPane.showInputDialog(message);

        /** Validation of User Input */
        Pattern p = Pattern.compile("[G-Z,g-z,&%$#@!()*^]");
        Matcher m = p.matcher(hex);
        if (m.find()) {
            JOptionPane.showMessageDialog(null, "Please enter 0 - 9, A - F");
            converttoDenaryH(); /* Task 8 */
        }
        hex = hex.toUpperCase(); // Turns characters into UPPERCASE
        int val = 0; // Sets the output as 0
        for (int i = 0; i < hex.length(); i++) // The loop allows us to add 1 to the power when multiplying by 16
        {
            char c = hex.charAt(i); // Works out what column the number is in
            int d = digits.indexOf(c); // Turns each part of the number into decimal
            val = 16 * val + d; // Returns the outcome after multiplying by 16 and adding the decimal number
        }
        JOptionPane.showMessageDialog(null, "The Decimal number is: " + val);
        exitFunction.exitfunction();
    }

}
