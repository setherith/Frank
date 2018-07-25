package utilities;

import java.io.File;

/**
 *
 * @author Shane Pudner <setherith@gmail.com>
 */
public class FileSystem {

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
        String[] all = directory.list();
        int fileCount = 0;
        for (String a : all) {
            if (new File(a).isFile()) {
                fileCount++;
            }
        }
        String[] result = new String[fileCount];
        int index = 0;
        for (String file : all) {
            if (new File(file).isFile()) {
                result[index] = file;
                index++;
            }
        }
        return result;
    }
}
