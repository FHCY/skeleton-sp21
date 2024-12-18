package hashmap;

import java.util.*;

import static java.lang.Math.abs;

/**
 *  A hash buckets-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double maxLoad;
    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_MAX_LOAD = 0.75;
    private int size = 0;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_MAX_LOAD);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_MAX_LOAD);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        this.maxLoad = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash buckets bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash buckets bucket
     *
     * The only requirements of a hash buckets bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<Node>();
    }

    /**
     * Returns a buckets to back our hash buckets. As per the comment
     * above, this buckets can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the buckets to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    private int hashCode(K key, int length) {
        int keyHashcode = abs(key.hashCode());
        return keyHashcode % length;
    }

    @Override
    public void clear() {
        buckets = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        if (size == 0) {
            return false;
        }
        int location = hashCode(key, buckets.length);
        for (Node c : buckets[location]) {
            if (c.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void put(K key, V value) {
        int location = hashCode(key, buckets.length);
        for (Node c : buckets[location]) {
            if (c.key.equals(key)) {
                c.value = value;
                return;
            }
        }
        Node newNode = createNode(key, value);
        buckets[location].add(newNode);
        size++;
        if ((double) size / buckets.length >= maxLoad) {
            buckets = resize(buckets.length * 2);
        }
    }

    private Collection<Node>[] resize(int length) {
        Collection<Node>[] newBuckets = createTable(length);
        for (Collection<Node> nodes : buckets) {
            for (Node c : nodes) {
                int location = hashCode(c.key, newBuckets.length);
                newBuckets[location].add(c);
            }
        }
        return newBuckets;
    }

    @Override
    public V get(K key) {
        if(!containsKey(key)) {
            return null;
        }
        int location = hashCode(key, buckets.length);
        V value = null;
        for (Node c : buckets[location]) {
            if (c.key.equals(key)) {
                value = c.value;
            }
        }
        return value;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();
        addKeys(buckets, keys);
        return keys;
    }

    private void addKeys(Collection<Node>[] table, Set<K> keys) {
        for (Collection<Node> nodes : table) {
            for (Node c : nodes) {
                if (c != null) {
                    keys.add(c.key);
                }
            }
        }
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int location = hashCode(key, buckets.length);
        V value = null;
        for (Node c : buckets[location]) {
            if (c.key.equals(key)) {
                value = c.value;
                buckets[location].remove(c);
            }
        }
        return value;
    }

    @Override
    public V remove(K key, V value) {
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
