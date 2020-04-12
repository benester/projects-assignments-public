import java.util.NoSuchElementException;

/**
 *The Class BinarySearchST is an implementation of the data-structure of a binary symbol table. Where the
 * user can input a Value with a corresponding key.
 * Code generated 2019-10-2
 * @author Benjamin Gafvelin
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int sizeST = 0;

    /**
     * Constructor for the SymbolTable which creates it empty with an initial capacity of 2
     */
    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    /**
     * Constructor for the SymbolTale which creates it with the initial capacity of the @param capacity
     * @param capacity The specified capacity for the symbol table
     */

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }


    /**
     * A helper method that when needed resize's the SymbolTable when it is at capacity or at quarter capacity and sets the new
     * capacity to @param capacity, which in this case will be twice the size of the current array, or half the size.
     * The key array and Value array will be copied to two new arrays, where the second arrays are twice the size, or half the size
     * is the array is a quarter full.
     * @param capacity the new capacity for the symbol table.
     */
    private void resize(int capacity) {
        assert capacity >= sizeST;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < sizeST; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    /**
     *This class return the amount of elements-pairs in the symbol table. It returns the Class member, Instance variable n
     * @return The number of element-pairs in the symbol table.
     */
    public int size() {
        return sizeST;
    }

    /**
     * This method will return whether or not the symbol table is empty
     * @return Returns true if the symbol table is empty, and false if it is not empty
     */

    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     * This method checks whether or not a given key exists in the symbol table.
     *
     * @param  key The key to check if it exist in the symbol table
     * @return Returns true if the key does exist in the array, and false if it does not.
     * @throws IllegalArgumentException If the specified key is null, then this exception is thrown
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("The Argument used to call the contains method is not valid");
        return get(key) != null;
    }

    /**
     * The get method will return the value associated with the key in the symbol table. If the key
     * does not exist in the symbol table, the return value will be null
     *
     * @param  key The value to compare to what's inside the symbol table.
     *
     * @return WIll return the associated value to the key if it exist, otherwise will return null.
     *
     * @throws IllegalArgumentException WIll throw this Exception if the specified key is equal to null
     */

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("The specified argument for the method get() is not valid, since it is null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < sizeST && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    /**
     * This method will calculate the amount of keys with a value less than a specified key
     * @param  key The key to be compared to the other keys
     * @return An Integer with the amount of keys with a value less than a specified key
     * @throws IllegalArgumentException Will be thrown if the specified key is null
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("Argument to method rank() is not valid since it is null");

        int lo = 0, hi = sizeST-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */


    /**
     * This method is used to insert the @param key and @param val into the symbol table. If a key already
     * has a value, the value will be overwritten.
     * @param key The key associated with the value
     * @param val The value to be associated with the key
     */
    public void put(Key key, Value val)  {
        if (key == null) throw new IllegalArgumentException("Key value is set to illegal argument: null");

        int i = rank(key);

        // If the key already exist inside the symbol table
        if (i < sizeST && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // If the symbol table is full
        if (sizeST == keys.length) resize(2*keys.length);

        //Inserting the new keypair
        for (int j = sizeST; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        sizeST++;

    }

    /**
     * This method is used to return the value of the smallest element in the symbol table to the user
     *
     * @return The minimum value in the symbol table
     * @throws NoSuchElementException In the case of an empty symbol table
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("This symbol table is empty. Cannot retrieve min()");
        return keys[0];
    }

    /**
     * This method is used to return the value of the biggest element in the symbol table to the user
     *
     * @return The maximum value in the symbol table
     * @throws NoSuchElementException In the case of an empty symbol table
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("This symbol table is empty. Cannot retrieve max()");
        return keys[sizeST-1];
    }

    /**
     * Will return all the keys in the symbol table as an Iterable. This can be used to
     * retrieve all the keys with a foreach notation.
     *
     * @return Every key inside of the symbol table
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Will return all the keys as an Iterable in the ragnge between @param lo to @param hi.
     *
     * @param lo The lower limit (inclusive)
     * @param hi The upper limit (inclusive)
     * @return Will return all the keys between lo and hi, as an Iterable
     * @throws IllegalArgumentException Will be thrown if either lo or hi is equal to null
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("lo argument in keys call is null");
        if (hi == null) throw new IllegalArgumentException("hi argument in keys call is null");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }
}