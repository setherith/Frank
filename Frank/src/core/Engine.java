package core;

import java.nio.file.Paths;
import java.util.List;

import domain.Story;
import utilities.FileSystem;

public class Engine {
	
	public static void Rename(String root, List<Story> stories) {
		/*ListModel<String> before = lstBefore.getModel();
        ListModel<String> after = lstAfter.getModel();
        
        for (int i = 0; i < before.getSize(); i++) {
            String fileBefore = before.getElementAt(i);
            String fileAfter = after.getElementAt(i);
            
            FileSystem.Rename(Paths.get(txtPath.getText(), fileBefore).toAbsolutePath().toString(), 
                    Paths.get(txtPath.getText(), fileAfter).toAbsolutePath().toString());
        }*/
		
		for (Story s : stories) {
			// rename to something different first...
			FileSystem.Rename(Paths.get(root, s.getOriginalName()), Paths.get(root, s.getLatestName() + ".frank"));
			
			// then rename with the correct case next...
			FileSystem.Rename(Paths.get(root, s.getLatestName() + ".frank"), Paths.get(root, s.getLatestName()));
		}
		
	}
	
	public static void Replace(List<Story> stories, String find, String replace) {
		for (Story s : stories) {
			s.updateName(s.getSnapshot().replace(find, replace));
		}
	}
	
    public static void Remove(List<Story> stories, String text) {
    	for (Story s : stories) {
			s.updateName(s.getSnapshot().replace(text, ""));
    	}
    }
    
    public static void Add(List<Story> stories, String text, int index) {
    	for (Story s : stories) {
    		if (index <= s.getSnapshot().length()) {
    			String before = s.getSnapshot().substring(0, index);
    			String after = s.getSnapshot().substring(index);
    			s.updateName(before + text + after);
    		}
    	}
    }
}