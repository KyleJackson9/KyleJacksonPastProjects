import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

public class TernarySearchTrie {
    private TSTNode root;
    private PriorityQueue<TSTNode> al;

    // http://www.sanfoundry.com/java-program-ternary-search-tree/
    // got help from here when thinking of how to implement it (changed much)
    public TernarySearchTrie() {
        root = null;
    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param word the string to be weighed
     * @param num double of the weight
     */
    public void insert(String word, double num) {
        root = insert(root, word.toCharArray(), 0, num);
    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param r the node to be weighed
     * @param word double of the weight
     * @param ptr pointer
     * @param num double num
     */
    public TSTNode insert(TSTNode r, char[] word, int ptr, double num) {
        if (r == null) {
            r = new TSTNode(word[ptr]);
        }
        if (word[ptr] < r.data) {
            if (r.max < num) {
                r.max = num;
            }
            r.left = insert(r.left, word, ptr, num);
        } else if (word[ptr] > r.data) {
            if (r.max < num) {
                r.max = num;
            }
            r.right = insert(r.right, word, ptr, num);
        } else {
            if (ptr + 1 < word.length) {
                if (r.max < num) {
                    r.max = num;
                }
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

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param term the string to be weighed
     * @return a double of the weight
     */
    public double search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param r the node to be weighed
     * @param word the chararray of the word
     * @param ptr the pointer to it
     * @return a double of the weight
     */
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
                return r.val; 
            } else {
                return search(r.middle, word, ptr + 1);
            }
        }        
    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param word the string to be prefixed
     * @return a PQ of the nodes
     */
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

        al = new PriorityQueue<TSTNode>(50, compareTST);
        prefixSearch(root, word.toCharArray(), 0);
        return al;
        
    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param r the node to be weighed
     * @param prefix to search for
     * @param ptr the pointer to get it
     */
    private void prefixSearch(TSTNode r, char[] prefix, int ptr) {
        if (r == null) {   
        } else if (prefix[ptr] < r.data) {
            prefixSearch(r.left, prefix, ptr);
        } else if (prefix[ptr] > r.data) {
            prefixSearch(r.right, prefix, ptr);
        } else {
            if (ptr == prefix.length - 1) {
                if(r.isEnd) {
                    al.add(r);
                }
                traverse(r.middle);
            } else {
                prefixSearch(r.middle, prefix, ptr + 1);
            }
        }
    }  
    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param r the node to be recursively tried
     */
    private void traverse(TSTNode r) {
        if (r != null) {
            traverse(r.left);
            if (r.isEnd) {
                al.add(r);
            }
            traverse(r.middle);
            traverse(r.right);
        }
    }
}





