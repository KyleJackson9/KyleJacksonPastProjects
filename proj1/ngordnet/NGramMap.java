package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.*;
import java.io.*;

public class NGramMap {

    private HashMap<Integer, YearlyRecord> map;
    private HashMap<Long, Long> timeMap;    
    

    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename){

         map = new HashMap<Integer,YearlyRecord>();
         
         timeMap = new HashMap<Long,Long>();
         //time = new TimeSeries<Double>();
        In in1 = new In(wordsFilename); 
        String line;
        while (in1.hasNextLine()) { 
           line = in1.readLine();
           String[] number = line.split("\t");
           String word = number[0];
           Integer year = Integer.parseInt(number[1]);
           Integer count = Integer.parseInt(number[2]);
           
           
           if (map.containsKey(year)){
            map.get(year).put(word, count);
           } else {
            YearlyRecord yRec = new YearlyRecord();
            yRec.put(word, count);
            map.put(year,yRec);
           }
        }



        In in2 = new In(countsFilename); 
        String line1;
        while (in2.hasNextLine()) { //read each line by enters
            line1 = in2.readLine();
           String[] number = line1.split(",");
            timeMap.put(Long.parseLong(number[0]), Long.parseLong(number[1]));
        }

    }
    
    /** Returns the absolute count of WORD in the given YEAR. If the word
      * did not appear in the given year, return 0. */
    public int countInYear(String word, int year){//works
        YearlyRecord ans = getRecord(year);
        return ans.count(word);
    }

    /** Returns a defensive copy of the YearlyRecord of WORD. */
    public YearlyRecord getRecord(int year){//works
        return map.get(year);
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory(){//works
        TimeSeries<Long> time = new TimeSeries<Long>();
        for (long i : timeMap.keySet()){
            time.put((int) i, timeMap.get(i));
        }
        return time;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear){
        TimeSeries<Integer> time = new TimeSeries<Integer>();
        for (int i =startYear; i <= endYear; i++){
            time.put(i, map.get(i).count(word));
        }
        return time;

    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word){ //works
        TimeSeries<Integer> time = new TimeSeries<Integer>();
        for (int i : map.keySet()){
            time.put(i, map.get(i).count(word));
        }
        return time;
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear){//works
        TimeSeries<Integer> count = this.countHistory(word, startYear, endYear);
        TimeSeries<Double> time = new TimeSeries<Double>();
        for (int i = startYear; i <= endYear; i++){
            time.put((int)i, (double) (long) timeMap.get(i));
        }
        return count.dividedBy(time);

    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word){//works
        TimeSeries<Integer> count = this.countHistory(word);
        TimeSeries<Double> time = new TimeSeries<Double>();
        for (long i : timeMap.keySet()){
            time.put((int)i, (double) (long) timeMap.get(i));
        }
        return count.dividedBy(time);

    }

    /** Provides the summed relative frequency of all WORDS between
      * STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words,  int startYear, int endYear){
        TimeSeries<Double> time = new TimeSeries<Double>();
        HashMap<String,TimeSeries<Double>> hold = new HashMap<String,TimeSeries<Double>>();
        for (String i : words){
            hold.put(i,weightHistory(i,startYear,endYear));
        }
         for (String i : words){
            time = time.plus(hold.get(i));
        }
        return time;

    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words){
        TimeSeries<Double> time = new TimeSeries<Double>();
        HashMap<String,TimeSeries<Double>> hold = new HashMap<String,TimeSeries<Double>>();
        for (String i : words){
            hold.put(i,weightHistory(i));
        }
         for (String i : words){
            time = time.plus(hold.get(i));
        }
        return time;

    }

    /** Provides processed history of all words between STARTYEAR and ENDYEAR as processed
    //   * by YRP. */
    // public TimeSeries<Double> processedHistory(int startYear, int endYear, YearlyRecordProcessor yrp){
    //     TimeSeries<Double> time = new TimeSeries<Double>();

    // }

    // * Provides processed history of all words ever as processed by YRP. 
    // public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
    //     TimeSeries<Double> time = new TimeSeries<Double>();

    // }
}

