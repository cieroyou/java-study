package algorithm.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//https://leetcode.com/problems/4sum/description/
public class FourSum {


    int[][] fourSum(int[] numbers, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(numbers);
        int firstNumber = -1;
        int secondNumber = -1;
        int left = -1;
        int right = -1;
        int sum = -1;
        for (int i = 0; i < numbers.length - 3; i++) {
            firstNumber = numbers[i];
            if (i > 0 && firstNumber == numbers[i - 1]) continue;
            for (int j = i+1; j < numbers.length - 2; j++) {
                secondNumber = numbers[j];
                if(j>i+1 && secondNumber == numbers[j-1]) continue;
                left = j + 1;
                right = numbers.length - 1;
                while (left < right) {

                    sum = firstNumber + secondNumber + numbers[left] + numbers[right];

                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        result.add(List.of(firstNumber, secondNumber, numbers[left], numbers[right]));
                        while (left < right && numbers[left] == numbers[left + 1]) left++;
                        while (left < right && numbers[right] == numbers[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        // todo: list to array
        // ✅ List<List<Integer>> → int[][] 변환
        return result.stream().map(list -> list.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);

    }
}
