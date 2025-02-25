package algorithm.backjoon.binarysearch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchNumber {

    boolean searchRecursive(int[] array, int target, int left, int right) {
        if (left > right) return false; // 종료 조건 (탐색 범위 초과)
        int midIndex = (left + right) / 2;
        int midValue = array[midIndex];

        if (target == midValue) return true;
        if (target < midValue) {
            // 데이터 왼쪽꺼 가져오기
            right = midIndex - 1;
            return searchRecursive(array, target, left, right);
        } else if (midValue < target) {
            // 데이터 오른쪽꺼 가져오기
            left = midIndex + 1;
            return searchRecursive(array, target, left, right);
        }
        return false;

    }

    boolean binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;

        while (left <= right) {  // ✅ 무조건 "<=" 써야 함!
            int mid = (left + right) / 2;

            if (array[mid] == target) return true;  // ✅ 찾으면 즉시 반환
            if (array[mid] < target) left = mid + 1;  // 🔹 오른쪽 탐색
            else right = mid - 1;  // 🔹 왼쪽 탐색
        }
        return false;  // ❌ 끝까지 못 찾으면 false
    }

    @Test
    @DisplayName("search 2")
    void search2() {

//        searchRecursive(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, 0, 9);
        binarySearch(new int[]{1, 3, 5, 7, 9, 11, 13}, 11);
    }
}
