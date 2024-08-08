package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MinimumStackTest {

	// 가장 작은 숫자 뽑기
	@Test
	void test() {
		MinimumStack stack = new MinimumStack();
		stack.push(1);
		stack.push(2);
		stack.push(0);
		// assertEquals(1, stack.getMin());
		int a = stack.getMin();
		stack.pop();
		int b = stack.getMin();
		// assertEquals(1, stack.getMin());
		stack.pop();
		int c = stack.getMin();
		// assertEquals(2, stack.getMin());
	}
}