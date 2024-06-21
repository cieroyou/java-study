package algorithm.backjoon.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/9663
 * NQueen 경우의 수
 * 풀이방법: 완전탐색-중복되지않는 숫자 N개(column) 뽑기
 */
public class NQueen {
	static int N;
	static int[] col; // col[0]
	static int result;
	static boolean[] checklist;

	static void input() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			N = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		col = new int[N];
		checklist = new boolean[N];
	}

	public static void main(String[] args) {
		input();
		withDuplication(0);
		System.out.println(result);
	}

	//0 4 7 5 2 6 1 3
	// 0 5 7 2 6 3 1 4
	// 0 6 3 5 7 1 4 2
	// 0 6 4 7 1 3 5 2
	// 1 3 5 7 2 0 6 4
	// 1 4 6 0 2 7 5 3
	// 1 4 6 3 0 7 5 2
	// 1 5 0 6 3 7 2 4
	// 1 5 7 2 0 3 6 4
	// 1 6 2 5 7 4 0 3
	// 1 6 4 7 0 3 5 2
	// 1 7 5 0 2 4 6 3
	// 2 0 6 4 7 1 3 5
	// 2 4 1 7 0 6 3 5
	// 2 4 1 7 5 3 6 0
	// 2 4 6 0 3 1 7 5
	// 2 4 7 3 0 6 1 5
	// 2 5 1 4 7 0 6 3
	// 2 5 1 6 0 3 7 4
	// 2 5 1 6 4 0 7 3
	// 2 5 3 0 7 4 6 1
	// 2 5 3 1 7 4 6 0
	// 2 5 7 0 3 6 4 1
	// 2 5 7 0 4 6 1 3
	// 2 5 7 1 3 0 6 4
	// 2 6 1 7 4 0 3 5
	// 2 6 1 7 5 3 0 4
	// 2 7 3 6 0 5 1 4
	// 3 0 4 7 1 6 2 5
	// 3 0 4 7 5 2 6 1
	// 3 1 4 7 5 0 2 6
	// 3 1 6 2 5 7 0 4
	// 3 1 6 2 5 7 4 0
	// 3 1 6 4 0 7 5 2
	// 3 1 7 4 6 0 2 5
	// 3 1 7 5 0 2 4 6
	// 3 5 0 4 1 7 2 6
	// 3 5 7 1 6 0 2 4
	// 3 5 7 2 0 6 4 1
	// 3 6 0 7 4 1 5 2
	// 3 6 2 7 1 4 0 5
	// 3 6 4 1 5 0 2 7
	// 3 6 4 2 0 5 7 1
	// 3 7 0 2 5 1 6 4
	// 3 7 0 4 6 1 5 2
	// 3 7 4 2 0 6 1 5
	// 4 0 3 5 7 1 6 2
	// 4 0 7 3 1 6 2 5
	// 4 0 7 5 2 6 1 3
	// 4 1 3 5 7 2 0 6
	// 4 1 3 6 2 7 5 0
	// 4 1 5 0 6 3 7 2
	// 4 1 7 0 3 6 2 5
	// 4 2 0 5 7 1 3 6
	// 4 2 0 6 1 7 5 3
	// 4 2 7 3 6 0 5 1
	// 4 6 0 2 7 5 3 1
	// 4 6 0 3 1 7 5 2
	// 4 6 1 3 7 0 2 5
	// 4 6 1 5 2 0 3 7
	// 4 6 1 5 2 0 7 3
	// 4 6 3 0 2 7 5 1
	// 4 7 3 0 2 5 1 6
	// 4 7 3 0 6 1 5 2
	// 5 0 4 1 7 2 6 3
	// 5 1 6 0 2 4 7 3
	// 5 1 6 0 3 7 4 2
	// 5 2 0 6 4 7 1 3
	// 5 2 0 7 3 1 6 4
	// 5 2 0 7 4 1 3 6
	// 5 2 4 6 0 3 1 7
	// 5 2 4 7 0 3 1 6
	// 5 2 6 1 3 7 0 4
	// 5 2 6 1 7 4 0 3
	// 5 2 6 3 0 7 1 4
	// 5 3 0 4 7 1 6 2
	// 5 3 1 7 4 6 0 2
	// 5 3 6 0 2 4 1 7
	// 5 3 6 0 7 1 4 2
	// 5 7 1 3 0 6 4 2
	// 6 0 2 7 5 3 1 4
	// 6 1 3 0 7 4 2 5
	// 6 1 5 2 0 3 7 4
	// 6 2 0 5 7 4 1 3
	// 6 2 7 1 4 0 5 3
	// 6 3 1 4 7 0 2 5
	// 6 3 1 7 5 0 2 4
	// 6 4 2 0 5 7 1 3
	// 7 1 3 0 6 4 2 5
	// 7 1 4 2 0 6 3 5
	// 7 2 0 5 1 4 6 3
	// 7 3 0 2 5 1 6 4
	static void withDuplication(int level) {
		if (level == N) {
			result++;
		} else {
			for (int i = 0; i < N; i++) {
				if (checklist[i])
					continue;
				boolean validate = true;
				if (level > 0) {
					for (int j = 0; j < level; j++) {
						if (Math.abs(level - j) == Math.abs(i - col[j])) {
							validate = false;
							break;
						}
					}
				}
				if (!validate)
					continue;
				col[level] = i;
				checklist[i] = true;
				withDuplication(level + 1);
				checklist[i] = false;
			}
		}
	}

	static void validate(int[] cols) {
		int row = 0;
		int col = cols[0];
		int count = 0;
		for (int i = 1; i < N; i++) {
			row = i;
			col = cols[i];
			for (int j = 0; j < row; j++) {
				if (Math.abs(i - j) == Math.abs(col - cols[j])) {
					return;
				}
				if (j == row - 1) {
					count++;
				}
			}
		}
		if (count == cols.length - 1) {
			Arrays.stream(cols).forEach(c -> System.out.print(c + " "));
			System.out.println();
			result++;
		}
	}
}
