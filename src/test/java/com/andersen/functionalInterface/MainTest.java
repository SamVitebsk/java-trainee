package com.andersen.functionalInterface;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void testConvert() {
        Converter<String, Integer> converter = (num) -> {
            switch (num) {
                case 0: return "Zero";
                case 1: return "One";
                case 2: return "Two";
                case 3: return "Three";
                case 4: return "Four";
                case 5: return "Five";
                case 6: return "Six";
                case 7: return "Seven";
                case 8: return "Eight";
                case 9: return "Nine";
            }

            return "Nan";
        };

        assertEquals("Zero", Main.convert(0, converter));
        assertEquals("Five", Main.convert(5, converter));
        assertEquals("Nan", Main.convert(11, converter));
    }
}