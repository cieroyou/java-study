package algorithm.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 주의해야할 점: int[] -> Queue 할 때 그냥 input for 문 돌면서 Queue 로 넣을 것
 * 가격이 한번 떨어지면 끝이므로 꼭 break 해줘야 함.(계속 for 문 돌면 안됨)
 * 이중포문으로 돌게 되면 성능 저하가 발생하므로 Queue 로 풀어야 함.
 */
public class StockPrice {

    @Test
    public void test() {
        var result = getResult(new int[]{1, 3, 3, 2, 3});
        assertEquals(List.of(4, 2, 1, 1, 0), result);
    }

    //    prices	return
//[1, 3, 3, 2, 3]	[4, 2, 1, 1, 0]
    private List<Integer> getResult(int[] input) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int i : input) {
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            int count = 0;
            // queue.poll() 과 같음
            Integer current = queue.remove();
            for (Integer c : queue) {
                count += 1;
                if (c < current) break;
            }
            result.add(count);
        }
        return result;
    }
}
