import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class exitFunction extends run {
    /** Exit Function */
    public static void exitfunction() {
        String message = "Type Y to continue or N to exit";
        String user_input = JOptionPane.showInputDialog(message);
        char choice = user_input.charAt(0);
        if (choice == 'Y' || choice == 'y') {
            run obj = new run();
            run.main(null);
            obj.initialize();
        } else {
            run obj = new run();
            JOptionPane.showMessageDialog(null, "Thanks for using our calculator");
            obj.Txt_Display.setText(Text);
            obj.frame.dispatchEvent(new WindowEvent(obj.frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
