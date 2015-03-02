import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.Iterator;
/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<K, V> implements Map61B<K, V>, Iterable<K> { 
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;
    private int size;

    public static <K,V> ULLMap<V,K> invert(ULLMap<K,V> map){
        int size = map.size();
        V value;
        K key;
        ULLMap<K,V> map2 = map;
        ULLMap<V,K> map3 = new ULLMap<V,K>();

        
        while (size != 0){
            size -=1;
            value = (V) map2.front.val;
            key = (K) map2.front.key;
            map3.put(value,key);
            map2.front = map2.front.next;
        }
        return map3;
    }

    @Override
    public V get(K key) { 
        if (front == null){
            return null;
        }
        for (Entry x = front; !x.equals(null); x=x.next){
            if (key.equals(x.key)){
                return (V) x.val;
            }
        }
        return null;

    }

    @Override
    public void put(K key, V val) { 
        V old;
        Entry f = front;
        if(containsKey(key)){
            old = (V) f.get(key).val;
            while (!f.equals(null)){
                if (f.val.equals(old)){
                    f.val = val;
                    break;
                } else {
                    f = f.next;
                }
            }
        }
        else{
        size +=1;
        front = new Entry(key, val, front);
        }
 
    }

    @Override
    public boolean containsKey(K key) { 
        if (front == null){
            return false;
        }
        if (front.get(key) != (null)){
            return true;
        }
        return false; 
    }

    @Override
    public int size() {
        return size; 
    }

    @Override
    public void clear() {
        while (!front.equals(null)){
        front = front.next;
    }
    }

    @Override
     public Iterator<K> iterator(){
            return new ULLMapIter();
     }



    private class ULLMapIter<K, V> implements Iterator<V> {

        private V returned;
        private int count;


        public ULLMapIter(){
            count = 0;

        }
        public boolean hasNext(){
            if (count!= 0){
                return true;
            }
            return false;
        }
        public V next (){
             //int diff = size() - count;
            int i =0;
             Entry f = front;
             if (count > size()){
                return null;
             } else{
             while (i < count){
                f = f.next;
                i +=1;

             }
             returned =  (V) f.val;
             count +=1;
             return returned;
            }
        
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry<K, V> {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(K k, V v, Entry n) { 
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(K k) { 

            Entry loop = front;
            if (loop == (null)){
                 return null; 
            } 
            while (loop != (null)){ 
                if (key.equals(k)){
                    return loop;
                } else{
                loop = loop.next;
             }
        }
        return null;

           
        }


        /** Stores the key of the key-value pair of this node in the list. */
        private K key;
        /** Stores the value of the key-value pair of this node in the list. */
        private V val; 
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public V remove(K key) { 
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove( K key, V value) { 
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() { 
        throw new UnsupportedOperationException();
    }


}