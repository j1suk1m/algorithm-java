import java.util.*;
import java.io.*;

class Edge {
	int dest; // 도착 정점 // 도착 도시
	int weight; // 가중치 // 도시를 연결하는 도로의 길이
	
	Edge(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
	}
}

public class Main {
	static int N; // 도시의 개수
	static int M; // 이진 점프를 위한 값 // 각 정점의 2^M 번째 조상 정점 접근을 위해 사용
	static int K; // 질의 대상이 되는 도시 쌍의 개수
	static final int ROOT = 1;
	static List<List<Edge>> graph; // 인접 리스트
	static int[] depth;
	static int[][] parent;
	static int[][] minWeight;
	static int[][] maxWeight;
	static int globalMinWeight;
	static int globalMaxWeight;
	static final StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = (int) Math.ceil(Math.log(N) / Math.log(2));
		graph = initGraph();
		depth = new int[N + 1];
		parent = new int[M + 1][N + 1];
		minWeight = new int[M + 1][N + 1];
		maxWeight = new int[M + 1][N + 1];
		
		// 연결 관계 저장 
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(node1).add(new Edge(node2, weight));
			graph.get(node2).add(new Edge(node1, weight));
		}
		
		// 각 정점의 깊이 및 부모 정점 계산
		calculateDepthAndParent();
		
		// 각 정점의 조상 정점 및 조상 정점까지의 경로 위의 최소/최대 가중치 계산
		calculateAncestorsAndMinMaxWeight();
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			globalMinWeight = (int) 1e6 + 1;
			globalMaxWeight = 0;
			
			// LCA와 각 정점을 연결하는 경로에서 최소 가중치, 최대 가중치 찾기
			findLCA(node1, node2);
			
			output.append(globalMinWeight).append(" ").append(globalMaxWeight).append("\n");
		}
		
		System.out.print(output);
	}
	
	static List<List<Edge>> initGraph() {
		List<List<Edge>> graph = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
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
			
			for (Edge edge : graph.get(currentNode)) {
				int nextNode = edge.dest;
				
				if (!visited[nextNode]) {
					queue.add(new int[] { nextNode, currentDepth + 1 });
					visited[nextNode] = true;
					depth[nextNode] = currentDepth + 1; 
					parent[0][nextNode] = currentNode;
					minWeight[0][nextNode] = edge.weight;
					maxWeight[0][nextNode] = edge.weight;
				}
			}
		}
	}
	
	static void calculateAncestorsAndMinMaxWeight() {
		for (int i = 1; i <= M; i++) {
			for (int j = 2; j <= N; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
				minWeight[i][j] = Math.min(minWeight[i - 1][j], minWeight[i - 1][parent[i - 1][j]]);
				maxWeight[i][j] = Math.max(maxWeight[i - 1][j], maxWeight[i - 1][parent[i - 1][j]]);
			}
		}
	}

    static int findMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    static int findMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
	
	static void findLCA(int node1, int node2) {
		int shallowerNode = depth[node1] < depth[node2] ? node1 : node2; 
		int deeperNode = depth[node1] < depth[node2] ? node2 : node1;
		
		int diff = depth[deeperNode] - depth[shallowerNode];
		
		// 깊은 정점을 얕은 정점과 동일한 깊이로 점프
		for (int i = M; i >= 0; i--) {
			if ((diff & (1 << i)) != 0) {
				globalMinWeight = Math.min(globalMinWeight, minWeight[i][deeperNode]);
				globalMaxWeight = Math.max(globalMaxWeight, maxWeight[i][deeperNode]);
				deeperNode = parent[i][deeperNode];
			}
		}
		
		// 깊이를 일치시켰더니 동일한 정점이 된 경우, 해당 정점이 LCA
		if (shallowerNode == deeperNode) return;
		
		// LCA를 찾을 때까지 점프
		for (int i = M; i >= 0; i--) {
			if (parent[i][shallowerNode] != parent[i][deeperNode]) { // 서로 다른 최상위의 두 정점까지 도달
                globalMinWeight = findMin(globalMinWeight, minWeight[i][shallowerNode], minWeight[i][deeperNode]);
				globalMaxWeight = findMax(globalMaxWeight, maxWeight[i][shallowerNode], maxWeight[i][deeperNode]);
				shallowerNode = parent[i][shallowerNode];
				deeperNode = parent[i][deeperNode];
			}
		}
		
        globalMinWeight = findMin(globalMinWeight, minWeight[0][shallowerNode], minWeight[0][deeperNode]);
        globalMaxWeight = findMax(globalMaxWeight, maxWeight[0][shallowerNode], maxWeight[0][deeperNode]);
	}
}