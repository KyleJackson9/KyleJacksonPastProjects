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



public class Gitlet implements Serializable {
//need a master branch that holds all
	private static int id;
	private static CommitNodes master;
	private static int countAdd;
	private static int inputs;
	private static LinkedList<String> branches;
	private static String curBranch;
	private static LinkedList<String> addList;
	private static LinkedList<String> removeList;
	private static LinkedList<String[]> globalList;
	private static HashSet<File> previousFiles;
	private static String[] holdCommits;
	private static String timeStamp;
	private static HashMap<String, String> finder;


	public Gitlet() {

	}

	public static void main(String[] args) {

            String command = "";
            if (args[0] != null) {
            	command = args[0];
        	} else {
        		return;
        	}
            inputs = args.length - 1;
            if (command.equals("init")) {
            	    	timeStamp = "";
			branches = new LinkedList<String>();
			branches.add("master");
			curBranch = "master";
			id = 0;
			countAdd = 0;
			inputs = 0;
			holdCommits = new String[3];
			finder = new HashMap<String,String>();
			previousFiles = new HashSet<File>();
			master = new CommitNodes();
			removeList = new LinkedList<String>();
			globalList = new LinkedList<String[]>();
			addList = new LinkedList<String>();
	    	String filename = ".gitlet";
	    	File git = new File(filename);
	    	if (git.exists()) {
	    		System.out.println("A gitlet version control system already exists in the current directory");
	    		return;
	    	} else {
	    		git.mkdir();
	    		
	    	}
	    	timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	    	commit(git, 0, filename, timeStamp, "initial commit");
	    	holdCommits[0] = "0";
	    	holdCommits[1] = timeStamp;
	    	holdCommits[2] = "initial commit";
	    	globalList.add(holdCommits);
	    	File previous = new File(".gitlet/previousFiles");
	    	previous.mkdir();
	    	File find = new File(".gitlet/finder");
	    	find.mkdir();
	    	File commitID = new File(".gitlet/id");
	    	commitID.mkdir();
	    	File commit = new File(".gitlet/commitNodes");
	    	commit.mkdir();
	    	File branch = new File(".gitlet/branches");
	    	branch.mkdir();
	    	File mast = new File(".gitlet/master");
	    	mast.mkdir();
	    	File hold = new File(".gitlet/holdCommits");
	    	hold.mkdir();
	    	File remove = new File(".gitlet/removeList");
	    	remove.mkdir();
	    	File global = new File(".gitlet/globalList");
	    	global.mkdir();
	    	File add = new File(".gitlet/addList");
	    	add.mkdir();
	    	saveGitlet(id, ".gitlet/id/id.ser");

	    	saveGitlet(previousFiles, ".gitlet/previousFiles/previousFiles.ser");
	    	saveGitlet(master, ".gitlet/commitNodes/CommitNodes.ser");
	    	saveGitlet(branches, ".gitlet/branches/branches.ser");
	    	saveGitlet(curBranch, ".gitlet/branches/curBranch.ser");
	    	saveGitlet(finder, ".gitlet/finder/finder.ser");
	    	saveGitlet(holdCommits, ".gitlet/holdCommits/holdCommits.ser");
	    	saveGitlet(globalList, ".gitlet/globalList/globalList.ser");
	    	saveGitlet(removeList, ".gitlet/removeList/removeList.ser");
	    	saveGitlet(addList, ".gitlet/addList/addList.ser");
            	System.out.println("====");
    			System.out.println("initial commit");
    			System.out.println(timeStamp);
            } else {

            switch (command) {
            case "add":
            	previousFiles = loadGitlet(".gitlet/previousFiles/previousFiles.ser");
            	addList = loadGitlet(".gitlet/addList/addList.ser");
            	removeList = loadGitlet(".gitlet/removeList/removeList.ser");

                	File toAdd = new File(args[1]);
                	if (!toAdd.exists()) {
                		System.out.println("File does not exist.");
                	} else if (previousFiles.contains(toAdd)) {
                		System.out.println("File has not been modified since the last commit.");
                	} else if (removeList.contains(args[1])) {
                		removeList.remove(args[1]);
                	} else {
                		if (!addList.contains(args[1])){
							addList.add(args[1]);
						} else {
							System.out.println("File has already been staged.");
						}
                	} 
                	saveGitlet(previousFiles, ".gitlet/previousFiles/previousFiles.ser");
            	  	saveGitlet(addList, ".gitlet/addList/addList.ser");
            	  	saveGitlet(removeList, ".gitlet/removeList/removeList.ser");
                    break;

            case "commit":
            // try{
            	previousFiles = loadGitlet(".gitlet/previousFiles/previousFiles.ser");
            	addList = loadGitlet(".gitlet/addList/addList.ser");
            	removeList = loadGitlet(".gitlet/removeList/removeList.ser");
            	id = loadGitlet(".gitlet/id/id.ser");
            	holdCommits = loadGitlet(".gitlet/holdCommits/holdCommits.ser");
            	master = loadGitlet(".gitlet/master/master.ser");
            	finder = loadGitlet(".gitlet/finder/finder.ser");
            	if (addList.size() == 0){
            		return;
            	}


            	String message = args[1];
            	id++;
            	for (int i = 1; i < inputs; i++){
            		message += args[1 + i];
            	}
            	for (int i = 0; i < addList.size(); i++) {
            		String filename = addList.get(i);
            		File commitFile = new File(filename);
            		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            		commit(commitFile, id, filename, timeStamp, message);
            	}
            	System.out.println("====");
    			System.out.println(message);
    			System.out.println(timeStamp);
    			globalList.add(holdCommits);
            	addList.clear();
            	removeList.clear();
            	    saveGitlet(globalList, ".gitlet/globalList/globalList.ser");
            	  	saveGitlet(addList, ".gitlet/addList/addList.ser");
            	  	saveGitlet(removeList, ".gitlet/removeList/removeList.ser");
            	  	saveGitlet(master, ".gitlet/master/master.ser");
            	  	saveGitlet(holdCommits, ".gitlet/holdCommits/holdCommits.ser");
            	  	saveGitlet(id, ".gitlet/id/id.ser");
            	  	saveGitlet(finder, ".gitlet/finder/finder");
            	break;
            // } catch(Throwable t) {
            // 	System.out.println("commit failed");
            // 	break;
            // }

            case "global-log":
                try {
                	globalList = loadGitlet(".gitlet/globalList/globalList.ser");
                	for (int i = 0; i < globalList.size(); i++) {
                		System.out.println("====");
                		System.out.println("Commit " + globalList.get(i)[0]);
                		System.out.println(globalList.get(i)[1]);
    					System.out.println(globalList.get(i)[2]);
    					
                	}

                    break;
                } catch (Throwable t) {
                    break;
                }
            case "find":
                	finder = loadGitlet(".gitlet/finder/finder.ser");

                	if (finder.get(args[1]) != null) {
                		System.out.println(finder.get(args[1]));
                	} else {
                		System.out.println("Found no commit with that message.");
                	}
                	break;

            case "status":
                try {
                	branches = loadGitlet(".gitlet/branches/branches.ser");
                	curBranch = loadGitlet(".gitlet/branches/curBranch.ser");
                	addList = loadGitlet(".gitlet/addList/addList.ser");
                	removeList = loadGitlet(".gitlet/removeList/removeList.ser");

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
                
                    break;
                } catch (Throwable t) {

                    break;
                }
            case "log":
                try {
                	globalList = loadGitlet(".gitlet/globalList/globalList.ser");
                	for (int i = 0; i < globalList.size(); i++) {
                		System.out.println("====");
                		System.out.println("Commit " + globalList.get(i)[0]);
                		System.out.println(globalList.get(i)[1]);
    					System.out.println(globalList.get(i)[2]);
    					
                	}
                
                    break;
                } catch (Throwable t) {

                    break;
                }
            case "checkout":
                try {

                    
                    break;
                } catch (Throwable t) {

                    break;
                }
            case "branch":
                try {

                    break;
                } catch (Throwable t) {

                    break;
                }

            case "remove":
                try {
                	if (addList.contains(args[1])) {
                		addList.remove(args[1]);
                	}
                    break;
                } catch (Throwable t) {

                    break;
                }

            case "reset":
                try {
                    break;
                } catch (Throwable t) {

                    break;
                }

            case "merge":
                try {
                    break;
                } catch (Throwable t) {
                    break;
                }

            case "rebase":
                try {
                    break;
                } catch (Throwable t) {

                    break;
                }

            case "interactive":
                try {
                    break;
                } catch (Throwable t) {

                    break;
                }
            default:
                System.out.println("Invalid command.");
                break;
            }
        }
    }
    public static void commit(File f, int id, String file, String timeStamp, String message){

    	countAdd = 0;
    	holdCommits[0] = Integer.toString(id);
    	holdCommits[1] = timeStamp;
    	holdCommits[2] = message;
    	previousFiles.add(f);
    	finder.put(message, holdCommits[0]);
    	master.addNode(id, file, timeStamp, message);    	
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
            String msg = "IOException while saving" + filename;
            System.out.println(msg);
        }
    }
}
