import java.util.LinkedList;
/**
 * Prefix-Trie. Supports linear time find() and insert(). 
 * Should support determining whether a word is a full word in the 
 * Trie or a prefix.
 * @author Kyle Jackson
 */
public class Trie {
        /**
     * Initializes required data structures from parallel arrays.
     * @param r  length of links.
     * @param isWord True if its a full word.
     * @param links All of the children of that Trie.
     * @param foundFullWord checks if found a full word.
     */
    private static final int r = 255;
    private boolean isWord;
    private Trie[] links;
    private boolean foundFullWord;

    public Trie() {
        links = new Trie[r];
        isWord = false;
        foundFullWord = false;
    }
        /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param term
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
     * @param s
     * @return int of how long the string got to before exiting
     */
    private int find(String s) {
        Trie sink = this;
        char[] charArray = s.toCharArray(); 
        int k = 0; 
        for (int i = 0; i < charArray.length; i++) {
            int indexOfChar = charArray[i];
            if (sink.links[indexOfChar] == null) {
                foundFullWord = false; 
                return k; 
            } 
            k++; 
            sink = sink.links[indexOfChar]; 
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
            if (sink.links[indexOfChar] != null) {
                sink = sink.links[indexOfChar]; 
            } else {
                sink.links[indexOfChar] = new Trie(); 
                sink = sink.links[indexOfChar]; 
            }
        }
        sink.isWord = true; 
    }

    public LinkedList<String> print(int size) {
        LinkedList rec = new LinkedList(); 
        final int BUFFER = 1024; 
        char[] buffer = new char[BUFFER];    
        doApply(rec, 0, buffer, this); 
        return rec; 
    } 

    private void doApply(LinkedList rec, int index, char buffer[], Trie t) {
        int i = 0; 
        if (t != null) {
            if (t.isWord) {
                rec.add(new String(buffer, 0, index));
                i++; 
            }
            int k; 
            for(k = 0; k < r; k++){
                if (t.links[k] != null) {
                    buffer[index] = (char) (k);
                    doApply(rec,index+1, buffer, t.links[k]);
                }
            }
        }       
    }
}
