package frank.components.panels;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.Engine;
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
		txtAdd.addKeyListener(new KeyAdapter() {
			
			@Override
            public void keyReleased(KeyEvent evt) {
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
		Engine.Add(gui.files, txtAdd.getText(), (int) sldAdd.getValue());
		gui.UpdateLists();
	}

}
