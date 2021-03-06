package frank.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import common.Dialogs;
import frank.ConfigurationScreen;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 5461153009699588813L;
	
	public MainMenu() {
		
		JMenu file = new JMenu("File");
		JMenu about = new JMenu("About");
		
		JMenuItem options = new JMenuItem("Options...");
		options.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfigurationScreen();
			}
		});
		
		JMenuItem exit = new JMenuItem("Exit...");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Dialogs.ConfirmExit();
			}
		});
		
		JMenuItem aboutPane = new JMenuItem("About...");
		aboutPane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Frank: The file system renaming tool for all operating systems.\nDeveloped by Shane Pudner (setherith@gmail.com)", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		file.add(options);
		file.add(exit);
		
		about.add(aboutPane);
		
		this.add(file);
		this.add(about);
	}
}
