package frank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import common.Frame;
import preferences.Preferences;

public class ConfigurationScreen extends Frame {

	private static final long serialVersionUID = -854378945145343327L;

	private Preferences p;
	
	private Hashtable<String, String> initialStates = new Hashtable<String, String>();
	
	private JToggleButton togConfirmClose;
	private JToggleButton togShowSplash;
	
	public ConfigurationScreen() {
		
		p = new Preferences();
		
		// get initial property states...
		for (String property : p.getProperties()) {
			initialStates.put(property, p.getPreference(property));
		}
		
		setSize(400, 400);
		setLayout(null);
		setTitle("Options...");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// Confirm Close...
		JLabel lblConfirmClose = new JLabel("Ask before closing application?");
		lblConfirmClose.setBounds(10, 10, 200, 25);
		
		togConfirmClose = new JToggleButton();
		togConfirmClose.setBounds(340, 10, 25, 25);
		
		boolean confirmClose = p.getPreference("confirm_close").equals("true");
		togConfirmClose.setSelected(confirmClose);
		
		add(lblConfirmClose);
		add(togConfirmClose);

		// Display Slash...
		JLabel lblShowSplash = new JLabel("Show splash screen on start-up?");
		lblShowSplash.setBounds(10, 45, 200, 25);
		
		togShowSplash = new JToggleButton();
		togShowSplash.setBounds(340, 45, 25, 25);
		
		boolean showSplash = p.getPreference("display_splash").equals("true");
		togShowSplash.setSelected(showSplash);
		
		add(lblShowSplash);
		add(togShowSplash);
		
		JButton btnSave = new JButton("Save & Close");
		btnSave.setBounds(10, 325, 360, 25);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAndClose();
			}
		});
		add(btnSave);
		
		setVisible(true);
	}
	
	private void saveAndClose() {
		
		// check for differences...
		if (togShowSplash.isSelected() != initialStates.get("display_splash").equals("true")
				|| togConfirmClose.isSelected() != initialStates.get("confirm_close").equals("true")) {

			var response = JOptionPane.showConfirmDialog(null, "Changes have been detected, would you like to save them?", 
					"Changes", JOptionPane.YES_OPTION);
			
			if (response == JOptionPane.YES_OPTION) {
				
				// set changes...
				p.setPreference("display_splash", togShowSplash.isSelected() ? "true" : "false");
				p.setPreference("confirm_close", togConfirmClose.isSelected() ? "true" : "false");
				
				// save changes...
				p.savePreferences();
			}
		}
		
		setVisible(false);
	}
}
