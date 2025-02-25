package algorithm.sum;

import java.util.Arrays;

// target 에 근사한 값을 찾는 법
public class CloseThreeSum {

    int closeThreeSum(int[] numbers, int target) {
        int closeNumber = numbers[0] + numbers[1] + numbers[2];
        Arrays.sort(numbers);

        int fixedNumber = -1;
        int left = -1;
        int right = -1;
        int sum = -1;
        for (int i = 0; i < numbers.length - 2; i++) {
            left = i + 1;
            right = numbers.length - 1;
            fixedNumber = numbers[i];

            while (left < right) {
                sum = fixedNumber + numbers[left] + numbers[right];
                if (Math.abs(closeNumber - target) > Math.abs(sum - target)) {
                    closeNumber = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return target;
                }
            }
        }
        return closeNumber;
    }
}
