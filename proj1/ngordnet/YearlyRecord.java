package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.*;
import java.io.*;

public class YearlyRecord {
    /** Creates a new empty YearlyRecord. */
    public HashMap<String, Integer> map;
    public HashMap<Integer, String> opposite;

    public YearlyRecord(){
        map = new HashMap<String,Integer>();
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap){
        map = otherCountMap;
        // Collection<Integer> value = this.values();
        // Set<String> key = this.keySet();
       //  List mapKeys = new ArrayList(map.keySet());
       // List mapValues = new ArrayList(map.values());

        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            opposite.put((Integer) pair.getValue(), (String) pair.getKey());
            
        }
        
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        return map.get(word);

    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        map.put(word,count);

    }

    /** Returns the number of words recorded this year. */
    public int size(){
        return map.size();

    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() {
        List mapKeys = new ArrayList(map.keySet());
       List mapValues = new ArrayList(map.values());
       Collections.sort(mapValues);
       Collections.sort(mapKeys);
       return mapKeys;


    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {
        List mapValues = new ArrayList(map.values());
        Collections.sort(mapValues);
        return mapValues;
    }

    /** Returns rank of WORD. Most common word is rank 1. 
      * If two words have the same rank, break ties arbitrarily. 
      * No two words should have the same rank.
      */
    public int rank(String word) {
        List mapKeys = new ArrayList(map.keySet());
       List mapValues = new ArrayList(map.values());
       Collections.sort(mapValues);
       Collections.sort(mapKeys);

       for (int i = 0; i < mapValues.size(); i++){
        if (mapKeys.contains(word)){
            return i+1;
        }
       }
       return 0;

    }
} 
