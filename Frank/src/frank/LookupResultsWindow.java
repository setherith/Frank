package frank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

import core.Lookup;
import domain.SeasonItem;

public class LookupResultsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Lookup lup;
	
	public LookupResultsWindow(String query) {
		
		setSize(400, 400);
		setLayout(null);
		setTitle("Results");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		String[] columns = new String[] {"Id", "Name"};
		
		lup = new Lookup(query);
		String[][] results = lup.getCandidates().get();

		JList<String> lstEpisodes = new JList<String>();
		lstEpisodes.setBounds(5, 200, 200, 390);
		lstEpisodes.setVisible(false);
		add(lstEpisodes);
		
		JComboBox<String> cmbSeasons = new JComboBox<String>();
		cmbSeasons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) cmbSeasons.getSelectedItem();
				if (selected != "Select Season..." && selected != null) {
					System.out.println("Seasons changed to: " + cmbSeasons.getItemAt(0));
				}
			}
		});
		
		cmbSeasons.setBounds(5, 170, 390, 25);
		cmbSeasons.setVisible(false);
		add(cmbSeasons);
		
		JTable series = new JTable(results, columns);
		TableColumnModel columnModel = series.getColumnModel();
		columnModel.getColumn(0).setMaxWidth(60);
		ListSelectionModel selectionModel = series.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					Optional<List<SeasonItem>> seasons = lup.getSeasons(series.getValueAt(selectedRow, 0).toString());
					if (seasons.isPresent()) {
						cmbSeasons.removeAllItems();
						cmbSeasons.addItem("Select Season...");
						for (SeasonItem item : seasons.get()) {
							cmbSeasons.addItem(String.format("%s (%s)", item.getName(), item.getId()));
						}
						cmbSeasons.setVisible(true);
					} else {
						cmbSeasons.setVisible(false);
					}
				}
			}
		});
		
		JScrollPane scroll = new JScrollPane(series);
		scroll.setBounds(5, 5, 390, 150);
		add(scroll);
		setVisible(true);
	}
}
