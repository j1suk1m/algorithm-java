import java.util.*;
import java.io.*;

public class Main {
	static long[][] dp = new long[201][201]; // dp[i][j] = k -> 길이가 i이고 "z"가 j개인 문자열이 k개
	static final StringBuilder answer = new StringBuilder();
	static final int MAX = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // "a"의 개수
		int M = Integer.parseInt(st.nextToken()); // "z"의 개수
		int K = Integer.parseInt(st.nextToken()); // 찾으려는 문자열의 순서
        
		// 다이나믹 프로그래밍
		for (int i = 0; i <= 200; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
            
			for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
			
				if (dp[i][j] > MAX) {
					dp[i][j] = MAX + 1;
				}
			}
		}
		
		if (K > dp[N + M][M]) {
			System.out.println(-1);
		} else {
			findKthString(N, M, K);
			System.out.println(answer);
		}
	}
	
	// N: 남은 "a"의 개수
	// M: 남은 "z"의 개수
	// K: 남은 순서
	static void findKthString(int N, int M, int K) {
		if (N == 0) {
			answer.append("z".repeat(M));
			return;
		}
		
		if (M == 0) {
			answer.append("a".repeat(N));
			return;
		}

        // "a"로 시작하는 문자열의 개수
		long rank = dp[N + M - 1][M];
		
		if (rank >= K) { // 찾으려는 문자열이 "a"로 시작하는 경우
			answer.append("a");
			findKthString(N - 1, M, K);
		} else { // 찾으려는 문자열이 "z"로 시작하는 경우
			answer.append("z");
			findKthString(N, M - 1, K - (int) rank);
		}
	}
}