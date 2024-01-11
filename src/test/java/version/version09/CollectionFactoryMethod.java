package version.version09;

import java.util.List;

public class CollectionFactoryMethod {
    /**
     * java 9 에서는 List, Set, Map 등의 Collection 에 factory method 를 제공한다.
     * 이를 통해 불변, 빈, 싱글턴, 작은 Collection 을 쉽게 만들 수 있다.
     */
    public void collectionFactoryMethod(){
        // 불변 Collection
        List<String> immutableList = List.of("a", "b", "c");
        // 빈 Collection
        List<String> emptyList = List.of();
        // 싱글턴 Collection
        List<String> singletonList = List.of("a");
        // 작은 Collection
        List<String> smallList = List.of("a", "b");
    }
}
