package javastream.chapter4;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

class ConsumerTest {

    // 단순히 print 만 하는 테스트
    @Test
    void consumerTest() {
        Consumer<String> stringConsumer = str -> System.out.println(str);
        stringConsumer.accept("hello");
    }

    @Test
    void processTest() {
        List<Integer> inputs = List.of(1, 2, 3, 4);
        Consumer<Integer> integerConsumer = x -> System.out.println(x);
        process(inputs, integerConsumer);

        Consumer<Integer> diffIntegerConsumer = x -> System.out.println("Diff Consumer" + x);
        process(inputs, diffIntegerConsumer);
    }

    @Test
    void processGenericTest() {
        List<Integer> inputs = List.of(1, 2, 3, 4);
        Consumer<Integer> integerConsumer = x -> System.out.println(x);
        processGeneric(inputs, integerConsumer);

        Consumer<Integer> diffIntegerConsumer = x -> System.out.println("Diff Consumer" + x);
        processGeneric(inputs, diffIntegerConsumer);

        List<Double> doubles = List.of(1.0, 2.0, 3.0, 4.0);
        Consumer<Double> doubleConsumer = x -> System.out.println("Double Consumer" + x);
        processGeneric(doubles, doubleConsumer);
        ;
    }


    private void process(List<Integer> inputs, Consumer<Integer> processor) {
        inputs.forEach(input -> processor.accept(input));
    }

    private <T> void processGeneric(List<T> inputs ,Consumer<T> processor) {
        inputs.forEach(input -> processor.accept(input));
    }
}


