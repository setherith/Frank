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
			FileSystem.Rename(Paths.get(root, s.getOriginalName()), Paths.get(root, s.getLatestName()));
		}
		
	}
	
    public static void Remove(List<Story> stories, String text) {
    	for (Story s : stories) {
			s.updateName(s.getSnapshot().replace(text, ""));
    	}
    }
    
    public static void Add(List<Story> stories, String text, int index) {
    	for (Story s : stories) {
    		s.updateName(s.getLatestName() + text);
    	}
    }
}