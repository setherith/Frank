package tests;

import core.RenameEngine;
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
    
    private String[] typical = new String[] {
        "supernatural.s07e01.720p.hdtv.x264-orenji.srt",
        "supernatural.s07e02.720p.hdtv.x264-2hd.srt",
        "supernatural.S07E03.720p.HDTV.x264-IMMERSE.srt",
        "supernatural.s07e04.720p.hdtv.x264-orenji.srt",
        "supernatural.s07e05.720p.hdtv.x264-compulsion.srt",
        "supernatural.S07E06.720p.HDTV.x264-IMMERSE.srt",
        "supernatural.S07E07.720p.HDTV.x264-IMMERSE.mkv",
        "supernatural.S07E07.720p.HDTV.x264-IMMERSE.srt",
        "supernatural.S07E08.720p.HDTV.x264-IMMERSE.mkv",
        "supernatural.S07E08.720p.HDTV.x264-IMMERSE.srt",
        "supernatural.s07e09.proper.720p.hdtv.x264-2hd.mkv",
        "supernatural.s07e09.proper.720p.hdtv.x264-2hd.srt",
        "supernatural.S07E10.720p.HDTV.x264-IMMERSE.mkv",
        "supernatural.S07E10.720p.HDTV.x264-IMMERSE.srt",
        "supernatural.S07E11.720p.HDTV.x264-IMMERSE.mkv",
        "supernatural.S07E11.720p.HDTV.x264-IMMERSE.srt",
        "supernatural.s07e12.720p.hdtv.x264-2hd.mkv",
        "supernatural.s07e12.720p.hdtv.x264-2hd.srt",
        "supernatural.S07E13.720p.HDTV.X264-DIMENSION.mkv",
        "supernatural.S07E13.720p.HDTV.X264-DIMENSION.srt",
        "supernatural.S07E14.720p.HDTV.X264-DIMENSION.mkv",
        "supernatural.S07E14.720p.HDTV.X264-DIMENSION.srt"
    };
    
    private String[] typicalMinusHdtv = new String[] {
        "supernatural.s07e01.720p..x264-orenji.srt",
        "supernatural.s07e02.720p..x264-2hd.srt",
        "supernatural.S07E03.720p..x264-IMMERSE.srt",
        "supernatural.s07e04.720p..x264-orenji.srt",
        "supernatural.s07e05.720p..x264-compulsion.srt",
        "supernatural.S07E06.720p..x264-IMMERSE.srt",
        "supernatural.S07E07.720p..x264-IMMERSE.mkv",
        "supernatural.S07E07.720p..x264-IMMERSE.srt",
        "supernatural.S07E08.720p..x264-IMMERSE.mkv",
        "supernatural.S07E08.720p..x264-IMMERSE.srt",
        "supernatural.s07e09.proper.720p..x264-2hd.mkv",
        "supernatural.s07e09.proper.720p..x264-2hd.srt",
        "supernatural.S07E10.720p..x264-IMMERSE.mkv",
        "supernatural.S07E10.720p..x264-IMMERSE.srt",
        "supernatural.S07E11.720p..x264-IMMERSE.mkv",
        "supernatural.S07E11.720p..x264-IMMERSE.srt",
        "supernatural.s07e12.720p..x264-2hd.mkv",
        "supernatural.s07e12.720p..x264-2hd.srt",
        "supernatural.S07E13.720p..X264-DIMENSION.mkv",
        "supernatural.S07E13.720p..X264-DIMENSION.srt",
        "supernatural.S07E14.720p..X264-DIMENSION.mkv",
        "supernatural.S07E14.720p..X264-DIMENSION.srt"
    };
    
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
    
    @Test
    public void RemovingWords() {
        String[] results = RenameEngine.RemoveAllInstancesOf("hdtv", typical, false);
        for (int i = 0; i < results.length; i++) {
            assert (results[i].equals(typicalMinusHdtv[i]));
        }
    }
}
