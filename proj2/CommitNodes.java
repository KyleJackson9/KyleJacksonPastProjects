import java.util.*;
import java.io.*;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;



public class CommitNodes implements Serializable {
	private int commitID;
	private String filename;
	private CommitNodes head;
	private String time;
	private String message;
	private LinkedList<CommitNodes> commitTree;


    public static void main(String[] args) {
    }
    public CommitNodes() {
    	commitTree = new LinkedList<CommitNodes>();

    }

    public CommitNodes(int cID, String file, String t, String m) {
    	commitID = cID;
    	filename = file;
    	time = t;
    	message = m;
    	head = this;
    	commitTree = new LinkedList<CommitNodes>();
    	commitTree.add(this);
    }

    public void addNode(int cID, String file, String t, String m) {
    	CommitNodes adder = new CommitNodes(cID,  file,  t,  m);
    	head = adder;
    	commitTree.add(adder);

    }

    public int getID() {
    	return commitID;
    }

    public String getFilename() {
    	return filename;
    }

    public String getMessage() {
    	return message;
    }

    public CommitNodes getHead() {
    	return head;
    }

    public void setHead(CommitNodes n) {
    	head = n;
    }


}