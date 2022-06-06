package javastream.chapter5;

import javastream.chapter4.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MethodReferenceTests {

    @Test
    void stringToIntegerTest() {
        Function<String, Integer> integerToString = Integer::parseInt;
//        Function<String, Integer> integerToString = x -> Integer.parseInt(x);

        assertEquals(2, integerToString.apply("2"));
    }

    @Test
    void equalsToHelloTest() {
        String str = "hello";
        Predicate<String> equalsTo = str::equals;
//        Predicate<String> equalsTo = x -> str.equals(x);

        assertFalse(equalsTo.test("Hello"));
    }


    int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator) {
        return operator.apply(x, y);
    }

    int minus(int x, int y) {
        return x - y;
    }

    @DisplayName("calculate 를 이용한 minus 계산 테스트")
    @Test
    void calculatingMinus() {
//        calculate(10, 1, (x, y) -> x - y);
        int nine = calculate(10, 1, this::minus);
        assertEquals(9, nine);
    }


    // ClassName::instanceMethodName
    @Test
    void strEqualToTest() {
//        BiPredicate<String,String> isEqaulTo = (x,y) -> x.equals(y);
        BiPredicate<String, String> isEqualTo = String::equals;
        assertFalse(isEqualTo.test("hello", "Hello"));
    }

    // User 의 필드를 print 하는 람수
    Object getUserField(User user, Function<User, Object> getter) {
        return getter.apply(user);
    }

    @Test
    void getUserFieldTest() {
        User user = new User(10, "Charlie");
//        String userName = (String) getUserField(user, u -> u.getName());
        String userName = (String) getUserField(user, User::getName);

        int userId = (int) getUserField(user, User::getId);
//        int userId = (int) getUserField(user, u -> u.getId());
        assertEquals("Charlie", userName);
        assertEquals(10, userId);
    }

}
