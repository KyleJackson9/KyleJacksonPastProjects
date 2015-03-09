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
    //public HashMap<Integer, String> opposite;

    public YearlyRecord(){
        map = new HashMap<String,Integer>();
        //opposite = new HashMap<Integer,String>();
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap){
        map = otherCountMap;
        // Collection<Integer> value = this.values();
        // Set<String> key = this.keySet();
       //  List mapKeys = new ArrayList(map.keySet());
       // List mapValues = new ArrayList(map.values());

        // Iterator it = map.entrySet().iterator();

        // while (it.hasNext()) {
        //     Map.Entry pair = (Map.Entry)it.next();
        //     opposite.put((Integer) pair.getValue(), (String) pair.getKey());
            
        // }
        
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {//works
        return map.get(word);

    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {//works
        map.put(word,count);
        //opposite.put(count,word);

    }

    /** Returns the number of words recorded this year. */
    public int size(){ //works
        return map.size();

    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() { //works
        List mapKeys = new ArrayList(map.keySet());
       List mapValues = new ArrayList(map.values());
       List mapValues2 = new ArrayList(map.values());
       Collections.sort(mapValues);
       
       List answer = new ArrayList();
       int k = 0;
  
     for (int i = 0; i < mapValues.size(); i++){
        for (int j = 0; j < mapValues.size(); j++){

        if (mapValues.get(i).equals(mapValues2.get(j))){
            answer.add(k,mapKeys.get(j)); 
            k += 1;
        }
       }
   }
       return answer;


    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {//works
        List mapValues = new ArrayList(map.values());
        Collections.sort(mapValues);
        return mapValues;
    }

    /** Returns rank of WORD. Most common word is rank 1. 
      * If two words have the same rank, break ties arbitrarily. 
      * No two words should have the same rank.
      */
    private int ranker(String word){
     List mapKeys = new ArrayList(this.words());
        for (int i = 0; i < mapKeys.size(); i++){
        if (mapKeys.get(i).equals(word)){
            return mapKeys.size() - i; //always1
        }
    }
        return 0;
    }
    public int rank(String word) {
      return ranker(word);




    }
} 
