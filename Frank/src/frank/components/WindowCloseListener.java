package frank.components;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import common.Dialogs;

public class WindowCloseListener extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		Dialogs.ConfirmExit();
	}
	
}
