package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Collection;

public class NGramMap {

    private HashMap<Integer, YearlyRecord> map;
    private HashMap<Integer, HashMap<String, Integer>> map2;
    private HashMap<Long, Long> timeMap;
    private TimeSeries<Long> timeS;

    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        map = new HashMap<Integer, YearlyRecord>();
        timeMap = new HashMap<Long, Long>();
        timeS = new TimeSeries<Long>();
        map2 = new HashMap<Integer, HashMap<String, Integer>>();
        In in1 = new In(wordsFilename);
        String line;
        while (in1.hasNextLine()) {
            line = in1.readLine();
            String[] number = line.split("\t");
            String word = number[0];
            Integer year = Integer.parseInt(number[1]);
            Integer count = Integer.parseInt(number[2]);
            if (map.containsKey(year)) {
                map.get(year).put(word, count);
                map2.get(year).put(word, count);
            } else {
                YearlyRecord yRec = new YearlyRecord();
                HashMap<String,Integer> hMap = new HashMap<String,Integer>();
                hMap.put(word,count);
                map2.put(year, hMap);
                yRec.put(word, count);
                map.put(year, yRec);
            }
        }

        In in2 = new In(countsFilename);
        String line1;
        while (in2.hasNextLine()) {
            line1 = in2.readLine();
            String[] number = line1.split(",");
            timeMap.put(Long.parseLong(number[0]), Long.parseLong(number[1]));
            timeS.put(Integer.parseInt(number[0]), Long.parseLong(number[1]));
        }

    }

    /**
     * Returns the absolute count of WORD in the given YEAR. If the word did not
     * appear in the given year, return 0.
     */
    public int countInYear(String word, int year) {
        YearlyRecord ans = getRecord(year);
        return ans.count(word);
    }

    /** Returns a defensive copy of the YearlyRecord of WORD. */
    public YearlyRecord getRecord(int year) {
        if (map2.containsKey(year)) {
            YearlyRecord ans = new YearlyRecord(map2.get(year));
            return ans;
        }
        return null;
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        TimeSeries<Long> totalCount = new TimeSeries<Long>();
        for (long year : timeMap.keySet()) {
            totalCount.put((int) year, timeMap.get(year));
        }
        return totalCount;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear,
            int endYear) {
        TimeSeries<Integer> countH = new TimeSeries<Integer>();
        for (int year = startYear; year <= endYear; year++) {
            if (map.containsKey(year)) {
                countH.put(year, map.get(year).count(word));
            }
        }
        return countH;

    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        TimeSeries<Integer> time = new TimeSeries<Integer>();
        for (int year : map.keySet()) {
            time.put(year, map.get(year).count(word));
        }
        return time;
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear,
            int endYear) {
        TimeSeries<Integer> count = countHistory(word, startYear, endYear);
        TimeSeries<Double> time = new TimeSeries<Double>();
        for (int year = startYear; year <= endYear; year++) {
            if (timeMap.containsKey(year)) {
                time.put((int) year, (double) (long) timeMap.get(year));
            }
        }
        return count.dividedBy(time);

    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        TimeSeries<Integer> count = countHistory(word);
        TimeSeries<Double> time = new TimeSeries<Double>();
        for (long year : timeMap.keySet()) {
            time.put((int) year, (double) (long) timeMap.get(year));
        }
        return count.dividedBy(time);

    }

    /**
     * Provides the summed relative frequency of all WORDS between STARTYEAR and
     * ENDYEAR.
     */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words,
            int startYear, int endYear) {
        TimeSeries<Double> time = new TimeSeries<Double>();
        HashMap<String, TimeSeries<Double>> hold = new HashMap<String, TimeSeries<Double>>();
        for (String word : words) {
            hold.put(word, weightHistory(word, startYear, endYear));
            time = time.plus(hold.get(word));
        }
        return time;

    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> time = new TimeSeries<Double>();
        HashMap<String, TimeSeries<Double>> hold = new HashMap<String, TimeSeries<Double>>();
        for (String word : words) {
            hold.put(word, weightHistory(word));
            time = time.plus(hold.get(word));
        }
        return time;

    }

    /**
     * Provides processed history of all words between STARTYEAR and ENDYEAR as
     * processed // * by YRP.
     */
    public TimeSeries<Double> processedHistory(int startYear, int endYear,
            YearlyRecordProcessor yrp) {
        TimeSeries<Double> time = new TimeSeries<Double>();
        for (int year = startYear; year <= endYear; year++) {
            if (map.containsKey(year)) {
                time.put(year, yrp.process(map.get(year)));
            }
        }
        return time;

    }

    // Provides processed history of all words ever as processed by YRP.
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        TimeSeries<Double> time = new TimeSeries<Double>();
        for (int year : map.keySet()) {
            time.put(year, yrp.process(map.get(year)));
        }
        return time;
    }
}
