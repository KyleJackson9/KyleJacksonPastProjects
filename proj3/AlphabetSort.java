import java.util.Scanner;
import java.util.HashSet;
/**
 * Alphabet Sort. Supports weird alphabets getting sorted. 
 * Should support determining whether a word is a full word in the 
 * Trie or a prefix.
 * @author Kyle Jackson
 */
public class AlphabetSort {
    private HashSet<Character> convert;
    private Trie t;
        /**
     * Initializes required data structures for conversion.
     */
    public AlphabetSort() {
        convert = new HashSet<Character>();
        t = new Trie();
    }
        /**
     * Initializes required data structures from parallel arrays.
     * @param sc  is the Scanner of System.in name.
     */
    public void sort(Scanner sc) {
        String line;  
        Scanner in = sc;
        char[] alpha;
        try {
            alpha = in.nextLine().toCharArray();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
       
        for (int j = 0; j < alpha.length; j++) {
            if (!convert.contains(alpha[j])) {
                convert.add(alpha[j]);
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (!in.hasNext()) {
            throw new IllegalArgumentException();
        }
        while (in.hasNext()) {
            line = in.nextLine();
            t.insert(line);
        }
        sorter(alpha, t, "");
        in.close();
    }
            /**
     * Spits out the sorted array.
     * @param alpha takes their weird alphabet
     * @param t takes the trie storing everything
     * @param s takes in the string they are building/testing
     */
    public void sorter(char[] alpha, Trie t, String s) {
        Trie x = t;
        if (t.isWord) {
            System.out.println(x.fullWord);
        }
        for (int i : alpha) {
            if (x.links.containsKey(i)) {
                s += (char) i;
                sorter(alpha, x.links.get(i), s);
            }

        }
    }

        /**
     * Spits out the sorted array.
     * @param args takes System.in
     */
    public static void main(String[] args) {
        AlphabetSort a = new AlphabetSort();
        Scanner sc = new Scanner(System.in);
        a.sort(sc);
        
    }
}
