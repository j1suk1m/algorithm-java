import java.util.Arrays;

class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private static final int PUDDLE = MOD + 1;
    private int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {  
        
        // 배열을 초기화한다.
        init(n, m, puddles);
        
        // 오른쪽 아래 방향으로 순회하며 최단 경로의 개수를 갱신한다.
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= m; y++) {
                int current = dp[x][y];
                
                // 집인 경우 1을 저장한다.
                if (x == 1 && y == 1) {
                    dp[x][y] = 1;
                    continue;
                }
                
                // 물에 잠긴 지역인 경우 0을 저장한다.
                if (current == PUDDLE) {
                    dp[x][y] = 0;
                    continue;
                } 
                
                // 이외의 경우 윗쪽 칸과 왼쪽 칸의 합이 최단 경로의 개수가 된다.
                dp[x][y] = (dp[x - 1][y] + dp[x][y - 1]) % MOD;
    
            }
        }
        
        // 학교까지 갈 수 있는 최단 경로의 개수를 반환한다.
        return dp[n][m];
    }
    
    // 배열을 초기화한다.
    public void init(int n, int m, int[][] puddles) {
        dp = new int[n + 1][m + 1];
        
        // 0으로 채운다.
        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }
        
        // 물에 잠긴 칸에 PUDDLE을 저장한다.
        // 이 시점에 0을 저장하지 않는 이유는, 단순히 초기화된 것인지 물에 잠긴 것인지 구분하기 위함이다.
        for (int[] puddle : puddles) {
            int puddleX = puddle[1];
            int puddleY = puddle[0];
            dp[puddleX][puddleY] = PUDDLE;
        }
    }
}