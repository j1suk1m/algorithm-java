import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {
	static final List<Integer> selectedNums = new ArrayList<>();
	static final StringBuilder answer = new StringBuilder();
	static int[] nums;
	static boolean[] selected;
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		selected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 수열을 사전 순으로 출력하기 위한 오름차순 정렬
		Arrays.sort(nums);
		
		backtrack();
		
		System.out.println(answer);
	}
	
	static void backtrack() {
		if (selectedNums.size() == M) { // 숫자 M개를 선택한 경우
			answer.append(selectedNums.stream()
					.map(String::valueOf)
					.collect(Collectors.joining(" ")))
			      .append("\n");
			return;
		}
		
		int previousNum = 0; // 중복 수열 방지
		
		for (int i = 0; i < N; i++) {
			if (!selected[i] && nums[i] != previousNum) {
				previousNum = nums[i];
				selected[i] = true;
				selectedNums.add(nums[i]);
				backtrack();
				selectedNums.remove(selectedNums.size() - 1);
				selected[i] = false;
			}
		}
	}
}