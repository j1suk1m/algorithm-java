import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] visited;
    private final List<List<Integer>> graph = new ArrayList<>();
    
    public int solution(int n, int[][] computers) {
        // 1. 방문 배열 및 그래프 초기화
        init(n);
        
        // 2. 연결 관계 저장
        link(n, computers);
        
        // 3. DFS를 이용한 네트워크 개수 계산 후 반환
        return calculateNetworkCount(n);
    }
    
    // 방문 배열 및 그래프 초기화
    private void init(int n) {
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }
    
    // 연결 관계 저장
    // 방향이 없으므로 양방향으로 저장
    private void link(int n, int[][] computers) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) { // 서로 다른 컴퓨터 두 대가 서로 연결
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
    }
    
    // 네트워크 개수 계산
    private int calculateNetworkCount(int n) {
        int networkCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                networkCount += dfs(i);
            }
        }
        
        return networkCount;
    }
    
    // 재귀 탐색
    private int dfs(int current) {
        visited[current] = true; // 방문 처리
        
        for (int next : graph.get(current)) { // 연결된 컴퓨터 중 방문하지 않은 컴퓨터 탐색
            if (!visited[next]) {
                dfs(next);
            }
        }
        
        return 1; // 재귀적으로 탐색한 경로에 있는 모든 컴퓨터는 하나의 네트워크에 속함
    }
}