package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;

public class WordNet {
    private String synset;
    private String hyponym;
    private HashMap<Integer, String[]> nouns;
    private HashMap<String, Integer> opposite;
    private Digraph g;
    private Iterator<String> n;
    private Iterator<Integer> n1;
    private Iterator<String[]> n3;
    private In in1;
    private In in2;
    private Set<String> nounResult;
    private Set<String[]> hy;
    private Set<Integer> sHypo;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = synsetFilename;
        hyponym = hyponymFilename;
        nouns = new HashMap<Integer, String[]>();
        opposite = new HashMap<String, Integer>();
        final int num = 100000;
        g = new Digraph(num);
        in1 = new In(synset);
        String line;
        while (in1.hasNextLine()) {

            line = in1.readLine();
            String[] number = line.split(",");
            String[] words = number[1].split(" ");

            nouns.put(Integer.parseInt(number[0]), words);

            for (int i = 0; i < words.length; i++) {

                opposite.put(words[i], Integer.parseInt(number[0]));

            }

        }

        in2 = new In(hyponym);
        String line2;
        while (in2.hasNextLine()) {
            line2 = in2.readLine();
            String[] x = line2.split(",");

            for (int i = 1; i < x.length; i++) {
                g.addEdge(Integer.parseInt(x[0]), Integer.parseInt(x[i]));

            }

        }

        // Set<Integer> syOut = nouns.keySet();
        // n1 = syOut.iterator();
        // Set<Integer> syResult = new HashSet<Integer>();
        // while (n1.hasNext()) {
        //     syResult.add((n1.next()));
        // }

    }

    public Set<String> nouns() {

        Set<String> nounOut = opposite.keySet();
        n = nounOut.iterator();
        nounResult = new HashSet<String>();
        while (n.hasNext()) {
            nounResult.add(n.next());
        }

        return nounResult;

    }

    public boolean isNoun(String s) {

        return opposite.containsKey(s);

    }

    public Set<String> hyponyms(String word) {
        sHypo = new HashSet<Integer>();
        hy = new HashSet<String[]>();

        for (Integer i : nouns.keySet()) {
            String[] x = nouns.get(i);
            for (int j = 0; j < x.length; j++) {
                if (x[j].equals(word)) {
                    sHypo.add(i);
                }
            }
        }

        Set<Integer> h = GraphHelper.descendants(g, sHypo);

        Integer[] h1 = h.toArray(new Integer[h.size()]);
        for (int i = 0; i < h1.length; i++) {
            hy.add(nouns.get(h1[i]));

        }

        n3 = hy.iterator();
        Set<String> syResult = new HashSet<String>();
        while (n3.hasNext()) {
            String[] xh = n3.next();
            for (int i = 0; i < xh.length; i++) {
                syResult.add(xh[i]);
            }
        }

        return syResult;

    }

}