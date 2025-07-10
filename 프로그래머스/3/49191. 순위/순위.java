class Solution {
    private static final int WIN = 1;
    private static final int LOSE = -1;
    private int[][] graph;
    
    public int solution(int n, int[][] results) {
        initGraph(n); // 그래프 초기화 
        recordMatchResults(results); // 그래프에 승패 관계 저장
        propagateMatchResults(n); // 플로이드 워셜을 이용한 간접 관계 전파
        
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int known = 0;
            
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == WIN || graph[i][j] == LOSE) { // 승패 관계가 결정된 경우
                    known++;
                }
            }
            
            if (known == n - 1) { // 자신을 제외한 모든 선수와의 승패 관계를 알고 있음 -> 순위 결정 가능
                answer++;
            }
        }
        
        return answer;
    }
    
    private void initGraph(int n) {
        graph = new int[n + 1][n + 1]; // 선수 번호가 1부터 시작 -> 인덱스 편의를 위해 1칸 패딩
    }
    
    // 승패 관계 저장
    private void recordMatchResults(int[][] results) {
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            
            // graph[x][y]: x 선수가 y 선수와 경기를 진행한 결과
            graph[winner][loser] = WIN;
            graph[loser][winner] = LOSE;
        }
    }
    
    // 플로이드 워셜을 이용한 간접 관계 전파
    // ex: x 선수가 y 선수를 이기고, y 선수가 z 선수를 이기면 x 선수가 z 선수를 이김
    private void propagateMatchResults(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] == WIN && graph[k][j] == WIN) {
                        graph[i][j] = WIN;
                        graph[j][i] = LOSE;
                    } else if (graph[i][k] == LOSE && graph[k][j] == LOSE) {
                        graph[i][j] = LOSE;
                        graph[j][i] = WIN;
                    }
                }
            }
        }
    }
}