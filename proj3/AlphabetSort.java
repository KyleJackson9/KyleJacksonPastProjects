import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.IOException;
import java.util.Scanner;
/**
 * Alphabet Sort. Supports weird alphabets getting sorted. 
 * Should support determining whether a word is a full word in the 
 * Trie or a prefix.
 * @author Kyle Jackson
 */
public class AlphabetSort {
    private HashMap<Character, Character> convert;
    private HashMap<Character, Character> convertBack;
    private Trie t;
        /**
     * Initializes required data structures for conversion.
     */
    public AlphabetSort() {
        convert = new HashMap<Character, Character>();
        convertBack = new HashMap<Character, Character>();
        t = new Trie();
    }
        /**
     * Initializes required data structures from parallel arrays.
     * @param in  is the String path name.
     */
    public void sort(Scanner sc) {
        String line;
        int count = 0;
        //char[] alphabet = x.toCharArray();
        char[] alphabet = new char[256];
        for (int i=0; i<256; i++){
          char temp = (char) i;
          alphabet[i] = temp;
        }
        //String x = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        
        Scanner in = sc;
        if (!in.hasNext()) {
           throw new IllegalArgumentException(); 
        }
        char[] alpha = in.nextLine().toCharArray();
            for (int j = 0; j < alpha.length; j++) {
                if (!convert.containsKey(alpha[j])) {
                    convert.put(alpha[j], alphabet[j]);
                    convertBack.put(alphabet[j], alpha[j]);
                } else {
                    throw new IllegalArgumentException();
                }
            }

            while (in.hasNext()) {
                line = in.nextLine();
                count++;
                char[] charWord = line.toCharArray();
                String toTrie = "";
                for (int i = 0; i < charWord.length; i++) {
                    if (i == charWord.length - 1 && convert.containsKey(charWord[i])) {
                        toTrie += convert.get(charWord[i]);
                        t.insert(toTrie);
                    } else if (convert.containsKey(charWord[i])) {
                        toTrie += convert.get(charWord[i]);
                    } else {
                        break;
                    }
                }
            }
       
        LinkedList<String> printed = t.print(count);
        for (int i = 0; i < printed.size(); i++) {
            String toPrint = printed.get(i);
            char[] charWord = toPrint.toCharArray();
            String toTrie = "";
            for (int k = 0; k < charWord.length; k++) {
                toTrie += convertBack.get(charWord[k]);
            }
            System.out.println(toTrie);
        }
        in.close();
    }

        /**
     * Spits out the sorted array.
     */
    public static void main(String[] args) {
        AlphabetSort a = new AlphabetSort();
        Scanner sc = new Scanner(System.in);
        a.sort(sc);
        
    }
}
