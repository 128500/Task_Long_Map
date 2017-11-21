package de.comparus.opensource.longmap;


import java.lang.reflect.Array;

/**
 * This is a simple imitation of the 'Map' interface.
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
    }

    /*An array of PairKV<> values*/
    private PairKV[] pairs;

    /*An indicator of how many objects(PairKV<>) are currently in the 'pairs' array*/
    private int existingPairs;


    /*Default constructor*/
    LongMapImpl(){
        this.pairs =  new PairKV[10];
    }

    /*Constructor defining the size of the creating array*/
    LongMapImpl(int size){
        if (size < 0) throw new IllegalArgumentException("Size of a map must be '0' or more!");
        if (size > (Integer.MAX_VALUE - 1)) throw new IllegalArgumentException("Size of a map mustn't be more than" + (Integer.MAX_VALUE - 1));
        this.pairs = new PairKV[size];
    }

    @SuppressWarnings("unchecked")
    public V put(long key, V value) {
        if (key < 0) throw new IllegalArgumentException("Value of the key cannot be negative!");
        V v = null;
        if (existingPairs == pairs.length) resize();
        if (existingPairs == 0) {
            pairs[0] = new PairKV<>(key, value);
            existingPairs++;
            return null;
        }
        int markNull = -1;
        int count = 0;
        for( int i = 0; i < pairs.length; i++){
            if(count == existingPairs && markNull != -1){
                pairs[markNull] = new PairKV<>(key, value);
                existingPairs++;
                return null;
            }
            if(count == existingPairs){
                pairs[count] = new PairKV<>(key, value);
                existingPairs++;
                return null;
            }
            if(pairs[i] == null) markNull = i;
            else {
                if(pairs[i].getKey() == key){
                    v = (V)pairs[i].getValue();
                    pairs[i].setValue(value);
                    existingPairs++;
                    break;
                }
                else count++;
            }
        }
        return v;
    }

    @SuppressWarnings("unchecked")
    public V get(long key) {
        V v = null;
        for(PairKV<V> pair : pairs){
            if(pair != null && pair.getKey() == key){
                v = pair.getValue();
                break;
            }
        }
        return v;
    }

    @SuppressWarnings("unchecked")
    public V remove(long key) {
        V v = null;
        for(int i = 0; i < pairs.length; i++){
            if(pairs[i] != null && pairs[i].getKey() == key){
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

    @SuppressWarnings("unchecked")
    public boolean containsKey(long key) {
        for (PairKV<V> pair : pairs){
            if(pair == null) continue;
            if(pair.getKey() == key) return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
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
        for (int i = 0; i < pairs.length; i++){
            if(pairs[i] != null) pairs[i] = null;
        }
        existingPairs = 0;
    }

    /**
     * Resizes the current array of PairKV values if
     * it is completely filled. The length of the
     * current array increases by 10% (multiplying
     * by 1.1).
     */
    private void resize(){
       int  newSize = Math.round(pairs.length * 1.1f);
       PairKV[] newPairs = new PairKV[newSize];
       for(int i = 0; i < pairs.length; i++){
           newPairs[i] = pairs[i];
           pairs[i] = null;
       }
       pairs = newPairs;
    }

    private Class<?> getClassType(){
        Class<?> clazz = null;
        for(PairKV pair : pairs){
            if(pair != null) {
                clazz = pair.getValue().getClass();
                break;
            }
        }
        return  clazz;
    }
}
