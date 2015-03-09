
package ngordnet;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import ngordnet.WordNet;


/**  You should write additional tests.
 *  @author Kyle Jackson
 */

public class WordNetTest {
    WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
    // String x = "one,two,three";

    @Test
    public void testBasic() {
     
        /* These should all print true. */
        System.out.println(wn.isNoun("jump"));
        System.out.println(wn.isNoun("leap"));
        System.out.println(wn.isNoun("nasal_decongestant"));

    }

    
    @Test
    public void testIterator() {
        System.out.println("All nouns:");
        for (String noun : wn.nouns()) {
            System.out.println(noun);
        }
    }
     @Test
    public void testInvert() {

        System.out.println("Hypnoyms of increase:"); //worked
        for (String noun : wn.hyponyms("increase")) {
            System.out.println(noun);
        }

        /* The code below should print the following (maybe not in this order): 
            Hypnoyms of jump:
            parachuting (didn't get this one)
            leap
            jump
        */

        System.out.println("Hypnoyms of jump:");
        for (String noun : wn.hyponyms("jump")) {
            System.out.println(noun);
        }  

        /* The code below should print the following (maybe not in this order):
            Hypnoyms of change:
            alteration
            saltation
            modification
            change
            variation
            increase
            transition
            demotion
            leap
            jump        
        */

        /** From: http://goo.gl/EGLoys */
        System.out.println("Hypnoyms of change:");

        WordNet wn2 = new WordNet("./wordnet/synsets14.txt", "./wordnet/hyponyms14.txt");
        for (String noun : wn2.hyponyms("change")) {
            System.out.println(noun);
        } 
    }

    

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(WordNetTest.class);
    }
} 