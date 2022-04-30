package javastream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SupplierTest {

    @Test
    void supplierTest() {
        String expected = "hello";
        Supplier<String> myStringSupplier = () -> "hello";
        assertEquals(expected, myStringSupplier.get());
    }

    @Test
    void getRandomSupplierTest() {
        Supplier<Double> randomDoubleSupplier = () -> Math.random();
        assertNotNull(randomDoubleSupplier.get());
    }

    @Test
    void getRandomDoublesTest() {
        int count = 5;
        Supplier<Double> randomDoubleSupplier = () -> Math.random();
        List<Double> randomDoubles = getRandomDoubles(randomDoubleSupplier, 5);
        assertEquals(5, randomDoubles.size());
    }

    private List<Double> getRandomDoubles(Supplier<Double> supplier, int count) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(supplier.get());
        }
        return result;
    }
}
