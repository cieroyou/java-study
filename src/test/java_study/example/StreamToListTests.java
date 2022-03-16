package example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Stream 을 List 로 변환하는 다양한 방법들에 대한 테스트코드 (feat. Java17 에서 Stream.toList() 를 권고하는 이유)
 * collect(Collectors.toList()), collect(Collectors.toUnmodifiableList()), Stream.toList()
 * 수정이 가능한가?, null 허용을 하는가
 */
public class StreamToListTests {

    @DisplayName("Collectors.toList() modify 가능 테스트")
    @Test
    public void collectorsToList() {
        List<String> modifiable = Stream.of("foo", "bar")
            .collect(Collectors.toList());
        modifiable.add("new");
        assertEquals(3, modifiable.size());
    }

    @DisplayName("Collectors.toUnmodifiableList() modify 불가능 테스트")
    @Test
    public void collectorsToUnmodifiableList() {
        List<String> unmodifiable = Stream.of("foo", "bar")
            .collect(Collectors.toUnmodifiableList());
        assertThrows(UnsupportedOperationException.class,
            () -> unmodifiable.add("new"));
    }

    @DisplayName("Stream.toList() modify 불가능 테스트")
    @Test
    public void streamToList() {
        List<String> unmodifiable = Stream.of("foo", "bar").toList();
//        java.lang.UnsupportedOperationException
//        at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
//        at java.base/java.util.ImmutableCollections$AbstractImmutableCollection.add(ImmutableCollections.java:147)
        assertThrows(UnsupportedOperationException.class,
            () -> unmodifiable.add("new"));

        List<String> copied = List.copyOf(unmodifiable);
        assertThrows(UnsupportedOperationException.class,
            () -> copied.add("new"));
    }

    @DisplayName("Collectors.toList() null 허용 테스트")
    @Test
    public void collectorsToList_null() {
        String strNull = null;
        List<String> list = Stream.of(strNull)
            .collect(Collectors.toList());
        assertEquals(1, list.size());
    }

    @DisplayName("Collectors.toUnmodifiableList() null 불허용 테스트")
    @Test
    public void collectorsToUnmodifiableList_null() {
        String strNull = null;
        assertThrows(NullPointerException.class,
            () -> Stream.of(strNull)
                .collect(Collectors.toUnmodifiableList()));
    }

    @DisplayName("Stream.toList() null 허용 테스트")
    @Test
    public void streamToList_null() {
        String strNull = null;
        List<String> list = Stream.of(strNull).toList();
        assertEquals(1, list.size());
    }
}
