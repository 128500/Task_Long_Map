package de.comparus.opensource.longmap;


import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * TODO checking the array for emptiness
 * TODO "not equal to null" checking in methods
 * This is a simple imitation of widely known 'Map' interface.
 * As a real map it allows to store elements as a key-value pair.
 * As a key it takes 'long' (only positive ones) and as a value
 * it takes any object that also might be 'null'.
 * @param <V> - type of the element that must be put into map
 */

public class LongMapImpl<V> implements LongMap<V> {

    static class PairKV<V> {

        /*The field containing the 'key' value
        * Note that its value must be positive*/
        private final long key;

        /*The field containing the value of generic class 'V'*/
        private V value;


        /*Simple constructor*/
        PairKV(long key, V value){
            this.key = key;
            this.value = value;
        }

        /**Setters and getters for the fields.
         * Note that there is no setter for the key field
         * because it is final and MUST NOT (and cannot) be changed.
         */
        long getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        /**int getHash() {
            return hash;
        }*/

        void setValue(V value) {
            this.value = value;
        }

        /**public void setHash(int hash) {
            this.hash = hash;
        }*/
    }

    /*An array of PairKV<> values*/
    private PairKV[] pairs;

    /*An indicator of how many objects(PairKV<>) are currently in the 'pairs' array*/
    private int existingPairs;

    /*An indicator of the current length of the 'pairs' array*/
    private int size;


    /*Default constructor*/
    LongMapImpl(){
        this.pairs =  new PairKV[10];
        this.size = 10;
    }

    /*Constructor defining the size of the creating array*/
    LongMapImpl(int size){
        if (size < 0) throw new IllegalArgumentException("Size of a map must be '0' or more!");
        if (size > (Integer.MAX_VALUE - 1)) throw new IllegalArgumentException("Size of a map mustn't be more than" + (Integer.MAX_VALUE - 1));
        this.pairs = new PairKV[size];
        this.size = size;
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
        V v = value;
        if (existingPairs == size) resize();

        if(containsKey(key)){
            for(PairKV<V> pair : pairs){
                if(pair == null) continue;
                if(pair.getKey() == key){
                    v = pair.getValue();
                    pair.setValue(value);
                }
            }
        }
        else {
            PairKV<V> newPair = new PairKV<>(key, value);
            for (int i = 0; i < size; i++){
                if(pairs[i] == null) {
                    pairs[i] = newPair;
                    break;
                }
            }
        }
        existingPairs++;
        return v;
    }

    public V get(long key) {
        V v = null;
        for(PairKV<V> pair : pairs){
            if(pair == null) continue;
            if(pair.getKey() == key){
                v = pair.getValue();
                break;
            }
        }
        return v;
    }

    public V remove(long key) {
        V v = null;
        for(int i = 0; i < size; i++){
            if(pairs[i] == null) continue;
            if(pairs[i].getKey() == key){
                v = (V) pairs[i].getValue();
                pairs[i] = null;
                existingPairs--;
                break;
            }
        }
        return v;
    }

    public boolean isEmpty() {
        return existingPairs == 0;
    }

    public boolean containsKey(long key) {
        for (PairKV<V> pair : pairs){
            if(pair == null) continue;
            if(pair.getKey() == key) return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (PairKV<V> pair : pairs){
            if(pair == null) continue;
            if(pair.getValue() == value ||
                    (value != null && pair.getValue().equals(value))) return true;
        }
        return false;
    }

    public long[] keys() {
        if(existingPairs == 0) return new long[0];
        long[] keys = new long[existingPairs];
        int keysCounter = 0;
        for(int i = 0; i < existingPairs; i++){
            if(pairs[i] != null && keysCounter < existingPairs){
                keys[keysCounter] = pairs[i].getKey();
                keysCounter++;
            }
        }
        return keys;
    }

    @SuppressWarnings("unchecked")
    public V[] values() {
        if(existingPairs == 0) return(V[]) new Object[0];
        Class<?> clazz = getClassType();
        V[] values = (V[]) Array.newInstance(clazz, existingPairs);
        int valCounter = 0;
        for(int i = 0; i < existingPairs; i++){
            if(pairs[i] != null && valCounter < existingPairs){
                values[valCounter] = (V) pairs[i].getValue();
                valCounter++;
            }
        }
        return values;
    }

    public long size() {
        return (long)existingPairs;
    }

    public void clear() {
        if(existingPairs == 0) return;
        for (int i = 0; i < size; i++){
            if(pairs[i] != null) pairs[i] = null;
        }
        existingPairs = 0;
    }

    private void resize(){
       int  newSize = Math.round(size * 1.1f);
       PairKV[] newPairs = new PairKV[newSize];
       for(int i = 0; i < size; i++){
           newPairs[i] = pairs[i];
           pairs[i] = null;
       }

       pairs = newPairs;
       size = pairs.length;
    }

    private Class<?> getClassType(){
        Class<?> clazz = null;
        for(int i = 0; i < size; i++){
            if(pairs[i] != null) {
                clazz = pairs[i].getValue().getClass();
                break;
            }
        }
        return  clazz;
    }
}
