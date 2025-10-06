package frank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

import core.Engine;
import core.Lookup;
import domain.EpisodeItem;
import domain.SeasonItem;
import domain.Story;

public class LookupResultsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Lookup lup;
	
	private int selectedId;
	private String selectedName;
	
	public LookupResultsWindow(String query, FrankGUI gui) {
		
		List<Story> stories = gui.files;
		
		lup = new Lookup(query);
		String[][] results = lup.getCandidates().get();

		String[] columns = new String[] {"Id", "Name"};
		JTable series = new JTable(results, columns);
		
		JButton btnSubmit = new JButton("Apply");
		btnSubmit.setBounds(5, 380, 390, 25);
		
		setSize(400, 450);
		setLayout(null);
		setTitle("Results");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JList<String> lstEpisodes = new JList<String>();
		
		JScrollPane sclEpisodes = new JScrollPane(lstEpisodes);
		sclEpisodes.setBounds(5, 190, 390, 185);
		add(sclEpisodes);
		sclEpisodes.setVisible(false);
		
		JComboBox<String> cmbSeasons = new JComboBox<String>();
		cmbSeasons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) cmbSeasons.getSelectedItem();
				if (selected != "Select Season..." && selected != null) {
					Optional<List<EpisodeItem>> episodes = lup.getEpisodes(selectedId, cmbSeasons.getSelectedIndex());
					if (episodes.isPresent()) {
						DefaultListModel<String> epispodeModel = new DefaultListModel<String>();
						for (EpisodeItem item : episodes.get()) {
							epispodeModel.addElement(String.format("%s - S%02dE%02d - %s", 
									selectedName, 
									cmbSeasons.getSelectedIndex(), 
									item.getEpisodeNumber(), 
									item.getName()));
						}
						lstEpisodes.setModel(epispodeModel);
						sclEpisodes.setVisible(true);
						btnSubmit.setVisible(true);
					}
				}
			}
		});
		
		cmbSeasons.setBounds(5, 160, 390, 25);
		cmbSeasons.setVisible(false);
		add(cmbSeasons);
		
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
					selectedId = Integer.valueOf(series.getValueAt(selectedRow, 0).toString());
					selectedName = series.getValueAt(selectedRow, 1).toString();
					Optional<List<SeasonItem>> seasons = lup.getSeasons(series.getValueAt(selectedRow, 0).toString());
					if (seasons.isPresent()) {
						cmbSeasons.removeAllItems();
						sclEpisodes.setVisible(false);
						btnSubmit.setVisible(false);
						cmbSeasons.addItem("Select Season...");
						for (SeasonItem item : seasons.get()) {
							cmbSeasons.addItem(String.format("%s", item.getName()));
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
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> episodes = (DefaultListModel<String>) lstEpisodes.getModel();
				int size = episodes.size();
				List<String> episodeNames = new ArrayList<String>();
				for (int i = 0; i < size; i++) episodeNames.add(episodes.elementAt(i));
				Engine.ReplaceWithList(stories, episodeNames);
				gui.UpdateLists();
				setVisible(false);
			}
		});
		btnSubmit.setVisible(false);
		add(btnSubmit);
	}
}
