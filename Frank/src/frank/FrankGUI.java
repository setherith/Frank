package frank;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import frank.components.MainMenu;
import frank.components.WindowCloseListener;
import frank.components.panels.AddPanel;
import frank.components.panels.RemovePanel;
import frank.components.panels.ReplacePanel;
import preferences.Preferences;
import utilities.FileSystem;

public class FrankGUI extends JFrame {

    private JButton btnRename;
    public JList<String> lstAfter;
    public JList<String> lstBefore;
    private JComboBox<String> lstDrive;
    private JScrollPane scrollAfter;
    private JScrollPane scrollBefore;
    private JTabbedPane tabOptions;
    private JTextField txtPath;
	
	private static final long serialVersionUID = 35470893526607351L;
	
	private static Preferences prefs;
	
	public FrankGUI() {
		
		prefs = new Preferences();
		
		// Initial setup...
		setTitle(String.format("Frank v%s", prefs.getPreference("version")));
		setLayout(null);
		
		try {
			InputStream is = getClass().getResourceAsStream("/logo.png");
			setIconImage(ImageIO.read(is));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		setPreferredSize(new Dimension(700, 500));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
		addWindowListener(new WindowCloseListener());
		setJMenuBar(new MainMenu(this));
        
        btnRename = new JButton();
        tabOptions = new JTabbedPane();
        JPanel pnlRemove = new RemovePanel(this);
        JPanel pnlAdd = new AddPanel(this);
        JPanel pnlReplace = new ReplacePanel(this);
        
        lstDrive = new JComboBox<String>();
        lstDrive.setBounds(5, 5, 100, 25);
        add(lstDrive);

        txtPath = new JTextField();
        txtPath.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
            	Update();
            }
        });
        txtPath.setBounds(110, 5, 540, 25);
        add(txtPath);
        
        JButton btnBrowse = new JButton(".");
        btnBrowse.setBounds(654, 5, 25, 24);
        btnBrowse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "Directories";
					}
					
					@Override
					public boolean accept(File f) {
						return f.isDirectory();
					}
				});
				int response = fc.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					txtPath.setText(f.getAbsolutePath());
					Update();
				}
			}
		});
        add(btnBrowse);

        lstBefore = new JList<String>();
        lstBefore.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstBefore.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                lstAfter.setSelectedIndex(lstBefore.getSelectedIndex());
                ChangeDirectoryCheck(lstBefore.getSelectedValue());
            }
        });
        
        scrollBefore = new JScrollPane();
        scrollBefore.setViewportView(lstBefore);
        scrollBefore.setBounds(5, 35, 335, 250);
        scrollBefore.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				// Sync before and after scrolling
				BoundedRangeModel scrollModel = scrollBefore.getVerticalScrollBar().getModel();
				scrollAfter.getVerticalScrollBar().setModel(scrollModel);
				
				
			}
		});
        add(scrollBefore);

        lstAfter = new JList<String>();
        lstAfter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        lstAfter.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent evt) {
        		lstBefore.setSelectedIndex(lstAfter.getSelectedIndex());
        		ChangeDirectoryCheck(lstAfter.getSelectedValue());
        	}
        });
        
        scrollAfter = new JScrollPane();
        scrollAfter.setViewportView(lstAfter);
        scrollAfter.setBounds(345, 35, 335, 250);
        scrollAfter.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// Sync before and after scrolling
				BoundedRangeModel scrollModel = scrollAfter.getVerticalScrollBar().getModel();
				scrollBefore.getVerticalScrollBar().setModel(scrollModel);
			}
		});
        add(scrollAfter);

        JLabel lblDrive = new JLabel("Drive / Mount:");
        lblDrive.setBounds(10, 10, -1, -1);
        add(lblDrive);

        JLabel lblPath = new JLabel("Path:");
        lblPath.setBounds(120, 10, -1, -1);
        add(lblPath);
        
        JLabel lblBefore = new JLabel("Before:");
        lblBefore.setBounds(10, 70, -1, -1);
        add(lblBefore);
        
        JLabel lblAfter = new JLabel("After:");
        lblAfter.setBounds(350, 70, -1, -1);
        add(lblAfter);

        btnRename.setText("Rename");
        btnRename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRenameActionPerformed(evt);
            }
        });
        btnRename.setBounds(580, 400, 100, 25);
        add(btnRename);

        tabOptions.addTab("Remove", pnlRemove);
        tabOptions.addTab("Add", pnlAdd);
        tabOptions.addTab("Replace", pnlReplace);

        add(tabOptions);
        tabOptions.setBounds(5, 285, 675, 100);

        pack();
        setLocationRelativeTo(null);

        // Get Drive List
        String[] drives = FileSystem.GetDriveList();
        lstDrive.removeAllItems();
        for (String drive : drives) {
            lstDrive.addItem(drive);    
        }
        
        // Setup start path
        String startingPath = prefs.getPreference("cd").isEmpty() ? FileSystem.GetApplicationPath() : prefs.getPreference("cd");
        txtPath.setText(startingPath);
        
        // Populate File List
        Update();
    }

    private void UpdateFileLists() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        model.clear();
        String wd = txtPath.getText();
        String[] files = FileSystem.GetFileList(wd);
        for (int i = 0; i < files.length; i++) {
            model.add(i, files[i]);    
        }
        lstBefore.setModel(model);
        lstAfter.setModel(model);
    }
    
    private void btnRenameActionPerformed(ActionEvent evt) {
        ListModel<String> before = lstBefore.getModel();
        ListModel<String> after = lstAfter.getModel();
        
        for (int i = 0; i < before.getSize(); i++) {
            String fileBefore = before.getElementAt(i);
            String fileAfter = after.getElementAt(i);
            
            FileSystem.Rename(Paths.get(txtPath.getText(), fileBefore).toAbsolutePath().toString(), 
                    Paths.get(txtPath.getText(), fileAfter).toAbsolutePath().toString());
        }

        Update();
    }

    private void ChangeDirectoryCheck(String path) {
        if (path == null || path.length() == 0) return;
        String domain = txtPath.getText();
        Path combination = Paths.get(domain, path);
        boolean isDir = combination.toFile().isDirectory();
        if (isDir) {
            txtPath.setText(combination.toAbsolutePath().toString());
            // update the lists here
            Update();
        }
    }
    
    private void Update() {
    	String path = txtPath.getText();
        if (FileSystem.PathExists(path) && FileSystem.PathIsDirectory(path)) {
            UpdateFileLists();
        }
    }
    
    public String[] ListModelToArray(ListModel<String> model) {
        String[] results = new String[model.getSize()];
        for (int i = 0; i < model.getSize(); i++) {
            results[i] = model.getElementAt(i).toString();
        }
        return results;
    }
    
    public void LoadArrayIntoList(JList<String> control, String[] list) {
        DefaultListModel<String> afterModel = new DefaultListModel<String>();
        for (int i = 0; i < list.length; i++) {
            afterModel.addElement(list[i]);
        }
        control.setModel(afterModel);
    }
}