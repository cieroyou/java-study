package algorithm.backjoon.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Problem2346 {

    Deque<int[]> list = new LinkedList<>();
    List<Integer> result = new ArrayList<>();

    void execute() {
        if (list.isEmpty()) return;
        int[] first = list.pollFirst();
        int value = first[1];
        result.add(first[0]);

        while (!list.isEmpty()) {

            if (value > 0) {
                moveLeft(value);
            } else if (value < 0) {
                moveRight(Math.abs(value));
            }
            first = list.pollFirst();
            value = first[1];
            result.add(first[0]);
        }
    }

    void moveLeft(int value) {
        // 왼쪽으로 이동: First를 뒤로
        for (int i = 0; i < value - 1; i++) {
            int[] num = list.pollFirst();
            list.offer(num);
        }
    }

    void moveRight(int value) {
        // 오른쪽으로 이동: last를 앞으로
        for (int i = 0; i < value; i++) {
            int[] num = list.pollLast();
            list.offerFirst(num);
        }
    }

    @Test
    void test1() {
        int[] numbers = new int[]{3, 2, 1, -3, -1};
        for (int i = 0; i < numbers.length; i++) {
            list.add(new int[]{i + 1, numbers[i]});
        }

        execute();
    }
}
