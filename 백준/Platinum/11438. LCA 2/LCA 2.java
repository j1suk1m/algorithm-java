import java.util.*;
import java.io.*;

public class Main {
    static int N; // 정점의 개수
	static int K; // 이진 점프 크기 // 각 정점의 2^K 번째 조상 정점을 찾기 위한 값
	static int[] depth; // 정점의 깊이
	static int[][] parent; // parent[K][V] == 정점 V의 2^K 번째 조상 정점 번호   
	static List<List<Integer>> graph; // 인접 리스트    
	static final StringBuilder output = new StringBuilder();
	static final String SEPARATOR = "\n";
	static final int ROOT = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); // 정점의 개수
		K = (int) Math.ceil(Math.log(N) / Math.log(2));
		depth = new int[N + 1]; // 정점의 번호는 1번부터 시작
		parent = new int[K + 1][N + 1];
		graph = initGraph();
		
		// 연결 관계 저장
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		
		// 각 정점의 깊이와 부모 정점 저장
		calculateDepthAndParent();
		
		// 각 정점의 조상 정점 저장
		calculateAncestors();
		
		int M = Integer.parseInt(br.readLine()); // LCA 질의 대상이 되는 정점 쌍의 개수
		
		// LCA 질의
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			output.append(findLCA(node1, node2)).append(SEPARATOR);
		}
		
		// 정답 출력
		System.out.print(output);
	}
	
	static List<List<Integer>> initGraph() {
		List<List<Integer>> graph = new ArrayList<>();
		
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}
		
		return graph;
	}
	
	static void calculateDepthAndParent() {
		Deque<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		
		queue.add(new int[] { ROOT, 0 });
		visited[ROOT] = true;
		
		while (!queue.isEmpty()) {
			int[] element = queue.poll();
			
			int currentNode = element[0];
			int currentDepth = element[1];
						
			for (int nextNode : graph.get(currentNode)) {
				if (!visited[nextNode]) {
					queue.add(new int[] { nextNode, currentDepth + 1 });
					visited[nextNode] = true; // 방문 처리
					depth[nextNode] = currentDepth + 1; // 깊이 저장
					parent[0][nextNode] = currentNode; // 부모 정점 저장
				}
			}
		}
	}
	
	static void calculateAncestors() {
		for (int i = 1; i <= K; i++) { // i == 0인 경우, 즉 부모 정점의 번호는 calculateDepthAndParent 실행 시 저장
			for (int j = 2; j <= N; j++) { // j == 1인 경우, 즉 루트는 계산할 필요가 없으므로 스킵
				parent[i][j] = parent[i - 1][parent[i - 1][j]]; // 정점 j의 2^i번째 조상은 2^(i - 1)번째 조상의 2^(i - 1)번째 조상과 동일
			}
		}
	}
	
	static int findLCA(int node1, int node2) {
		int shallowerNode = depth[node1] < depth[node2] ? node1 : node2; 
		int deeperNode = depth[node1] < depth[node2] ? node2 : node1;
		
		int diff = depth[deeperNode] - depth[shallowerNode];
		
		// 깊은 정점을 얕은 정점과 동일한 깊이로 점프
		for (int i = K; i >= 0; i--) {
			if ((diff & (1 << i)) != 0) {
				deeperNode = parent[i][deeperNode];
			}
		}
		
		// 깊이를 일치시켰더니 동일한 정점이 된 경우, 해당 정점이 LCA
		if (shallowerNode == deeperNode) return shallowerNode;
		
		// LCA를 찾을 때까지 점프
		for (int i = K; i >= 0; i--) {
			if (parent[i][shallowerNode] != parent[i][deeperNode]) { // 서로 다른 최상위의 두 정점까지 도달
				shallowerNode = parent[i][shallowerNode];
				deeperNode = parent[i][deeperNode];
			}
		}
		
		return parent[0][shallowerNode]; // parent[0][deeperNode]와 동일
	}
}