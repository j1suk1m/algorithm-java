class Solution {
    private static boolean[] visited;
    
    private static int dfs(int computer, int n, int[][] computers) {
        visited[computer] = true;
        
        for (int next = 0; next < n; next++) {
            if (next == computer) {
                continue;
            } else if (visited[next] == true) {
                continue;
            } else if (computers[computer][next] == 1) {
                dfs(next, n, computers);
            }
        }
        
        return 1;
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for (int computer = 0; computer < n; computer++) {
            if (visited[computer] == false) {
                answer += dfs(computer, n, computers);
            }
        }
        
        return answer;
    }
}