import java.util.*;
import java.io.*;

public class Main {
	static int[] nums;
	static long[][] dp; // dp[index][currentResult] = count 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); // 숫자의 개수
		
		nums = new int[N];
		dp = new long[N - 1][21];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
        // 0번째 숫자까지 연산을 마쳤을 때, 연산 결과가 0번째 숫자의 값과 같은 경우의 수
		dp[0][nums[0]] = 1;

		for (int i = 1; i < N - 1; i++) { // 등호 기준 우변에 존재하는 마지막 숫자 제외
			for (int currentResult = 0; currentResult <= 20; currentResult++) {
				int num = nums[i];
				
                // 현재 숫자를 더하는 경우
				if (0 <= currentResult - num && currentResult - num <= 20) {
					dp[i][currentResult] += dp[i - 1][currentResult - num];
				}
				
                // 현재 숫자를 빼는 경우
				if (0 <= currentResult + num && currentResult + num <= 20) {
					dp[i][currentResult] += dp[i - 1][currentResult + num];
				}
			}
		}
		
        // N - 2번째 숫자까지 연산을 마쳤을 때, 연산 결과가 N - 1번째 숫자의 값과 같은 경우의 수
        // 즉, 등식을 만족하는 경우의 수
		System.out.println(dp[N - 2][nums[N - 1]]);
	}
}