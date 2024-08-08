package datastructure;

import java.util.Stack;

public class MinimumStack {
	private Stack<Integer> stack;
	private Stack<Integer> minStack;

	public MinimumStack() {
		stack = new Stack<>();
		minStack = new Stack<>();
	}

	public void push(int val) {
		if (minStack.isEmpty()) {
			minStack.push(val);
			stack.push(val);
			return;
		}
		// 집어넣으려는 숫자가, stack.peek()보다 작으면 그대로 minStack.push()
		if (stack.peek() > val) {
			minStack.push(val);
		} else {
			minStack.push(minStack.peek());
		}
		stack.push(val);
	}

	public void pop() {
		stack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}
}
