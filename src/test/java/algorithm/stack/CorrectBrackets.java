package algorithm.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * String 문자 캐릭터를 forEach 문을 돌면서, ( 이면 스택에 넣고, ) 이면 스택에서 빼서, 모든 캐릭터를 다 돌았을 때 isEmpty()가 false 이면 올바르지 않은 괄호. 이때, forEach문을 돌면서 isEmpty() 인데 ) 로 시작하는 경우에는 바로 false 반환
 */
public class CorrectBrackets {

    // input: (())() output: true
    // input: (((()())() output: false
    // 이거 런테임 에러 남
    public boolean result1(String input) {
        Stack<Character> stack = new Stack<>();
        if (input.charAt(0) == ')') return false;
        for (char c : input.toCharArray()) {
            if (c == '(') stack.push(c);
            else stack.pop();
        }

        if (stack.isEmpty()) return true;
        else return false;
    }

    @Test
    public void test() {
//        assertTrue(result("(())()"));
        assertFalse(result1(")()("));
    }


    boolean solution1(String s) {
        Stack<Character> stack = new Stack<>();
//        "))))"
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            if (c == ')') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        Boolean answer;
        if (stack.isEmpty()) {
            answer = true;
        } else {
            answer = false;
        }

        return answer;
    }

    boolean solution2(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (stack.isEmpty()) {
                if (c == ')') return false;
            }
            if (c == ')') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
