package frank.components.panels;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Engine;
import frank.FrankGUI;

public class RemovePanel extends JPanel {

	private static final long serialVersionUID = 901470878575222952L;

	private static FrankGUI gui;
	
	private JTextField txtRemove;
	
	public RemovePanel(FrankGUI gui) {
		
		RemovePanel.gui = gui;
		
		setLayout(null);
		
		JLabel lblRemove = new JLabel("What to remove:");
        lblRemove.setBounds(5, 5, 100, 25);
        
        add(lblRemove);
        
        txtRemove = new JTextField();
        txtRemove.addKeyListener(new KeyAdapter() {
        	
        	@Override
            public void keyReleased(KeyEvent evt) {
            	Update();
            }
        	
        });
        txtRemove.setBounds(110, 5, 550, 25);
        add(txtRemove);
	}
	
	private void Update() {
		String remove = txtRemove.getText();
        Engine.Remove(gui.files, remove);
        gui.UpdateLists();
	}
	
	public void Clear() {
		txtRemove.setText("");
	}
}