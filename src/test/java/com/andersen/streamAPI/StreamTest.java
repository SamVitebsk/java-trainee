package com.andersen.streamAPI;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class StreamTest {
    List<Integer> nums;

    @BeforeEach
    void setUp() {
        nums = List.of(1, 2, 51, 19 ,4, 17,  7, 9, 88);
    }

    @Test
    void testFilter() {
        List<Integer> actual = nums.stream().filter(num -> num > 10).collect(Collectors.toList());

        assertEquals(4, actual.size());
        assertTrue(actual.contains(51));
        assertTrue(actual.contains(19));
        assertTrue(actual.contains(17));
        assertTrue(actual.contains(88));
    }

    @Test
    void testMap() {
        nums = List.of(-2, 0, 3);
        List<Integer> actual = nums.stream().map(num -> num * num).collect(Collectors.toList());

        assertEquals(3, actual.size());
        assertEquals(4, (int) actual.get(0));
        assertEquals(0, (int) actual.get(1));
        assertEquals(9, (int) actual.get(2));
    }

    @Test
    void testFlatMap() {
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(5, 6, 7, 8);
        List<List<Integer>> listOfLists = List.of(list1, list2);

        List<Integer> actual = listOfLists.stream().flatMap(Collection::stream).collect(Collectors.toList());

        assertEquals(8, actual.size());
        assertTrue(actual.contains(1));
        assertTrue(actual.contains(8));
    }

    @Test
    void testPeek() {
        List<Integer> res = nums.stream()
                .peek(num -> log.info("before filter: {}", num))
                .filter(num -> num % 2 == 0)
                .peek(num -> log.info("after filter: {}", num))
                .collect(Collectors.toList());

        assertEquals(3, res.size());
    }

    @Test
    void testFindFirst() {
        int first = nums.stream()
                .filter(num -> num >= 18)
                .findFirst()
                .get();

        assertEquals(51, first);
    }

    @Test
    void testFindFirst_notFound() {
        nums = List.of(1, 1, 2);

        Integer first = nums.stream()
                .filter(num -> num >= 18)
                .findFirst()
                .orElse(null);

        assertNull(first);
    }

    @Test
    void testFindAny() {
        int any = nums.stream()
                .filter(num -> num >= 18)
                .findFirst().get();

        assertNotNull(any);
    }

    @Test
    void testFindAny_notFound() {
        nums = List.of(1, 2, 3, 4);

        Integer any = nums.stream()
                .filter(num -> num >= 5)
                .findFirst()
                .orElse(null);

        assertNull(any);
    }

    @Test
    void testToList() {
        String[] strings = {"Java", "Spring", "Hibernate", "JPA", "RabbitMQ", "JMS"};

        List<String> actual = Arrays.stream(strings)
                .filter(str -> str.length() > 3)
                .collect(Collectors.toList());

        assertEquals(4, actual.size());
        assertFalse(actual.contains("JPA"));
        assertFalse(actual.contains("JMS"));
    }

    @Test
    void testToSet() {
        nums = List.of(1, 1, 1, 2, 2, 3, 3, 4, 4);

        Set<Integer> actual = nums.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toSet());

        assertEquals(2, actual.size());
        assertTrue(actual.contains(2));
        assertTrue(actual.contains(4));
        assertFalse(actual.contains(1));
        assertFalse(actual.contains(3));
    }

    @Test
    void testToMap() {
        List<String> strings = List.of("Java", "Spring", "Hibernate", "JPA", "RabbitMQ", "JMS");

        Map<String, Integer> actual = strings.stream()
                .collect(Collectors.toMap(str -> str, String::length));

        assertEquals(6, actual.size());
        assertEquals(4, (int) actual.get("Java"));
        assertEquals(9, (int) actual.get("Hibernate"));
    }

    @Test
    void testGroupingBy() {
        List<String> strings = List.of("Java", "Spring", "Hibernate", "JPA", "RabbitMQ", "JMS");

        Map<Boolean, List<String>> actual = strings.stream()
                .collect(Collectors.groupingBy(str -> str.length() > 3));

        List<String> lengthGreatThanThree = actual.get(true);
        List<String> lengthLessThanThree = actual.get(false);

        assertEquals(4, lengthGreatThanThree.size());
        assertEquals(2, lengthLessThanThree.size());
    }

    @Test
    void testForEach() {
        IntStream.range(0, 1000)
                .parallel()
                .forEach(num -> log.info("{}", num));
    }

    @Test
    void testForEachOrdered() {
        IntStream.range(0, 1000)
                .parallel()
                .forEachOrdered(num -> log.info("{}", num));
    }

    @Test
    void testReduce() {
        int res = IntStream.range(1, 100).reduce(0, Integer::sum);

        assertEquals(4950, res);
    }

    @Test
    void testParallelStream() {
        nums = IntStream.range(2, 100).boxed().collect(Collectors.toList());

        Integer res = nums.parallelStream()
                .peek(num -> log.info("peek: {}", num))
                .findFirst().orElse(null);

        log.info("RESULT: {}", res);
    }

    @Test
    void testSeqStream() {
        nums = IntStream.range(2, 100).boxed().collect(Collectors.toList());

        Integer res = nums.stream()
                .peek(num -> log.info("peek: {}", num))
                .findFirst().orElse(null);

        log.info("RESULT: {}", res);
    }

    @Test
    void testStreamParallel() {
        nums = IntStream.range(2, 100).boxed().collect(Collectors.toList());

        Integer res = nums.stream().parallel()
                .peek(num -> log.info("peek: {}", num))
                .findFirst().orElse(null);

        log.info("RESULT: {}", res);
    }

    @Test
    void testStreamParallelAndThenSequential() {
        nums = IntStream.range(2, 100).boxed().collect(Collectors.toList());

        Integer res = nums.stream().parallel()
                .peek(num -> log.info("peek: {}", num))
                .sequential()
                .peek(num -> log.info("peek: {}", num))
                .findFirst().orElse(null);

        log.info("RESULT: {}", res);
    }


    @Test
    void task() {
        Collection<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .filter(num -> num % 2 == 0)
                .peek(num -> log.info("{}", num))
                .map(num -> ++num)
                .forEachOrdered(num -> log.info("{}", num));

        numbers.stream().parallel().map(num -> num * num).forEach(num ->log.info("for each: {}", num));
        numbers.stream().parallel().map(num -> num * num).forEachOrdered(num ->log.info("for each ordered: {}", num));

        numbers.parallelStream().map(num -> num * num).forEach(num ->log.info("for each: {}", num));
        numbers.parallelStream().map(num -> num * num).forEachOrdered(num ->log.info("for each ordered: {}", num));
    }

    @Test
    void advancedTask() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        Stream<Integer> stream = numbers.stream();

        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        stream.forEachOrdered(num -> log.info("{}", num));
    }
}