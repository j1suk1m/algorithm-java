import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int stairCount = Integer.parseInt(br.readLine()); // 계단의 개수
		
		int[] stairs = new int[stairCount];
		int[] dp = new int[stairCount]; // dp[n] == n번째 계단까지 밟았을 때 얻을 수 있는 최대 점수
		
		// 계단의 점수 저장
		for (int i = 0; i < stairCount; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		if (stairCount == 1) {
			bw.write(String.valueOf(stairs[0]));
		} else if (stairCount == 2) {
			bw.write(String.valueOf(stairs[0] + stairs[1]));
		} else if (stairCount == 3) {
			bw.write(String.valueOf(Math.max(stairs[0], stairs[1]) + stairs[2]));
		} else {
			// 초깃값 설정
			dp[0] = stairs[0];
			dp[1] = stairs[0] + stairs[1];
			dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];
			
			// 다이나믹 프로그래밍을 이용한 최대 점수 계산
			// 후보 1: 전전전 계단, 직전 계단, 현재 계단을 밟은 경우
			// 후보 2: 전전 계단, 현재 계단을 밟은 경우
			for (int i = 3; i < stairCount; i++) {
				dp[i] = Math.max(dp[i - 2], stairs[i - 1] + dp[i - 3]) + stairs[i];
			}
			
			bw.write(String.valueOf(dp[stairCount - 1]));	
		}
		
		bw.flush();
		
		br.close();
		bw.close();
	}
}