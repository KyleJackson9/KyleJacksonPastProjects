package ngordnet;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {
    private int start;
    private int end;
    private TimeSeries<T> copy;
    private int go;
    private int no;

    public TimeSeries() {
        super();
        start = 0;
        end = 0;
        copy = this;
    }

    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {
        copy = new TimeSeries<T>();
        start = startYear;
        end = endYear;
        for (int i = start; i <= end; i++) {
            this.put(i, ts.get(i));
            copy.put(i, ts.get(i));
        }

    }

    public TimeSeries(TimeSeries<T> ts) {
        super(ts);
        copy = ts;
    }

    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {
        //got help from Jake Moskowitz for avoiding error throws
        TimeSeries<Double> divMap = new TimeSeries<Double>();
        HashSet<Integer> allYears = new HashSet<Integer>();
        for (int i : this.keySet()){
            allYears.add(i);
        }

        for (int i : ts.keySet()){
            allYears.add(i);
        }

        for (int year : allYears) {
            if (this.containsKey(year) && ts.containsKey(year)) {
                divMap.put(year,
                        (this.get(year).doubleValue() / (ts.get(year).doubleValue())));
            } else if (ts.containsKey(year)) {
                divMap.put(year, 0.0);
            } else if (this.containsKey(year)) {
                divMap.put(year, this.get(year).doubleValue());
            } else if (!ts.containsKey(year)) {
                throw new IllegalArgumentException();
            }
        }
        return divMap;
    }

    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> pMap = new TimeSeries<Double>();
        if (this.isEmpty() && !ts.isEmpty()) {
            for (int i : ts.keySet()) {
                pMap.put(i, ts.get(i).doubleValue());
            }
            return pMap;
        } else if (!this.isEmpty() && ts.isEmpty()) {
            for (int i : this.keySet()) {
                pMap.put(i, this.get(i).doubleValue());
            }
            return pMap;
        }
        for (int i : ts.keySet()) {
            if (this.containsKey(i) && ts.containsKey(i)) {
                pMap.put(i,
                        (this.get(i).doubleValue() + (ts.get(i).doubleValue())));
            } else if (this.containsKey(i)) {
                pMap.put(i, this.get(i).doubleValue());
            } else if (ts.containsKey(i)) {
                pMap.put(i, ts.get(i).doubleValue());
            }
        }
        return pMap;
    }

    public Collection<Number> years() {
        Set<Integer> year = this.keySet();
        ArrayList<Number> x = new ArrayList<Number>();
        x.addAll(year);
        return x;
    }

    public Collection<Number> data() {
        return (Collection<Number>) this.values();
    }
}
