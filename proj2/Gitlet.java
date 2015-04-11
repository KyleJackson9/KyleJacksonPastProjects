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
import java.util.Arrays;

public class Gitlet implements Serializable {
	private static int id;
	private static CommitNodes curNode;
	private static int countAdd;
	private static int inputs;
	private static LinkedList<String> branches;
	private static String curBranch;
	private static LinkedList<String> addList;
	private static LinkedList<String> removeList;
	private static LinkedList<String[]> globalList;
	private static HashMap<String, File> previousFiles;
	private static LinkedList<String> previousPaths;
	private static String[] holdCommits;
	private static String timeStamp;
	private static HashMap<String, CommitNodes> getEndBranches;
	private static HashSet<String> comFiles;

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
				comFiles = new HashSet<String>();
				holdCommits = new String[3];
				previousFiles = new HashMap<String, File>();
				getEndBranches = new HashMap<String, CommitNodes>();
				removeList = new LinkedList<String>();
				globalList = new LinkedList<String[]>();
				addList = new LinkedList<String>();
				previousPaths = new LinkedList<String>();
		    	String filename = ".gitlet";
		    	File git = new File(filename);
		    	if (git.exists()) {
		    		System.out.println("A gitlet version control system already exists in the current directory");
		    		return;
		    	} else {
		    		git.mkdir();
		    	}
		    	File c = new File(".gitlet/" + Integer.toString(id));
	            c.mkdir();
		    	timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		    	HashSet<String> comFiles = new HashSet<String>();
		    	comFiles.add(filename);
		    	commit(git, 0, filename, timeStamp, "initial commit");
		    	curNode = new CommitNodes("master", id, comFiles, timeStamp, "initial commit");
		    	getEndBranches.put(curBranch, curNode);
		    	holdCommits[0] = "0";
		    	holdCommits[1] = timeStamp;
		    	holdCommits[2] = "initial commit";
		    	globalList.add(holdCommits);
		    	File previous = new File(".gitlet/previousFiles");
		    	previous.mkdir();
		    	File prev = new File(".gitlet/previousPaths");
		    	prev.mkdir();
		    	File commitID = new File(".gitlet/id");
		    	commitID.mkdir();
		    	File commit = new File(".gitlet/commitNodes");
		    	commit.mkdir();
		    	File branch = new File(".gitlet/branches");
		    	branch.mkdir();
		    	File mast = new File(".gitlet/curNode");
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
		    	saveGitlet(previousPaths, ".gitlet/previousPaths/previousPaths.ser");
		    	saveGitlet(curNode, ".gitlet/curNode/curNode.ser");
		    	saveGitlet(branches, ".gitlet/branches/branches.ser");
		    	saveGitlet(comFiles, ".gitlet/branches/comFiles.ser");
		    	saveGitlet(curBranch, ".gitlet/branches/curBranch.ser");
		    	saveGitlet(holdCommits, ".gitlet/holdCommits/holdCommits.ser");
		    	saveGitlet(globalList, ".gitlet/globalList/globalList.ser");
		    	saveGitlet(removeList, ".gitlet/removeList/removeList.ser");
		    	saveGitlet(addList, ".gitlet/addList/addList.ser");
		    	saveGitlet(getEndBranches, ".gitlet/branches/getEndBranches.ser");
	            System.out.println("====");
	    		System.out.println("initial commit");
	    		System.out.println(timeStamp);
            } else {

            switch (command) {
            	case "test":
            	    curNode = loadGitlet(".gitlet/curNode/curNode.ser");
            	    CommitNodes temp4 = curNode;

            		while (temp4 != null) {
            			System.out.println(temp4.getID());
            			System.out.println(temp4.getBranch());
            			System.out.println(temp4.getFilenames());
            			System.out.println(temp4.getTime());
            			System.out.println(temp4.getMessage());

            			temp4 = temp4.getParent();
            		}
            		break;
            case "add":
            	previousFiles = loadGitlet(".gitlet/previousFiles/previousFiles.ser");
            	addList = loadGitlet(".gitlet/addList/addList.ser");
            	removeList = loadGitlet(".gitlet/removeList/removeList.ser");
            	id = loadGitlet(".gitlet/id/id.ser");
            	boolean tester = false;
                File toAdd = new File(args[1]);
                File toTest = new File(".gitlet/" + Integer.toString(id) + "/" + args[1]);
                	if (!toAdd.exists()) {
                		System.out.println("File does not exist.");
                	} else if (previousFiles.containsKey(args[1])) {
                		try {
                			tester = Arrays.equals(Files.readAllBytes(toAdd.toPath()), Files.readAllBytes(toTest.toPath()));
                		} catch (IOException e) {
                			System.out.println(e);
                		}
                		 if (tester) {
                		 	System.out.println("File has not been modified since the last commit.");
	                	} else if (removeList.contains(args[1])) {
                		removeList.remove(args[1]);
                		}
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
            	previousFiles = loadGitlet(".gitlet/previousFiles/previousFiles.ser");
            	previousPaths = loadGitlet(".gitlet/previousPaths/previousPaths.ser");
            	addList = loadGitlet(".gitlet/addList/addList.ser");
            	removeList = loadGitlet(".gitlet/removeList/removeList.ser");
            	id = loadGitlet(".gitlet/id/id.ser");
            	holdCommits = loadGitlet(".gitlet/holdCommits/holdCommits.ser");
            	curNode = loadGitlet(".gitlet/curNode/curNode.ser");
            	globalList = loadGitlet(".gitlet/globalList/globalList.ser");
            	curBranch = loadGitlet(".gitlet/branches/curBranch.ser");
            	getEndBranches = loadGitlet(".gitlet/branches/getEndBranches.ser");
            	if (addList.size() == 0 && removeList.size() == 0) {
            		return;
            	}
            	String message = args[1];
            	id++;
            	File commit = new File(".gitlet/" + Integer.toString(id));
            	commit.mkdir();
            	HashSet<String> comFiles = new HashSet<String>();
            	String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            	for (int i = 0; i < addList.size(); i++) {
            		String filename = addList.get(i);
            		File commitFile = new File(filename);
            		commit(commitFile, id, filename, timeStamp, message);
            	}
            	for (int i = 0; i < previousPaths.size(); i++) {
            		if (!removeList.contains(previousPaths.get(i))) {
            			String filename = previousPaths.get(i);
            			File commitFile = new File(previousPaths.get(i));
            			commit2(commitFile, id, filename, timeStamp, message);
            			
            		}
            	}
            	previousPaths.clear();
            	for (String s : previousFiles.keySet()) {
            		if (!removeList.contains(s)) {
            		comFiles.add(s);
            		previousPaths.add(s);
            	}
            	}
            	CommitNodes tempNode = new CommitNodes(curBranch, id, comFiles, timeStamp, message);
            	tempNode.setParent(curNode);
            	curNode = tempNode;
            	System.out.println(curNode.getFilenames());
            	getEndBranches.put(curBranch, curNode);
            	getEndBranches.put(holdCommits[0], curNode);
            	System.out.println("====");
    			System.out.println(message);
    			System.out.println(timeStamp);
    			globalList.add(holdCommits);
            	addList.clear();
            	removeList.clear();
            	    saveGitlet(globalList, ".gitlet/globalList/globalList.ser");
            	  	saveGitlet(addList, ".gitlet/addList/addList.ser");
            	  	saveGitlet(removeList, ".gitlet/removeList/removeList.ser");
            	  	saveGitlet(curNode, ".gitlet/curNode/curNode.ser");
            	  	saveGitlet(holdCommits, ".gitlet/holdCommits/holdCommits.ser");
            	  	saveGitlet(id, ".gitlet/id/id.ser");
            	  	saveGitlet(previousPaths, ".gitlet/previousPaths/previousPaths.ser");
            	  	saveGitlet(previousFiles, ".gitlet/previousFiles/previousFiles.ser");
            	  	saveGitlet(getEndBranches, ".gitlet/branches/getEndBranches.ser");
            	break;
            case "global-log":
                try {
                	globalList = loadGitlet(".gitlet/globalList/globalList.ser");
                	for (int i = 0; i < globalList.size(); i++) {
                		System.out.println("====");
                		System.out.println("Commit " + globalList.get(i)[0] + ".");
                		System.out.println(globalList.get(i)[1]);
    					System.out.println(globalList.get(i)[2]);
    					System.out.println();
                	}
                    break;
                } catch (Throwable t) {
                    break;
                }
            case "find":
                	globalList = loadGitlet(".gitlet/globalList/globalList.ser");
                	boolean breaker = true;
                	for (int i = 0; i < globalList.size(); i++) {
                		if (globalList.get(i)[2].equals(args[1])) {
                			System.out.println(globalList.get(i)[0]);
                			breaker = false;
                		}
                	}
                	if (breaker) {
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
                	curNode = loadGitlet(".gitlet/curNode/curNode.ser");
                	CommitNodes looper = curNode;
                	while (looper != null) {
                		System.out.println("====");
                		System.out.println("Commit " + looper.getID() + ".");
                		System.out.println(looper.getTime());
                		System.out.println(looper.getMessage());
                		System.out.println();
                		looper = looper.getParent();
                	}
                    break;
                } catch (Throwable t) {
                    break;
                }
            case "checkout":
            try {
                	previousPaths = loadGitlet(".gitlet/previousPaths/previousPaths.ser");
                	previousFiles = loadGitlet(".gitlet/previousFiles/previousFiles.ser");   
                	curNode = loadGitlet(".gitlet/curNode/curNode.ser"); 
                	curBranch = loadGitlet(".gitlet/branches/curBranch.ser"); 
                	branches = loadGitlet(".gitlet/branches/branches.ser");
                	id = loadGitlet(".gitlet/id/id.ser");  
                	getEndBranches = loadGitlet(".gitlet/branches/getEndBranches.ser");       	
                	String info = args[1];
                	if (branches.contains(info)) {
                	 	if (!curBranch.equals(info)) {
                	 		curBranch = info;
                	 		curNode = getEndBranches.get(info);
                	 		previousFiles.clear();
                	 		previousPaths.clear();
                	 		String oldID = Integer.toString(curNode.getID());
                	 		for (String s : curNode.getFilenames()) {
                	 		    File temps = new File(".gitlet/" + oldID + "/" + s);
                	 		    previousFiles.put(s, temps);
                	 		    previousPaths.add(s);

							}
							saveGitlet(previousPaths, ".gitlet/previousPaths/previousPaths.ser");
							saveGitlet(previousFiles, ".gitlet/previousFiles/previousFiles.ser");
                	 		saveGitlet(curNode, ".gitlet/curNode/curNode.ser");
                	 		saveGitlet(curBranch, ".gitlet/branches/curBranch.ser");
                	 		break;
                	 	} else {
                	 		System.out.println("No need to checkout of the current branch.");
                	 	}
                	 }  else if (previousPaths.contains(info)) {
                		File replacement = new File(".gitlet/" + Integer.toString(id) + "/" + info);
                		File old = new File(info);
						FileOutputStream oldStream = new FileOutputStream(old, false); 
						byte[] myBytes = Files.readAllBytes(replacement.toPath());
						oldStream.write(myBytes);
						oldStream.flush();
						oldStream.close();
						break;
                	} else {
                		System.out.println("File does not exist in the most recent commit, or no such branch exists.");
                	}
                
                	if (args[2] != null) {
                	if (Integer.parseInt(info) <= id && Integer.parseInt(info) >= 0) {
                		File oldVersion = new File(".gitlet/" + info + "/" + args[2]);
                		if (oldVersion.exists()){
                			File oldFile = new File(args[2]);
							FileOutputStream oStream = new FileOutputStream(oldFile, false); 
							byte[] myBytes = Files.readAllBytes(oldVersion.toPath());
							oStream.write(myBytes);
							oStream.flush();
							oStream.close();
                		} else {
                			System.out.println("File does not exist in that commit.");
                			break;
                		}
                	} else {
                		System.out.println("No commit with that id exists.");
                	}
                	}  	
                    break;
                    } catch (Throwable t) {
                    	System.out.println(t);
                    	break;
                    }

                
            case "branch":
                	branches = loadGitlet(".gitlet/branches/branches.ser");
                	getEndBranches = loadGitlet(".gitlet/branches/getEndBranches.ser");
                	curNode = loadGitlet(".gitlet/curNode/curNode.ser");
                	String branch = args[1];
                	if (branches.contains(branch)) {
                		System.out.println("A branch with that name already exists.");
                	} else {
                		branches.add(branch);
                		getEndBranches.put(branch, curNode);
                		saveGitlet(getEndBranches, ".gitlet/branches/getEndBranches.ser");
                		saveGitlet(branches, ".gitlet/branches/branches.ser");
                	}
                    break;
            case "remove":
                	addList = loadGitlet(".gitlet/addList/addList.ser");
                	removeList = loadGitlet(".gitlet/removeList/removeList.ser");
                	previousPaths = loadGitlet(".gitlet/previousPaths/previousPaths.ser");
                	if (addList.contains(args[1])) {
                		addList.remove(args[1]);
                	} else {
                		if (!previousPaths.contains(args[1])) {
                			System.out.println("No reason to remove the file.");
                		} else if (!removeList.contains(args[1])) {
                			removeList.add(args[1]);
                		} else {
                			System.out.println("File already on removal list.");
                		}
                }
                	saveGitlet(addList, ".gitlet/addList/addList.ser");
                	saveGitlet(removeList, ".gitlet/removeList/removeList.ser");
                    break;
            case "reset":
                try {
                	id = loadGitlet(".gitlet/id/id.ser");
                	getEndBranches = loadGitlet(".gitlet/branches/getEndBranches.ser");
                	curBranch = loadGitlet(".gitlet/branches/curBranch.ser");
                	curNode = loadGitlet(".gitlet/curNode/curNode.ser");
                	previousPaths = loadGitlet(".gitlet/previousPaths/previousPaths.ser");
                	previousFiles = loadGitlet(".gitlet/previousFiles/previousFiles.ser");

                	String cID = args[1];
                	int check = Integer.parseInt(cID);
                	if (check <= id && check >= 0) {
                	CommitNodes temp = getEndBranches.get(cID);
                	System.out.println(temp);
                	System.out.println(temp.getFilenames());
                	previousPaths.clear();
                	previousFiles.clear();
                	for (String s : temp.getFilenames()) {
                		System.out.println(s);
                			File oldVersion = new File(".gitlet/" + cID + "/" + s);
                			previousFiles.put(s, oldVersion);
                	 		previousPaths.add(s);
							File oldFile = new File(s);
							FileOutputStream oStream = new FileOutputStream(oldFile, false); 
							byte[] myBytes = Files.readAllBytes(oldVersion.toPath());
							oStream.write(myBytes);
							oStream.flush();
							oStream.close();
                	}
                	curBranch = temp.getBranch();
                	curNode = getEndBranches.get(temp.getBranch());
                			saveGitlet(previousPaths, ".gitlet/previousPaths/previousPaths.ser");
							saveGitlet(previousFiles, ".gitlet/previousFiles/previousFiles.ser");
                	 		saveGitlet(curNode, ".gitlet/curNode/curNode.ser");
                	 		saveGitlet(curBranch, ".gitlet/branches/curBranch.ser");
                	break;
                }

                	 else {
                			System.out.println("No commit with that id exists.");
                		}
                    break;
                } catch (Throwable t) {

                    break;
                }
            case "rm-branch":
                try {
                	curBranch = loadGitlet(".gitlet/branches/curBranch.ser"); 
                	branches = loadGitlet(".gitlet/branches/branches.ser");
                	String bToRemove = args[1];

                	if (bToRemove.equals(curBranch)) {
                		System.out.println("Cannot remove the current branch.");
                	} else if (branches.contains(bToRemove)) {
                		branches.remove(bToRemove);
                	} else {
                		System.out.println("A branch with that name does not exist.");
                	}
                	saveGitlet(branches, ".gitlet/branches/branches.ser");

                    break;
                } catch (Throwable t) {

                    break;
                }
            case "merge":
                try {
                	String mBranch = args[1];


                	if (!branches.contains(mBranch)) {
                		System.out.println("A branch with that name does not exist.");
                	} else if (curBranch.equals(mBranch)) {
                		System.out.println("Cannot merge a branch with itself.");
                	} else {
                		int split = findSplit(getEndBranches.get(mBranch), curNode);
                	}
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

            case "i-rebase":
                try {
                    break;
                } catch (Throwable t) {

                    break;
                }
            default:
                break;
            }
        }
    }

    public static void commit(File f, int id, String file, String timeStamp, String message){
    	holdCommits[0] = Integer.toString(id);
    	holdCommits[1] = timeStamp;
    	holdCommits[2] = message;
    	System.out.println(file);
    	previousPaths.add(file);
    	File k = new File(file);
    	File test = new File(".gitlet/" + holdCommits[0] + "/" + file);
    	try {
    		Files.copy(k.toPath(), test.toPath()); 	
	    } catch (IOException e) {
	    	System.out.println(e);
	    }
	    previousFiles.put(file,f);
    }
    public static void commit2(File f, int id, String file, String timeStamp, String message){
    	holdCommits[0] = Integer.toString(id);
    	holdCommits[1] = timeStamp;
    	holdCommits[2] = message;
    	previousFiles.put(file, f);
    	File test = new File(".gitlet/" + holdCommits[0] + "/" + file);
    	try {
    		Files.copy(f.toPath(), test.toPath()); 	
	    } catch (Throwable T) {
	    }
    }
    private static int findSplit(CommitNodes old, CommitNodes current) {
    	CommitNodes temp1 = old;
    	CommitNodes temp2 = current;
    	LinkedList<Integer> hold = new LinkedList<Integer>();
    	while (temp1 != null && temp2 != null) {
    		if (temp1 != null) {
    			if (hold.contains(temp1.getID())) {
    				return temp1.getID();
    			} else {
    			hold.add(temp1.getID());
    			temp1 = temp1.getParent();
    		}
    		}
    		if (temp2 != null) {
    			if (hold.contains(temp2.getID())) {
    				return temp2.getID();
    			} else {
    			hold.add(temp1.getID());
    			temp2 = temp2.getParent();
    		}
    	}
    	}
    	return 0;

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
                String msg = "IOException while loading" + filename;
                System.out.println(msg);
            } catch (ClassNotFoundException e) {
                String msg = "ClassNotFoundException while loading " + filename;
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
