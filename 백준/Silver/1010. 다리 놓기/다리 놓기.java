import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[M + 1][N + 1];
			
            // 다이나믹 프로그래밍
			for (int n = 0; n <= M; n++) {
				for (int r = 0; r <= Math.min(n,  N); r++) {
					if (r == 0 || r == n) {
						dp[n][r] = 1;
					} else {
						dp[n][r] = dp[n - 1][r - 1] + dp[n - 1][r];
					}
				}
			}
			
			answer.append(dp[M][N]).append("\n");
		}
		
		System.out.println(answer);
	}
}