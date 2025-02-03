import java.util.ArrayList;
import java.util.List;

class Solution {
    private final List<List<Integer>> graph = new ArrayList<>(); // 연결 그래프
    private boolean[] visited; // 방문 여부
    
    public int solution(int n, int[][] wires) {
        int answer = n;

        // 그래프 초기화
        init(n);
        
        // 연결 관계 저장
        connect(wires);

        for(int[] wire : wires) {
            int node1 = wire[0];
            int node2 = wire[1];
                      
            // 연결 끊기
            graph.get(node1).remove((Integer) node2);
            graph.get(node2).remove((Integer) node1);
            
            // 두 전력망이 갖는 송전탑의 개수 차이 계산
            int result = calculatePartitionDifference(n);
            
            // 정답 갱신
            answer = Math.min(answer, result);
            
            // 재연결
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        return answer;
    }
    
    private void init(int size) {
        for (int i = 0; i < size + 1; i++) {
            graph.add(new ArrayList<>());
        }
    }
    
    private void connect(int[][] wires) {
        for (int[] wire : wires) {
            int node1 = wire[0];
            int node2 = wire[1];
            
            // 양방향 연결
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
    }
    
    private int dfs(int current) {
        int count = 1;
        visited[current] = true;
        
        for (int next : graph.get(current)) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }
        
        return count; // 현재 전력망에 속하는 송전탑의 개수
    }
    
    // 두 전력망이 갖는 송전탑의 개수 차이 계산
    private int calculatePartitionDifference(int n) {
        int result = 0;
        visited = new boolean[n + 1]; // 방문 여부 초기화
        
        for (int node = 1; node < n + 1; node++) {
            if (!visited[node]) {
                result = Math.abs(result - dfs(node));
            }
        }
        
        return result;
    }
}