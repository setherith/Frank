package frank;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import core.Lookup;

public class LookupResultsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String query;
	private Lookup lup;
	
	public LookupResultsWindow(String query) {
		
		this.query= query; 
		
		setSize(400, 400);
		setLayout(null);
		setTitle("TMDB Lookup");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		String[] columns = new String[] {"Id", "Name"};
		
		lup = new Lookup(query);
		String[][] results = lup.getCandidates().get();
		
		JTable series = new JTable(results, columns);
		
		JScrollPane scroll = new JScrollPane(series);
		scroll.setBounds(5, 5, 390, 390);
		
		add(scroll);
		
		
		setVisible(true);
	}

}
