import javafx.util.Pair;

import java.util.*;


public class MyHashMap<K, V>{

    private List<Pair<K, V>>[] table = new LinkedList[CAPACITY];

    private static final int CAPACITY = 15;
    private Set<K> keys = new HashSet<>();
    private Set<Pair<K, V>> pairs = new HashSet<>();
    private List<V> values = new ArrayList<>();

    public void put(K key, V value) {
        checkKeyOrValue(key, value);
        if (table[getBucket(key)] == null) {
            table[getBucket(key)] = new LinkedList<>();
        }
        updateIfSuchKeyExist(key, value);
        addPairs(key, value);
    }

    public V get(K key) {
        checkKeyOrValue(key);
        if (table[getBucket(key)] != null) {
            for (Pair<K, V> pairList : table[getBucket(key)]) {
                if (pairList.getKey().equals(key)) {
                    return pairList.getValue();
                }
            }
        }
        return null;
    }

    public V remove(K key) {
        checkKeyOrValue(key);
        if (containsKey(key)) {
            for (Pair<K, V> kvPair : table[getBucket(key)]) {
                removePairsKeysValues(kvPair, key);
                return kvPair.getValue();
            }
        }
        return null;
    }

    public void clear() {
        clearPairsKeysValues();
    }

    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    public int size() {
        return pairs.size();
    }

    private void updateIfSuchKeyExist(K key, V value) {
        Pair<K, V> getPair = getPairUseKey(table[getBucket(key)], key);
        if (getPair != null) {
            table[getBucket(key)].remove(getPair);
            values.remove(getPair.getValue());
            pairs.remove(getPair);
        }
    }

    private void addPairs(K key, V value) {
        Pair<K, V> pair = new Pair<>(key, value);
        table[getBucket(key)].add(pair);
        pairs.add(pair);
        keys.add(key);
        values.add(value);
    }

    private void checkKeyOrValue(K key) {
        if ((key == null)) {
            throw new IllegalArgumentException("Key does not have to contain null");
        }
    }

    private void checkKeyOrValue(K key, V value) {
        if ((key == null) || (value == null)) {
            throw new IllegalArgumentException("Map can`t contain null keys or null values");
        }
    }

    private Pair<K, V> getPairUseKey(List<Pair<K, V>> list, K key) {
        checkKeyOrValue(key);
        for (Pair<K, V> kvPair : list) {
            if (kvPair.getKey().equals(key)) {
                return kvPair;
            }
        }
        return null;
    }

    private void clearPairsKeysValues() {
        pairs.clear();
        keys.clear();
        values.clear();
    }

    private void removePairsKeysValues(Pair<K, V> pair, K key) {
        table[getBucket(key)].remove(pair);
        pairs.remove(pair);
        keys.remove(key);
        values.remove(pair.getValue());
    }

    private int getBucket(K key) {
        return Math.abs(key.hashCode() % CAPACITY);
    }


    @Override
    public String toString() {
        return "{" + pairs + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashMap<?, ?> myHashMap = (MyHashMap<?, ?>) o;
        return Arrays.equals(table, myHashMap.table) &&
                Objects.equals(keys, myHashMap.keys) &&
                Objects.equals(pairs, myHashMap.pairs) &&
                Objects.equals(values, myHashMap.values);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(keys, pairs, values);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }
}




