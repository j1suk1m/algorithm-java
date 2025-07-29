import java.util.*;
import java.io.*;

public class Main {
	static int[] treeHeights; // 나무 높이 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int treeCount = Integer.parseInt(st.nextToken()); // 나무의 수
		long targetHeight = Long.parseLong(st.nextToken()); // 집으로 가져가려고 하는 나무의 길이
		
		treeHeights = new int[treeCount];
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < treeCount; i++) { // 나무 높이 배열 저장
			treeHeights[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(treeHeights); // 매개변수 탐색을 위한 오름차순 정렬
		
		System.out.println(runParametricSearch(targetHeight));
	}
	
	// 매개변수 탐색을 이용한 최적의 절단기 높이 계산
	static int runParametricSearch(long targetHeight) {
		int left = 0; // check(left) == true // 나무를 targetHeight만큼 무조건 가져갈 수 있음
		int right = treeHeights[treeHeights.length - 1]; // check(right) == false // 나무를 하나도 가져가지 못함
		
		while (left + 1 < right) {
			int mid = left - (left - right) / 2; // (left + right) / 2와 동일
			
			if (check(mid, targetHeight)) { // 절단기 높이가 mid일 때, 적어도 targetHeight만큼 나무를 가져갈 수 있음
				left = mid; // 절단기 높이를 더 높여도 targetHeight만큼 가져갈 수 있는지 탐색
			} else { // 절단기 높이가 mid일 때, targetHeight만큼 나무를 가져가지 못함
				right = mid; // 절단기 높이를 낮춰서 나무를 더 많이 잘라봄
			}
		}
		
		return left;
	}
	
	// 절단기 높이가 mid일 때, 적어도 targetHeight만큼 나무를 가져갈 수 있는지 확인
	static boolean check(int mid, long targetHeight) {
		long sum = 0L; // 나무의 수가 최대 1e6, 나무의 높이가 최대 1e9이므로 오버플로 방지를 위해 long 사용
		
		for (int i = treeHeights.length - 1; i >= 0; i--) { // 절단기로 자른 나무 길이의 합 계산
			int treeHeight = treeHeights[i];
			
			if (treeHeight <= mid) break;
			
			sum += (long) (treeHeights[i] - mid);
		}
		
		return sum >= targetHeight;
	}
}