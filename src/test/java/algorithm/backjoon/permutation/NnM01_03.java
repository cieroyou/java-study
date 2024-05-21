package algorithm.backjoon.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 중복 허용하지 않은 것(NnM01)과 중복허용하는 것(NnM03) 모두 하나의 클래스로 문제풀이를 담음
 * https://www.acmicpc.net/problem/15649
 * 순열 nPr 이라고도 불린다. n개의 공 중에서 r개의 공을 꺼내는 것으로 총 경우의 수는 n!/(n-r)! 이다.
 * 여기에서 N = n, M = r 로, n 배열에는 N개의 공이 담겨져 있다.
 * 예를들어 N = 3, M = 2 일 때, n 배열에는 [1, 2, 3] 이 담겨져 있고,
 * 이 때, 1 2, 1 3, 2 1, 2 3, 3 1, 3 2 의 경우의 수가 나온다.
 * DFS 와 checklist 로 푼다. !!!!! 외운다.
 *
 * 주의해야할 것:
 * 1. checklist 개수는 M개가 아니라 N개이다.
 * 2. print()에서 시간초과 발생한다. StringBuilder 로 풀것
 */


public class NnM01_03 {

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

	// n: n[0]=1, n[1]=2, n[2]=3
	// checklist(c): c[0]=true, c[1]=false, c[n]=false
	// result: result[0]=n[i], .., result[m]=n[i]

	/**
	 * NnM03
	 * @param level
	 */
	static void DfsWithDuplication(int level) {
		// 모든 공을 다 뽑으면
		if (level == M) {
			print(result);
		}
		/// 다 뽑지 않으면 계속 뽑기 진행
		else {
			for (int i = 0; i < N; i++) {
				result[level] = n[i];
				checklist[level] = true;
				DfsWithDuplication(level + 1);

			}
		}
	}

	/**
	 * 총 공을 뽑은 개수(level)가 M개와 같다면 M개가 담긴 배열(result) 출력하고, 아니면 계속 뽑는다.
	 * @param level: 공을 뽑은 개수
	 */
	/**
	 * NnM01
	 * @param level
	 */
	static void DfsWithoutDuplication(int level) {
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
					DfsWithoutDuplication(level + 1);
					// dfs 백트래킹 처리를 위해 checklist[i] = false 로 다시 초기화해준다.
					checklist[i] = false;
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

		for(int i=0; i<M; i++) {
			sb.append(result[i]).append(' ');
			sb.append('\n');
		}
	}



	public static void main(String[] args) throws Exception {
		input();
		DfsWithDuplication(0);
		System.out.println(sb.toString());
	}

}
