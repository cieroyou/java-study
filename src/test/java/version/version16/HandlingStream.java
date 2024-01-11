package version.version16;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * toList(), mapMulti() 가 추가되었다.
 * collect(Collectors.toList()) -> toList()
 */
public class HandlingStream {
    @Test
    public void handlingStream() {
        List<Integer> oldList = Stream.of(1, 2, 3).filter(n -> n % 2 == 0).collect(Collectors.toList());
        List<Integer> v16List = Stream.of(1, 2, 3).filter(n -> n % 2 == 0).toList();

        // 기존에는 flatMap 을 쓰고, 또 flatMap 안에서 생성하는 애가 Stream 이여야 했음
        List<Integer> oldUsingFlatMap = Stream.of(1, 2, 3)
                .flatMap(n -> Stream.of(n, -n))
                .toList();

        List<Integer> v16 = Stream.of(1, 2, 3)
                .mapMulti((Integer n, Consumer<Integer> consumer) -> {
                    consumer.accept(n);
                    consumer.accept(-n);
                }).toList();

        assertEquals(List.of(1, -1, 2, -2, 3, -3), v16);
        assertEquals(oldUsingFlatMap, v16);
    }
}
