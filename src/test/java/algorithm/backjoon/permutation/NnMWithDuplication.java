package algorithm.backjoon.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NnMWithDuplication {

	static int[] n; // n 배열에는 N개의 공이 담겨져 있다.
	static int N; // n.size()
	static int M;
	static int[] result; // 결과를 담는 배열로, M개의 공이 담긴다.

	public static void main(String[] args) throws Exception {
		input();
		nnM4(0);
		System.out.println(sb.toString());
	}

	/**
	 * NnM(3)
	 * @param level
	 */
	static void nnM3(int level) {
		// 모든 공을 다 뽑으면
		if (level == M) {
			print(result);
		}
		/// 다 뽑지 않으면 계속 뽑기 진행
		else {
			for (int i = 0; i < N; i++) {
				result[level] = n[i];
				nnM3(level + 1);
			}
		}
	}

	/**
	 * N과 M (4)
	 * @param level
	 */
	static void nnM4(int level) {
		// 모든 공을 다 뽑으면
		if (level == M) {
			print(result);
		}
		/// 다 뽑지 않으면 계속 뽑기 진행
		else {
			for (int i = 0; i < N; i++) {
				if(level == 0) {
					result[level] = n[i];
					nnM4(level + 1);
				}else{
					if (result[level - 1] <= n[i]) {
						result[level] = n[i];
						nnM4(level + 1);
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
			result = new int[M];
		}
	}

}
