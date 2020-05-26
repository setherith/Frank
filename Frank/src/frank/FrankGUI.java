package frank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.RenameEngine;
import utilities.FileSystem;

public class FrankGUI extends JFrame {

    private JButton btnRename;
    private JCheckBox chkRemoveAll;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuFrankGUI;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JLabel lblAfter;
    private JLabel lblBefore;
    private JLabel lblDrive;
    private JLabel lblPath;
    private JLabel lblRemove;
    private JList<String> lstAfter;
    private JList<String> lstBefore;
    private JComboBox<String> lstDrive;
    private JPanel pnlRemove;
    private JScrollPane scrollAfter;
    private JScrollPane scrollBefore;
    private JTabbedPane tabOptions;
    private JTextField txtPath;
    private JTextField txtRemove;
	
	private static final long serialVersionUID = 35470893526607351L;
	
	public FrankGUI() {
		lstDrive = new JComboBox<>();
        lblDrive = new JLabel();
        lblPath = new JLabel();
        txtPath = new JTextField();
        scrollBefore = new JScrollPane();
        lstBefore = new JList<>();
        scrollAfter = new JScrollPane();
        lstAfter = new JList<>();
        lblBefore = new JLabel();
        lblAfter = new JLabel();
        btnRename = new JButton();
        tabOptions = new JTabbedPane();
        pnlRemove = new JPanel();
        txtRemove = new JTextField();
        lblRemove = new JLabel();
        chkRemoveAll = new JCheckBox();
        jMenuFrankGUI = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenu2 = new JMenu();
        jMenuItem2 = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Frank");
        setBackground(java.awt.Color.white);
        setName("FrankGUI");
        setPreferredSize(new java.awt.Dimension(700, 500));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        getContentPane().add(lstDrive);
        lstDrive.setBounds(10, 30, 100, 30);

        lblDrive.setText("Drive / Mount:");
        getContentPane().add(lblDrive);
        lblDrive.setBounds(10, 10, -1, -1);

        lblPath.setText("Path:");
        getContentPane().add(lblPath);
        lblPath.setBounds(120, 10, -1, -1);

        txtPath.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                txtPathKeyReleased(evt);
            }
        });
        getContentPane().add(txtPath);
        txtPath.setBounds(120, 30, 560, 30);

        lstBefore.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstBefore.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                lstBeforeValueChanged(evt);
            }
        });
        scrollBefore.setViewportView(lstBefore);

        getContentPane().add(scrollBefore);
        scrollBefore.setBounds(10, 90, 328, 246);

        lstAfter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstAfter.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                lstAfterValueChanged(evt);
            }
        });
        scrollAfter.setViewportView(lstAfter);

        getContentPane().add(scrollAfter);
        scrollAfter.setBounds(350, 90, 330, 246);

        lblBefore.setText("Before:");
        getContentPane().add(lblBefore);
        lblBefore.setBounds(10, 70, -1, -1);
        
        lblAfter.setText("After:");
        getContentPane().add(lblAfter);
        lblAfter.setBounds(350, 70, -1, -1);

        btnRename.setText("Rename");
        btnRename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRenameActionPerformed(evt);
            }
        });
        getContentPane().add(btnRename);
        btnRename.setBounds(580, 400, 100, -1);

        pnlRemove.setLayout(null);

        txtRemove.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                txtRemoveKeyReleased(evt);
            }
        });
        pnlRemove.add(txtRemove);
        txtRemove.setBounds(150, 10, 310, 30);

        lblRemove.setText("What to remove:");
        pnlRemove.add(lblRemove);
        lblRemove.setBounds(10, 10, -1, 30);

        chkRemoveAll.setText("All?");
        pnlRemove.add(chkRemoveAll);
        chkRemoveAll.setBounds(470, 10, -1, 30);
        
        
        tabOptions.addTab("Remove", pnlRemove);

        getContentPane().add(tabOptions);
        tabOptions.setBounds(10, 340, 550, 90);

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuFrankGUI.add(jMenu1);

        jMenu2.setText("About");

        jMenuItem2.setText("About...");
        jMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuFrankGUI.add(jMenu2);

        setJMenuBar(jMenuFrankGUI);

        getAccessibleContext().setAccessibleDescription("Renaming is the game, any operating system is the aim!");

        pack();
        setLocationRelativeTo(null);

        // Get Drive List
        String[] drives = FileSystem.GetDriveList();
        lstDrive.removeAllItems();
        for (String drive : drives) {
            lstDrive.addItem(drive);    
        }
        
        // Get Current Working Directory
        String wd = FileSystem.GetApplicationPath();
        txtPath.setText(wd);
        
        // Populate File List
        UpdateFileLists();
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
    
    private void ConfirmExit() {
        int answer = JOptionPane.showConfirmDialog(null, "Exit application?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    private void jMenuItem1ActionPerformed(ActionEvent evt) {
        ConfirmExit();
    }

    private void jMenuItem2ActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Frank: The file system renaming tool for all operating systems.\nDeveloped by Shane Pudner (setherith@gmail.com)", "About", JOptionPane.INFORMATION_MESSAGE);    
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

    private void lstBeforeValueChanged(ListSelectionEvent evt) {
        lstAfter.setSelectedIndex(lstBefore.getSelectedIndex());
        ChangeDirectoryCheck(lstBefore.getSelectedValue());
    }

    private void lstAfterValueChanged(ListSelectionEvent evt) {
        lstBefore.setSelectedIndex(lstAfter.getSelectedIndex());
        ChangeDirectoryCheck(lstAfter.getSelectedValue());
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
    
    private void txtPathKeyReleased(KeyEvent evt) {
        Update();
    }

    private void formWindowClosing(WindowEvent evt) {
        ConfirmExit();
    }

    private String[] ListModelToArray(ListModel<String> model) {
        String[] results = new String[model.getSize()];
        for (int i = 0; i < model.getSize(); i++) {
            results[i] = model.getElementAt(i).toString();
        }
        return results;
    }
    
    private void txtRemoveKeyReleased(KeyEvent evt) {
        String remove = txtRemove.getText();
        if (remove.isEmpty()) return;
        lstAfter.removeAll();
        String[] before = ListModelToArray(lstBefore.getModel());
        String[] after = RenameEngine.RemoveAllInstancesOf(remove, before, false);
        LoadArrayIntoList(lstAfter, after);
    }

    private void LoadArrayIntoList(JList<String> control, String[] list) {
        DefaultListModel<String> afterModel = new DefaultListModel<String>();
        for (int i = 0; i < list.length; i++) {
            afterModel.addElement(list[i]);
        }
        control.setModel(afterModel);
    }
    
    public void SetLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrankGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrankGUI ui = new FrankGUI();
                ui.SetLookAndFeel();
                ui.setVisible(true);
            }
        });
    }
}