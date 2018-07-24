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
}
