package algorithm.backjoon.permutation;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class NnM02 {

    @Test
    void example1() {
        int n = 3;
        int m = 1;

        func(3, 1, new HashSet<>());
//        func(n, m, new HashSet<>());
    }

    @Test
    void example2() {
        int n = 4;
        int m = 2;
//
//        func(4, 2, new HashSet<>());
////        func(n, m, new HashSet<>());

        Set<Integer> current = new HashSet<>();
        for(int i=1; i<=4; i++) {
            current.add(i);
            for(int j=1; j<=4; j++){
                if(current.contains(j)) continue;
                current.add(j);
                print(current);
                current.remove(j);
            }
        }
    }

    static void func(int n, int m, Set<Integer> current) {
        for (int i = 1; i <= n; i++) {
            if (!current.contains((i))) {
                current.add(i);
                if (current.size() == m) {
                    print(current);
                    current.remove(current.size() - 1);
                } else {
                    func(n, m, current);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    static void print(Set<Integer> current) {
        // 1 2 3 4
        for (Integer integer : current) {
            System.out.print(integer + " ");
        }
        System.out.println();

    }
}
