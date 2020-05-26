package frank;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.UnsupportedLookAndFeelException;

import core.RenameEngine;
import utilities.FileSystem;

public class FrankGUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 35470893526607351L;
	public FrankGUI() {
        initComponents();
        InitUI();
    }

    public void InitUI() {
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
    
    private void initComponents() {

        lstDrive = new javax.swing.JComboBox<>();
        lblDrive = new javax.swing.JLabel();
        lblPath = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        scrollBefore = new javax.swing.JScrollPane();
        lstBefore = new javax.swing.JList<>();
        scrollAfter = new javax.swing.JScrollPane();
        lstAfter = new javax.swing.JList<>();
        lblBefore = new javax.swing.JLabel();
        lblAfter = new javax.swing.JLabel();
        btnRename = new javax.swing.JButton();
        tabOptions = new javax.swing.JTabbedPane();
        pnlRemove = new javax.swing.JPanel();
        txtRemove = new javax.swing.JTextField();
        lblRemove = new javax.swing.JLabel();
        chkRemoveAll = new javax.swing.JCheckBox();
        jMenuFrankGUI = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Frank");
        setBackground(java.awt.Color.white);
        setName("FrankGUI"); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
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

        txtPath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPathKeyReleased(evt);
            }
        });
        getContentPane().add(txtPath);
        txtPath.setBounds(120, 30, 560, 30);

        lstBefore.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstBefore.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstBeforeValueChanged(evt);
            }
        });
        scrollBefore.setViewportView(lstBefore);

        getContentPane().add(scrollBefore);
        scrollBefore.setBounds(10, 90, 328, 246);

        lstAfter.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstAfter.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
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
        btnRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenameActionPerformed(evt);
            }
        });
        getContentPane().add(btnRename);
        btnRename.setBounds(580, 400, 100, -1);

        pnlRemove.setLayout(null);

        txtRemove.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
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
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuFrankGUI.add(jMenu1);

        jMenu2.setText("About");

        jMenuItem2.setText("About...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuFrankGUI.add(jMenu2);

        setJMenuBar(jMenuFrankGUI);

        getAccessibleContext().setAccessibleDescription("Renaming is the game, any operating system is the aim!");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmExit() {
        int answer = JOptionPane.showConfirmDialog(null, "Exit application?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ConfirmExit();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JOptionPane.showMessageDialog(null, "Frank: The file system renaming tool for all operating systems.\nDeveloped by Shane Pudner (setherith@gmail.com)", "About", JOptionPane.INFORMATION_MESSAGE);    
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenameActionPerformed
        ListModel<String> before = lstBefore.getModel();
        ListModel<String> after = lstAfter.getModel();
        
        for (int i = 0; i < before.getSize(); i++) {
            String fileBefore = before.getElementAt(i);
            String fileAfter = after.getElementAt(i);
            
            FileSystem.Rename(Paths.get(txtPath.getText(), fileBefore).toAbsolutePath().toString(), 
                    Paths.get(txtPath.getText(), fileAfter).toAbsolutePath().toString());
        }

        Update();
    }//GEN-LAST:event_btnRenameActionPerformed

    private void lstBeforeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstBeforeValueChanged
        lstAfter.setSelectedIndex(lstBefore.getSelectedIndex());
        ChangeDirectoryCheck(lstBefore.getSelectedValue());
    }//GEN-LAST:event_lstBeforeValueChanged

    private void lstAfterValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstAfterValueChanged
        lstBefore.setSelectedIndex(lstAfter.getSelectedIndex());
        ChangeDirectoryCheck(lstAfter.getSelectedValue());
    }//GEN-LAST:event_lstAfterValueChanged

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
    
    private void txtPathKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPathKeyReleased
        Update();
    }//GEN-LAST:event_txtPathKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ConfirmExit();
    }//GEN-LAST:event_formWindowClosing

    private String[] ListModelToArray(ListModel<String> model) {
        String[] results = new String[model.getSize()];
        for (int i = 0; i < model.getSize(); i++) {
            results[i] = model.getElementAt(i).toString();
        }
        return results;
    }
    
    private void txtRemoveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRemoveKeyReleased
        String remove = txtRemove.getText();
        if (remove.isEmpty()) return;
        lstAfter.removeAll();
        String[] before = ListModelToArray(lstBefore.getModel());
        String[] after = RenameEngine.RemoveAllInstancesOf(remove, before, false);
        LoadArrayIntoList(lstAfter, after);
    }//GEN-LAST:event_txtRemoveKeyReleased

    private void LoadArrayIntoList(JList<String> control, String[] list) {
        DefaultListModel<String> afterModel = new DefaultListModel<String>();
        for (int i = 0; i < list.length; i++) {
            afterModel.addElement(list[i]);
        }
        control.setModel(afterModel);
    }
    
    public void SetLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrankGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrankGUI ui = new FrankGUI();
                ui.SetLookAndFeel();
                ui.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRename;
    private javax.swing.JCheckBox chkRemoveAll;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuFrankGUI;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JLabel lblAfter;
    private javax.swing.JLabel lblBefore;
    private javax.swing.JLabel lblDrive;
    private javax.swing.JLabel lblPath;
    private javax.swing.JLabel lblRemove;
    private javax.swing.JList<String> lstAfter;
    private javax.swing.JList<String> lstBefore;
    private javax.swing.JComboBox<String> lstDrive;
    private javax.swing.JPanel pnlRemove;
    private javax.swing.JScrollPane scrollAfter;
    private javax.swing.JScrollPane scrollBefore;
    private javax.swing.JTabbedPane tabOptions;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextField txtRemove;
    // End of variables declaration//GEN-END:variables
}
