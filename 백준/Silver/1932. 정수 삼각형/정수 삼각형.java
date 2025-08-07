import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine()); // 삼각형의 크기
		
		int[][] triangle = new int[n][n]; // 정수 삼각형
		int[][] dp = new int[n][n];
		
		// 정수 삼각형 저장
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j <= i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (n == 1) {
			bw.write(String.valueOf(triangle[0][0]));
		} else {
			// 초깃값 설정
			dp[0][0] = triangle[0][0];
			
			// 다이나믹 프로그래밍
			for (int i = 1; i < n; i++) {
				dp[i][0] = dp[i - 1][0] + triangle[i][0];
				
				for (int j = 1; j < i; j++) {
					dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
				}
				
				dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
			}
			
			int max = 0;
			
			// 맨 마지막 층의 최댓값 계산
			for (int j = 0; j < n; j++) {
				max = Math.max(max, dp[n - 1][j]);
			}
			
			bw.write(String.valueOf(max));
		}
		
		bw.flush();
		
		br.close();
		bw.close();
	}
}