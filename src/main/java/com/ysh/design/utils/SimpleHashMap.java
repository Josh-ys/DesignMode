package com.ysh.design.utils;

import java.util.*;

/**
 * 简单map实现
 *
 * @author joeysh
 * @date 2018/10/17 00:14
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
    private static final int SIZE = 1024;

    private LinkedList<MapEntity<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        LinkedList<MapEntity<K, V>> bucket = buckets[index];
        ListIterator<MapEntity<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntity<K, V> next = it.next();
            if (next.getKey().equals(key)) {
                oldValue = next.getValue();
                next.setValue(value);
                it.set(next);
                return oldValue;
            }
        }
        buckets[index].add(new MapEntity<>(key, value));
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }
        for (MapEntity<K, V> kv : buckets[index]) {
            if (kv.getKey().equals(key)) {
                return kv.getValue();
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (LinkedList<MapEntity<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (MapEntity<K, V> kvMapEntity : bucket) {
                entries.add(kvMapEntity);
            }
        }
        return entries;
    }

    private static class MapEntity<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public MapEntity(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;

        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            MapEntity<?, ?> that = (MapEntity<?, ?>) o;

            return (key != null ? key.equals(that.key) : that.key == null) && (value != null ? value.equals(that.value) : that.value == null);
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }

    public static void main(String[] args) {
        SimpleHashMap<String, Integer> hashMap = new SimpleHashMap<>();
        hashMap.put("a", 1);
        hashMap.put("b", 2);
        hashMap.put("c", 3);
        hashMap.put("a", 2);
        hashMap.put("b", 3);
        for (Entry<String, Integer> stringIntegerEntry : hashMap.entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + "--" + stringIntegerEntry.getValue());
        }
    }
}
