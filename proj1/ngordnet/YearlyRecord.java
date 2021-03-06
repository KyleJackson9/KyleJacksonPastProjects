package ngordnet;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.Comparator;

public class YearlyRecord {
    private TreeMap<String, Integer> map;
    private TreeMap<Integer, ArrayList<String>> opposite;
    private TreeMap<String, Integer> rank;
    private boolean ranker;

    public YearlyRecord() {
        map = new TreeMap<String, Integer>();
        opposite = new TreeMap<Integer, ArrayList<String>>();
        rank = new TreeMap<String, Integer>();
        ranker = true;
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        opposite = new TreeMap<Integer, ArrayList<String>>();
        map = new TreeMap<String, Integer>();
        rank = new TreeMap<String, Integer>();
        HashMap<String, Integer> map1 = otherCountMap;
        int size = 1;
        List mapKeys = new ArrayList(map1.keySet());
        List mapValues = new ArrayList(map1.values());

        for (int i = 0; i < mapValues.size(); i++) {
            ArrayList<String> copy = new ArrayList<String>();
            copy.add((String) mapKeys.get(i));
            map.put((String) mapKeys.get(i), (int) mapValues.get(i));
            opposite.put((int) mapValues.get(i), copy);
        }
        this.rerank();

    }

    /** Comparator that compares strings based on zCount. */
    private class ZComparator implements Comparator<String> {
        public int compare(String x, String y) {
            return count(x) - count(y);
        }
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        if (map.containsKey(word)) {
            return map.get(word);
        }
        return 0;
    }

    private void rerank() {
        //got idea for this from Josh Hug and Finn Howell
        rank = new TreeMap<String, Integer>();
        String[] words = new String[map.size()];
        int cnt = 0;
        for (String word : map.keySet()) {
            words[cnt] = word;
            cnt += 1;
        }

        // Sort words by order of number of s
        Arrays.sort(words, new ZComparator());
        for (int i = 0; i < words.length; i += 1) {
            rank.put(words[i], map.size() - i);
        }
        ranker = false;
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        ranker = true;
        if (map.containsKey(word)) {
            for (int i : opposite.keySet()) {
                if (opposite.get(i).indexOf(word) != -1) {
                    opposite.get(i).remove(word);
                }
            }
        }
        map.put(word, count);

        if (opposite.containsKey(count)) {
            opposite.get(count).add(word);
        } else {
            ArrayList<String> copy = new ArrayList<String>();
            copy.add(word);
            opposite.put(count, copy);
        }

    }

    public int size() {
        return map.size();
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() {
        ArrayList<String> mapster = new ArrayList<String>();
        for (int i : opposite.keySet()) {
            ArrayList<String> copy = opposite.get(i);
            for (int j = 0; j < copy.size(); j++) {
                mapster.add(copy.get(j));
            }
        }
        return mapster;
    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {
        List mapValues = new ArrayList(opposite.keySet());
        return mapValues;
    }

    /**
     * Returns rank of WORD. Most common word is rank 1. If two words have the
     * same rank, break ties arbitrarily. No two words should have the same
     * rank.
     */
    public int rank(String word) {
        if (!map.containsKey(word)) {
            return -1;
        }
        if (ranker) {
            rerank();
        }
        return rank.get(word);
    }
}
