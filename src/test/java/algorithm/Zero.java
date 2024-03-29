package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Zero {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		Stack<Integer> stack = new Stack<>();
		for (int num : list) {
			if (num == 0) {
				stack.pop();
			} else {
				stack.push(num);
			}
		}

		Integer sum =0;
		for (Integer integer : stack) {
			sum += integer;
		}
		System.out.println(sum);
	}

}
