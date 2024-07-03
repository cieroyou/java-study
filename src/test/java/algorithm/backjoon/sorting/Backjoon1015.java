package algorithm.backjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1015
 */
public class Backjoon1015 {
	static int N;
	static A[] sortedA;
	static int[] P;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		input();
		sort();
		for(int i=0; i<N; i++) {
			int index = sortedA[i].index;
			P[index] = i;
		}

		for(int i=0; i<N; i++){
			if(i == N-1)
				sb.append(P[i]);
			else
				sb.append(P[i]).append(" ");
		}
		System.out.println(sb);

	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		sortedA = new A[N];
		P = new int[N];
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
			sortedA[i] = new A(value, i);
		}
	}

	static void sort() {
		Arrays.sort(sortedA);
	}

	static class A implements Comparable<A> {
		int value;
		int index;
		public A(int value, int index) {
			this.value = value;
			this.index = index;
		}

		@Override
		public int compareTo(A other) {
			// 오름차순 정렬
			if (this.value > other.value)
				return 1;
			else if (this.value < other.value)
				return -1;
			else if(this.value == other.value){
				if(this.index > other.index)
					return 1;
				else if(this.index < other.index)
					return -1;
			}
			return 0;
		}
	}
}
