package algorithm.backjoon.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14888번, 연산자 끼워넣기
 * https://www.acmicpc.net/problem/14888
 * 완전탐색
 */
public class Operators {
	static int N, M, max, min, current;
	static boolean[] checklist; // 연산자 중복뽑기 방지
	static int[] numbers;
	static int[] operators, result, currents;

	enum Oprator {
		plus(0, "+") {
			@Override
			public int calculate(int num1, int num2) {
				return num1 + num2;
			}
		}, minus(1, "-") {
			@Override
			public int calculate(int num1, int num2) {
				return num1 - num2;
			}
		}, multiply(2, "*") {
			@Override
			public int calculate(int num1, int num2) {
				return num1 * num2;
			}
		}, divide(3, "/") {
			@Override
			public int calculate(int num1, int num2) {
				return num1 / num2;
			}
		};
		final Integer order;
		final String symbol;

		Oprator(Integer order, String symbol) {
			this.order = order;
			this.symbol = symbol;
		}

		public String getSymbol() {
			return this.symbol;
		}

		public static Oprator getOperator(int order) {
			if (order == 0)
				return plus;
			else if (order == 1)
				return minus;
			else if (order == 2)
				return multiply;
			else if (order == 3)
				return divide;
			else
				return plus;
		}

		public abstract int calculate(int num1, int num2);
	}

	/**
	 * 연산자 개수 N개중에 중복없이 전부 뽑기
	 */
	static void pick(int level) {
		if (level == M) {
			// 연산하기
			max = Math.max(max, current);
			min = Math.min(min, current);
			// System.out.println();
			// Arrays.stream(result).forEach(a -> System.out.printf(
			// 	Oprator.getOperator(a).getSymbol()));
			// System.out.printf("값: %d, currents: ".formatted(current));
			// Arrays.stream(currents).forEach(b -> System.out.printf(String.valueOf(b) + " "));
			// System.out.println();
		} else {
			for (int i = 0; i < operators.length; i++) {
				if (checklist[i])
					continue;
				if (level == 0)
					// 뽑은 개수가 0개일때
					current = numbers[0];
				else {
					current = currents[level - 1];
				}
				Oprator oprator = Oprator.getOperator(operators[i]);
				result[level] = operators[i];
				// System.out.printf(
				// 	"pick(%d), current: %d, operator: %s, 식: %d%s%s=".formatted(level, current, oprator.getSymbol(),
				// 		current, oprator.getSymbol(), numbers[level + 1]));

				current = oprator.calculate(current, numbers[level + 1]);
				currents[level] = current;
				// System.out.printf(current + " / ");
				checklist[i] = true;
				pick(level + 1);
				checklist[i] = false;
			}
		}
	}

	public static void initializeInput() throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			numbers = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				numbers[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			operators = new int[N - 1];
			int currentOperator = 0;
			for (int i = 0; i < 4; i++) {
				int v = Integer.parseInt(st.nextToken());
				for (int j = 0; j < v; j++) {
					operators[currentOperator] = i;
					currentOperator++;
				}
			}
		}
	}

	static StringTokenizer st;

	public static String next(BufferedReader br) throws IOException {
		st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}

	public static void main(String[] args) throws IOException {
		// numbers = new int[] {3, 4, 5};
		// operators = new int[] {0, 2};
		initializeInput();
		checklist = new boolean[numbers.length];
		M = operators.length;
		result = new int[M];
		current = numbers[0];
		currents = new int[M];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		pick(0);
		System.out.println(max);
		System.out.println(min);
	}

}

