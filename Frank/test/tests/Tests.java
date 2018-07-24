package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repositories.TestFiles;
import utilities.FileSystem;

/**
 *
 * @author Shane Pudner <setherith@gmail.com>
 */
public class Tests {
    
    TestFiles tf;
    
    public Tests() {
        tf = new TestFiles();
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void CheckTestAreaExists() {
        assert (tf.CheckTestFilesExist());
    }
    
    @Test
    public void GetCurrentDirectory() {
        String result = FileSystem.GetApplicationPath();
        System.out.println(result);
        assert (result != null);
    }
    
    @Test
    public void GetListOfDrives() {
        String[] results = FileSystem.GetDriveList();
        for (String result : results) {
            System.out.println(result);
        }
        assert (results != null);
        assert (results.length > 0);
    }
}
