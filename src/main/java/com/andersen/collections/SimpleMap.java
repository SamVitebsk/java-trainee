package com.andersen.collections;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private int size = 0;
    private int capacity = 16;
    private Entry<K, V>[] table;

    public SimpleMap() {
        table = new Entry[capacity];
    }

    public SimpleMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        return keySet().contains(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return values().contains(o);
    }

    @Override
    public V get(Object key) {
        int index = getIndex((K) key);
        Entry<K, V> entry = table[index];

        while (Objects.nonNull(entry)) {
            if (entry.key.equals(key)) {
                return entry.value;
            }

            entry = entry.next;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (Objects.isNull(table[index])) {
            table[index] = newEntry;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[index];

            while (Objects.nonNull(current)) {
                if (current.key.equals(key)) {
                    current.value = value;
                    size--;
                    break;
                }

                previous = current;
                current = current.next;
            }

            if (Objects.nonNull(previous)) {
                previous.next = newEntry;
            }
        }

        size++;

        return value;
    }

    @Override
    public V remove(Object key) {
        V result = null;
        int index = getIndex((K) key);
        Entry<K, V> prev = null;
        Entry<K, V> current = table[index];

        while (Objects.nonNull(current)) {
            if (current.key.equals(key)) {
                if (Objects.isNull(prev)) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }

                result = current.value;
                break;
            }

            prev = current;
            current = current.next;
        }

        size--;

        return result;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();

        for (Entry<K, V> entry : table) {
            Entry<K, V> current = entry;

            while (Objects.nonNull(current)) {
                result.add(current.key);
                current = current.next;
            }
        }

        return result;
    }

    @Override
    public Collection<V> values() {
        List<V> result = new ArrayList<>();

        for (Entry<K, V> entry : table) {
            Entry<K, V> current = entry;

            while (Objects.nonNull(current)) {
                result.add(current.value);
                current = current.next;
            }
        }

        return result;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();

        for (Entry<K, V> entry : table) {
            Entry<K, V> current = entry;

            while (Objects.nonNull(current)) {
                entrySet.add(current);
                current = current.next;
            }
        }

        return entrySet;
    }

    private int getIndex(K key) {
        if (Objects.isNull(key)) {
            return 0;
        }

        return Math.abs(key.hashCode() % capacity);
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
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
            V old = this.value;
            this.value = value;

            return old;
        }
    }
}
