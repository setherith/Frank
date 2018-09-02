package core;

/**
 * @author Shane Pudner
 */
public class RenameEngine {
    public static String[] RemoveAllInstancesOf(String remove, String[] input, boolean caseSensative) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            if (caseSensative) {
                while(input[i].contains(remove)) {
                    input[i] = input[i].replace(remove, "");
                }
            } else {
                while(input[i].toLowerCase().contains(remove.toLowerCase())) {
                    input[i] = input[i].replace(remove.toLowerCase(), "");
                    input[i] = input[i].replace(remove.toUpperCase(), "");
                }
            }
            result[i] = input[i];
        }
        return result;
    }
}
