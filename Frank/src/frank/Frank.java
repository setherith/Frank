package frank;

import preferences.Preferences;

public class Frank {

    public static void main(String[] args) {
    	
    	Preferences p = new Preferences();
    	boolean splash = p.getPreference("display_splash").equals("true");
    	
    	// Display splash screen...
    	if (splash) {
    		try {
    			Splash s = new Splash();
    			Thread.sleep(3000);
    			s.dispose();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	// Display main application...
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrankGUI ui = new FrankGUI();
                ui.setVisible(true);
            }
        });
    }
    
}
