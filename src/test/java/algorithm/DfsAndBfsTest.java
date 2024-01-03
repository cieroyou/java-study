package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DfsAndBfsTest {

    final List<List<Integer>> GRAPH = List.of(
            List.of(1, 2, 4),
            List.of(0, 3),
            List.of(0),
            List.of(1, 5),
            List.of(0),
            List.of(3)
    );

    @Test
    public void testDfs() {
        var result = dfs(GRAPH, 0);
        Assertions.assertEquals(List.of(0, 1, 3, 5, 2, 4), result);
    }

    @Test
    public void testBfs() {
        var bfsResult = bfs(GRAPH, 0);
        Assertions.assertEquals(List.of(0, 1, 2, 4, 3, 5), bfsResult);
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

    private List<Integer> bfs(List<List<Integer>> graph, int start) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> route = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start); // 0 부터 시작하는 경우, stack에 0 을 넣어준다. 그래야 시작이 가능 = stack.empty() 부터 시작 가능

        // stack 이 빌때까지 계속 반복
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            // 방문한 적 없으면 진행하고, 방문한적 있으면 진행하지 않는다.
            if (!visited.contains(vertex)) {
                // 방문처리 한다.
                visited.add(vertex);
                route.add(vertex);

                for (int i = 0; i < graph.get(vertex).size(); i++) {
                    queue.add(graph.get(vertex).get(i));
                }
                System.out.println(queue);
            }
        }
        return route;
    }
}
