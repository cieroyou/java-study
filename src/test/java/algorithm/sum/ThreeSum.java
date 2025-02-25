package algorithm.sum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/3sum/
public class ThreeSum {

    public void threeSum(List<Integer> nums) {
        int left = 0;
        int right = nums.size() - 1;
        int fixedNumber = -1;

        // nums를 []로 변환
        int[] arr = nums.stream().mapToInt(i -> i).toArray();

        // two pointer를 사용하기 위해 정렬할 것
        Arrays.sort(arr);

        for (int i = 0; i < arr.length-2; i++) {
            left = i + 1;
             right = arr.length - 1;
            fixedNumber = arr[i];
            if (i > 0 && fixedNumber == arr[i - 1]){
                continue;
            }
            while (left < right) {
                int sum = sum(fixedNumber, arr[left], arr[right]);
                if (sum < 0) {
                    left = left + 1;
                } else if (sum == 0) {

                    while (left < right && arr[left] == arr[left + 1]) {
                        left++;
                    }
                    while (left < right && arr[right] == arr[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if(sum >0) {
                    right = right - 1;
                }

            }
        }

    }

    private Integer sum(int fixedNumber, Integer left, Integer right) {
        return fixedNumber + left + right;
    }


    @Test
    void test() {
//        threeSum(new int[]{}).get(0) 의 값을 모두 더하면 0이 되어야 한다.
        threeSum(List.of(-4, -1, -1, 0, 1, 2));
    }
}
