package tests;

import org.junit.Test;

import preferences.Preferences;

public class PrefsTests {

	@Test
	public void LoadPrefsTest() {
		Preferences sut = new Preferences();
		String result = sut.getPreference("version");
		assert(result.equals("0.1"));
	}
}
