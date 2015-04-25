import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
/**
 * Prefix-Trie. Supports linear time find() and insert(). 
 * Should support determining whether a word is a full word in the 
 * Trie or a prefix.
 * @author Kyle Jackson
 */
public class AlphabetSort {
    public static HashMap<Character, Character> convert;
    public static HashMap<Character, Character> convertBack;
    public static Trie t;

    public AlphabetSort() {
        convert = new HashMap<Character, Character>();
        convertBack = new HashMap<Character, Character>();
        t = new Trie();
    }

    public static void sort(String in) {
        String line;
        int count = 0;
        String x = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] alphabet = x.toCharArray();
        try (
            InputStream fis = new FileInputStream(in);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("US-ASCII"));
            BufferedReader br = new BufferedReader(isr);
        ) {

            char[] alpha = br.readLine().toCharArray();
            if (alpha == null) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; j < alpha.length; j++) {
                if (!convert.containsKey(alpha[j])) {
                    convert.put(alpha[j], alphabet[j]);
                    convertBack.put(alphabet[j], alpha[j]);
                } else {
                    throw new IllegalArgumentException();
                }

            }

            while ((line = br.readLine()) != null) {
                count++;
                char[] charWord = line.toCharArray();
                String toTrie = "";
                for (int i = 0; i < charWord.length; i++) {
                    if (i== charWord.length - 1 && convert.containsKey(charWord[i])) {
                        toTrie += convert.get(charWord[i]);
                        t.insert(toTrie);
                    } else if (convert.containsKey(charWord[i])) {
                        toTrie += convert.get(charWord[i]);
                    } else {
                        break;
                    }
                }
                
            }
        } catch (Throwable t) {
            System.out.println(t);
        }
        


        LinkedList<String> printed = t.print(count);
        for (int i = 0; i < printed.size(); i++) {
            String toPrint = printed.get(i);
            char[] charWord = toPrint.toCharArray();
            String toTrie = "";
            for (int k = 0; k < charWord.length; k++) {
                if (k == charWord.length - 1 && convertBack.containsKey(charWord[k])) {
                    toTrie += convertBack.get(charWord[k]);
                    System.out.println(toTrie);
                } else if (convertBack.containsKey(charWord[k])) {
                    toTrie += convertBack.get(charWord[k]);
                } else {
                    System.out.println(charWord[k] + toTrie);
                    break;
                }
            }

        }

    }
    
    public static void main(String[] args) {
        AlphabetSort a = new AlphabetSort();
        a.sort("test.in");
        
    }
}
}
}
