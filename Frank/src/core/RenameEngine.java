package core;

public class RenameEngine {
	
    public static String[] RemoveAllInstancesOf(String remove, String[] input) {
    	
        String[] result = new String[input.length];
        
        for (int i = 0; i < input.length; i++) {
        	
            while(input[i].contains(remove)) {
                input[i] = input[i].replace(remove, "");
            }
            
            result[i] = input[i];
        }
        return result;
    }
}