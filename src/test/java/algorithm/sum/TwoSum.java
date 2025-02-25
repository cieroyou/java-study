package algorithm.sum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/two-sum/description/
public class TwoSum {
    Map<Integer, Integer> map = new HashMap<>();

    int[] execute(int target, int[] numbers) {
        for (int index = 0; index < numbers.length; index++) {
            int needed = target - numbers[index];
            if(map.containsKey(needed)){
                int neededIndex = map.get(needed);
                return new int[]{neededIndex, index};
            }
            map.put(numbers[index], index);
        }
        return new int[]{};
    }



    @Test
    @DisplayName("numbers: {2, 7, 11, 15}, target: 9")
    void targetNine(){
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;

        int[] expected = new int[]{0 ,1};
        int[] actual = execute(target, numbers);

        // expected = actual
        for (int i = 0; i < expected.length; i++) {
            assert expected[i] == actual[i];
        }
    }

}
