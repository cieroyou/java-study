package algorithm.backjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10825
 * 국어 점수가 감소하는 순서로(국어점수 높은 순)
 * 국어 점수가 같으면 영어 점수가 증가하는 순서로(영어점수 낮은 순)
 * 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로(수학점수 높은 순)
 * 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)
 * 학생(이름, 국어점수, 영어점수, 수학점수)
 * 점수 필드는 0~100 이므로 Integer 사용
 * 복잡도: O(NlogN), N이 100,000이므로 10만 * log10만 = 160만 -> 1초안에 가능
 */
public class Backjoon10825 {
	static int N; // 반 학생수
	static Student[] students;

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		input();
		sort();
		for (int i = 0; i < N; i++) {
			sb.append(students[i].name).append('\n');
		}
		System.out.println(sb);
	}
	static void sort() {
		Arrays.sort(students);
	}
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		students = new Student[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int korean = Integer.parseInt(st.nextToken());
			int english = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			students[i] = new Student(name, korean, english, math);
		}

	}
	static class Student implements Comparable<Student> {
		String name;
		int korean, english, math;

		public Student(String name, int korean, int english, int math) {
			this.name = name;
			this.korean = korean;
			this.english = english;
			this.math = math;
		}

		@Override
		public int compareTo(Student other) {
			// 1. 국어점수 내림차순(국어점수 높은 순)
			// 내림차순 = 내가 쟤보다 작다(this < o)
			if (this.korean != other.korean) {
				return other.korean - this.korean;
			}
			// 2. 영어점수 오름차순(영어점수 낮은 순)
			if(this.english != other.english) {
				return this.english - other.english;
			}
			// 3. 수학점수 내림차순(수학점수 높은 순)
			if(this.math != other.math) {
				return other.math - this.math;
			}
			return this.name.compareTo(other.name);
		}
	}
}
