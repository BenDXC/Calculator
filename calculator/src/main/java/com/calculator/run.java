package calculator.src.main.java.com.calculator;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class run {

    protected JFrame frame; /* Initialise Frame */

    double firstnum; /* Variables to store each number before operations */
    double secondnum;
    double result;
    String operations; /* Variable for Addition, Subtraction, Multiplication, Division & Modulo */
    String Answer;
    static String Text; /* Used for Exit Function */
    protected JTextField Txt_Display; /* Main Part of the Calculator's Display */

    /** * Launch the application */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    run window = new run();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** * Create the application. */
    public run() {
        initialize();
    }

    /** * Initialise the contents of the frame */

    protected void initialize() {
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
                switch (operations) {
                    case "+":
                        result = firstnum + secondnum;
                        Answer = String.valueOf(result);
                        Txt_Display.setText(Answer);
                        break;
                    case "-":
                        result = firstnum - secondnum;
                        Answer = String.valueOf(result);
                        Txt_Display.setText(Answer);
                        break;
                    case "*":
                        result = firstnum * secondnum;
                        Answer = String.valueOf(result);
                        Txt_Display.setText(Answer);
                        break;
                    case "/":
                        result = firstnum / secondnum;
                        Answer = String.valueOf(result);
                        Txt_Display.setText(Answer);
                        break;
                    case "%":
                        result = firstnum % secondnum;
                        Answer = String.valueOf(result);
                        Txt_Display.setText(Answer);
                        break;
                    default:
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
                calculations.complexCalculations();
            }
        });
        btnPowerMandR.setBounds(257, 82, 275, 23);
        frame.getContentPane().add(btnPowerMandR);

        JButton btnTemperature = new JButton("Convert to Celsuis or Fahreheit");
        btnTemperature.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculations.conversionsofTemperatureUnits();
            }
        });
        btnTemperature.setBounds(257, 115, 275, 23);
        frame.getContentPane().add(btnTemperature);

        JButton btnMetricImperial = new JButton("Convert to Metric and Imperial");
        btnMetricImperial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculations.conversionsOfMassandImperialUnits();
            }
        });
        btnMetricImperial.setBounds(263, 149, 269, 23);
        frame.getContentPane().add(btnMetricImperial);

        JButton btnDecimaltoBinary = new JButton("Convert to Binary");
        btnDecimaltoBinary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculations.converttoBinary();
            }
        });
        btnDecimaltoBinary.setBounds(263, 179, 269, 23);
        frame.getContentPane().add(btnDecimaltoBinary);

        JButton btnBinarytoDenary = new JButton("Convert to Decimal");
        btnBinarytoDenary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculations.converttoDenary();
            }
        });
        btnBinarytoDenary.setBounds(263, 213, 269, 23);
        frame.getContentPane().add(btnBinarytoDenary);

        JButton btnConverttoHexadecimal = new JButton("Convert to Hexadecimal");
        btnConverttoHexadecimal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculations.converttoHexadecimal();
            }
        });
        btnConverttoHexadecimal.setBounds(263, 241, 269, 23);
        frame.getContentPane().add(btnConverttoHexadecimal);

        JButton btnConverttoDenary = new JButton("Convert to Decimal");
        btnConverttoDenary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculations.converttoDenaryH();
            }
        });
        btnConverttoDenary.setBounds(263, 275, 250, 23);
        frame.getContentPane().add(btnConverttoDenary);
    }
}
