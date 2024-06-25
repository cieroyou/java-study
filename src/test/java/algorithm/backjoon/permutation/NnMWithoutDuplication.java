package algorithm.backjoon.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NnMWithoutDuplication {

	static int[] n; // n 배열에는 N개의 공이 담겨져 있다.
	static boolean[] checklist; // 뽑은 공을 체크하는 배열로, 뽑히면 1로 체크한다. 배열 개수는 담겨져 있는 공의 개수이다.
	static int N; // n.size()
	static int M;
	static int[] result; // 결과를 담는 배열로, M개의 공이 담긴다.

	public static void main(String[] args) throws Exception {
		input();
		withoutDuplication(0);
		System.out.println(sb.toString());
	}


	/**
	 * 총 공을 뽑은 개수(level)가 M개와 같다면 M개가 담긴 배열(result) 출력하고, 아니면 계속 뽑는다.
	 * @param level: 공을 뽑은 개수
	 */
	/**
	 * NnM01
	 * @param level
	 */
	static void withoutDuplication(int level) {
		if (level == M) {
			// 총 공을 뽑은 개수(level)가 M개와 같다면 출력
			print(result);
		} else {
			// 아니면 계속 뽑는다.
			for (int i = 0; i < N; i++) {
				// 뽑지 않았던 공만 뽑도록 checklist[i] == false 조건문을 실행한다.
				if(checklist[i]) continue;
				// if (checklist[i] == false) {
				// 뽑은 애는 뽑았다고 checklist[i] = true 로 체크해준다.
				checklist[i] = true;
				result[level] = n[i];
				withoutDuplication(level + 1);
				// dfs 백트래킹 처리를 위해 checklist[i] = false 로 다시 초기화해준다.
				checklist[i] = false;
				// }
			}
		}

	}

	/**
	 * NnM02
	 * @param level
	 */
	static void withAsc(int level) {
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
					withAsc(level + 1);
					checklist[i] = false;
				} else {
					if (!checklist[i] && result[level - 1] < n[i]) {
						checklist[i] = true;
						result[level] = n[i];
						withAsc(level + 1);
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

}
