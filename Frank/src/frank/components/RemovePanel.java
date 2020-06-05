package frank.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.RenameEngine;
import frank.FrankGUI;

public class RemovePanel extends JPanel {

	private static final long serialVersionUID = 901470878575222952L;

	private static FrankGUI gui;
	
	public RemovePanel(FrankGUI gui) {
		
		RemovePanel.gui = gui;
		
		setLayout(null);
		
		JLabel lblRemove = new JLabel("What to remove:");
        lblRemove.setBounds(5, 5, 100, 25);
        
        add(lblRemove);
        
        JTextField txtRemove = new JTextField();
        txtRemove.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
            	String remove = txtRemove.getText();
                if (remove.isEmpty()) return;
                gui.lstAfter.removeAll();
                String[] before = gui.ListModelToArray(gui.lstBefore.getModel());
                String[] after = RenameEngine.RemoveAllInstancesOf(remove, before);
                gui.LoadArrayIntoList(gui.lstAfter, after);
            }
        });
        txtRemove.setBounds(110, 5, 310, 25);
        add(txtRemove);
        
        JCheckBox chkRemoveAll = new JCheckBox();
        chkRemoveAll.setText("All?");
        chkRemoveAll.setBounds(420, 5, 50, 25);
        add(chkRemoveAll);

	}
	
	
	
}
