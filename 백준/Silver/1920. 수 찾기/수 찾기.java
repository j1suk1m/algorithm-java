import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		// 정수 개수 입력
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 정수 배열 입력
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이분 탐색을 위한 오름차순 정렬
		Arrays.sort(A);
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			
			if (Arrays.binarySearch(A, target) >= 0) { // 정수 배열에 해당 숫자가 존재하는 경우
				answer.append("1\n");
			} else { // 정수 배열에 해당 숫자가 존재하지 않는 경우
				answer.append("0\n");
			}
		}
		
		System.out.println(answer);
	}
}