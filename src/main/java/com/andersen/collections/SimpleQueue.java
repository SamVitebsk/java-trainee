package com.andersen.collections;

import java.util.*;

public class SimpleQueue<T> implements Queue<T> {
    private int size;
    private Node<T> head;

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
        Node<T> current = head;

        while (Objects.nonNull(current)) {
            if (current.value.equals(o)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return index != size;
            }

            @Override
            public T next() {
                T result = current.value;
                current = current.next;
                index++;

                return result;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> current = head;
        int index = 0;

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

        if (Objects.isNull(head)) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (Objects.nonNull(current.next)) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
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
        for (T item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean offer(T t) {
        return add(t);
    }

    @Override
    public T remove() {
        if (Objects.isNull(head)) {
            throw new NoSuchElementException();
        }

        T result = head.value;
        head = head.next;
        size--;

        return result;
    }

    @Override
    public T poll() {
        if (Objects.isNull(head)) {
            return null;
        }

        T result = head.value;
        head = head.next;
        size--;

        return result;
    }

    @Override
    public T element() {
        if (Objects.nonNull(head)) {
            return head.value;
        }

        throw new NoSuchElementException();
    }

    @Override
    public T peek() {
        return Objects.nonNull(head) ? head.value : null;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
