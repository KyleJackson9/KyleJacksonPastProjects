
package ngordnet;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import ngordnet.YearlyRecord;
import java.util.*;


/**  You should write additional tests.
 *  @author Kyle Jackson
 */

public class YearlyRecordTest {

 
    YearlyRecord yr = new YearlyRecord();
 
        

    @Test
    public void testBasic() {
     
        yr.put("quayside", 95);        
        yr.put("surrogate", 340);
        yr.put("merchantman", 181);
        
        System.out.println(yr.rank("surrogate")); // should print 1
        System.out.println(yr.rank("quayside")); // should print 3
        System.out.println(yr.rank("merchantman")); // should print 2
        System.out.println("This is size " + yr.size()); // should print 3 
        Collection<String> words = yr.words(); 

    }

    
    @Test
    public void testWords() {
        /* The code below should print: 
            
            quayside appeared 95 times.
            merchantman appeared 181 times.
            surrogate appeared 340 times.
        */
        yr.put("quayside", 95);        
        yr.put("surrogate", 340);
        yr.put("merchantman", 181); 
            Collection<String> words = yr.words(); 
        for (String word : words) {
            System.out.println(word + " appeared " + yr.count(word) + " times.");
        }
    }
     @Test
    public void testCount() {

      
        /* The code below should print the counts in ascending order:
            
            95
            181
            340
        */
        yr.put("quayside", 95);        
        yr.put("surrogate", 340);
        yr.put("merchantman", 181); 

        Collection<Number> counts = yr.counts();
        for (Number count : counts) {
            System.out.println(count);
        }

        HashMap<String, Integer> rawData = new HashMap<String, Integer>();
        rawData.put("berry", 1290);
        rawData.put("auscultating", 6);
        rawData.put("temporariness", 20);
        rawData.put("puppetry", 191);
        YearlyRecord yr2 = new YearlyRecord(rawData);
        System.out.println(yr2.rank("auscultating")); // should print 4
        yr2.put("hello",100000);

        System.out.println("wat" + yr2.rank("hello")); // should print 1
        System.out.println(yr2.rank("puppetry")); // should print 3
    }

    

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(YearlyRecordTest.class);
    }
} 