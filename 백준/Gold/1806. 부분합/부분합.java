import java.util.*;
import java.io.*;

public class Main {
	static final int INF = (int) ((1e5) + 1);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 수열의 길이
		int S = Integer.parseInt(st.nextToken()); // 부분 수열의 합으로 만들고자 하는 타겟
		
		int[] sequence = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) { // 수열 저장
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		
		// 초깃값 설정
		int left = 0;
		int right = 0;
		int sum = sequence[0]; // 부분 수열의 합 
		int answer = INF; // 합이 S 이상이 되는, 가장 짧은 부분 수열의 길이
		int length = 1; // 부분 수열의 길이 // right - left + 1
				
		while (left < N) { // 투 포인터 알고리즘
			if (sum >= S) {
				answer = Math.min(answer, length);
				sum -= sequence[left];
				left++;
				length--;
			} else {
				right++;
				
				if (right >= N) break;
				
				sum += sequence[right];
				length++;
			}
		}
		
		System.out.println(answer == INF ? 0 : answer);
	}
}