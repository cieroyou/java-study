package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CroatiaAlphabet {
	static Set<String> croatiaAlphabets = Set.of("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=");


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		List<String> inputs = new LinkedList<>();
		input.chars().forEach(c -> inputs.add(String.valueOf((char)c)));
		int result = getNumber(inputs);
		System.out.println(result);
	}
	@Test
	void test() {
		String input = "ljes=njak";
		// List<String> inputs = new LinkedList<>();
		List<String> inputs = new LinkedList<>();
		input.chars().forEach(c -> inputs.add(String.valueOf((char)c)));

		getNumber(inputs);
	}
	@Test
	void test2() {
		String input = "ddz=z=";
		// List<String> inputs = new LinkedList<>();
		List<String> inputs = new LinkedList<>();
		input.chars().forEach(c -> inputs.add(String.valueOf((char)c)));

		int count = getNumber(inputs);
		Assertions.assertEquals(3, count);
	}

	@Test
	void test3() {
		String input = "nljj";
		// List<String> inputs = new LinkedList<>();
		List<String> inputs = new LinkedList<>();
		input.chars().forEach(c -> inputs.add(String.valueOf((char)c)));

		int count = getNumber(inputs);
		Assertions.assertEquals(3, count);

	}

	@Test
	void test4() {
		String input = "c=c=";
		// List<String> inputs = new LinkedList<>();
		List<String> inputs = new LinkedList<>();
		input.chars().forEach(c -> inputs.add(String.valueOf((char)c)));

		int count = getNumber(inputs);
		Assertions.assertEquals(2, count);

	}

	@Test
	void test5(){
		String input = "dz=ak";
		// List<String> inputs = new LinkedList<>();
		List<String> inputs = new LinkedList<>();
		input.chars().forEach(c -> inputs.add(String.valueOf((char)c)));
		int count = getNumber(inputs);
		Assertions.assertEquals(3, count);
	}
	private static int getNumber(List<String> inputs) {
		int count = 0;
		while (!inputs.isEmpty()) {
			String first = inputs.get(0);
			if (inputs.size() < 2) {
				inputs.remove(0);
				count += 1;
				continue;
			}
			if (first.equals("c") || first.equals("l") || first.equals("d") || first.equals("n") || first.equals("s")
				|| first.equals("z")) {
				String second = inputs.get(1);
				String temp = first + second;
				if (croatiaAlphabets.contains(temp)) {
					inputs.remove(0);
					inputs.remove(0);
					count += 1;
					continue;
				}
				if(inputs.size() < 3) {
					continue;
				}
				String third = inputs.get(2);
				if (first.equals("d") && second.equals("z") && third.equals("=")) {
					inputs.remove(0);
					inputs.remove(0);
					inputs.remove(0);
					count += 1;
					continue;
				}else{
					inputs.remove(0);
					count += 1;
				}
			} else {
				inputs.remove(0);
				count += 1;
			}
		}
		return count;
	}
}
