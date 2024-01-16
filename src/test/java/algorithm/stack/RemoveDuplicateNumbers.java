package algorithm.stack;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

//arr	answer
//        [1,1,3,3,0,1,1]	[1,3,0,1]
//        [4,4,4,3,3]	[4,3]
public class RemoveDuplicateNumbers {

    @Test
    public void test() {
        assertEquals(List.of(1, 3, 0, 1), getResult(new int[]{1, 1, 3, 3, 0, 1, 1}));
    }

    public Stack<Integer> getResult(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i : arr) {
            if (stack.isEmpty()) {
                stack.add(i);
                continue;
            }
            Integer current = stack.peek();
            if (current != i) stack.add(i);
        }
        return stack;
    }

}
