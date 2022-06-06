package javastream.chapter4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/*
input 하나를 받아서 boolean 을 리턴하는 Functional Interface
 */
class PredicateTest {

    Predicate<Integer> isPositivePredicate = x -> isPositive(x);

    @Test
    void predicateTest() {
//        Predicate<Integer> isPositive = x -> isPositive(x);
        Assertions.assertTrue(isPositivePredicate.test(10));
    }

    @Test
    void filterPositiveTest() {
        List<Integer> inputs = List.of(10, -5, 4, -2, 0);
        List<Integer> result = filter(inputs, isPositivePredicate);
        Assertions.assertEquals(2, result.size());
    }

    // predicate.negate() 를 사용하여 양수가 아닌 값을 필터링하는 테스트
    @Test
    void filterNonPositiveTest() {
        List<Integer> inputs = List.of(10, -5, 4, -2, 0);
//        Predicate<Integer> isPositivePredicate = x -> isPositive(x);
        List<Integer> result = filter(inputs, isPositivePredicate.negate());
        Assertions.assertEquals(3, result.size());
    }

    // 0 보다 크거나, 0 인 값을 필터링 하는 테스트
    @Test
    void filterNonNegativeTest() {
        List<Integer> inputs = List.of(10, -5, 4, -2, 0);
//        Predicate<Integer> isPositivePredicate = x -> isPositive(x);
        Predicate<Integer> isZeroPredicate = x -> x == 0;
        List<Integer> result = filter(inputs, isPositivePredicate.or(isZeroPredicate));
        Assertions.assertEquals(3, result.size());
    }

    // 양수이고, 짝수인 값을 필터링 하는 테스트
    @Test
    void filterPositiveEvenTest() {
        List<Integer> inputs = List.of(10, -5, 4, -2, 0);
//        Predicate<Integer> isPositivePredicate = x -> isPositive(x);
        Predicate<Integer> isEvenPredicate = x -> (x % 2) == 0;
        List<Integer> result = filter(inputs, isPositivePredicate.and(isEvenPredicate));
        Assertions.assertEquals(2, result.size());
    }


    private boolean isPositive(Integer x) {
        return x > 0;
    }


    /**
     * input 과 predicate 를 받아서 필터링을 해주는 함수
     *
     * @param inputs    필터를 하고자 하는 input 값
     * @param condition 필터링 될 조건
     * @return 필터링 된 아이템들
     */
    private <T> List<T> filter(List<T> inputs, Predicate<T> condition) {

        List<T> result = new ArrayList<>();
        for (T input : inputs) {
            if (condition.test(input)) result.add(input);
        }
        return result;
    }
}
