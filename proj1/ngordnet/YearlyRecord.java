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
    public TreeMap<Integer, String> opposite;

    public YearlyRecord(){
        map = new HashMap<String,Integer>();
        opposite = new TreeMap<Integer,String>();
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap){
        map = otherCountMap;
        opposite = new TreeMap<Integer,String>();

        List mapKeys = new ArrayList(map.keySet());
       List mapValues = new ArrayList(map.values());
      

       for (int i = 0; i < mapValues.size(); i++){
        opposite.put((int)mapValues.get(i), (String) mapKeys.get(i));
       }
        
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {//works
        return map.get(word);

    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {//works
        map.put(word,count);
        if (opposite.containsKey(count)){
          opposite.put(count+1,word);//need to re-sort but it should on its own
        } else{
        opposite.put(count,word);//need to re-sort but it should on its own
      }


    }

    /** Returns the number of words recorded this year. */
    public int size(){ //works
        return map.size();

    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() { //works

       return opposite.values();


    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {//works
        List mapValues = new ArrayList(opposite.keySet());
        
        return mapValues;
    }

    /** Returns rank of WORD. Most common word is rank 1. 
      * If two words have the same rank, break ties arbitrarily. 
      * No two words should have the same rank.
      */
    public int rank(String word) {
        List mapKeys = new ArrayList(opposite.values());
      for (int i = 0; i < mapKeys.size(); i++){
        if (mapKeys.get(i).equals(word)){
            return mapKeys.size() - i; 
        }
      }
        return 0;



    }
} 
