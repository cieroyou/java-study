package effectivejava._05_generic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * List<Object> vs List vs List<?> 의 비교 테스트코드
 */
public class _26_NoUseRawTypeTest {


    /**
     * 원소 타입에 상관없는 타입을 받아, 단순히 print 만 하는 함수
     */



    void printObjectList(List<Object> list) {
        for (Object elem : list) {
            System.out.println(elem + " ");
        }
    }

    void printRawType(List list) {
        list.add(1); // Runtime Exception 발생 -> 타입 안전하지 않다
        for (Object elem : list) {
            System.out.println(elem + " ");
        }
    }

    // 타입안전하게 어떤 리스트 타입이든 상관 없이 받아서 처리하고 싶은 경우 wildcard type 을 사용하는 예시
    void printWildcardType(List<?> list) {
//        list.add(1); // null 이외의 값을 add 하지 못하므로 타입 안전하다.
        for (Object elem : list) {
            System.out.println(elem + " ");
        }
    }

    @Test
    void test() {
        // 1.
        List<String> stringList = List.of("가", "나", "다");
//        printObjectList(stringList); // compile error : List<String> 은 List<Object> 를 상속하지 않는다.

        // 2. printRawType() 은 타입 안전하지 않다.
        // Runtime Exception 발생
        Assertions.assertThrows(UnsupportedOperationException.class, () -> printRawType(stringList));

        // 3
        printWildcardType(stringList);
    }

    void printListOfElementType(List<?> list) {
        for (Object elem : list) {
            System.out.println(elem + " ");
            if (elem instanceof String) {
                System.out.println("This is String Type : " + elem);
            } else if (elem instanceof Integer) {
                System.out.println("This is Integer Type : " + elem);
            }
        }

    }
}
