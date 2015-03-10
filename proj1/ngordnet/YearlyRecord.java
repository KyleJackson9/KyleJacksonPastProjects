package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.*;
import java.io.*;

public class YearlyRecord {
    /** Creates a new empty YearlyRecord. */
    private HashMap<String, Integer> map;
    private TreeMap<Integer, ArrayList<String>> opposite;

    public YearlyRecord(){
        map = new HashMap<String,Integer>();
        opposite = new TreeMap<Integer,ArrayList<String>>();
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap){
        opposite = new TreeMap<Integer,ArrayList<String>>();
        map = otherCountMap;

        List mapKeys = new ArrayList(map.keySet());
       List mapValues = new ArrayList(map.values());

       for (int i = 0; i < mapValues.size(); i++){
        ArrayList<String> copy = new ArrayList<String>();
        copy.add((String) mapKeys.get(i));
        opposite.put((int)mapValues.get(i), copy);
       }    
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {//works
      if (map.containsKey(word)){
          return map.get(word);
      }
        return 0;

    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {//remove when add a word thats already in there
      if (map.containsKey(word)){
        for (int i : opposite.keySet()){
          if (opposite.get(i).indexOf(word) != -1){//got help & ideas from Jake Moskowitz
            opposite.get(i).remove(word);
          }
        }
      }
        map.put(word,count);

        if (opposite.containsKey(count)){
          opposite.get(count).add(word);//need to re-sort but it should on its own
        } else{
         ArrayList<String> copy = new ArrayList<String>();
        copy.add(word);
        opposite.put(count,copy);//need to re-sort but it should on its own
      }


    }
// treemap into array list; if duplicates append the array list of strings
    /** Returns the number of words recorded this year. */
    public int size(){ //works
        return map.size();
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() { //works
      ArrayList<String> mapster = new ArrayList<String>();
      for (int i : opposite.keySet()){
        ArrayList<String> copy = opposite.get(i);
        for (int j = 0; j<copy.size(); j++){
          mapster.add(copy.get(j));
        }
      }
       return mapster;
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
        int k = 0;

      for (int i : opposite.keySet()){
        ArrayList<String> copy = opposite.get(i);
        for(int j = 0; j < copy.size(); j++){

        if (copy.get(j).equals(word)){
            return map.size() - k; 
        }
                  k +=1;
      }
      }
        return 0;
    }
} 
