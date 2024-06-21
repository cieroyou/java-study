package algorithm.backjoon.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분수열: N개의 정수로 이루어진 수열이 있을 때의 모든 경우의 수 구하기
// 선택됨 = 1, 선택되지않음 = 0 으로 총 N개의 부분순열 개수는 2^N개
// 0과 1을 N개만큼 중복해서 뽑기
public class Boj1182 {
	static int[] n, operated;
	static int[] m = new int[] {0, 1};
	static int expected_result;
	static int result = 0;
	static int zerocount = 0;

	public static void main(String[] args) throws IOException {
		input();
		withDuplicate(0);
		System.out.println(result);
	}

	static void input() {
		int N;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			expected_result = Integer.parseInt(st.nextToken());
			n = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				n[i] = Integer.parseInt(st.nextToken());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		operated = new int[n.length];
	}

	static void withDuplicate(int level) {
		if (level == n.length) {
			if (zerocount == n.length) {
				zerocount = 0;
				return;
			}
			if (operated[n.length - 1] == expected_result) {
				result++;
			}
			zerocount = 0;
		} else {
			for (int i = 0; i < m.length; i++) {
				int current = getNumber(m[i], level);
				// result 는 뽑은 숫자. operated는 뽑은 숫자들의 합
				if (level == 0) {
					// 첫번째 뽑은 숫자이므로 operated[0] 초기화
					operated[level] = current;
				} else {
					int a = operated[level - 1] + current;
					operated[level] = a;
				}
				withDuplicate(level + 1);
			}
		}
	}

	// 0 인 경우는 안뽑혔으므로 0 그래도 리턴
	// 1인 경우에는 뽑인 것이므로 해당 숫자 리턴
	static int getNumber(int number, int i) {
		if (number == 0) {
			zerocount++;
			return 0;
		} else
			return n[i];
	}
}
