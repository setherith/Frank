package frank.components.panels;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Engine;
import frank.FrankGUI;

public class ReplacePanel extends JPanel {

	private static final long serialVersionUID = -5734343878943396176L;
	private static FrankGUI gui;

	private JTextField txtFind;
	private JTextField txtReplace;

	public ReplacePanel(FrankGUI gui) {

		ReplacePanel.gui = gui;

		setLayout(null);

		JLabel lblFind = new JLabel("Find:");
		lblFind.setBounds(5, 5, 100, 25);

		JLabel lblReplace = new JLabel("replace:");
		lblReplace.setBounds(5, 35, 100, 25);

		add(lblFind);
		add(lblReplace);

		txtFind = new JTextField();
		txtFind.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				Update();
			}

		});
		txtFind.setBounds(110, 5, 550, 25);

		txtReplace = new JTextField();
		txtReplace.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				Update();
			}

		});
		txtReplace.setBounds(110, 35, 550, 25);

		add(txtFind);
		add(txtReplace);
	}

	private void Update() {
		if (txtFind.getText().isEmpty()) return;
		Engine.Replace(gui.files, txtFind.getText(), txtReplace.getText());
		gui.UpdateLists();
	}

}
