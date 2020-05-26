package repositories;

import interfaces.ITestFiles;
import java.io.File;
import utilities.FileSystem;

public class TestFiles implements ITestFiles {

    private String testPath = "/TestArea";

    @Override
    public boolean CheckTestFilesExist() {
        String path = FileSystem.GetApplicationPath();
        File f = new File(path + testPath);
        return f.exists();
    }

    @Override
    public void GenerateTestFiles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void DeleteTestFiles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
