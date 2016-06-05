import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

/**
 * Your implementation of HashMap.
 *
 * @author Nishant Roy
 * @version 2.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code STARTING_SIZE}.
     * Do not use magic numbers!
     * Use constructor chaining.
     */
    public HashMap() {
        this(STARTING_SIZE);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     * <p>
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V add(K key, V value) {
        //Null Check
        if ((key == null) || (value == null)) {
            throw new IllegalArgumentException("Null value cannot be added.");
        }

        //Create entry to be added to table
        MapEntry<K, V> entry = new MapEntry(key, value);


        //Check if load factor is exceeded and resize if needed
        double loadFac = (double) (size + 1) / (double) table.length;
        if (loadFac > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }


        //Generate hashcode to use as starting index to add entry
        int hashIndex = Math.abs(entry.getKey().hashCode()) % table.length;

        //If starting index is empty, store entry there
        if (table[hashIndex] == null) {
            table[hashIndex] = entry;

            size++;
            return null;


        } else {
            //Otherwise, look for closest empty spot

            int i = 0;

            boolean isRemoved = false;

            int start = hashIndex;
            int next = hashIndex;

            //Get first entry with this hashcode
            MapEntry<K, V> currentEntry = table[hashIndex];

            //Make temp hashcode to index with from entry to be added
            int hashTemp = Math.abs(entry.getKey().hashCode()) % table.length;

            //Look for closest entry that has been removed
            while (currentEntry != null) {

                if (!isRemoved) {
                    if (currentEntry.isRemoved()) {
                        start = hashTemp;
                        isRemoved = true;
                    }
                }

                //if the key already exists, get the old value and update it
                //return the old value
                if (currentEntry.getKey().equals(key)) {
                    V out = table[hashTemp].getValue();
                    table[hashTemp].setValue(value);
                    return out;
                }

                hashTemp = next;
                currentEntry = table[hashTemp];
                i++;
                next = (Math.abs(entry.getKey().hashCode()) + i) % table.length;
            }

            if (isRemoved) {

                table[start] = entry;
                size++;
                return null;

            } else if (table[hashTemp] == null) {
                table[hashTemp] = entry;
                size++;
                return null;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        //Null check
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        int hashCode = Math.abs(key.hashCode()) % table.length;
        
        MapEntry<K, V> current = table[hashCode];

        int i = 0;
        if (current == null) {
            throw new java.util.NoSuchElementException("Key : <" + key
                + "> was not found in this hashmap");
        }

        int mapSize = 0;

        //loop through the hashmap and look for entry with matching key
        //return old value, and update map entry isRemoved field to true
        while ((mapSize < table.length) && (current != null)) {

            if (current.getKey().equals(key)) {

                if (!current.isRemoved()) {

                    V out = current.getValue();
                    current.setRemoved(true);
                    size--;

                    return out;

                } else if (current.isRemoved()) {
                    throw new java.util.NoSuchElementException("This element "
                            + "has already been removed!");
                }
            }

            i++;

            int hashTemp = (Math.abs(key.hashCode()) + i) % table.length;

            current = table[hashTemp];

            mapSize++;

            //Throw exception if entry doesn't exist
            if (current == null) {
                throw new java.util.NoSuchElementException("Key could not be "
                        + "found");
            }

        }

        if (mapSize == table.length) {
            throw new java.util.NoSuchElementException("Key could not be"
                    + " found");
        }

        return null;
    }

    @Override
    public V get(K key) {

        //Null check
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int hash = key.hashCode() % table.length;

        MapEntry<K, V> current = table[hash];


        //Entry doesn't exist
        if (current == null) {
            throw new java.util.NoSuchElementException("Hashmap does not  "
                    + "contain that key");
        }

        int i = 0;

        int hashTemp = hash;
        int mapSize = 0;

        //Loop through looking for entry with matching key and return its value
        while ((current != null) && (mapSize < table.length)) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }

            i++;

            hashTemp = (Math.abs(key.hashCode()) + i) % table.length;

            current = table[hashTemp];

            mapSize++;
        }

        //Exception when matching key not found
        throw new java.util.NoSuchElementException("The hashmap does not"
                + " contain that key.");
    }

    @Override
    public boolean contains(K key) {
        //Get throws exception if key isn't found, so try-catch block checks
        //if key could be found or not
        try {
            get(key);

        } catch (java.util.NoSuchElementException exception) {

            return false;
        }

        return true;

    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        size = 0;
        table = new MapEntry[STARTING_SIZE];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        for (MapEntry<K, V> entry : table) {

            if (entry != null) {

                if (entry.getKey() != null) {
                    keys.add(entry.getKey());
                }

            }
        }

        return keys;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>();

        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                values.add(entry.getValue());
            }
        }

        return values;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void resizeBackingTable(int length) {
        if ((length <= 0) || (length) < size) {
            throw new IllegalArgumentException("Length was not positive or"
                    + " was less than the size of the hashmap");
        }
        MapEntry<K, V>[] temp = new MapEntry[length];

        int index = 0;

        while ((index < table.length)) {
            MapEntry<K, V> current = table[index];

            if (current == null) {
                index++;
            } else if (current != null) {

                if (!current.isRemoved()) {

                    int hash = Math.abs(current.getKey().hashCode()) % length;
                    if (temp[hash] == null) {

                        temp[hash] = current;

                    } else {

                        int k = 0;
                        int hashTemp =  (Math.abs(current.getKey().hashCode())
                                + k) % length;

                        MapEntry<K, V> toAdd = temp[hashTemp];

                        while (toAdd != null) {
                            k++;

                            hashTemp = (Math.abs(current.getKey().hashCode())
                                    + k) % length;

                            toAdd = temp[hashTemp];

                        }

                        if (temp[hashTemp] == null) {

                            temp[hashTemp] = current;

                        }
                    }
                }
                index++;
            }
        }
        table = temp;

    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}