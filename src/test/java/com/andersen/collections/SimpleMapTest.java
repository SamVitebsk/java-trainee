package com.andersen.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMapTest {
    private Map<String, String> map;

    @BeforeEach
    void setUp() {
        map = new SimpleMap<>();
    }

    @Test
    void size_onEmptyMap() {
        assertEquals(0, map.size());
    }

    @Test
    void size_onNotEmptyMap() {
        map.put("1", "value1");
        map.put("2", "value2");

        assertEquals(2, map.size());
    }

    @Test
    void isEmpty_onEmptyMap() {
        assertTrue(map.isEmpty());
    }

    @Test
    void isEmpty_onNotEmptyMap() {
        map.put("1", "value1");
        map.put("2", "value2");

        assertFalse(map.isEmpty());
    }

    @Test
    void containsKey() {
        map.put("1", "value1");
        map.put("2", "value2");

        assertTrue(map.containsKey("1"));
        assertTrue(map.containsKey("2"));
        assertFalse(map.containsKey("3"));
    }

    @Test
    void containsValue() {
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("1", "value11");

        assertFalse(map.containsValue("value1"));
        assertTrue(map.containsValue("value2"));
        assertTrue(map.containsValue("value11"));
    }

    @Test
    void get_trueKey() {
        map.put("1", "value1");
        map.put("2", "value2");

        assertEquals("value2", map.get("2"));
    }

    @Test
    void get_badKey() {
        map.put("1", "value1");
        map.put("2", "value2");

        assertNull(map.get("bad"));
    }

    @Test
    void put() {
        map.put("0", "value");
        map.put("1", "value1");
        map.put("1", "value2");

        assertEquals("value2", map.get("1"));
        assertEquals(2, map.size());
    }

    @Test
    void remove() {
        map.put("1", "value1");
        map.put("2", "value2");

        String removed = map.remove("1");

        assertEquals("value1", removed);
        assertEquals(1, map.size());
        assertNull(map.get("1"));
        assertEquals("value2", map.get("2"));

    }

    @Test
    void putAll() {
        map.put("3", "old");

        Map<String, String> addMap = new HashMap<>();
        addMap.put("3", "new");
        addMap.put("4", "value4");

        map.putAll(addMap);

        assertEquals(2, map.size());
        assertEquals("new", map.get("3"));
    }

    @Test
    void clear() {
        map.put("1", "value1");
        map.put("2", "value2");

        map.clear();

        assertEquals(0, map.size());
    }

    @Test
    void keySet() {
        map.put("1", "value1");
        map.put("2", "value2");

        Set<String> keys = map.keySet();

        assertEquals(2, keys.size());
        assertTrue(keys.contains("1"));
        assertTrue(keys.contains("2"));
        assertFalse(keys.contains("3"));
    }

    @Test
    void values() {
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("1", "value11");

        List<String> values = (List) map.values();

        assertEquals(2, values.size());
        assertEquals("value11", values.get(0));
        assertEquals("value2", values.get(1));
    }

    @Test
    void entrySet() {
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("1", "value11");

        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        assertEquals(2, entrySet.size());
    }
}