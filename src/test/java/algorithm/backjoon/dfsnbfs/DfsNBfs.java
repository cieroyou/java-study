package algorithm.backjoon.dfsnbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 문제 풀이시 주의점. graph(Map<Integer,List<Integer>) 를 만들어 준뒤, List<Integer> 를 오름차순으로 소팅을 해줘야 함
 * List<Integer> 가져올 때 null 체크 해야 NullPointer 발생하지 않는다.
 */
public class DfsNBfs {

	// List<List<Integer>> generateGraph(){
	// 	/**
	// 	 *      0
	// 	 *    / \ \
	// 	 *   1  2  4
	// 	 *   |
	// 	 *   3 - 5
	// 	 *   위와 같은 그래프를 만들어보자.
	// 	 *   0 인접노드는 1, 2, 4
	// 	 *   1 인접노드는 0, 3
	// 	 *   2 인접노드는 0
	// 	 *   3 인접노드는 1, 5
	// 	 *   4 인접노드는 0
	// 	 *   5 인접노드는 3
	// 	 *
	// 	 * graph = {
	// 	 *
	// 	 *
	// 	 *   0: [1, 2, 4],
	// 	 *     1: [0, 3],
	// 	 *     2: [0],
	// 	 *     3: [1, 5],
	// 	 *     4: [0],
	// 	 *     5: [3]
	// 	 * }
	// 	 */
	//
	// 	List<List<Integer>> graph = new ArrayList<>();
	// 	graph.add(List.of(2,3,4));
	// 	graph.add(List.of(4));
	// 	graph.add(List.of(4));
	// 	graph.add(List.of(1,2,3));
	// }

	//4 5 1
	// 1 2
	// 1 3
	// 1 4
	// 2 4
	// 3 4

	// Map<List<Integer>> generateGraph(Integer N, Integer M) throws IOException {
	// 	// 		N,M,V = map(int,input().split())
	// 	//
	// 	// #행렬 만들기
	// 	// 		graph = [[0]*(N+1) for _ in range(N+1)]
	// 	// 		for i in range (M):
	// 	// 		a,b = map(int,input().split())
	// 	// 		graph[a][b] = graph[b][a] = 1
	//
	// 	// 위의 파이썬코드를 자바로 변환
	// 	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	// 	StringTokenizer st = new StringTokenizer(bf.readLine());
	// 	int n = Integer.parseInt(st.nextToken());
	// 	int m = Integer.parseInt(st.nextToken());
	// 	Map<Integer, List<Integer>> graph = new HashMap<>();
	//
	// 	for(int i = 0; i>n; i++) {
	// 		StringTokenizer st2 = new StringTokenizer(bf.readLine());
	// 		int a = Integer.parseInt(st2.nextToken());
	// 		int b = Integer.parseInt(st2.nextToken());
	// 		if(graph.containsKey(a)) {
	// 			graph.get(a).add(b);
	// 		} else {
	// 			graph.put(a, new ArrayList<>(List.of(b)));
	// 		}
	// 	}
	// 	// N,M,V = map(int,input().split())
	// 	// #행렬 만들기
	//
	// }

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken()); // Start

		Map<Integer, List<Integer>> graph = new HashMap<>();

		for (int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st2.nextToken());
			int child = Integer.parseInt(st2.nextToken());
			if (graph.containsKey(node)) {
				graph.get(node).add(child);
			} else {
				List<Integer> c = new ArrayList<>();
				c.add(child);
				graph.put(node, c);
			}

			if (graph.containsKey(child)) {
				graph.get(child).add(node);
			} else {
				List<Integer> c = new ArrayList<>();
				c.add(node);
				graph.put(child, c);
			}
		}

		dfs(graph, V);
		bfs(graph, V);
	}

	private static void dfs(Map<Integer, List<Integer>> graph, int start) {
		Set<Integer> visited = new HashSet<>();
		List<Integer> route = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		stack.push(start);

		while (!stack.isEmpty()) {
			int current = stack.pop();
			if (!visited.contains(current)) {
				visited.add(current);
				route.add(current);
				List<Integer> togoList = graph.get(current);
				if (togoList != null) {
					togoList.sort(Comparator.naturalOrder());
					for (int i = 0; i < togoList.size(); i++) {
						// get(2)->get(1)->get(0)
						stack.push(togoList.get(togoList.size() - 1 - i));
					}
				}
			}
		}
		for (int i = 0; i < route.size() - 1; i++) {
			System.out.print(route.get(i) + " ");
		}
		System.out.println(route.get(route.size() - 1));
	}

	private static void bfs(Map<Integer, List<Integer>> graph, int start) {
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
				List<Integer> togoList = graph.get(vertex);
				if(togoList != null) {
					togoList.sort(Comparator.naturalOrder());
					for (int i = 0; i < graph.get(vertex).size(); i++) {
						queue.add(togoList.get(i));
					}
				}
			}
		}
		for (int i = 0; i < route.size() - 1; i++) {
			System.out.print(route.get(i) + " ");
		}
		System.out.print(route.get(route.size() - 1));
	}
}
