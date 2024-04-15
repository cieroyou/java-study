package algorithm.backjoon.permutation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

/**
 * https://www.acmicpc.net/problem/15649
 * 순열 nPr 이라고도 불린다. n개의 공 중에서 r개의 공을 꺼내는 것으로 총 경우의 수는 n!/(n-r)! 이다.
 * 여기에서 N = n, M = r 로, n 배열에는 N개의 공이 담겨져 있다.
 * 예를들어 N = 3, M = 2 일 때, n 배열에는 [1, 2, 3] 이 담겨져 있고,
 * 이 때, 1 2, 1 3, 2 1, 2 3, 3 1, 3 2 의 경우의 수가 나온다.
 * DFS 와 checklist 로 푼다. !!!!! 외운다.
 */
public class NnM01 {

	static int[] n; // n 배열에는 N개의 공이 담겨져 있다.
	static boolean[] checklist; // 뽑은 공을 체크하는 배열로, 뽑히면 1로 체크한다. 배열 개수는 담겨져 있는 공의 개수이다.
	static int N; // n.size()
	static int M;
	static int[] result; // 결과를 담는 배열로, M개의 공이 담긴다.

	/**
	 * 총 공을 뽑은 개수(level)가 M개와 같다면 M개가 담긴 배열(result) 출력하고, 아니면 계속 뽑는다.
	 * @param level: 공을 뽑은 개수
	 */
	static void Dfs(int level) {
		if (level == M) {
			// 총 공을 뽑은 개수(level)가 M개와 같다면 출력
			print(result);
		} else {
			// 아니면 계속 뽑는다.
			for (int i = 0; i < N; i++) {
				// 뽑지 않았던 공만 뽑도록 checklist[i] == false 조건문을 실행한다.
				if (checklist[i] == false) {
					// 뽑은 애는 뽑았다고 checklist[i] = true 로 체크해준다.
					checklist[i] = true;
					result[level] = n[i];
					Dfs(level + 1);
					// dfs 백트래킹 처리를 위해 checklist[i] = false 로 다시 초기화해준다.
					checklist[i] = false;
				}
			}
		}

	}

	private static void print(int[] result) {
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// int n = Integer.parseInt(st.nextToken());
			// int m = Integer.parseInt(st.nextToken());
			// List<Integer> current = new LinkedList<>();
			// for (int i = 1; i <= n; i++) {
			//     current.add(i);
			//     func(n, m, current);
			//     current.remove(current.size() - 1);
			// }
		}
	}

	// 1부터 3까지 중복없이 1개를 뽑는 경우
	@Test
	void example1() {
		n = new int[] {1, 2, 3};
		checklist = new boolean[n.length];
		N = n.length;
		M = 1;
		result = new int[M];
		Dfs(0);
		// int n = 3;
		// int m = 1;

		// func(3, 1, new LinkedList<>());
		//        func(n, m, new HashSet<>());
	}

	// 1부터 4까지 중복없이 2개를 뽑는 경우
	@Test
	void example2() {
		// int n = 4;
		// int m = 2;
		// func(n, m, new LinkedList<>());
		n = new int[] {1, 2, 3, 4};
		checklist = new boolean[n.length];
		N = n.length;
		M = 2;
		result = new int[M];
		Dfs(0);
	}

	// 1부터 4까지 중복없이 4개를 뽑는 경우
	@Test
	void example3() {
		// int n = 4;
		// int m = 4;
		// List<Integer> current = new LinkedList<>();
		// func(n, m, current);
		n = new int[] {1, 2, 3, 4};
		checklist = new boolean[n.length];
		N = n.length;
		M = 4;
		result = new int[M];
		Dfs(0);
	}

	// static void func(int n, int m, List<Integer> current) {
	//     for (int i = 1; i <= n; i++) {
	//         if (!current.contains((i))) {
	//             current.add(i);
	//             if (current.size() == m) {
	//                 print(current);
	//                 current.remove(current.size() - 1);
	//             } else {
	//                 func(n, m, current);
	//                 current.remove(current.size() - 1);
	//             }
	//         }
	//     }
	// }

	// static void print(List<Integer> current) {
	//     // 1 2 3 4
	//     for (int i = 0; i < current.size(); i++) {
	//         System.out.print(current.get(i) + " ");
	//     }
	//     System.out.println();
	//
	// }
}
