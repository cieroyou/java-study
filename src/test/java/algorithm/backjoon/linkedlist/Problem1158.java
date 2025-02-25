package algorithm.backjoon.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 원형링크드 리스트 구현하기
public class Problem1158 {

    Deque<Integer> list = new LinkedList<>();
    List<Integer> result = new ArrayList<>();

    void execute(int k) {
        while (!list.isEmpty()) {
//            if (list.size() == 1) {
//                result.add(list.getFirst());
//                return;
//            }
            // k번째 아이템을 가장 앞으로 끌어오기
            for (int i = 0; i < k - 1; i++) {
                int num = list.poll();
                list.addLast(num);
            }
            // k 삭제하기
            int removed = list.removeFirst();
            result.add(removed);
        }
    }


    @Test
    void test1() {

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        list.addAll(List.of(1, 2, 3, 4, 5, 6, 7));

        execute(3);
    }
}
