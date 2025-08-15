import java.util.*;

class Solution {
    final int INF = 120;
    
    public int solution(int[][] info, int n, int m) {
        int answer = INF;
        
        if (info.length == 1) {
            return info[0][1] < m ? 0 : info[0][0];
        }
        
        // dp[물건 인덱스][B의 누적 개수] = A의 최소 개수
        int[][] dp = new int[info.length][m];
        
        for (int i = 0; i < info.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        // 0번째 물건을 A가 훔친 경우
        dp[0][0] = info[0][0];
        
        // 0번째 물건을 B가 훔친 경우
        if (info[0][1] < m) dp[0][info[0][1]] = 0;
        
        // 다이나믹 프로그래밍
        for (int i = 1; i < info.length; i++) {
            for (int j = 0; j < m; j++) {
                
                // i번째 물건을 A가 훔친 경우 -> B의 누적 개수는 변하지 않음
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + info[i][0]);
                
                // i번째 물건을 B가 훔친 경우 -> A의 최소 개수는 변하지 않음
                if (j + info[i][1] < m) {
                    dp[i][j + info[i][1]] = Math.min(dp[i][j + info[i][1]], dp[i - 1][j]);
                }
            }
        }
        
        // A의 최소 개수 구하기
        for (int j = 0; j < m; j++) {
            answer = Math.min(answer, dp[info.length - 1][j]);
        }
        
        return answer < n ? answer : -1;
    }
}