package frank;

/**
 *
 * @author Shane Pudner <setherith@gmail.com>
 */
public class Frank {

    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrankGUI ui = new FrankGUI();
                ui.SetLookAndFeel();
                ui.setVisible(true);
            }
        });
    }
    
}
