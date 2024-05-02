package algorithm.backjoon.dfsnbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Virus {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String input = br.readLine();
            String[] split = input.split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            if(graph.containsKey(a)) {
                graph.get(a).add(b);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(b);
                graph.put(a, newList);
            }

            if(graph.containsKey(b)) {
                graph.get(b).add(a);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(a);
                graph.put(b, newList);
            }

        }
        List<Integer> result = dfs(graph, 1);
        System.out.println(result);

    }



    public static List<Integer> dfs(Map<Integer, List<Integer>> graph, int start) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> route = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start); // 0 부터 시작하는 경우, stack에 0 을 넣어준다. 그래야 시작이 가능 = stack.empty() 부터 시작 가능

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
            }
        }
        return route;
    }

}
