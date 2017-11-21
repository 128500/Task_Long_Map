package de.comparus.opensource.longmap;

/**
 * This interface imitates the Map interface.
 * It stores the key-value elements. Each key corresponds to
 * the value it is associated with. LongMap cannot have duplicates of
 * key (each key is unique).
 * As a key it takes long values and as a value
 * it takes any object that also might be 'null'.
 * Note!!! This imitation does not provide a random access to an element
 * containing in the map like a HashMap does.
 * @param <V> - generic type of an object that stores in this map
 */
public interface LongMap<V> {

    /**
     * Puts a given value with a given key
     * into the 'map'.
     * @param key - key's value (cannot be 'null')
     * @param value - a value of generic type 'V'
     * @return old value of V class if there was assigned value
     * or null if there is no value assigned with the given key
     * or if the value assigned with the given key is null.
     */
    V put(long key, V value);

    /**
     * Retrieves the value from the map according
     * to the given key
     * @param key - value of the key
     * @return  - value of generic type V with
     * which associated the given key, or null if there are none
     */
    V get(long key);

    /**
     * Removes from the map an element
     * according to the given key
     * @param key - value of the key
     * @return value of generic type V which was removed
     */
    V remove(long key);

    /**
     * Checks if this map is empty (has no elements in it)
     * @return true - if it is empty, false - if not
     */
    boolean isEmpty();

    /**
     * Checks if this map contains a specific key
     * @param key - value of the key
     * @return true if it contains the given key, false - if not
     */
    boolean containsKey(long key);

    /**
     * Checks if this map contains a specific value
     * @param value - value of generic type V
     * @return true -  if it contains the given value, false - if not
     */
    boolean containsValue(V value);

    /**
     * Returns an array of key values that this map contains
     * at the current moment
     * @return an array of the key values
     */
    long[] keys();

    /**
     * Returns an array of the generic type V values that this map contains
     * at the current moment
     * @return an array of the the generic type V values
     */
    V[] values();

    /**
     * Shows the size of the map (how many elements in it)
     * @return the amount of elements containing in this map
     */
    long size();

    /**
     * Clears this map (all elements are put to 'null' value)
     */
    void clear();
}
