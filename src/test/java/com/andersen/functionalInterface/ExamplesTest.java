package com.andersen.functionalInterface;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ExamplesTest {
    @Test
    public void biConsumer() {
        BiConsumer<String, String> biConsumer = (s1, s2) -> log.info("{} {}!!!", s1, s2);

        biConsumer.accept("Hello", "World");
    }

    @Test
    public void biFunction() {
        BiFunction<Integer, Integer, String> biFunction
                = (num1, num2) -> "Result = " + (num1 * num2);

        String actual = biFunction.apply(3, 4);

        assertEquals("Result = 12", actual);
    }

    @Test
    public void binaryOperator() {
        BinaryOperator<String> binaryOperator =
                (s1, s2) -> s1.toUpperCase() + " " + s2.toUpperCase() + "!!!";

        String actual = binaryOperator.apply("Hello", "World");

        assertEquals("HELLO WORLD!!!", actual);
    }

    @Test
    public void biPredicate() {
        BiPredicate<String, Integer> biPredicate =
                (str, length) -> str.length() == length;

        assertTrue(biPredicate.test("test", 4));
    }

    @Test
    public void booleanSupplier() {
        BooleanSupplier booleanSupplier = () -> LocalDateTime.now().getDayOfMonth() < 32;

        assertTrue(booleanSupplier.getAsBoolean());
    }

    @Test
    public void consumer() {
        Consumer<String> consumer = str -> log.info("consumer: {}", str);

        consumer.accept("Test");
    }

    @Test
    public void doubleBinaryOperator() {
        DoubleBinaryOperator doubleBinaryOperator = (num1, num2) -> num1 - num2;

        double actual = doubleBinaryOperator.applyAsDouble(123.4, 122.4);

        assertEquals(1, actual);
    }

    @Test
    public void doubleConsumer() {
        DoubleConsumer doubleConsumer = num -> log.info("double consumer: {}", num);

        doubleConsumer.accept(123.4);
    }

    @Test
    public void doubleFunction() {
        DoubleFunction<String> doubleFunction = num -> "Result: " + (num * num);

        String result = doubleFunction.apply(1.5);

        assertEquals("Result: 2.25", result);
    }

    @Test
    public void doublePredicate() {
        DoublePredicate doublePredicate = num -> num > 100;

        assertTrue(doublePredicate.test(101));
        assertFalse(doublePredicate.test(100));
    }

    @Test
    public void doubleSupplier() {
        DoubleSupplier doubleSupplier = () -> 3.14;

        assertEquals(3.14, doubleSupplier.getAsDouble());
    }

    @Test
    public void doubleToIntFunction() {
        DoubleToIntFunction doubleToIntFunction = num -> (int) Math.round(num);

        assertEquals(4, doubleToIntFunction.applyAsInt(4.4));
        assertEquals(5, doubleToIntFunction.applyAsInt(4.6));
    }

    @Test
    public void doubleToLongFunction() {
        DoubleToLongFunction doubleToLongFunction = Math::round;

        assertEquals(4, doubleToLongFunction.applyAsLong(4.4));
        assertEquals(5, doubleToLongFunction.applyAsLong(4.6));
    }

    @Test
    public void doubleUnaryOperator() {
        DoubleUnaryOperator doubleUnaryOperator = num -> Math.pow(num, 2);

        assertEquals(2.25, doubleUnaryOperator.applyAsDouble(1.5));
    }

    @Test
    public void function() {
        Function<String, Integer> function = String::length;

        assertEquals(4, (int) function.apply("test"));
    }

    @Test
    public void intBinaryOperator() {
        IntBinaryOperator intBinaryOperator = (a, b) -> a - b;

        assertEquals(2, intBinaryOperator.applyAsInt(5, 3));
    }

    @Test
    public void intConsumer() {
        IntConsumer intConsumer = num -> log.info("intConsumer: {}", num);

        intConsumer.accept(123);
    }

    @Test
    public void intFunction() {
        IntFunction<Double> intFunction = Math::sqrt;

        assertEquals(12, (double) intFunction.apply(144));
    }

    @Test
    public void intPredicate() {
        IntPredicate intPredicate = num -> num % 2 == 0;

        assertFalse(intPredicate.test(43));
        assertTrue(intPredicate.test(44));
    }

    @Test
    public void intSupplier() {
        IntSupplier intSupplier = () -> 42;

        assertEquals(42, intSupplier.getAsInt());
    }

    @Test
    public void intToDoubleFunction() {
        IntToDoubleFunction intToDoubleFunction = Math::sqrt;

        assertEquals(11, intToDoubleFunction.applyAsDouble(121));
    }

    @Test
    public void intToLongFunction() {
        IntToLongFunction intToLongFunction = num -> (long) num;

        assertEquals(12, intToLongFunction.applyAsLong(12));
    }

    @Test
    public void intUnaryOperator() {
        IntUnaryOperator intUnaryOperator = num -> --num;

        assertEquals(1, intUnaryOperator.applyAsInt(2));
    }

    @Test
    public void longBinaryOperator() {
        LongBinaryOperator longBinaryOperator = (num1, num2) -> num1 - num2;

        assertEquals(1, longBinaryOperator.applyAsLong(123, 122));
    }

    @Test
    public void longConsumer() {
        LongConsumer longConsumer = num -> log.info("long consumer: {}", num);

        longConsumer.accept(Long.MAX_VALUE);
    }

    @Test
    public void longFunction() {
        LongFunction<Integer> longFunction = num -> (int) num / 4;

        assertEquals(4, (int) longFunction.apply(16));
    }

    @Test
    public void longPredicate() {
        LongPredicate longPredicate = num -> num >= Long.MAX_VALUE;

        assertFalse(longPredicate.test(1234));
        assertTrue(longPredicate.test(Long.MAX_VALUE));
    }

    @Test
    public void longSupplier() {
        LongSupplier longSupplier = () -> Long.MAX_VALUE;

        assertEquals(Long.MAX_VALUE, longSupplier.getAsLong());
    }

    @Test
    public void longToDoubleFunction() {
        LongToDoubleFunction longToDoubleFunction = num -> (double) num / 2;

        assertEquals(2.5, longToDoubleFunction.applyAsDouble(5));
    }

    @Test
    public void longToIntFunction() {
        LongToIntFunction longToIntFunction = num -> (int) (num / 1_000_000);

        assertEquals(10_000, longToIntFunction.applyAsInt(10_000_000_000L));
    }

    @Test
    public void longUnaryOperator() {
        LongUnaryOperator longUnaryOperator = num -> -1 * num;

        assertEquals(-123, longUnaryOperator.applyAsLong(123));
    }

    @Test
    public void objDoubleConsumer() {
        ObjDoubleConsumer<String> objDoubleConsumer =
                (str, num) -> log.info("object double consumer: {} - {}", str, num);

        objDoubleConsumer.accept("Max double", Double.MAX_VALUE);
        objDoubleConsumer.accept("Min double", Double.MIN_VALUE);
    }

    @Test
    public void objIntConsumer() {
        ObjIntConsumer<String> objIntConsumer =
                (str, num) -> log.info("object integer consumer: {} - {}", str, num);

        objIntConsumer.accept("Max int", Integer.MAX_VALUE);
        objIntConsumer.accept("Mix int", Integer.MIN_VALUE);
    }

    @Test
    public void objLongConsumer() {
        ObjLongConsumer<String> objLongConsumer =
                (str, num) -> log.info("obj long consumer: {} - {}", str, num);

        objLongConsumer.accept("Max long", Long.MAX_VALUE);
        objLongConsumer.accept("Min long", Long.MIN_VALUE);
    }

    @Test
    public void predicate() {
        Predicate<String> predicate = str -> str.trim().length() > 0;

        assertTrue(predicate.test("test "));
        assertFalse(predicate.test("    "));
    }

    @Test
    public void supplier() {
        Supplier<String> supplier = () -> "test";

        assertEquals("test", supplier.get());
    }

    @Test
    public void toDoubleBiFunction() {
        ToDoubleBiFunction<Integer, Float> toDoubleBiFunction =
                (num1, num2) -> Math.sqrt(num1 * num2);

        assertEquals(2, toDoubleBiFunction.applyAsDouble(16, 0.25f));
    }

    @Test
    public void toDoubleFunction() {
        ToDoubleFunction<Integer> toDoubleFunction = num -> num * 0.3;

        assertEquals(1.2, toDoubleFunction.applyAsDouble(4));
    }

    @Test
    public void toIntBiFunction() {
        ToIntBiFunction<String, Integer> toIntBiFunction = (str, num) -> str.length() * num;

        assertEquals(40, toIntBiFunction.applyAsInt("test", 10));
    }

    @Test
    public void toIntFunction() {
        ToIntFunction<String> toIntFunction = String::length;

        assertEquals(4, toIntFunction.applyAsInt("test"));
    }

    @Test
    public void toLongBiFunction() {
        ToLongBiFunction<Integer, Integer> toLongBiFunction = (num1, num2) -> num1 * num2;

        assertEquals(400, toLongBiFunction.applyAsLong(10, 40));
    }

    @Test
    public void toLongFunction() {
        ToLongFunction<Integer> toLongFunction = num -> num * num;

        assertEquals(64, toLongFunction.applyAsLong(8));
    }

    @Test
    public void unaryOperator() {
        UnaryOperator<String> unaryOperator = str -> str + "!!!";

        assertEquals("test!!!", unaryOperator.apply("test"));
    }
}
