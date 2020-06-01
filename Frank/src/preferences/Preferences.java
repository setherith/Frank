package preferences;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Preferences {

	private Properties props;
	
	public Preferences() {
		props = new Properties();
		InputStream f = getClass().getResourceAsStream("/frank.conf");
		try {
			props.load(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPreference(String name) {
		return props.getProperty(name);
	}
}
