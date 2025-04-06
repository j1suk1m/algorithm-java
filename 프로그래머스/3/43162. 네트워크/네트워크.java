import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] visited; // 방문 여부를 저장한다.
    private final List<List<Integer>> graph = new ArrayList<>(); // 연결된 컴퓨터 번호를 저장한다.
    
    public int solution(int n, int[][] computers) {
        int answer = 0;

        // visited, graph를 초기화한다.
        init(n);
        
        // 연결 여부가 담긴 computers를 순회하면서 graph에 연결 정보를 저장한다. 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                // 서로 다른 컴퓨터 i와 j가 연결된 경우 양방향으로 연결 정보를 저장한다.
                if (i != j && computers[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        // 모든 컴퓨터들을 순회하며 아직 방문하지 않은 컴퓨터에 대해 dfs를 수행한다.
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer += dfs(i);
            }
        }
        
        return answer;
    }
    
    // visited, graph를 초기화한다.
    private void init(int n) {
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }
    
    // dfs를 통해 하나의 네트워크로 연결된 컴퓨터들을 방문한다.
    private int dfs(int node) {
        visited[node] = true; // 방문 처리한다.
        
        // 연결된 컴퓨터 중에서 아직 방문하지 않은 것을 방문한다.
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
        
        return 1; // 한 번의 dfs로 탐색한 모든 컴퓨터들은 하나의 네트워크에 있다.
    }
}