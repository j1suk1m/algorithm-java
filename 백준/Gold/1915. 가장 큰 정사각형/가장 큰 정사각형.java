import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 편의를 위해 패딩 추가 -> 1-based 인덱싱
		int[][] arr = new int[n + 1][m + 1]; 
		int[][] dp = new int[n + 1][m + 1]; // dp[i][j] == (i, j)를 오른쪽 아래 꼭짓점으로 하는, 모든 칸이 1인 정사각형의 최대 길이 
		
		// 배열 저장
		for (int i = 1; i <= n; i++) {
			String row = br.readLine();
			
			for (int j = 1; j <= m; j++) {
				arr[i][j] = row.charAt(j - 1) - '0'; // char -> int 변환
			}
		}
		
		// 초깃값 설정
		dp[1][1] = arr[1][1];
		
		// 다이나믹 프로그래밍
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (arr[i][j] == 0) {
                    continue;
                }
                
                // 왼쪽, 위, 왼쪽 대각선 위와 비교
				dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
			}
		}
		
		int max = 0;
		
		// 모든 칸이 1인 정사각형의 최대 길이 찾기
		for (int i = 1; i <= n ;i++) {
			for (int j = 1; j <= m; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		
		// 최대 넓이 출력
		bw.write(String.valueOf(max * max));
		bw.flush();
		
		br.close();
		bw.close();
	}
}