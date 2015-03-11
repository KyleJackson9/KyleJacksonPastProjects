package ngordnet;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Set;

public class WordLengthProcessor implements YearlyRecordProcessor {
	//Given a YearlyRecord, returns the average length of all words (in characters) from volumes in that year.
    public double process(YearlyRecord yearlyRecord) {
    	double total = 0;
    	double average = 0;
    	int numWords = 0;
    	ArrayList<String> words = new ArrayList<String>(yearlyRecord.words());
    	numWords = words.size();
    	for (String word : words){
    		total += word.length();
    	}
    	average = total/numWords;	
    	return average;
    }
}
