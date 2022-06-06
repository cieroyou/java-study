package javastream.chapter4;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiConsumer;

/*
BiConsumer 는 파라미터를 두개 받는 return 없는 Functional Interface 이다
 */
public class BiConsumerTest {
    @Test
    void biConsumerTest() {
        BiConsumer<Integer, Double> doubleProcessor =
                (index, input) -> System.out.println("Processing " + input + "at index" + index);

        List<Double> inputs = List.of(1.1, 2.2, 3.3);
        process(inputs, doubleProcessor);
    }

    private <T> void process(List<T> inputs, BiConsumer<Integer, T> processor) {
        for (int i = 0; i < inputs.size(); i++) {
            processor.accept(i, inputs.get(i));
        }

    }
}
