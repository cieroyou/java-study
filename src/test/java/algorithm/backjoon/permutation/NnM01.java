package algorithm.backjoon.permutation;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15649
 */
public class NnM01 {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            List<Integer> current = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                current.add(i);
                func(n, m, current);
                current.remove(current.size() - 1);
            }
        }
    }

    // 1부터 3까지 중복없이 1개를 뽑는 경우
    @Test
    void example1() {
        int n = 3;
        int m = 1;

        func(3, 1, new LinkedList<>());
//        func(n, m, new HashSet<>());
    }

    // 1부터 4까지 중복없이 2개를 뽑는 경우
    @Test
    void example2() {
        int n = 4;
        int m = 2;
        func(n, m, new LinkedList<>());

    }
//        List<Integer> current = new LinkedList<>();
//        for (int i = 1; i <= n; i++) {
//            current.add(i);
//            func(n, m, current);
//            current.remove(current.size() - 1);




    // 1부터 4까지 중복없이 4개를 뽑는 경우
    @Test
    void example3() {
        int n = 4;
        int m = 4;
        List<Integer> current = new LinkedList<>();
        func(n, m, current);

    }

    static void func(int n, int m, List<Integer> current) {
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

    static void print(List<Integer> current) {
        // 1 2 3 4
        for (int i = 0; i < current.size(); i++) {
            System.out.print(current.get(i) + " ");
        }
        System.out.println();

    }
}
