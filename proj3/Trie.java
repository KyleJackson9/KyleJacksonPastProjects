/**
 * Prefix-Trie. Supports linear time find() and insert(). 
 * Should support determining whether a word is a full word in the 
 * Trie or a prefix.
 * @author Kyle Jackson
 */
public class Trie {

	private static final int r = 255;
	public boolean isWord;
	public Trie[] links;
	public boolean foundFullWord;

	public Trie() {
		links = new Trie[r];
		isWord = false;
		foundFullWord = false;
	}

    public boolean find(String s, boolean isFullWord) {
    	int found = find(s);
    	if (foundFullWord) {
    		return true;
    	} else if (!isFullWord && found == s.length()) {
    		return true;
    	} 
    	return false;

    }

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
    public static void main(String[] args) {
    Trie t = new Trie();
    t.insert("hello");
    t.insert("hey");
    t.insert("goodbye");
    System.out.println(t.find("hell", false));
    System.out.println(t.find("hello", true));
    System.out.println(t.find("good", false));
    System.out.println(t.find("bye", false));
    System.out.println(t.find("heyy", false));
    System.out.println(t.find("hell", true));   
}
}
