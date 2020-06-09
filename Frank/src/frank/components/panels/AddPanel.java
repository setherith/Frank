package frank.components.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.RenameEngine;
import frank.FrankGUI;

public class AddPanel extends JPanel {

	private static final long serialVersionUID = -5734343878943396176L;
	
	private static FrankGUI gui;
	
	private JTextField txtAdd;
	private JSpinner sldAdd;

	public AddPanel(FrankGUI gui) {

		AddPanel.gui = gui;

		setLayout(null);

		// Add panel code here...
		JLabel lblAdd = new JLabel("What to add:");
		lblAdd.setBounds(5, 5, 100, 25);

		add(lblAdd);

		txtAdd = new JTextField();
		txtAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Update();
			}
		});
		txtAdd.setBounds(110, 5, 550, 25);
		add(txtAdd);

		sldAdd = new JSpinner(new SpinnerNumberModel(0, 0, 256, 1));
		sldAdd.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Update();
			}
		});
		
		sldAdd.setBounds(110, 35, 50, 25);
		add(sldAdd);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(560, 35, 99, 25);
		add(btnAdd);
	}
	
	private void Update() {
		RenameEngine.Add(txtAdd.getText(), (int) sldAdd.getValue());
	}

}
