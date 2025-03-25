import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스의 개수를 저장한다.
        int T = Integer.parseInt(reader.readLine());
        
        // T개의 테스트 케이스를 실행한다.
        while (T-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            
            // 편의를 위해 패딩을 넣어 이차원 배열을 정의한다.
            int[][] dp = new int[M + 1][N + 1];
            
            // M개 중에 순서 없이 N개를 뽑는 조합의 개수를 계산한다.
            calculateCombinationCount(dp, M, N);
            System.out.println(dp[M][N]);
        }
    }
    
    // 다이나믹 프로그래밍을 이용해 조합의 개수를 계산한다.
    private static void calculateCombinationCount(int[][] dp, int M, int N) {
        for (int m = 1; m <= M; m++) {
            for (int n = 1; n <= N; n++) {
                
                if (m == n) { // mCm = 1이다.
                    dp[m][n] = 1;
                    continue;
                } else if (n == 1) { // mC1 = m이다.
                    dp[m][n] = m;
                    continue;
                }
                
                // mCn = (m-1)C(n-1) + (m-1)Cn이 성립한다.
                dp[m][n] = dp[m - 1][n - 1] + dp[m - 1][n];
            }
        }
    }
}