package common;

import javax.swing.JOptionPane;

public class Dialogs {

	public static void ConfirmExit() {
		int answer = JOptionPane.showConfirmDialog(null, "Exit application?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
	}
}
