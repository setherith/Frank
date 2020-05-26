package frank;

public class Frank {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrankGUI ui = new FrankGUI();
                ui.setVisible(true);
            }
        });
    }
    
}
