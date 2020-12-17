package com.andersen.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLinkedListTest {
    private List<String> list;

    @BeforeEach
    void setUp() {
        list = new SimpleLinkedList<>();
    }

    @Test
    void size_onEmptyList() {
        assertEquals(0, list.size());
    }

    @Test
    void size_onNotEmptyList() {
        list.addAll(List.of("first", "second", "third"));

        assertEquals(3, list.size());
    }

    @Test
    void isEmpty_onEmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test
    void isEmpty_onNotEmptyList() {
        list.addAll(List.of("first", "second", "third"));

        assertFalse(list.isEmpty());
    }

    @Test
    void contains() {
        list.add("first");
        list.add("second");

        assertTrue(list.contains("first"));
        assertTrue(list.contains("second"));
        assertFalse(list.contains("third"));
    }

    @Test
    void iterator() {
        list.addAll(List.of("first", "second", "third"));
        List<String> expected = new SimpleLinkedList<>();

        list.forEach(expected::add);

        assertArrayEquals(expected.toArray(), list.toArray());
    }

    @Test
    void toArray() {
        list.addAll(List.of("first", "second", "third"));
        Object[] arr = list.toArray();
        String[] expected = {"first", "second", "third"};

        assertArrayEquals(expected, arr);
    }

    @Test
    void add_toTail() {
        list.add("First");

        assertEquals(1, list.size());
        assertEquals("First", list.get(0));
    }

    @Test
    void add_byBadIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.add(123, "Bad")
        );
    }

    @Test
    void add_toTrueIndex() {
        list.addAll(List.of("first", "second", "third"));

        list.add(1, "0.5");

        assertEquals(4, list.size());
        assertEquals("0.5", list.get(1));
    }

    @Test
    void add_toFirstIndex() {
        list.addAll(List.of("first", "second", "third"));

        list.add(0, "zero");

        assertEquals(4, list.size());
        assertEquals("zero", list.get(0));
    }

    @Test
    void add_toLastIndex() {
        list.addAll(List.of("first", "second", "third"));

        list.add(2, "2.5");

        assertEquals(4, list.size());
        assertEquals("2.5", list.get(2));
        assertEquals("third", list.get(3));
    }

    @Test
    void remove() {
        list.addAll(List.of("first", "second", "third"));

        list.remove("first");

        assertEquals(2, list.size());
        assertFalse(list.contains("first"));
    }

    @Test
    void containsAll_success() {
        list.addAll(List.of("1", "2", "3", "4"));

        assertTrue(list.containsAll(List.of("2", "4")));
    }

    @Test
    void containsAll_notAllContains() {
        list.addAll(List.of("1", "2", "3", "4"));

        assertFalse(list.containsAll(List.of("2", "5")));
    }

    @Test
    void addAll() {
        list.addAll(Arrays.asList("1", "2", "3", "4"));

        assertEquals(4, list.size());
    }

    @Test
    void removeAll() {
        list.addAll(List.of("1", "2", "3", "4", "5", "6", "7"));

        list.removeAll(List.of("2", "3", "5"));

        assertEquals(4, list.size());
        assertFalse(list.contains("2"));
        assertFalse(list.contains("3"));
        assertFalse(list.contains("5"));
    }

    @Test
    void clear() {
        list.addAll(List.of("first", "second", "third"));

        assertTrue(list.size() > 0);

        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void get_trueIndexes() {
        list.addAll(List.of("first", "second", "third"));

        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    @Test
    void get_badIndex() {
        list.addAll(List.of("first", "second", "third"));

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(3)
        );
    }

    @Test
    void set_onBadIndex() {
        list.addAll(List.of("first", "second", "third"));

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(123, "Bad")
        );
    }

    @Test
    void set_onTrueIndex() {
        list.addAll(List.of("first", "second", "third"));

        list.set(0, "1");
        list.set(1, "2");
        list.set(2, "3");

        assertEquals("1", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("3", list.get(2));
    }

    @Test
    void testRemove_goodIndex() {
        list.addAll(List.of("1", "2", "3", "4", "5", "6", "7"));

        list.remove(2);

        assertEquals(6, list.size());
        assertFalse(list.contains("3"));
    }

    @Test
    void testRemove_badIndex() {
        list.addAll(List.of("1", "2", "3", "4", "5", "6", "7"));

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(111)
        );

    }

    @Test
    void testRemove_firstElement() {
        list.addAll(List.of("1", "2", "3", "4", "5", "6", "7"));

        list.remove(0);

        assertEquals(6, list.size());
        assertEquals("2", list.get(0));
    }

    @Test
    void testRemove_lastElement() {
        list.addAll(List.of("1", "2", "3", "4", "5", "6", "7"));

        list.remove(list.size() - 1);

        assertEquals(6, list.size());
        assertEquals("6", list.get(list.size() - 1));

    }

    @Test
    void indexOf_trueValue() {
        list.addAll(List.of("first", "second", "third"));

        assertEquals(0, list.indexOf("first"));
        assertEquals(2, list.indexOf("third"));
    }

    @Test
    void indexOf_valueNotFound() {
        list.add("first");

        assertEquals(-1, list.indexOf("third"));
    }

    @Test
    void lastIndexOf() {
        list.addAll(List.of(
                "first",
                "second",
                "third",
                "second",
                "third"
        ));

        assertEquals(3, list.lastIndexOf("second"));
        assertEquals(-1, list.lastIndexOf("four"));
    }

    @Test
    void subList() {
        list.addAll(List.of("1", "2", "3", "4", "5", "6", "7"));

        List<String> res = list.subList(2, 5);

        assertEquals(3, res.size());
        assertEquals("3", res.get(0));
        assertEquals("5", res.get(2));
    }
}