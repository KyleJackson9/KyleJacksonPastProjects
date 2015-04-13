import java.util.*;
import java.io.*;

public class CommitNodes implements Serializable {
	private int commitID;
	private HashSet<String> filenames;
	private CommitNodes head;
	private String time;
	private String message;
	private CommitNodes parent;
	private String branch;
	private LinkedList<CommitNodes> children;


    public static void main(String[] args) {
    }
    public CommitNodes() {
    	children = new LinkedList<CommitNodes>();

    }

    public CommitNodes(String branchName, int cID, HashSet<String> file, String t, String m) {
    	commitID = cID;
    	filenames = file;
    	time = t;
    	message = m;
    	head = this;
    	branch = branchName;
    	children = new LinkedList<CommitNodes>();
    }

    public CommitNodes(String branchName, CommitNodes parent) {
    	branch = branchName;
    	message = parent.getMessage();
    	filenames = parent.getFilenames();
    	head = this;
    	commitID = parent.getID();
    	time = parent.getTime();
    	children = new LinkedList<CommitNodes>();
    	this.parent = parent;

    }

    public void addChild(CommitNodes node) {
    	children.add(node);
    }

    public void setParent(CommitNodes node) {
    	parent = node;
    }

    public CommitNodes getParent() {
    	return parent;
    }

    public String getBranch() {
    	return branch;
    }

    public int getID() {
    	return commitID;
    }

    public String getTime() {
    	return time;
    }
    public void setTime(String t) {
    	time = t;
    }

    public void setMessage(String m) {
    	message = m;
    }

    public HashSet<String> getFilenames() {
    	return filenames;
    }

    public String getMessage() {
    	return message;
    }

    public LinkedList<CommitNodes> getChildren() {
    	return children;
    }

    public CommitNodes getHead() {
    	return head;
    }

    public void setHead(CommitNodes n) {
    	head = n;
    }

}
