package effectivejava._05_generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class _26_NoUseRawTypeTest {


    /**
     * 원소 타입에 상관없는 타입을 받아, 단순히 print 만 하는 함수
     */
    void printList(List<?> list) {
        for (Object elem : list) {
            System.out.println(elem + " ");
            if (elem instanceof String) {
                System.out.println("This is String Type : " + elem);
            } else if (elem instanceof Integer) {
                System.out.println("This is Integer Type : " + elem);
            }
        }

    }


    void printObjectList(List<Object> list) {
        for (Object elem : list) {
            System.out.println(elem + " ");
        }
    }

    @Test
    void test() {
        List<String> stringList = List.of("1", "2", "3");
        printList(stringList);
    }

    @Test
    void test2() {
        List<Object> objectList = new ArrayList<>();
        objectList.add("가나다");
        objectList.add(1);
        printList(objectList);



//        List<Integer> integerList = List.of(1, 2, 3, 4);
//        printObjectList(integerList);

    }
}
