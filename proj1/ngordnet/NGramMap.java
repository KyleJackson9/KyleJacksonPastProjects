package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.*;
import java.io.*;

public class NGramMap {
    public YearlyRecord year;
    private In in1;
    private In in2;
    public TimeSeries<? extends Number> time;
    public HashMap<String, HashMap<Integer,Integer>> yMap;
    public HashMap<Integer, Integer> timeMap;
    
    

    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename){
         year = new YearlyRecord();
         yMap = new HashMap<String,HashMap<Integer,Integer>>();
         
         timeMap = new HashMap<Integer,Integer>();
         TimeSeries<? extends Number> time = new TimeSeries<? extends Number>();
        in1 = new In(wordsFilename); 
        String line;
        while (in1.hasNextLine()) { //read each line by enters
            line = in1.readLine();
           String[] number = line.split("\t");
           HashMap<Integer,Integer> copy = new HashMap<Integer,Integer>();
           copy.put(Integer.parseInt(number[1]),Integer.parseInt(number[2]))
            yMap.put(number[0], copy);//constructs  
 
        }
        in2 = new In(countsFilename); 
        String line1;
        while (in2.hasNextLine()) { //read each line by enters
            line1 = in2.readLine();
           String[] number = line1.split(",");
            time.put(Integer.parseInt(number[0]), Double.parseDouble(number[1]));// constructs time series  
            timeMap.put(Integer.parseInt(number[0]), Integer.parseInt(number[1]));
        }

    }
    
    /** Returns the absolute count of WORD in the given YEAR. If the word
      * did not appear in the given year, return 0. */
    public int countInYear(String word, int year){
        if (yMap.containsKey(word)){
            return yMap.get(get(year));
        }
        return 0;

    }

    /** Returns a defensive copy of the YearlyRecord of WORD. */
    public YearlyRecord getRecord(int year){
        YearlyRecord answer = new YearlyRecord(yMap);
        return answer; 

    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory(){
        TimeSeries<Long> time = new TimeSeries<Long>();

    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear){
        TimeSeries<Integer> time = new TimeSeries<Integer>();

    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word){
        TimeSeries<Integer> time = new TimeSeries<Integer>();

    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear){
        TimeSeries<Double> time = new TimeSeries<Double>();

    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word){
        TimeSeries<Double> time = new TimeSeries<Double>();

    }

    /** Provides the summed relative frequency of all WORDS between
      * STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words,  int startYear, int endYear){
        TimeSeries<Double> time = new TimeSeries<Double>();

    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words){
        TimeSeries<Double> time = new TimeSeries<Double>();

    }

    /** Provides processed history of all words between STARTYEAR and ENDYEAR as processed
      * by YRP. */
    public TimeSeries<Double> processedHistory(int startYear, int endYear, YearlyRecordProcessor yrp){
        TimeSeries<Double> time = new TimeSeries<Double>();

    }

    /** Provides processed history of all words ever as processed by YRP. */
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        TimeSeries<Double> time = new TimeSeries<Double>();

    }
}

