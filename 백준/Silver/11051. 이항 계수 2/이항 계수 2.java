import java.util.*;
import java.io.*;

public class Main {
	static final int MOD = (int) 1e4 + 7;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][K + 1];
		
		for (int n = 0; n <= N; n++) {
			for (int r = 0; r <= Math.min(n,  K); r++) {
				if (r == 0 || r == n) {
					dp[n][r] = 1;
				} else {
					dp[n][r] = ((dp[n - 1][r - 1]) % MOD + (dp[n - 1][r] % MOD)) % MOD;
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}	
}