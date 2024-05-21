package algorithm.backjoon.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 고른 수열은 오름차순이어야 한다.
 */
public class NnM02 {

	static int[] n; // n 배열에는 N개의 공이 담겨져 있다.
	static boolean[] checklist; // 뽑은 공을 체크하는 배열로, 뽑히면 1로 체크한다. 배열 개수는 담겨져 있는 공의 개수이다.
	static int N; // n.size()
	static int M;
	static int[] result; // 결과를 담는 배열로, M개의 공이 담긴다.

	static void input() throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			n = new int[N];
			for (int i = 0; i < N; i++) {
				n[i] = i + 1;
			}
			checklist = new boolean[N];
			result = new int[M];
		}
	}

	/**
	 * NnM03
	 * @param level
	 */
	static void DfsWithoutDuplication(int level) {
		// 모든 공을 다 뽑으면
		if (level == M) {
			print(result);
		}
		/// 다 뽑지 않으면 계속 뽑기 진행
		// 중복이면 안되고, 이전에 뽑은 값보다 더 큰 값이여야 한다.
		else {
			for (int i = 0; i < N; i++) {
				if (level == 0) {
					checklist[i] = true;
					result[level] = n[i];
					DfsWithoutDuplication(level + 1);
					checklist[i] = false;
				} else {
					if (!checklist[i] && result[level - 1] < n[i]) {
						checklist[i] = true;
						result[level] = n[i];
						DfsWithoutDuplication(level + 1);
						checklist[i] = false;
					}
				}
			}
		}
	}

	static StringBuilder sb = new StringBuilder();

	private static void print(int[] result) {
		// for (int i = 0; i < result.length; i++) {
		// 	System.out.print(result[i] + " ");
		// }
		// System.out.println();

		for (int i = 0; i < M; i++) {
			sb.append(result[i]).append(' ');
		}
		sb.append('\n');
	}

	public static void main(String[] args) throws Exception {
		input();
		DfsWithoutDuplication(0);
		System.out.println(sb.toString());
	}

	@Test
	void example1() {
		int n = 3;
		int m = 1;

		func(3, 1, new HashSet<>());
		//        func(n, m, new HashSet<>());
	}

	@Test
	void example2() {
		int n = 4;
		int m = 2;
		//
		//        func(4, 2, new HashSet<>());
		////        func(n, m, new HashSet<>());

		Set<Integer> current = new HashSet<>();
		for (int i = 1; i <= 4; i++) {
			current.add(i);
			for (int j = 1; j <= 4; j++) {
				if (current.contains(j))
					continue;
				current.add(j);
				print(current);
				current.remove(j);
			}
		}
	}

	static void func(int n, int m, Set<Integer> current) {
		for (int i = 1; i <= n; i++) {
			if (!current.contains((i))) {
				current.add(i);
				if (current.size() == m) {
					print(current);
					current.remove(current.size() - 1);
				} else {
					func(n, m, current);
					current.remove(current.size() - 1);
				}
			}
		}
	}

	static void print(Set<Integer> current) {
		// 1 2 3 4
		for (Integer integer : current) {
			System.out.print(integer + " ");
		}
		System.out.println();

	}
}
