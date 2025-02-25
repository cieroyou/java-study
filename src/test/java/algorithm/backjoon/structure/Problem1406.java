package algorithm.backjoon.structure;

import java.util.Deque;
import java.util.LinkedList;

public class Problem1406 {

    Deque<String> leftStack = new LinkedList<>();
    Deque<String> rightStack = new LinkedList<>();


    void executeL() {
        if (!leftStack.isEmpty()) { // 🔹 스택 비어있는지 체크!
            rightStack.push(leftStack.pop());
        }
    }

    void executeD() {
        if (!rightStack.isEmpty()) { // 🔹 스택 비어있는지 체크!
            leftStack.push(rightStack.pop());
        }
    }

    void executeB() {
        if (!leftStack.isEmpty()) { // 🔹 스택 비어있는지 체크!
            leftStack.pop();
        }
    }

    void executeP(String character) {
        leftStack.push(character);
    }

    // 🔹 명령어 실행 함수 추가
    void executeCommand(String command) {
        if (command.startsWith("P")) {
            executeP(command.split(" ")[1]);
        } else if (command.equals("L")) {
            executeL();
        } else if (command.equals("D")) {
            executeD();
        } else if (command.equals("B")) {
            executeB();
        }
    }
}
