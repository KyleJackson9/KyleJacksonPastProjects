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
                return r.val; 
            } else {
                return search(r.middle, word, ptr + 1);
            }
        }        
    }

    public PriorityQueue prefixSearch(String word, int k) {
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

        al = new PriorityQueue<TSTNode>(k, compareTST);
        prefixSearch(root, word.toCharArray(), 0, k);
        return al;
        
    }

    /** function to search for a word **/
    private void prefixSearch(TSTNode r, char[] prefix, int ptr, int k) {
        if (r == null) {   
        } else if (prefix[ptr] < r.data) {
            prefixSearch(r.left, prefix, ptr, k);
        } else if (prefix[ptr] > r.data) {
            prefixSearch(r.right, prefix, ptr, k);
        } else {
            if (ptr == prefix.length - 1) {
                if(r.isEnd) {
                    al.add(r);
                }
                traverse(r.middle, k);
            } else {
                prefixSearch(r.middle, prefix, ptr + 1, k);
            }
        }
    }  
    /** function to traverse tree **/
    private void traverse(TSTNode r, int k) {
        if (r != null) {
            // if (al.size() < k) {
                traverse(r.left, k);
                if (r.isEnd) {
                    //System.out.println(r.word);
                    al.add(r);
                }

                traverse(r.middle, k);
                traverse(r.right, k);
        }
    }
}





            // } else {
                // if (r.left != null) {
                //     if (r.left.max > al.peek().val) {
                //         traverse(r.left, k);
                //     }
                // }

                // if (al.peek().val <= r.val && r.isEnd) {
                //     // al.poll();
                //     // System.out.println(r.word)
                //     // al.add(r);
                //     if (al.size() == k) {
                //         al.poll();
                //         al.add(r);
                //     } else {
                //         al.add(r);
                //     }
                // }
                // if (r.middle != null) {
                //     if (r.middle.max > al.peek().val) {
                //         traverse(r.middle, k);
                //     }
                // }

                //                 if (r.right != null) {
                //     if (r.right.max > al.peek().val) {
                //         traverse(r.right, k);
//                 //     }
//                 // }


//             // }
//         }
//     }
// }
