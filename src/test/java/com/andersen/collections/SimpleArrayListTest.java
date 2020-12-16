package com.andersen.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleArrayListTest {
    List<String> list;

    @BeforeEach
    void before() {
        list = new SimpleArrayList<>();
    }

    @Test
    void size_onNotEmptyList() {
        list.add("Java");
        list.add("Spring");

        assertEquals(2, list.size());
    }

    @Test
    void isEmpty_onEmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test
    void contains_trueValue() {
        list.add("test str");
        assertTrue(list.contains("test str"));
    }

    @Test
    void contains_badValue() {
        list.add("test str");
        assertFalse(list.contains("ffff"));
    }

    @Test
    void iterator() {
        list.addAll(Arrays.asList("1", "2", "3", "4"));

        List<String> res = new SimpleArrayList<>();
        for (String str : list) {
            res.add(str);
        }

        assertTrue(Arrays.equals(list.toArray(), res.toArray()));
    }

    @Test
    void toArray_OnSameArrays() {
        list.add("Java");
        list.add("Spring");
        Object[] expected = {"Java", "Spring"};

        assertArrayEquals(expected, list.toArray());
    }

    @Test
    void toArray_OnDifferentArrays() {
        list.add("Java");
        list.add("Spring");
        Object[] expected = {"safsd", "dsad"};

        assertFalse(Arrays.equals(expected, list.toArray()));
    }

    @Test
    void add_toTail() {
        list = new SimpleArrayList<>(1);
        list.add("Hello");
        list.add("World");

        assertEquals(2, list.size());
    }

    @Test
    void add_badIndex() {
        list.add("World");

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.add(2, "bad")
        );
    }

    @Test
    void add_successByIndex() {
        list.add("World");
        list.add("!!!");
        list.add(0, "Hello");

        assertEquals(3, list.size());
        assertEquals("Hello", list.get(0));
    }

    @Test
    void containsAll_success() {
        list.addAll(Arrays.asList("0", "1", "2", "3", "4"));
        List<String> nums = Arrays.asList("0", "3", "4");

        assertTrue(list.containsAll(nums));
    }

    @Test
    void containsAll_notAllContains() {
        list.addAll(Arrays.asList("0", "1", "2", "3", "4"));


        List<String> nums = Arrays.asList("10", "3", "4");

        assertFalse(list.containsAll(nums));
    }

    @Test
    void addAll() {
        List<String> nums = Arrays.asList("1", "2", "3");

        assertTrue(list.addAll(nums));
        assertTrue(list.containsAll(nums));
    }

    @Test
    void removeAll_success() {
        list.addAll(Arrays.asList("0", "1", "2", "3"));

        assertTrue(list.removeAll(Arrays.asList("0", "3")));
        assertEquals(2, list.size());
        assertTrue(list.contains("1"));
        assertTrue(list.contains("2"));
        assertFalse(list.contains("0"));
        assertFalse(list.contains("3"));


    }

    @Test
    void retainAll() {
        list.addAll(Arrays.asList("0", "1", "2", "3"));
        list.retainAll(Arrays.asList("0", "3", "4", "5"));

        assertEquals(2, list.size());
        assertTrue(list.contains("0"));
        assertTrue(list.contains("3"));
    }

    @Test
    void clear() {
        list.addAll(Arrays.asList("0", "1", "2", "3", "4"));
        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    void get_trueValue() {
        list.add("Java");

        assertEquals("Java", list.get(0));
    }

    @Test
    void get_badIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(15)
        );
    }

    @Test
    void set_badIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(4, "asdf")
        );
    }

    @Test
    void set_goodIndex() {
        list.add("PHP");
        list.set(0, "Java");

        assertEquals("Java", list.get(0));
    }

    @Test
    void remove_badIndex() {
        list.add("test");

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(12)
        );
    }

    @Test
    void remove_success() {
        list.addAll(Arrays.asList("1", "2", "3"));
        String removed = list.remove(1);

        assertEquals(removed, "2");
        assertEquals(2, list.size());
    }

    @Test
    void remove_objectNotFound() {
        assertFalse(list.remove("bad"));
    }

    @Test
    void remove_successObjectFound() {
        list.add("test");

        assertTrue(list.remove("test"));
        assertEquals(0, list.size());
    }

    @Test
    void indexOf_goodValue() {
        list.addAll(Arrays.asList("Java", "Spring", "Hibernate"));

        assertEquals(0, list.indexOf("Java"));
        assertEquals(1, list.indexOf("Spring"));
        assertEquals(2, list.indexOf("Hibernate"));
    }

    @Test
    void indexOf_objectNotFound() {
        assertEquals(-1, list.indexOf("Java"));
    }

    @Test
    void lastIndexOf() {
        list.addAll(Arrays.asList("Java", "PHP", "Java", "PHP"));

        assertEquals(2, list.lastIndexOf("Java"));
    }

    @Test
    void lastIndexOf_notFound() {
        list.addAll(Arrays.asList("Java", "PHP"));

        assertEquals(-1, list.lastIndexOf("JavaScript"));
    }

    @Test
    void subList() {
        list.addAll(Arrays.asList("0", "1", "2", "3"));


        List<String> expected = Arrays.asList("1", "2");
        List<String> actual = list.subList(1, 3);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0), actual.get(0));
        assertEquals(expected.get(1), actual.get(1));
    }

    @Test
    void subList_badIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.subList(0, 123)
        );
    }

    @Test
    void subList_fromLessThanTo() {
        list.addAll(Arrays.asList("1", "1", "1"));

        assertThrows(
                IllegalArgumentException.class,
                () -> list.subList(2, 1)
        );
    }
}