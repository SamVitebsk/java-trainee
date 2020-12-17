package com.andersen.collections;

import java.util.*;

public class SimpleLinkedList<T> implements List<T> {
    private Node<T> root;
    private int size;

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
        Node<T> current = root;

        while (Objects.nonNull(current)) {
            if (o.equals(current.value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = root;

            @Override
            public boolean hasNext() {
                return Objects.nonNull(current);
            }

            @Override
            public T next() {
                T result = current.value;
                current = current.next;

                return result;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] arr = (T[]) new Object[size];
        int index = 0;

        Node<T> current = root;

        while (Objects.nonNull(current)) {
            arr[index] = current.value;
            current = current.next;
            index++;
        }

        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        Node<T> newNode = new Node<>(t);

        if (Objects.isNull(root)) {
            root = newNode;
        } else {
            Node<T> current = root;

            while (Objects.nonNull(current.next)) {
                current = current.next;
            }

            current.next = newNode;
            newNode.prev = current;
        }

        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> current = root;

        while (Objects.nonNull(current)) {
            if (current.value.equals(o)) {
                if (Objects.nonNull(current.next)) {
                    current.next.prev = current.prev;
                }
                if (Objects.nonNull(current.prev)) {
                    current.prev.next = current.next;
                } else {
                    root = current.next;
                }
                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
            if (! contains(item)) {
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object item : collection) {
            remove(item);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public T get(int i) {
        checkIndex(i);

        int index = 0;
        Node<T> current = root;
        T result = null;

        while (Objects.nonNull(current)) {
            if (index == i) {
                result = current.value;
                break;
            }

            current = current.next;
            index++;
        }

        return result;
    }

    @Override
    public T set(int i, T t) {
        checkIndex(i);
        int index = 0;
        Node<T> current = root;

        while (index != i) {
            current = current.next;
            index++;
        }

        current.value = t;

        return get(i);
    }

    @Override
    public void add(int i, T value) {
        checkIndex(i);

        int index = 0;
        Node<T> current = root;

        while (index != i) {
            current = current.next;
            index++;
        }

        Node<T> newNode = new Node<>(value, current, current.prev);

        if (Objects.isNull(current.prev)) {
            root = newNode;
        } else {
            current.prev.next = newNode;
            current.prev = newNode;
        }

        size++;
    }

    @Override
    public T remove(int i) {
        checkIndex(i);

        int index = 0;
        Node<T> current = root;

        while (index != i) {
            current = current.next;
            index++;
        }

        if (Objects.nonNull(current.prev)) {
            current.prev.next = current.next;
        } else {
            root = current.next;
        }

        if (Objects.nonNull(current.next)) {
            current.next.prev = current.prev;
        }

        size--;

        return current.value;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<T> current = root;

        while (Objects.nonNull(current)) {
            if (current.value.equals(o)) {
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        int resultIndex = -1;
        Node<T> current = root;

        while (Objects.nonNull(current)) {
            if (current.value.equals(o)) {
                resultIndex = index;
            }

            index++;
            current = current.next;
        }

        return resultIndex;
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
        if (from > to) {
            throw new IllegalArgumentException("from great than to");
        }

        int index = 0;
        Node<T> current = root;
        List<T> resultList = new SimpleLinkedList<>();

        while (Objects.nonNull(current)) {
            if (index >= from && index < to) {
                resultList.add(current.value);
            }

            current = current.next;
            index++;
        }

        return resultList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
