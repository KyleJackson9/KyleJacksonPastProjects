
package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.*;
import java.io.*;

public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {    
    /** Constructs a new empty TimeSeries. */
    public int start;
    public int end;
    public TimeSeries<T> copy;
    public int go;
    public int no;

    public TimeSeries(){
      start = 0;
      end = 0;
    }

    /** Returns the years in which this time series is valid. Doesn't really
      * need to be a NavigableSet. This is a private method and you don't have 
      * to implement it if you don't want to. */
    // private NavigableSet<Integer> validYears(int startYear, int endYear){

    // }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR. 
     * inclusive of both end points. */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear){
     
      start = startYear;
      end = endYear;
      for (int i = start; i <= end; i++){
        copy.put(ts.get(i));
      }
    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts){
      copy = ts;
    }

    /** Returns the quotient of this time series divided by the relevant value in ts.
      * If ts is missing a key in this time series, return an IllegalArgumentException. */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts){ //ts bottom of division must have all years of time
      copy = new TimeSeries(this);
      if (this.start <= ts.start && this.end >= ts.end){
        for (int i = 0; i < ts.end - ts.start; i++){
          copy.put(ts.start + i, this.get(ts.start + i)/(ts.get(ts.start +i)));
        }
        return (TimeSeries<Double>) copy;
      } else{
        throw new IllegalArgumentException();
      }

    }

    /** Returns the sum of this time series with the given ts. The result is a 
      * a Double time series (for simplicity). */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts){
      copy = new TimeSeries(this);
      if (this.start - ts.start > 0){
         go = ts.start;
      } else {
         go = this.start;
      }
      if (this.end - ts.end > 0){
         no = this.end;
      } else {
         no = ts.end;
      }
     
      for (int i = go; i < no; i++){
        if (this.get(i) != null && ts.get(i) != null){
          copy.put(ts.get(i), this.get(ts.start + i) + (ts.get(ts.start +i)));
        } else if (this.get(i) == null){
          copy.put(ts.get(i), ts.get(i));
        } else{
          copy.put(this.get(i), this.get(i));
        }
      }
        return (TimeSeries<Double>) copy;
      } 
     

    /* Returns all years for this time series (in any order). */
    public Collection<Number> years() {
      Set<Number> year = this.keySet();
      return year;

    }

    // /** Returns all data for this time series. 
    //   * Must be in the same order as years(). */
    public Collection<Number> data() {
      ArrayList<Number> data = new ArrayList<Integer>();
      for (int i = start; i <= end; i++){
        data.add(this.get(i));

      }
      return data;
    }
}
