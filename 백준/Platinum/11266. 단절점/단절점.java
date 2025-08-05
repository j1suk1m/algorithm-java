import java.util.*;
import java.io.*;

public class Main {
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static final List<List<Integer>> graph = new ArrayList<>(); // 인접 리스트
	static boolean[] isArticulationPoint; // 단절점 여부
	static int[] visitedTime; // 정점 방문 시간
	static final int NONE = 0;
	static final boolean IS_ROOT = true;
	static final boolean IS_NOT_ROOT = false;
	static int order = 0; // 방문 순서
	static int articulationPointCount = 0; // 단절점의 개수
	static final StringBuilder articulationPointNumbers = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		isArticulationPoint = new boolean[V + 1]; // 단절점 여부
		visitedTime = new int[V + 1]; // 정점 방문 시간
		
		initGraph();
		
		// 두 정점의 양방향 연결 관계 저장
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		
		// 깊이 우선 탐색을 이용한 단절점 찾기
		for (int node = 1; node <= V; node++) {
			if (visitedTime[node] == NONE) { // 방문 필요
				dfs(node, IS_ROOT);
			}
		}
		
		// 단절점의 개수 및 번호 찾기
		for (int node = 1; node <= V; node++) {
			if (isArticulationPoint[node]) {
				articulationPointCount++;
				articulationPointNumbers.append(node).append(" ");
			}
		}
		
		System.out.println(articulationPointCount);
		System.out.println(articulationPointNumbers);
	}
	
	static void initGraph() {
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
	}
	
	static int dfs(int node, boolean isRoot) {
        order++;
        
		visitedTime[node] = order;
		int minVisitedTimeOfNext = order; // 인접한 노드의 방문 시간 중 최솟값
		int childrenCount = 0;
		
		for (int next : graph.get(node)) {
			if (visitedTime[next] == NONE) { // 방문 필요
                int low = dfs(next, IS_NOT_ROOT);
				minVisitedTimeOfNext = Math.min(minVisitedTimeOfNext, low);
				childrenCount++;

                // 단절점 조건 1: 자식 정점이 현재 정점의 조상 정점으로 갈 수 없는 경우
                if (!isRoot && visitedTime[node] <= low) {
        			isArticulationPoint[node] = true;
        		} 
			} else { // 방문 불필요
				minVisitedTimeOfNext = Math.min(minVisitedTimeOfNext, visitedTime[next]);
			}
		}

        // 단절점 조건 2: 루트 정점의 자식 정점 개수가 2개 이상인 경우
		if (isRoot && childrenCount >= 2) {
			isArticulationPoint[node] = true;
		}
		
		return minVisitedTimeOfNext;
	}
}