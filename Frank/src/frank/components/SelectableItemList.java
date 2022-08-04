package frank.components;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

import domain.SelectableItem;

public class SelectableItemList extends JPanel {

	private final int ROW_HEIGHT = 30;
	private final boolean STRIPE = true;
	private final Color ALT = new Color(181, 255, 250);
	private final Color DEFAULT = Color.white;

	private ListModel<SelectableItem> model;
	private int width;

	private static final long serialVersionUID = -5345407534839288843L;

	public SelectableItemList() {
		model = new DefaultListModel<SelectableItem>();
		setBackground(Color.red);
		setLayout(null);
	}

	public void setModel(ListModel<SelectableItem> model) {
		this.model = model;
		update();
	}

	private void update() {
		int y = 0;
		width = getWidth();
		
		for (int i = 0; i < model.getSize(); i++) {
			JPanel itemPane = new JPanel();
			itemPane.setLayout(null);
			Color background = (STRIPE && i % 2 == 0) ? ALT : DEFAULT;
			itemPane.setBackground(background);
			itemPane.setBounds(0, y, width, ROW_HEIGHT);
			
			JCheckBox chkBox = new JCheckBox();
			chkBox.setSelected(model.getElementAt(i).Selected);
			chkBox.setBackground(background);
			chkBox.setBounds(2, 5, 20, 20);
			itemPane.add(chkBox);
			
			JLabel lblLabel = new JLabel(model.getElementAt(i).Value);
			lblLabel.setBounds(25, 3, 1000, 25);
			itemPane.add(lblLabel);
			
			add(itemPane);
			y += ROW_HEIGHT;
		}
	}

}
