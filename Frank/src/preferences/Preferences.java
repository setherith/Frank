package preferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Preferences {

	private Properties props;

	private final String ROOT_NAME = ".Frank";
	private final String PROPERTIES_NAME = "frank.conf";

	Path root = Paths.get(System.getProperty("user.home"), ROOT_NAME);

	public Preferences() {

		unpack();

		try {
			props = new Properties();
			InputStream is = new FileInputStream(Paths.get(root.toString(), PROPERTIES_NAME).toString());
			props.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getPreference(String name) {
		return props.getProperty(name);
	}

	private void unpack() {

		File rootDir = new File(root.toUri());
		if (!rootDir.exists()) {
			rootDir.mkdir();
		}

		File properties = new File(Paths.get(root.toString(), PROPERTIES_NAME).toUri());

		if (!properties.exists()) {

			InputStream is = this.getClass().getResourceAsStream("/" + PROPERTIES_NAME);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String currentLine = null;
			try {
				FileWriter fw = new FileWriter(properties);
				while ((currentLine = br.readLine()) != null) {
					fw.append(currentLine + "\n");
				}
				br.close();
				fw.close();

			} catch (IOException e) {
				return;
			}
		}

	}

	public void setPreference(String name, String value) {
		props.setProperty(name, value);
	}

	public void savePreferences() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yy HH:MM:ss");

			File properties = new File(Paths.get(root.toString(), PROPERTIES_NAME).toUri());
			OutputStream os = new FileOutputStream(properties);

			props.store(os, "Updated: " + formatter.format(System.currentTimeMillis()).toString());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] getProperties() {
		List<String> results = new ArrayList<String>();
		for (Object key : props.keySet()) {
			results.add(key.toString());
		}
		return results.toArray(new String[results.size()]);
	}
}