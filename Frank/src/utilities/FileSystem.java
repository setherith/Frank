package utilities;

import java.io.File;
import java.nio.file.Path;

import preferences.Preferences;

public class FileSystem {
    
    public static boolean PathExists(Path path) {
        return path.toFile().exists();
    }
    
    public static boolean PathIsDirectory(Path path) {
        return path.toFile().isDirectory();
    }

    public static String GetApplicationPath() {
        return System.getProperty("user.dir");
    }
    
    public static String[] GetDriveList() {
        File[] roots = File.listRoots();
        String[] results = new String[roots.length];
        for (int i = 0; i < roots.length; i++) {
            results[i] = roots[i].toString();
        }
        return results;
    }
    
    public static String[] GetFileList(String path) {
        File directory = new File(path);
        File[] allFiles = directory.listFiles();
        String[] result = new String[allFiles.length];
        for (int i = 0; i < allFiles.length; i++) {
            result[i] = allFiles[i].getName();
        }
        return result;
    }
    
    public static boolean Rename(Path origin, Path destination) {
        if (PathExists(destination)) return false;
        if (PathIsDirectory(origin) || PathIsDirectory(destination)) return false;
        if (!PathExists(origin)) return false;
        Preferences prefs = new Preferences();
        String debug = prefs.getPreference("debug");
        if (debug.equals("true")) {
        	System.out.println(String.format("%s -> %s", origin, destination));
        	return true;
        } else {
        	return origin.toFile().renameTo(destination.toFile());
        }
    }
}
