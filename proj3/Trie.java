import java.util.LinkedList;
import java.util.HashMap;
/**
 * Prefix-Trie. Supports linear time find() and insert(). 
 * Should support determining whether a word is a full word in the 
 * Trie or a prefix.
 * @author Kyle Jackson
 */
public class Trie {

    private static final int MAX = 255;
    private boolean isWord;
    private HashMap<Integer, Trie> links;
    private boolean foundFullWord;
        /**
     * Initializes required data structures from parallel arrays.
     */
    public Trie() {
        links = new HashMap<Integer, Trie>();
        isWord = false;
        foundFullWord = false;
    }
        /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param s the string you try and find
     * @param isFullWord checks to see what type of word they looking for. 
     * @return true if full word is true and it was a full word. 
     */
    public boolean find(String s, boolean isFullWord) {
        int found = find(s);
        if (foundFullWord) {
            return true;
        } else if (!isFullWord && found == s.length()) {
            return true; 
        } 
        return false; 

    }
    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param s  string that is input to be found.
     * @return int of how long the string got to before exiting
     */
    private int find(String s) {
        Trie sink = this;
        char[] charArray = s.toCharArray(); 
        int k = 0; 
        for (int i = 0; i < charArray.length; i++) {
            int indexOfChar = charArray[i];
            if (sink.links.get(indexOfChar) == null) {
                foundFullWord = false; 
                return k; 
            } 
            k++; 
            sink = sink.links.get(indexOfChar); 
            foundFullWord = sink.isWord; 
        }
        return k; 
    }
    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param s a String to be inputted.
     */
    public void insert(String s) {
        if (s.equals("")) {
            throw new IllegalArgumentException();
        }
        Trie sink = this; 
        char[] charArray = s.toCharArray(); 
        for (int i = 0; i < charArray.length; i++) {
            int indexOfChar = charArray[i]; 
            if (sink.links.get(indexOfChar) != null) {
                sink = sink.links.get(indexOfChar); 
            } else {
                sink.links.put(indexOfChar, new Trie()); 
                sink = sink.links.get(indexOfChar); 
            }
        }
        sink.isWord = true; 
    }
    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param size a size of the total to be inputted.
     * @return LinkedList of all the words popping out of the print.
     */
    public LinkedList<String> print(int size) {
        LinkedList rec = new LinkedList(); 
        final int BUFFER = 1024; 
        char[] buffer = new char[BUFFER];    
        doApply(rec, 0, buffer, this); 
        return rec; 
    } 
    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param rec a String to be inputted.
     * @param index a int to see how deep.
     * @param buffer a character array to hold output.
     * @param t a Trie to test.
     */
    private void doApply(LinkedList rec, int index, char[] buffer, Trie t) {
        int i = 0; 
        if (t != null) {
            if (t.isWord) {
                rec.add(new String(buffer, 0, index));
                i++; 
            }
            int k; 
            for (k = 0; k < MAX; k++) {
                if (t.links.get(k) != null) {
                    buffer[index] = (char) (k);
                    doApply(rec, index + 1, buffer, t.links.get(k)); 
                }
            }
        }       
    }
}
