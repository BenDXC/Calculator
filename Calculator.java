package calculator;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator {

	static char reset; /* Used for Task 2 */
	private JFrame frame; /* Initialise Frame */

	double firstnum; /* Variables to store each number before operations */
	double secondnum;
	double result;
	String operations; /* Variable for Addition, Subtraction, Multiplication, Division & Modulo */
	String Answer;
	static String Text; /* Used for Exit Function */
	private JTextField Txt_Display; /* Main Part of the Calculator's Display */

	/** * Launch the application */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** * Create the application. */
	public Calculator() {
		initialize();
	}

	/** * Initialise the contents of the frame */

	private void initialize() {
		/** -------------------Borders & Text-Box--------------------------- */
		frame = new JFrame();
		frame.setBounds(100, 100, 539, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Txt_Display = new JTextField();
		Txt_Display.setHorizontalAlignment(SwingConstants.RIGHT);
		Txt_Display.setBounds(10, 11, 503, 60);
		frame.getContentPane().add(Txt_Display);
		Txt_Display.setColumns(10);

		/** -------------------Row-1--------------------------- */

		JButton btn_C = new JButton("C");
		btn_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Txt_Display.setText(null);
			}
		});
		btn_C.setBounds(65, 95, 64, 43);
		frame.getContentPane().add(btn_C);

		JButton btn_BackSpace = new JButton("\uF0E7");
		btn_BackSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BackSpace = null;
				if (Txt_Display.getText().length() > 0) {
					StringBuilder str8 = new StringBuilder(Txt_Display.getText());
					str8 = str8.deleteCharAt(str8.length() - 1);
					BackSpace = str8.toString();
					Txt_Display.setText(BackSpace);
				}

			}
		});
		btn_BackSpace.setBounds(10, 95, 55, 43);
		frame.getContentPane().add(btn_BackSpace);

		JButton btn_Modulo = new JButton("%");
		btn_Modulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = Double.parseDouble(Txt_Display.getText());
				Txt_Display.setText(""); /* Actions for each button press */
				operations = "%";
			}
		});
		btn_Modulo.setBounds(126, 95, 64, 43);
		frame.getContentPane().add(btn_Modulo);

		JButton btn_Plus_Minus = new JButton("\u00B1");
		btn_Plus_Minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ops = Double.parseDouble(String.valueOf(Txt_Display.getText()));
				ops = ops * (-1); /* Actions for each button press */
				Txt_Display.setText(String.valueOf(ops));
			}
		});
		btn_Plus_Minus.setBounds(189, 95, 64, 43);
		frame.getContentPane().add(btn_Plus_Minus);

		/** -------------------Row-2--------------------------- */

		JButton btn_7 = new JButton("7");
		btn_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_7.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_7.setBounds(10, 139, 55, 43);
		frame.getContentPane().add(btn_7);

		JButton btn_8 = new JButton("8");
		btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_8.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_8.setBounds(65, 139, 64, 43);
		frame.getContentPane().add(btn_8);

		JButton btn_9 = new JButton("9");
		btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_9.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_9.setBounds(126, 139, 64, 43);
		frame.getContentPane().add(btn_9);

		/** -------------------Row-3--------------------------- */
		JButton btn_6 = new JButton("6");
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_6.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_6.setBounds(126, 179, 64, 43);
		frame.getContentPane().add(btn_6);

		JButton btn_5 = new JButton("5");
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_5.getText();
				Txt_Display.setText(EnterNumber); /* Actions for each button press */
			}
		});
		btn_5.setBounds(65, 179, 64, 43);
		frame.getContentPane().add(btn_5);

		JButton btn_4 = new JButton("4");
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_4.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_4.setBounds(10, 179, 55, 43);
		frame.getContentPane().add(btn_4);

		/** -------------------Row-4--------------------------- */
		JButton btn_3 = new JButton("3");
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_3.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_3.setBounds(126, 221, 64, 43);
		frame.getContentPane().add(btn_3);

		JButton btn_2 = new JButton("2");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_2.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_2.setBounds(65, 221, 64, 43);
		frame.getContentPane().add(btn_2);

		JButton btn_1 = new JButton("1");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_1.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_1.setBounds(10, 221, 64, 43);
		frame.getContentPane().add(btn_1);

		JButton btn_0 = new JButton("0");
		btn_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = Txt_Display.getText() + btn_0.getText(); /* Actions for each button press */
				Txt_Display.setText(EnterNumber);
			}
		});
		btn_0.setBounds(10, 265, 70, 43);
		frame.getContentPane().add(btn_0);

		/** -------------------Task 1 Calculations--------------------------- */
		JButton btn_Equals = new JButton("=");
		btn_Equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Answer;
				secondnum = Double.parseDouble(Txt_Display.getText());
				if (operations == "+") {
					result = firstnum + secondnum;
					Answer = String.valueOf(result);
					Txt_Display.setText(Answer);
				} else if (operations == "-") {
					result = firstnum - secondnum;
					Answer = String.valueOf(result);
					Txt_Display.setText(Answer);
				} else if (operations == "*") {
					result = firstnum * secondnum;
					Answer = String.valueOf(result);
					Txt_Display.setText(Answer);
				} else if (operations == "/") {
					result = firstnum / secondnum;
					Answer = String.valueOf(result);
					;
					Txt_Display.setText(Answer);
				} else if (operations == "%") {
					result = firstnum % secondnum;
					Answer = String.valueOf(result);
					;
					Txt_Display.setText(Answer);
				} else {
					Txt_Display.setText("Error");
				}
			}
		});
		btn_Equals.setBounds(126, 265, 64, 43);
		frame.getContentPane().add(btn_Equals);

		/** -------------------Decimal Point--------------------------- */

		JButton btn_Decimal = new JButton(".");
		btn_Decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Txt_Display.getText().contains(".")) {
					Txt_Display.setText(Txt_Display.getText() + btn_Decimal.getText());
				}
			}
		});
		btn_Decimal.setBounds(75, 265, 54, 43);
		frame.getContentPane().add(btn_Decimal);

		/** -------------------Operators--------------------------- */

		JButton btn_Plus = new JButton("+");
		btn_Plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = Double.parseDouble(Txt_Display.getText());
				Txt_Display.setText("");
				operations = "+";
			}
		});
		btn_Plus.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Plus.setBounds(189, 139, 64, 43);
		frame.getContentPane().add(btn_Plus);

		JButton btn_Minus = new JButton("-");
		btn_Minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = Double.parseDouble(Txt_Display.getText());
				Txt_Display.setText("");
				operations = "-";
			}
		});
		btn_Minus.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Minus.setBounds(189, 183, 64, 43);
		frame.getContentPane().add(btn_Minus);

		JButton btn_Multiply = new JButton("*");
		btn_Multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = Double.parseDouble(Txt_Display.getText());
				Txt_Display.setText("");
				operations = "*";
			}
		});
		btn_Multiply.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Multiply.setBounds(189, 221, 64, 43);
		frame.getContentPane().add(btn_Multiply);

		JButton btn_Divide = new JButton("/");
		btn_Divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = Double.parseDouble(Txt_Display.getText());
				Txt_Display.setText("");
				operations = "/";
			}
		});
		btn_Divide.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Divide.setBounds(189, 265, 64, 43);
		frame.getContentPane().add(btn_Divide);

		/** -------------------Tasks 2-8 Buttons--------------------------- */

		JButton btnPowerMandR = new JButton("Power, Root and Modulo of 2 numbers");
		btnPowerMandR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complexCalculations();
			}
		});
		btnPowerMandR.setBounds(257, 82, 275, 23);
		frame.getContentPane().add(btnPowerMandR);

		JButton btnTemperature = new JButton("Convert to Celsuis or Fahreheit");
		btnTemperature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conversionsofTemperatureUnits();
			}
		});
		btnTemperature.setBounds(257, 115, 275, 23);
		frame.getContentPane().add(btnTemperature);

		JButton btnMetricImperial = new JButton("Convert to Metric and Imperial");
		btnMetricImperial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conversionsOfMassandImperialUnits();
			}
		});
		btnMetricImperial.setBounds(263, 149, 269, 23);
		frame.getContentPane().add(btnMetricImperial);

		JButton btnDecimaltoBinary = new JButton("Convert to Binary");
		btnDecimaltoBinary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				converttoBinary();
			}
		});
		btnDecimaltoBinary.setBounds(263, 179, 269, 23);
		frame.getContentPane().add(btnDecimaltoBinary);

		JButton btnBinarytoDenary = new JButton("Convert to Decimal");
		btnBinarytoDenary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				converttoDenary();
			}
		});
		btnBinarytoDenary.setBounds(263, 213, 269, 23);
		frame.getContentPane().add(btnBinarytoDenary);

		JButton btnConverttoHexadecimal = new JButton("Convert to Hexadecimal");
		btnConverttoHexadecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				converttoHexadecimal();
			}
		});
		btnConverttoHexadecimal.setBounds(263, 241, 269, 23);
		frame.getContentPane().add(btnConverttoHexadecimal);

		JButton btnConverttoDenary = new JButton("Convert to Decimal");
		btnConverttoDenary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				converttoDenaryH();
			}
		});
		btnConverttoDenary.setBounds(263, 275, 250, 23);
		frame.getContentPane().add(btnConverttoDenary);
	}

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
		exitFunction();
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
		exitFunction();
	}

	public static void conversionsOfMassandImperialUnits() {
		int number; // Declares the variable number
		String message = ("1:Centimetres, 2:Inches, 3:Grams, 4:Ounces ");
		number = Integer.parseInt(JOptionPane.showInputDialog(message)); // grabs input and places into variable number

		if (number == 1) {
			final double to_CM = 2.54; // Conversion rate as a constant
			double Answer; // variable for answer
			double number_CM; // variable for user input

			String Input = JOptionPane.showInputDialog("Enter a number in Inches to convert to Centimetres"); // grabs
																												// input
																												// and
																												// places
																												// into
																												// variable
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

			exitFunction();
		}

		else if (number == 2) {
			final double to_Inches = 0.3937; // Conversion rate as a constant
			double Answer;
			double inches_number;

			String Input = JOptionPane.showInputDialog("Enter a number in Centimetres to convert to Inches"); // grabs
																												// input
																												// and
																												// places
																												// into
																												// variable
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
			exitFunction();
		} else if (number == 3) {

			final double to_Grams = 28.35; // Conversion rate as a constant
			double Answer;
			double number_Grams;

			String Input = JOptionPane.showInputDialog("Enter a number in Ounces to convert to Grams"); // grabs user
																										// input
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
			exitFunction();
		} else if (number == 4) {
			final double to_Ounces = 0.035; // Conversion rate as a constant
			double Answer;
			double number_Ounces;

			String Input = JOptionPane.showInputDialog("Enter a number in Ounces to convert to Grams");// grabs user
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
			exitFunction();
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
		exitFunction();
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
					Decimal += temp * Math.pow(2, power); /*
															 * Each 1 and 0 are multiplied by Base 2 to change the
															 * number to Decimal
															 */
					Binary = Binary / 10;
					power++; /* Power increases after each column */
				}
			}
			JOptionPane.showMessageDialog(null, "The Decimal Number is: " + Decimal);
			exitFunction();
		} else {
			JOptionPane.showMessageDialog(null, "You can only input numbers 0 and 1", "Error",
					JOptionPane.ERROR_MESSAGE);
			converttoDenary();
		}

	}

	public static void converttoHexadecimal() {
		int number, remainder; // Stores Input Number and Remainder
		String result = ""; // Stores Hexadecimal number
		char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' }; /*
																											 * hexadecimal
																											 * number
																											 * digits
																											 */

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
		exitFunction();
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
		exitFunction();
	}

	/** Exit Function */
	public static void exitFunction() {
		String message = "Type Y to continue or N to exit";
		String user_input = JOptionPane.showInputDialog(message);
		char choice = user_input.charAt(0);
		if (choice == 'Y' || choice == 'y') {
			Calculator obj = new Calculator();
			Calculator.main(null);
			obj.initialize();
		} else {
			Calculator obj = new Calculator();
			JOptionPane.showMessageDialog(null, "Thanks for using our calculator");
			obj.Txt_Display.setText(Text);
			obj.frame.dispatchEvent(new WindowEvent(obj.frame, WindowEvent.WINDOW_CLOSING));
		}
	}
}
