import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 수열의 길이
		int M = Integer.parseInt(st.nextToken()); // 타겟
		
		int[] A = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		// 수열 입력
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		int answer = 0; // 수들의 합으로 M을 만들 수 있는 경우의 수
		
		while (right < N) {
			int sum = calculateSum(A, left, right); // A[left]부터 A[right]까지의 합 계산
			
			if (sum == M) {
				answer++;
				right++;
			} else if (sum < M) { // M을 만들기 위해 전체 합을 늘려야 하므로 right를 증가시킴
				right++;
			} else { // M을 만들기 위해 전체 합을 줄여야 하므로 left를 증가시킴
				left++;
			}
		}
		
		System.out.println(answer);
	}
	
	// nums[from]부터 nums[to]까지의 합 계산
	private static int calculateSum(int[] nums, int from, int to) {
		int sum = 0;
		
		for (int i = from; i <= to; i++) {
			sum += nums[i];
		}
		
		return sum;
	}
}