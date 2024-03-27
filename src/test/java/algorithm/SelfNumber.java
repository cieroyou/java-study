package algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class SelfNumber {

    public static void main(String[] args) {
        Set<Integer> selfNumbers = new HashSet<>();
        for(int i =1; i<=10000; i++) {
            selfNumbers.add(i);
        }
        for(int i =1; i<=10000; i++) {
            selfNumbers.remove(getSelfNumber(i));
        }

        selfNumbers.forEach(number -> System.out.println(number));
    }
    @Test
    void test() {

    }

    static Integer getSelfNumber(int n) {
        Integer result = 0;
        if (n < 10) {
            result = n + n;
        } else if (n >= 10 && n < 100) {
            int a = n / 10;
            int b = n % 10;
            result = n + a + b;
        } else if (n >= 100 && n < 1000) {


            int a = n / 100;
            int b = (n % 100) / 10;
            int c = (n % 100 % 10);
            result = n + a + b + c;
        } else if (n >= 1000 && n < 10000) {
            int a = n / 1000;
            int b = (n % 1000) / 100;
            int c = (n % 1000 % 100) / 10;
            int d = n % 1000 % 100 % 10;
            result = n + a + b + c + d;

        } else if (n >= 10000 && n < 100000) {
            int a = n / 10000;
            int b = (n % 10000) / 1000;
            int c = (n % 10000 % 1000) / 100;
            int d = (n % 10000 % 1000 % 100) / 10;
            int e = (n % 10000 % 1000 % 100 % 10);
            result = n + a + b + c + d + e;

        }
        return result;
    }
}
