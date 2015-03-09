
package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.*;
import java.io.*;

public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {    
    /** Constructs a new empty TimeSeries. */
    private int start;
    private int end;
    private TimeSeries<T> copy;
    private int go;
    private int no;

    public TimeSeries(){
      super();
      start = 0;
      end = 0;
      copy = this;
    }

    /** Returns the years in which this time series is valid. Doesn't really
      * need to be a NavigableSet. This is a private method and you don't have 
      * to implement it if you don't want to. */

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR. 
     * inclusive of both end points. */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear){
      // copy = new TimeSeries<? extends Number>();
      start = startYear;
      end = endYear;
      for (int i = start; i <= end; i++){
        copy.put(i, ts.get(i));
      }
    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts){
      super(ts);
       copy = ts;
    }

    /** Returns the quotient of this time series divided by the relevant value in ts.
      * If ts is missing a key in this time series, return an IllegalArgumentException. */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts){ //ts bottom of division must have all years of time

      TimeSeries<Double> divMap = new TimeSeries<Double>();
      if (this.start <= ts.start && this.end >= ts.end){
        for (int i : ts.keySet()){
          if (!ts.containsKey(i)){
            throw new IllegalArgumentException();
          }
        if (this.containsKey(i) && ts.containsKey(i)){
          divMap.put(i, (this.get(i).doubleValue()/(ts.get(i).doubleValue())));
        } else if (this.containsKey(i)){
          divMap.put(i,this.get(i).doubleValue());
        } else if (ts.containsKey(i)){
          divMap.put(i,ts.get(i).doubleValue());
        } 
        }
        return divMap;
      } else{
        throw new IllegalArgumentException();
      }

    }

    /** Returns the sum of this time series with the given ts. The result is a 
      * a Double time series (for simplicity). 
    }*/
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts){
      TimeSeries<Double> pMap = new TimeSeries<Double>();
      for (int i : ts.keySet()){
        if (this.containsKey(i) && ts.containsKey(i)){
          pMap.put(i, (this.get(i).doubleValue()+(ts.get(i).doubleValue())));
        } else if (this.containsKey(i)){
          pMap.put(i,this.get(i).doubleValue());
        } else if (ts.containsKey(i)){
          pMap.put(i,ts.get(i).doubleValue());
        }
      }

        return pMap;
      } 
     

    /* Returns all years for this time series (in any order). */
    public Collection<Number> years() {//works
      Set<Integer> year = this.keySet();
      ArrayList<Number> x = new ArrayList<Number>();
      x.addAll(year);
      return x;

    }

    // /** Returns all data for this time series. 
    //   * Must be in the same order as years(). */
    public Collection<Number> data() {//works
      return (Collection<Number>) this.values();
    }
}
