package common;

import javax.swing.JOptionPane;

import frank.FrankGUI;

public class Dialogs {

	public static void ConfirmExit() {
		if (FrankGUI.prefs.getPreference("confirm_close").equals("true")) {
			int answer = JOptionPane.showConfirmDialog(null, "Exit application?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (answer == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		} else {
			System.exit(0);
		}
	}
}
