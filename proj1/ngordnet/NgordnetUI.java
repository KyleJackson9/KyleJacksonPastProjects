package ngordnet;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Set;

/**
 * Provides a simple user interface for exploring WordNet and NGram data.
 * 
 * @author Kyle Jackson
 */
public class NgordnetUI {
  public static void main(String[] args) {
    In in = new In("./ngordnet/ngordnetui.config");
    System.out.println("Reading ngordnetui.config...");

    String wordFile = in.readString();
    String countFile = in.readString();
    String synsetFile = in.readString();
    String hyponymFile = in.readString();
    NGramMap ng = new NGramMap(wordFile, countFile);
    WordNet wn = new WordNet(synsetFile, hyponymFile);
    WordLengthProcessor yrp = new WordLengthProcessor();
    Plotter plot = new Plotter();
    int startDate = 0;
    int endDate = 0;
    System.out
        .println("\nBased on ngordnetui.config, using the following: "
            + wordFile + ", " + countFile + ", " + synsetFile
            + ", and " + hyponymFile + ".");

    while (true) {
      System.out.print("> ");
      String line = StdIn.readLine();
      String[] rawTokens = line.split(" ");
      String command = rawTokens[0];
      String[] tokens = new String[rawTokens.length - 1];
      System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
      switch (command) {
      case "quit":
        return;
      case "help":
        // In in = new In("help.txt");
        // String helpStr = in.readAll();
        System.out
            .println("choose either range, count, hyponyms, history, or hypohist");
        System.out
            .println("I guess there is also wordlength and zipf[year]");
        break;
      case "range":
        try {
          if (tokens[0] != null && tokens[1] != null) {
            startDate = Integer.parseInt(tokens[0]);
            endDate = Integer.parseInt(tokens[1]);
            System.out.println("Start date: " + startDate);
            System.out.println("End date: " + endDate);
          } else {
            System.out.println("Please provide a valid date range");
          }
          break;
        } catch (Throwable t) {
          System.out
              .println("Sorry that input did not bode well for range");
          break;
        }
      case "count":
        try {
          if (tokens[0] != null && tokens[1] != null) {
            String wordy = tokens[0];
            int year = Integer.parseInt(tokens[1]);
            System.out.println(ng.countInYear(wordy, year));
          }
          break;
        } catch (Throwable t) {
          System.out.println("Sorry your input to count misfired!");
          break;
        }
      case "hyponyms":
        try {
          String wordy2 = tokens[0];
          System.out.println(wn.hyponyms(wordy2));
          break;
        } catch (Throwable t) {
          System.out.println("Sorry try that again!");
          break;
        }
      case "history":// throws null
        try {
          String[] wordy3 = tokens;
          if (wordy3.length > 1) {
            plot.plotCategoryWeights(ng, wn, wordy3, startDate,
                endDate);
          } else {
            plot.plotCategoryWeights(ng, wn, wordy3[0], startDate,
                endDate);
          }
        } catch (Throwable t) {
          System.out.println("Sorry, maybe add some words");
          break;
        }
      case "hypohist":
        try {
          String[] wordy4 = tokens;
          ArrayList<String> hold = new ArrayList<String>();
          String[] hyp;
          for (String i : wordy4) {
            hold.addAll(wn.hyponyms(i));
            for (String j : hold) {
              plot.plotCategoryWeights(ng, wn, j, startDate,
                  endDate);
            }
          }
          break;
        } catch (Throwable t) {
          System.out
              .println("Sorry that input did not work for hypohist");
          break;
        }
      case "wordlength":
        try {
          TimeSeries<Double> wod = ng.processedHistory(startDate,
              endDate, yrp);
          plot.plotTS(wod, "WordLengths", "average letters per word",
              "years", "Average");
          break;
        } catch (Throwable t) {
          System.out
              .println("Sorry maybe try putting correct start & end dates");
          break;
        }
      case "zipf":
        try {
          if (tokens[0] != null) {
            int year = Integer.parseInt(tokens[0]);
            plot.plotZipfsLaw(ng, year);
          }
          break;
        } catch (Throwable t) {
          System.out
              .println("Sorry that input for Zipf did not go over well put in year perhaps");
          break;
        }
      default:
        System.out.println("Invalid command.");
        break;
      }
    }
  }
}
