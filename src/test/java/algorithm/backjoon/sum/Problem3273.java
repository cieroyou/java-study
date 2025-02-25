package algorithm.backjoon.sum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem3273 {

    Map<Integer, Integer> map = new HashMap<>();

    void sumUsingMap(int[] numbers, int target) {
        // map: key=값, value=index
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            int search = target - numbers[i];
            if (map.containsKey(target - numbers[i])) {
                if (i < map.get(search)) {
                    result++;
                }
            }
        }
    }

    int sumUsingTwoPointer(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int result = 0;

        Arrays.sort(numbers);
        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                result++;
                while (left < right && numbers[left] == numbers[left + 1]) left++;
                while (left < right && numbers[right] == numbers[right - 1]) right--;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    @Test
    void test1() {
//        5 12 7 10 9 1 2 3 11
//        5 12 7 10 9 1 2 3 11
        int[] array = new int[]{5, 12, 7, 10, 9, 1, 2, 3, 11};
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], i);
        }

//        sum(new int[]{5, 12, 7, 10, 9, 1, 2, 3, 11}, 13);
        int result = sumUsingTwoPointer(array, 13);
        String bb = "";
    }
}
