package frank.components.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Lookup;
import frank.FrankGUI;
import frank.LookupResultsWindow;

public class LookupPanel extends JPanel {

	private static final long serialVersionUID = -5734343878943396176L;

	private JTextField txtQuery;

	public LookupPanel(FrankGUI gui) {

		setLayout(null);

		JLabel lblQuery = new JLabel("Find:");
		lblQuery.setBounds(5, 5, 100, 25);
		add(lblQuery);

		txtQuery = new JTextField();
		txtQuery.setBounds(110, 5, 550, 25);
		add(txtQuery);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(560, 35, 99, 25);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Update();
			}
		});
		add(btnSearch);
	}

	private void Update() {
		if (txtQuery.getText().isEmpty()) return;
		new LookupResultsWindow(txtQuery.getText());
	}

}
