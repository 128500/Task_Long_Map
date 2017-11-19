package de.comparus.opensource.longmap;

import java.util.Collection;
import java.util.Iterator;

public class PairSet<E> implements java.util.Set<E> {

    static class PairKV<Long, V> {

        /*The field containing the 'key' value*/
        private final Long key;

        /*The field containing the value of generic class 'V'*/
        private V value;

        /**The field of the class containing the hashCode value.
         * The hashCode value calculates by the value of key field
         * as the latter is always unique.
         */
        private int hash;

        /*Simple constructor*/
        PairKV(Long key, V value){
            //this.hash = java.lang.Long.hashCode((long)key);
            this.key = key;
            this.value = value;
        }

        /**Setters and getters for the fields.
         * Note that there is no setter for the key field
         * because it is final and MUST NOT be changed.
         */
        public Long getKey() {
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

    private PairKV [] pairs;

    private  final class PairIterator<E> implements java.util.Iterator<E>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }

    /*Indicates the size of this set*/
    private int size;

    /*Iterator to go through this set*/
    private Iterator<PairSet> iterator = new PairIterator<>();


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
