package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factorial {

//    public static void main(String[] args) throws IOException {
//        try (BufferedReader br
//                     = new BufferedReader(new InputStreamReader(System.in))) {
//            String str = br.readLine();
//
//            int number = Integer.parseInt(str);
//            System.out.println(getFactorialNumber(number));
//        }
//
//    }
//
//    @Test
//    void test() {
//        System.out.println(getFactorialNumber(0));
//        System.out.println(getFactorialNumber(1));
//        System.out.println(getFactorialNumber(2));
//
//        System.out.println(getFactorialNumber(3));
//    }
//
//    static Integer getFactorialNumber(int number) {
//        if (number == 0) return 1;
//
//        return number * getFactorialNumber(number - 1);
//    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            int number = Integer.parseInt(str);
            System.out.println(getFactorialNumber(number));
        }
    }

    static int getFactorialNumber(int number){
        if(number == 0) return 1;
        return number * getFactorialNumber(number-1);
    }
}
