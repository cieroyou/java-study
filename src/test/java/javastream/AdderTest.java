package javastream;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdderTest {

    @Test
    void functionTest() {
        Function<Integer, Integer> myAdder = new Adder();
        int result = myAdder.apply(1);
        assertEquals(11, result);
    }

    @Test
    void functionUsingLambda() {
        Function<Integer, Integer> myAdder = x -> x + 10;
        int result = myAdder.apply(1);
        assertEquals(11, result);
    }

    @Test
    void biFunctionTest() {
        BiFunction<Integer, Integer, Integer> myAdder = (x, y) -> x + y;
        int result = myAdder.apply(2, 3);
        assertEquals(5, result);
    }
}