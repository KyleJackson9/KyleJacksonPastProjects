import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;
/**
 * Implements autocomplete on prefixes for a given dictionary of terms and weights.
 * @author Kyle Jackson
 */
public class Autocomplete {
    TernarySearchTrie t;
    ArrayList<String> topMatch;
    PriorityQueue<TSTNode> tops;
    TreeMap<Integer, String> nothing;
    String[] terms;
    static int N;
    /**
     * Initializes required data structures from parallel arrays.
     * @param terms Array of terms.
     * @param weights Array of weights.
     */
    public Autocomplete(String[] terms, double[] weights) {
        t = new TernarySearchTrie();
        HashSet<String> check = new HashSet<String>();
        this.terms = terms;
        nothing = new TreeMap<Integer, String>();
        if (terms.length != weights.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < terms.length; i++) {
            t.insert(terms[i], weights[i]);
            if (weights[i] <= 0 || check.contains(terms[i])) {
                throw new IllegalArgumentException();
            }
            check.add(terms[i]);
            nothing.put((int) weights[i], terms[i]);
        }
    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param term the string to be weighed
     * @return a double of the weight
     */
    public double weightOf(String term) {
        double output = t.search(term);
        int check = (int) output;
        if (check == -1) {
            return 0.0;
        }
        return output;
    }

    /**
     * Return the top match for given prefix, or null if there is no matching term.
     * @param prefix Input prefix to match against.
     * @return Best (highest weight) matching string in the dictionary.
     */
    public String topMatch(String prefix) {
        //need to print out only those below that (fix Trie print by putting in prefix)
        tops = new PriorityQueue<TSTNode>();
        if (prefix.equals("")) {
            LinkedList<String> hold = new LinkedList(nothing.values());
            return hold.pollLast();
        // if (prefix.equals("")) {
        //     tops = t.traverseAll(1);
        } else {
            tops = t.prefixSearch(prefix, 1);
        }
        return tops.poll().word;
    }

    /**
     * Returns the top k matching terms (in descending order of weight) as an iterable.
     * If there are less than k matches, return all the matching terms.
     * @param prefix the prefix to be found its buddies. 
     * @param k the length of which to get the iterable.
     * @return an Iterable of String in the form of ArrayList of top matches.
     */
    public Iterable<String> topMatches(String prefix, int k) {
        if (k < 0) {
            throw new IllegalArgumentException();
        }

        topMatch = new ArrayList<String>();
        tops = new PriorityQueue<TSTNode>();
        int max = k;
        if (prefix.equals("")) {
            //tops = t.traverseAll(k);
            LinkedList<String> hold = new LinkedList(nothing.values());
            if (hold.size() < k) {
                k = hold.size();
            }
            for (int i = 0; i < k; i++) {
                topMatch.add((hold.pollLast()));
            }
            return topMatch;
        } else {
            tops = t.prefixSearch(prefix, k);
        }
        if (tops.size() < k) {
            max = tops.size();
        }
        for (int i = 0; i < max; i++) {
            topMatch.add(tops.poll().word);
        }
        return topMatch;
    }

    /**
     * Returns the highest weighted matches within k edit distance of the word.
     * If the word is in the dictionary, then return an empty list.
     * @param word The word to spell-check
     * @param dist Maximum edit distance to search
     * @param k    Number of results to return 
     * @return Iterable in descending weight order of the matches
     */
    public Iterable<String> spellCheck(String word, int dist, int k) {
        LinkedList<String> results = new LinkedList<String>();  
        /* YOUR CODE HERE; LEAVE BLANK IF NOT PURSUING BONUS */
        return results;
    }
    /**
     * Test client. Reads the data from the file, 
     * then repeatedly reads autocomplete queries from standard 
     * input and prints out the top k matching terms.
     * @param args takes the name of an input file and an integer k as command-line arguments
     */
    public static void main(String[] args) {
        // initialize autocomplete data structure
        In in = new In(args[0]);
        In in2 = new In(args[0]); 
        // double[] t = in2.readAllDoubles();
        // String[] w = in2.readAllStrings(); 
        // if (t.length != w.length) {
        //     throw new IllegalArgumentException();
        // }  
        N = in.readInt();
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
        }

        Autocomplete autocomplete = new Autocomplete(terms, weights);

        // process queries from standard input
        int k = Integer.parseInt(args[1]);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            for (String term : autocomplete.topMatches(prefix, k)) {
                StdOut.printf("%14.1f  %s\n", autocomplete.weightOf(term), term);
            }
        }
    }
}
