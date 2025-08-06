import java.util.*;
import java.io.*;

class Edge {
	int src;
	int dest;
	long cost;
	
	Edge(int src, int dest, long cost) {
		this.src = src;
		this.dest = dest;
		this.cost = cost;
	}
}

public class Main {
	static int N; // 도시의 개수
	static int M; // 버스 노선의 개수
	static final List<Edge> edges = new ArrayList<>(); // 간선 리스트
	static long[] costs;
	static final long INF = Long.MAX_VALUE; // 언더플로우, 오버플로우 방지
	static final boolean HAS_NEGATIVE_CYCLE = true; 
	static final StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		costs = new long[N + 1];
		Arrays.fill(costs, INF);
		
		// 연결 관계 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());
			
			edges.add(new Edge(src, dest, cost));
		}
		
        // 벨만 포드 알고리즘을 이용한 최소 비용 계산
		boolean hasNegativeCycle = bellmanFord();
		
		if (hasNegativeCycle) {
			output.append(-1);
		} else {
			for (int i = 2; i <= N; i++) {
				output.append(costs[i] < INF ? costs[i] : -1).append("\n");
			}
		}
		
		bw.write(output.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static boolean bellmanFord() {
		int now = 1;
		costs[now] = 0L;
		
		for (int i = 0; i < N - 1; i++) {
			for (Edge edge : edges) {
				if (costs[edge.src] != INF && costs[edge.dest] > costs[edge.src] + edge.cost) {
					costs[edge.dest] = costs[edge.src] + edge.cost;
				}
			}
		}
		
		// 최대 간선 수를 사용했는데도 비용이 갱신된다면 음의 사이클이 존재한다는 의미
		for (Edge edge : edges) {
			if (costs[edge.src] != INF && costs[edge.dest] > costs[edge.src] + edge.cost) {
				return HAS_NEGATIVE_CYCLE;
			}
		}
		
		return !HAS_NEGATIVE_CYCLE;
	}
}