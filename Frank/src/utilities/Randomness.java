package utilities;

public class Randomness {
    
    /**
     * @param <T> Any enumerable object
     * @param array An array object
     * @return Object of type within passed in array
     */
    public static <T> T GetRandomElement(T[] array) {
        int length = array.length;
        int element = (int) (Math.random() * length);
        return array[element];
    }
}
