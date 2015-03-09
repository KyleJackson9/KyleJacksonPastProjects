

package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.*;
import java.io.*;


public class WordNet {
	private String synset;
	private String hyponym;
	private HashMap<Integer,String[]> nouns;
	private HashMap<String, Integer> opposite;
	private Digraph g;// directed graph for hyponyms have descent point to jump point to glide etc
	private Iterator<String> n;
	private Iterator<Integer> n1;
	private Iterator<String[]> n3;
	private In in1;
	private In in2;
	private Set<String> nounResult;
	private Set<String[]> hy;
	private Set<Integer> syResult;
	private Set<Integer> h;
	private Set<Integer> sHypo;


	public WordNet(String synsetFilename, String hyponymFilename){
		synset = synsetFilename;
		hyponym = hyponymFilename;
		nouns = new HashMap<Integer, String[]>();
		opposite = new HashMap<String, Integer>();
		g = new Digraph(10000);
			
		in1 = new In(synset); 
		String line;
		while (in1.hasNextLine()) { //read each line by enters
		   
			line = in1.readLine();
		   String[] number = line.split(",");
		   String[] words = number[1].split(" ");//cut each by spaces because multiple entries on synset sometimes
		
			nouns.put(Integer.parseInt(number[0]), words);
		   for (int i = 0; i < words.length; i++) {
		   	//should fill each with synset as key and noun as value
		  
		   	opposite.put(words[i],Integer.parseInt(number[0])); // fills key as noun then value as number

		
		   }
		   
		}


		in2 = new In(hyponym); 
		String line2;
		while (in2.hasNextLine()) { //read each line by enters
			line2 = in2.readLine();
		   String[] x = line2.split(",");//cut each by commas
		  

		   
		   for (int i = 1; i< x.length; i++) {
		   	g.addEdge(Integer.parseInt(x[0]),Integer.parseInt(x[i]));//should have verticies synsets and edges of verticies hyponyms
		   	
		   }
		   
		}


   		//makes set of synsets Integers
		Set<Integer> syOut = nouns.keySet();
		 n1 = syOut.iterator(); 
    	Set<Integer> syResult = new HashSet<Integer>();
    	while (n1.hasNext()) {
    	    syResult.add((n1.next()));
   		}
		


	}

	
	public Set<String> nouns() {//works

		//makes set of nouns (String)

		Set<String> nounOut = opposite.keySet();//keyset gives null pointer must have added null keys
		 n = nounOut.iterator(); 
    	nounResult = new HashSet<String>();
    	while (n.hasNext()) {
    	    nounResult.add(n.next());
   		}

    	return nounResult;

	}


	public boolean isNoun(String s){//works

		return opposite.containsKey(s);

	}
	//The first field is a synset id; subsequent fields are the id numbers of the synset's direct hyponyms.
	    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
 
	public Set<String> hyponyms(String word){//does not grab enough
		 sHypo = new HashSet<Integer>();
		 hy = new HashSet<String[]>();
		sHypo.add(opposite.get(word));//should use word as a key and return its value (Integer)
		 /** Returns the set of all vertex numbers reachable from the start vertices. */
		h = GraphHelper.descendants(g,sHypo);//takes entire digraph & checks it against result

		
		//returns a set of integers
		Integer[] h1 = h.toArray(new Integer[0]);
		for (int i = 0; i< h1.length; i++) {
			hy.add(nouns.get(h1[i]));//converts from int to string then gets the value(noun) associated with that synset
			//gives me String[] of the synsets
		}
		//do I also need to add in the descendants of the descendants or did it to that itself?
		//need to get everything out of String[] hy Set
		 n3 = hy.iterator(); 
    	Set<String> syResult = new HashSet<String>();
    	while (n3.hasNext()) {
    		String[] xh = n3.next();
    		for (int i = 0; i < xh.length; i++){
    	    	syResult.add(xh[i]);
    	}
   		}
		


		return syResult;

	}
	public static void main(String[] args){	
	}

}