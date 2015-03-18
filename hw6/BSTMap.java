import java.util.Set;
import java.util.TreeSet;
/* Your implementation BSTMap should implement this interface. To do so, 
 * append "implements Map61B<K,V>" to the end of your "public class..."
 * declaration, though you can use other formal type parameters if you'd like.
 */ 
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K, V>  {

    private Node root;
    private TreeSet<K> allkeys;

    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
    }

    private class Node {
        private Node left;
        private Node right;
        private K key;
        private V value;
        private int n;

     public Node(K key1, V value1, int num){
        key = key1;
        value = value1;
        n = num;

        }
    }


    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {

        if (get(key) != null){
            return true;
        }
        return false;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    public V get(K key){
        return get(root, key);
    }

    private V get(Node root, K key){
        if (root == null){
            return null;
        } else {
            int compare = key.compareTo(root.key); //assuming int comparable
            if (compare < 0){
                return get(root.left, key);
            } else if (compare > 0) {
                return get(root.right, key);
            } else {
                return root.value;
            }
    }
    }

   /* Returns the number of key-value mappings in this map. */
    public int size(){
        return root.n;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        root = put(root, key, value);
        //allkeys.add(key);
    }

    private Node put(Node x, K key, V value) {
        if (x == null){
            return new Node(key, value, 1);
        } else {
            int cmp = key.compareTo(x.key);
        if  (cmp < 0) {
            x.left = put(x.left,  key, value);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, value);
        }
        else {
             x.value  = value;
             
            
        }
         x.n += 1;;
            return x;
        
    }
}

    

    /* Removes the mapping for the specified key from this map if present.
     * Not required for HW6. */
    public V remove(K key){
        return null;
    }

    public void printInOrder() {
        // String[] x = new String[];
        // //int i = 0;

        // for (int i : root.n){
        //     x[i] = get(root, )

        // }
        System.out.println(allkeys);

    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for HW6a. */
    public V remove(K key, V value){
        return null;
    }

    //  Returns a Set view of the keys contained in this map. Not required for HW6. 
    public Set<K> keySet(){
        return allkeys;
    }    
}
