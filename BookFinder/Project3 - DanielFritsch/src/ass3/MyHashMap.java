package ass3;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

public class MyHashMap<K, V> {
    private static final int TABLE_SIZE[] = { 5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,
            102877, 205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939,
            210719881, 421439783, 842879579, 1685759167 };
    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private LinkedList<SimpleEntry<K, V>>[] table;
    private int size;
    private final float maxLoadFactor;

    @SuppressWarnings("unchecked")
    private LinkedList<SimpleEntry<K, V>>[] createTable(int capacity) {
        for (int primeCapacity : TABLE_SIZE) {
            if (primeCapacity >= capacity) {
                capacity = primeCapacity;
                break;
            }
        }
        LinkedList<SimpleEntry<K, V>> storage [] = new LinkedList [capacity];
        this.size = 0;
        return storage;
    }

    public MyHashMap(int capacity, float maxLoadFactor) {
        this.table = createTable(capacity);
        this.maxLoadFactor = maxLoadFactor;
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        Arrays.fill(this.table, null);
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Null keys or values are not allowed");
        }

        int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;

        if (this.table[index] == null) {
            this.table[index] = new LinkedList<>();
        }

        for (SimpleEntry<K, V> entry : this.table[index]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        this.table[index].add(new SimpleEntry<>(key, value));
        this.size++;

        if ((float) this.size / this.table.length > this.maxLoadFactor) {
            resizeRehash();
        }

        return null;
    }


    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Null keys are not allowed");
        }

        int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;

        if (this.table[index] != null) {
            for (SimpleEntry<K, V> entry : this.table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("Null keys are not allowed");
        }

        int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;

        if (this.table[index] != null) {
            Iterator<SimpleEntry<K, V>> iter = this.table[index].iterator();
            while (iter.hasNext()) {
                SimpleEntry<K, V> entry = iter.next();
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    iter.remove();
                    this.size--;
                    return oldValue;
                }
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new NullPointerException("Null keys are not allowed");
        }

        int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;

        if (this.table[index] != null) {
            for (SimpleEntry<K, V> entry : this.table[index]) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsValue(V value) {
        if (value == null) {
            throw new NullPointerException("Null values are not allowed");
        }

        for (LinkedList<SimpleEntry<K, V>> bucket : this.table) {
            if (bucket != null) {
                for (SimpleEntry<K, V> entry : bucket) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void resizeRehash() {
        LinkedList<SimpleEntry<K, V>>[] oldTable = this.table;
        this.table = createTable(this.table.length * 2);

        for (LinkedList<SimpleEntry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (SimpleEntry<K, V> entry : bucket) {
                    int index = (entry.getKey().hashCode() & Integer.MAX_VALUE) % this.table.length;
                    if (this.table[index] == null) {
                        this.table[index] = new LinkedList<>();
                    }
                    this.table[index].add(entry);
                }
            }
        }
    }



    
   
   
}