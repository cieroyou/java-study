package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DfsTest {

    @Test
    public void dfsTests() {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(List.of(1, 2, 4));
        graph.add(List.of(0, 3));
        graph.add(List.of(0));
        graph.add(List.of(1, 5));
        graph.add(List.of(0));
        graph.add(List.of(3));

        var result = dfs(graph, 0);
        Assertions.assertEquals(List.of(0, 1, 3, 5, 2, 4), result);

    }


    private List<Integer> dfs(List<List<Integer>> graph, int start) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> route = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start); // 0 부터 시작하는 경우, stack에 0 을 넣어준다. 그래야 시작이 가능 = stack.empty() 부터 시작 가능

        // stack 이 빌때까지 계속 반복
        while (!stack.empty()) {
            int vertex = stack.pop();
            // 방문한 적 없으면 진행하고, 방문한적 있으면 진행하지 않는다.
            if (!visited.contains(vertex)) {
                // 방문처리 한다.
                visited.add(vertex);
                route.add(vertex);

                for (int i = graph.get(vertex).size() - 1; i >= 0; i--) {
                    stack.push(graph.get(vertex).get(i));
                }
                System.out.println(stack);
            }
        }
        return route;
    }
}
