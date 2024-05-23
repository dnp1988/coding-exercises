package org.example.practice.impls;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BidirectionalMap<K,V> {

    private HashMap<K,V> mapKeyValue = new HashMap<>();
    private HashMap<V,K> mapValueKey = new HashMap<>();

    public V getByKey(K key) {
        return mapKeyValue.get(key);
    }

    public K getByValue(V value) {
        return mapValueKey.get(value);
    }

    public V put(K key, V value) {
        if(!mapValueKey.keySet().contains(value) && !mapKeyValue.keySet().contains(key)) {
            mapValueKey.put(value, key);
            return mapKeyValue.put(key, value);
        } else {
            return null;
        }
    }

    public V removeByKey(K key) {
        V value = getByKey(key);
        mapValueKey.remove(value);
        return mapKeyValue.remove(key);
    }

    public K removeByValue(V value) {
        K key = getByValue(value);
        mapKeyValue.remove(key);
        return mapValueKey.remove(value);
    }

    public void clear() {
        mapKeyValue.clear();
        mapValueKey.clear();
    }

    public Set<K> keySet() {
        return mapKeyValue.keySet();
    }

    public Collection<V> values() {
        return mapKeyValue.values();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return mapKeyValue.entrySet();
    }

    public int size() {
        return mapKeyValue.size();
    }

    public boolean isEmpty() {
        return mapKeyValue.isEmpty();
    }

    public boolean containsKey(K key) {
        return mapKeyValue.containsKey(key);
    }

    public boolean containsValue(V value) {
        return mapValueKey.containsKey(value);
    }
}
