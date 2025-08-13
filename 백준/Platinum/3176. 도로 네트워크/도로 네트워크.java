import java.util.*;
import java.io.*;

class Edge {
	int dest;
	int cost;

	Edge(int dest, int cost) {
		this.dest = dest;
		this.cost = cost;
	}
}

class Main {
	static int N; // 도시의 개수, [2, 1e5]
	static int K; // 질의 횟수, [1, 1e5]
	static int M; // 이진 점프 크기
	static int[][] parent;
	static int[] depth;
	static int[][] minCost;
	static int[][] maxCost;
	static int globalMinCost;
	static int globalMaxCost;
	static List<List<Edge>> edges = new ArrayList<>(); // 인접 리스트
	static final int ROOT = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine()); // 도시의 개수

		// 인접 리스트 초기화
		for (int i = 0; i < N + 1; i++) {
			edges.add(new ArrayList<>());
		}

		M = (int) Math.ceil(Math.log(N) / Math.log(2));
		parent = new int[M + 1][N + 1];
		depth = new int[N + 1];
		minCost = new int[M + 1][N + 1];
		maxCost = new int[M + 1][N + 1];

		// 도로 입력
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges.get(v1).add(new Edge(v2, cost));
			edges.get(v2).add(new Edge(v1, cost));
		}

		// 도시 별 깊이 저장 및 부모 도시 저장
		runBfs();

		// 최소 비용 및 최대 비용 계산
		runDp();

		K = Integer.parseInt(br.readLine()); // 질의 횟수

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int D = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			globalMinCost = Integer.MAX_VALUE;
			globalMaxCost = 0;

			findLCA(D, E);

			sb.append(globalMinCost).append(" ").append(globalMaxCost).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void runBfs() {
		Deque<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		visited[ROOT] = true;
		queue.add(new int[] {ROOT, 0});

		while (!queue.isEmpty()) {
			int[] element = queue.poll();
			int now = element[0];
			int nowDepth = element[1];

			for (Edge next : edges.get(now)) {
				if (!visited[next.dest]) {
					visited[next.dest] = true;
					queue.add(new int[] {next.dest, nowDepth + 1});
					depth[next.dest] = nowDepth + 1;
					parent[0][next.dest] = now;
					minCost[0][next.dest] = next.cost;
					maxCost[0][next.dest] = next.cost;
				}
			}
		}
	}

	static void runDp() {
		for (int i = 1; i <= M; i++) {
			for (int j = 2; j <= N; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
				minCost[i][j] = Math.min(minCost[i - 1][j], minCost[i - 1][parent[i - 1][j]]);
				maxCost[i][j] = Math.max(maxCost[i - 1][j], maxCost[i - 1][parent[i - 1][j]]);
			}
		}
	}

	static void findLCA(int v1, int v2) {
		int shallower = depth[v1] < depth[v2] ? v1 : v2;
		int deeper = depth[v2] > depth[v1] ? v2 : v1;

		int diff = depth[deeper] - depth[shallower];

		// 더 깊은 정점을 끌어 올려서 깊이 맞추기
		for (int i = M; i >= 0; i--) {
			if ((diff & (1 << i)) != 0) {
				globalMinCost = Math.min(globalMinCost, minCost[i][deeper]);
				globalMaxCost = Math.max(globalMaxCost, maxCost[i][deeper]);
				deeper = parent[i][deeper];
			}
		}

		// 깊이를 맞췄더니 같아진 경우 -> LCA 발견
		if (shallower == deeper) return;

		// LCA를 찾을 때까지 이진 점프
		for (int i = M; i >= 0; i--) {
			if (parent[i][deeper] != parent[i][shallower]) {
				globalMinCost = Math.min(globalMinCost, Math.min(minCost[i][shallower], minCost[i][deeper]));
				globalMaxCost = Math.max(globalMaxCost, Math.max(maxCost[i][shallower], maxCost[i][deeper]));
				shallower = parent[i][shallower];
				deeper = parent[i][deeper];
			}
		}

		globalMinCost = Math.min(globalMinCost, Math.min(minCost[0][shallower], minCost[0][deeper]));
		globalMaxCost = Math.max(globalMaxCost, Math.max(maxCost[0][shallower], maxCost[0][deeper]));
	}
}