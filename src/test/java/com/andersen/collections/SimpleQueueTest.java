package com.andersen.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleQueueTest {
    private Queue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new SimpleQueue<>();
    }

    @Test
    void size_onEmptyQueue() {
        assertEquals(0, queue.size());
    }

    @Test
    void isEmpty_onEmptyQueue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void contains_itemContains() {
        queue.addAll(List.of("1", "2", "3"));

        assertTrue(queue.contains("2"));
    }

    @Test
    void contains_itemNotContains() {
        queue.addAll(List.of("1", "2", "3"));

        assertFalse(queue.contains("11"));
    }

    @Test
    void iterator() {
        queue.addAll(List.of("1", "2", "3"));
        Queue<String> res = new SimpleQueue<>();

        queue.forEach(res::add);

        assertEquals(3, res.size());
    }

    @Test
    void toArray_onSameArrays() {
        queue.addAll(List.of("1", "2", "3"));
        String[] expected = {"1", "2", "3"};

        assertArrayEquals(expected, queue.toArray());
    }

    @Test
    void toArray_onDifferentArrays() {
        queue.addAll(List.of("1", "2", "3"));
        String[] expected = {"1", "2", "6"};

        assertFalse(Arrays.equals(expected, queue.toArray()));
    }

    @Test
    void add() {
        queue.add("1");
        queue.add("2");

        assertEquals(2, queue.size());
    }

    @Test
    void containsAll_success() {
        queue.addAll(List.of("1", "2", "3"));

        assertTrue(queue.containsAll(List.of("1", "2")));
    }

    @Test
    void containsAll_containsNotAll() {
        queue.addAll(List.of("1", "2", "3"));

        assertFalse(queue.containsAll(List.of("1", "2", "7")));
    }

    @Test
    void addAll() {
        queue.addAll(List.of("1", "2", "3"));

        assertEquals(3, queue.size());
    }

    @Test
    void clear() {
        queue.addAll(List.of("1", "2", "3"));

        queue.clear();

        assertEquals(0, queue.size());
    }

    @Test
    void offer() {
        queue.offer("1");
        queue.offer("2");

        assertEquals(2, queue.size());
    }

    @Test
    void remove_onEmptyQueue() {
        assertThrows(
                NoSuchElementException.class,
                () -> queue.remove()
        );
    }

    @Test
    void remove_onNotEmptyQueue() {
        queue.addAll(List.of("1", "2"));

        assertEquals("1", queue.remove());
        assertEquals("2", queue.remove());
        assertEquals(0, queue.size());
    }

    @Test
    void poll_onEmptyQueue() {
        assertNull(queue.poll());
    }

    @Test
    void poll_onNotEmptyQueue() {
        queue.addAll(List.of("1", "2", "3"));

        assertEquals("1", queue.poll());
        assertEquals("2", queue.poll());
        assertEquals("3", queue.poll());
        assertEquals(0, queue.size());
        assertNull(queue.poll());
    }

    @Test
    void element_onEmptyQueue() {
        assertThrows(
                NoSuchElementException.class,
                () -> queue.element()
        );
    }

    @Test
    void element_onNotEmptyQueue() {
        queue.addAll(List.of("1", "2", "3"));

        assertEquals("1", queue.element());
    }

    @Test
    void peek_onNotEmptyQueue() {
        queue.addAll(List.of("1", "2", "3"));

        assertEquals("1", queue.peek());
    }

    @Test
    void peek_onEmptyQueue() {
        assertNull(queue.peek());
    }

    @Test
    void test() {
        Queue<Integer> nums = new ArrayDeque<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);

//        for (Integer num : nums) {
//            System.out.println(nums.peek());
//        }

//        for (Integer num : nums) {
//            System.out.println(nums.element());
//        }

        for (Integer num : nums) {
            System.out.println(nums.poll());
        }

    }
}