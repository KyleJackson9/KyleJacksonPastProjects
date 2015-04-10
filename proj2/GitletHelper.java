import java.util.*;
import java.io.*;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.LinkedList;

public class GitletHelper {
//need a master branch that holds all
	private int id;
	private int commitID;
	private CommitNodes master;
	private boolean hasCommitted;
	private int countAdd;
	private int inputs;
	private LinkedList<String> branches;
	private String curBranch;
	private LinkedList<String> addList;
	private LinkedList<String> removeList;
	private LinkedList<LinkedList<String>> globalList;
	private String[] holdCommits;
	private String timeStamp;

	public static void main(String[] args) {

	}

	public GitletHelper() {
		timeStamp = "";
		branches.add("master");
		curBranch = "master";
		id = 0;
		commitID = 0;
		hasCommitted = false;
		countAdd = 0;
		inputs = 0;
		holdCommits = new String[3];
	}
	    public void initialize() {
    	String filename = ".gitlet";
    	File git = new File(filename);
    	if (!git.exists()) {
    		System.out.println("A gitlet version control system already exists in the current directory");
    	} else {
    		git.mkdir();
    	}
    	timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    	commitID = id;
    	commit(git, commitID, filename, timeStamp, "initial commit");
    }
    public void status() {
    	System.out.println("=== Branches ===");
		if (branches.size() > 0){
			System.out.println("*" + curBranch);
			for (int i = 0; i < branches.size(); i++) {
				if (!branches.get(i).equals(curBranch)) {
					System.out.println(branches.get(i));
				}
			}
		}
    	System.out.println();


		System.out.println("=== Staged Files ===");
		if (addList.size() > 0){
			for (int i = 0; i < addList.size(); i++) {
				System.out.println(addList.get(i));
			}
		}
		System.out.println();


		System.out.println("=== Files Marked for Removal ===");
		if (removeList.size() > 0) {
			for (int i =0; i < removeList.size(); i++) {
				System.out.println(removeList.get(i));
			}
		}
    }
    public void add(String[] filenames){
    	for (int i =0; i < inputs; i++) {
    		String filename = filenames[i];
    		File file = new File(filename);
    		Path path = Paths.get(filename);
			byte[] data = Files.readAllBytes(path);
			addList.add(filename);
    	}
    	//figure out the bytes to compare if it has change
    }


    public void commit(File f, int id, String file, String timeStamp, String message){
    	saveGitlet(f, file);
    	master.addNode(id, file, timeStamp, message);
    	hasCommitted = true;
    	countAdd = 0;
    	holdCommits[0] = Integer.toString(id);
    	holdCommits[1] = timeStamp;
    	holdCommits[2] = message;
    }

    public int generateID(){
    	id++;
    	return id;
    }

    private static <Zerp> Zerp loadGitlet(String filename) {
    	//majority taken from Sarah Kim
        Zerp myGit = null;
        File myGitFile = new File(filename);
        if (myGitFile.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(myGitFile);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                myGit = (Zerp) objectIn.readObject();
            } catch (IOException e) {
                String msg = "IOException while loading my Git.";
                System.out.println(msg);
            } catch (ClassNotFoundException e) {
                String msg = "ClassNotFoundException while loading my Gitlet.";
                System.out.println(msg);
            }
        }
        return myGit;
    }

    private static <Zerp> void saveGitlet(Zerp myGit, String filename) {
    	//majority taken from Sarah Kim
        if (myGit == null) {
            return;
        }
        try {
            File myGitFile = new File(filename);
            FileOutputStream fileOut = new FileOutputStream(myGitFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(myGit);
        } catch (IOException e) {
            String msg = "IOException while saving myCat.";
            System.out.println(msg);
        }
    }
}