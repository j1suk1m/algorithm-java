import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 수열의 길이
		int M = Integer.parseInt(st.nextToken()); // 부분 수열의 합으로 만들고자 하는 타겟
		
		int[] A = new int[N]; // 수열
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) { // 수열 입력
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		int sum = A[0]; // 부분 수열의 합 // A[left] + ... + A[right]
		int answer = 0; // 부분 수열의 합이 M이 되는 경우의 수
		
		while (left < N) { // 투 포인터 알고리즘 수행
			if (sum == M) { // 부분 수열의 합으로 M을 만들 수 있는 경우
				answer++;
				sum -= A[left];
				left++;
			} else if (sum < M) { // 부분 수열의 합이 M보다 작은 경우 -> 부분 수열을 늘림
				right++;
				
				if (right >= N) break;
				
				sum += A[right];
			} else { // 부분 수열의 합이 M보다 큰 경우 -> 부분 수열을 줄임
				sum -= A[left];
				left++;
			}
		}
		
		System.out.println(answer);
	}
}