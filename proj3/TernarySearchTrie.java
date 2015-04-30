import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

public class TernarySearchTrie {
    private TSTNode root;
    private PriorityQueue<TSTNode> al;
    private PriorityQueue<String> a2;


    // http://www.sanfoundry.com/java-program-ternary-search-tree/
    // got help from here when thinking of how to implement it (changed much)
    public TernarySearchTrie() {
        root = null;
    }

    /** function to insert for a word **/
    public void insert(String word, double num) {
        root = insert(root, word.toCharArray(), 0, num);
    }

    /** function to insert for a word **/
    public TSTNode insert(TSTNode r, char[] word, int ptr, double num) {
        if (r == null) {
            r = new TSTNode(word[ptr]);
        }
        if (word[ptr] < r.data) {
            if (r.max < num) r.max = num;
            r.left = insert(r.left, word, ptr, num);
        } else if (word[ptr] > r.data) {
            if (r.max < num) r.max = num;
            r.right = insert(r.right, word, ptr, num);
        } else {
            if (ptr + 1 < word.length) {
                if (r.max < num) r.max = num;
                r.middle = insert(r.middle, word, ptr + 1, num);
            } else {
                r.max = (double) num;
                r.val = (double) num;
                r.word = new String(word);
                r.isEnd = true;
            }
        }
        return r;
    }


    /** function to search for a word **/
    public double search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    /** function to search for a word **/
    private double search(TSTNode r, char[] word, int ptr) {
        if (r == null) {
            return -1.0;
        }
        if (word[ptr] < r.data) {
            return search(r.left, word, ptr);
        } else if (word[ptr] > r.data) {
            return search(r.right, word, ptr);
        } else {
            if (r.isEnd && ptr == word.length - 1) {
                return r.val;
            } else if (ptr == word.length - 1) {
                return r.val; //should be -1
            } else {
                return search(r.middle, word, ptr + 1);
            }
        }        
    }

    public PriorityQueue prefixSearch(String word) {
        Comparator<TSTNode> compareTST = new Comparator<TSTNode>() {
            @Override
            public int compare(TSTNode a, TSTNode b) {
                if (a.val > b.val) {
                    return -1;
                } else if (b.val > a.val) {
                    return 1;
                } 
                return 0;
            }
        };

        al = new PriorityQueue<TSTNode>(11, compareTST);
        prefixSearch(root, word.toCharArray(), 0);
        return al;
        
    }

    /** function to search for a word **/
    private void prefixSearch(TSTNode r, char[] prefix, int ptr) {
        if (r == null) {
            
        } else if (prefix[ptr] < r.data) {
            prefixSearch(r.left, prefix, ptr);
        } else if (prefix[ptr] > r.data) {
            prefixSearch(r.right, prefix, ptr);
        } else { //this will get me to the end of the prefix
            String pre = new String(prefix);
            traverse(r,"", pre);

            //pre = Character.prefix.to;

            // if (ptr == prefix.length - 1) {
            //     //a2.add(pre);
            //     traverse(r,"", pre);
            //     System.out.println(pre);
                
            // // } else if (ptr == prefix.length - 1) {
            // //     return r.val; //should be -1
            // // } else {
            // }

            // if (r.middle != null) {
            //     System.out.println("checking mid");
            //     pre = pre + r.middle.data;
            //     prefixSearch(r.middle, pre.toCharArray(), ptr);
            // }
            // if (r.right != null) {
            //     System.out.println("right");
            //     pre = pre + r.right.data;
            //     prefixSearch(r.right, pre.toCharArray(), ptr);
            // }
            // if (r.left != null) {
            //     System.out.println("left");
            //     pre = pre + r.left.data;
            //     prefixSearch(r.left, pre.toCharArray(), ptr);
            // }
        }
    }        
    
    


    // /** function to print tree **/
    // public PriorityQueue traversal(String prefix) {
    //     //use Priority Queue
    //     al = new PriorityQueue<String>();
    //     traverse(root, prefix);
    //     return al;
    // }

    /** function to traverse tree **/
    private void traverse(TSTNode r, String str, String original) {
        //need to fix this to do it only in regard to prefix

        if (r != null) {
            traverse(r.left, str, original);
            str = str + r.data;
            if (r.isEnd) {
                if (str.startsWith(original)) {
                    al.add(r);
                }
            }
            traverse(r.middle, str, original);
            str = str.substring(0, str.length() - 1);
            traverse(r.right, str, original);

        }

    }

}
