package com.andersen.collections;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size = 0;

    public SimpleArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public SimpleArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        if (capacity > 0) {
            data = (T[]) new Object[capacity];
        } else {
            data = (T[]) new Object[DEFAULT_CAPACITY];
        }
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
    public boolean contains(Object o) {
        for (T item : data) {
            if (item != null) {
                if (item.equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return data[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        checkSize();
        data[size] = t;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        } else {
            remove(index);

            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object obj : collection) {
            if (! contains(obj)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        collection.forEach(this::add);

        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean result = true;

        for (Object item : collection) {
            if (! remove(item)) {
                result = false;
            }
        }

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Object[] res = new Object[size];
        int index = 0;

        for (T item : data) {
            if (collection.contains(item)) {
                res[index++] = item;
            }
        }

        data = (T[]) res;
        size = index;

        return true;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public T get(int i) {
        checkIndex(i);

        return data[i];
    }

    @Override
    public T set(int i, T t) {
        checkIndex(i);

        T oldValue = data[i];
        data[i] = t;

        return oldValue;
    }

    @Override
    public void add(int index, T t) {
        checkIndex(index);
        checkSize();

        for (int i = size - 1; i  >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = t;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T old = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;

        size--;

        return old;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;

        for (T item : data) {
            if (item != null && item.equals(o)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i] != null && data[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int from, int to) {
        checkIndex(from);
        checkIndex(to);

        if (from > to) {
            throw new IllegalArgumentException();
        }

        return Arrays.asList(Arrays.copyOfRange(data, from, to));

    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    private void checkSize() {
        if (size == data.length) {
            T[] newArr = (T[]) new Object[size * 2];
            System.arraycopy(data, 0, newArr, 0, size);
            data = newArr;
        }
    }
}
