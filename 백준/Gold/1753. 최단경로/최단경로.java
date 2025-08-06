import java.util.*;
import java.io.*;

class Edge {
	int dest;
	int weight;
	
	Edge(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
	}
}

public class Main {
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static int root;
	static final List<List<Edge>> edges = new ArrayList<>(); // 인접 리스트
	static int[] distances;
	static final int INF = Integer.MAX_VALUE;
	static final StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		root = Integer.parseInt(br.readLine());
		
		initDistances();
		initEdges();
		
		// 연결 관계 저장
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges.get(src).add(new Edge(dest, weight));
		}
		
        // 다익스트라 수행
		dijkstra();
		makeOutput();
		
		bw.write(output.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void initDistances() {
		distances = new int[V + 1];
		Arrays.fill(distances, INF); // 무한으로 초기화
	}
	
	static void initEdges() {
		for (int i = 0; i <= V; i++) {
			edges.add(new ArrayList<>());
		}
	}
	
    // 다익스트라 알고리즘을 이용한 최단 경로 찾기
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.weight, e2.weight));
		
		distances[root] = 0;
		pq.add(new Edge(root, 0));
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if (distances[now.dest] < now.weight) continue; // 이미 확정된 경우
			
			for (Edge next : edges.get(now.dest)) {
				if (distances[next.dest] > distances[now.dest] + next.weight) { // 갱신할 수 있는 경우
					distances[next.dest] = distances[now.dest] + next.weight;
					pq.add(new Edge(next.dest, distances[next.dest]));
				}
			}
		}
	}
	
	static void makeOutput() {
		for (int node = 1; node <= V; node++) {
			output.append(distances[node] < INF ? distances[node] : "INF").append("\n");
		}
	}
}