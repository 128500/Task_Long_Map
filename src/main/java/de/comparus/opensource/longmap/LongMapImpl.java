package de.comparus.opensource.longmap;

import java.util.HashSet;
import java.util.Set;

public class LongMapImpl<V> implements LongMap<V> {

    static class PairKV<V> {

        /*The field containing the 'key' value
        * Note that its value must be positive*/
        private final long key;

        /*The field containing the value of generic class 'V'*/
        private V value;

        /**The field of the class containing the hashCode value.
         * The hashCode value calculates by the value of key field
         * as the latter is always unique.
         */
        private int hash;

        /*Simple constructor*/
        PairKV(long key, V value){
            this.key = key;
            this.value = value;
        }

        /**Setters and getters for the fields.
         * Note that there is no setter for the key field
         * because it is final and MUST NOT (and cannot) be changed.
         */
        public long getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getHash() {
            return hash;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }
    }

    /*An indicator that shows the absence of the element (after
    * 'remove' method was called).*/
    //private final static long EMPTY = -1L;

    /*An array of PairKV<> values*/
    private PairKV<V> [] pairs;

    /*An indicator of how many objects(PairKV<>) are currently in the 'pairs' array*/
    private int existingPairs;

    /*An indicator of the current length of the 'pairs' array*/
    private int size;


    /*Default constructor*/
    LongMapImpl(){
        this.pairs = new PairKV[10];
    }

    /*Constructor defining the size of the creating array*/
    LongMapImpl(int size){
        if (size < 0) throw new IllegalArgumentException("Size of a map must be '0' or more!");
        if (size > (Integer.MAX_VALUE - 1)) throw new IllegalArgumentException("Size of a map mustn't be more than" + (Integer.MAX_VALUE - 1));
        this.pairs = new PairKV[size];
    }

    /**
     * Puts a given value with a given key
     * into the 'map'.
     * @param key - key's value (cannot be 'null')
     * @param value - a given value of V class
     * @return old value of V class if there was assigned value
     * or null if there was no value assigned with the given key
     * or if the value assigned with the given key was null.
     */
    public V put(long key, V value) {
        if (key < 0) throw new IllegalArgumentException("Value of the key cannot be negative!");
        PairKV<V> newPair = new PairKV<>(key, value);
        V v = value;
        if (existingPairs == size) resize();

        if(containsKey(key)){
            for(PairKV<V> pair : pairs){
                if(pair.getKey() == key){
                    v = pair.getValue();
                    pair.setValue(value);
                }
            }
        }
        else for (PairKV<V> pair : pairs){
           if(pair == null) {
               pair = newPair;
               break;
           }
        }
        return v;
    }

    public V get(long key) {
        return null;
    }

    public V remove(long key) {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean containsKey(long key) {
        for (PairKV<V> pair : pairs){
            if(pair.getKey() == key) return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (PairKV<V> pair : pairs){
            if(pair.getValue() == value ||
                    (value != null && pair.getValue().equals(value))) return true;
        }
        return false;
    }

    public long[] keys() {
        return null;
    }

    public V[] values() {
        return null;
    }

    public long size() {
        return 0;
    }

    public void clear() {

    }

    private void resize(){
       int  newSize = Math.round(size * 1.1f);
       PairKV<V>[] newPairs = new PairKV[newSize];
       for(int i = 0; i < size; i++){
           newPairs[i] = pairs[i];
           pairs[i] = null;
       }

       pairs = newPairs;
       size = pairs.length;
    }
}
