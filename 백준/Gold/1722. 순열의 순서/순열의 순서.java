import java.util.*;
import java.io.*;

public class Main {
	static final int QUERY_PERMUTATION = 1;
	static final int QUERY_ORDER = 2;
	static final List<Integer> availableNums = new ArrayList<>();
	static final List<Integer> permutation = new ArrayList<>();
	static long[] factorials;
	static final StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		factorials = new long[N];
        factorials[0] = 1;
		st = new StringTokenizer(br.readLine());
		
		int query = Integer.parseInt(st.nextToken());

		initAvailableNums(N);
		initFactorials(N - 1); // 1! ~ (N - 1)! 계산
		
		if (query == QUERY_PERMUTATION) {
			long k = Long.parseLong(st.nextToken()) - 1; // 0-based로 변환
		
			queryPermutationAt(k); // k번째의 순열 조회
			
			System.out.println(answer);
		} else {
			initPermutation(N, st); // 순서를 조회할 순열 입력

			System.out.println(queryOrder(N, 0) + 1); // 순열의 순서 조회 후 1-based로 변환
		}
	}
	
	static void initPermutation(int N, StringTokenizer st) {
		for (int i = 0; i < N; i++) {
			permutation.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	static void initAvailableNums(int N) {
		for (int i = 1; i <= N; i++) {
			availableNums.add(i);
		}		
	}
	
	static long initFactorials(int num) {
		if (num <= 1) {
			factorials[num] = 1;
		}
		
		if (factorials[num] != 0) {
			return factorials[num];
		}
		
		return factorials[num] = num * 1L * initFactorials(num - 1); // n! = n * (n - 1)!
	}
	
	static void queryPermutationAt(long k) {
		if (availableNums.size() == 1) {
			answer.append(availableNums.get(0));
			return;
		}
		
		long factorial = factorials[availableNums.size() - 1];
		int selectedIndex = (int) (k / factorial);
		int selectedNum = availableNums.remove(selectedIndex);
		
		answer.append(selectedNum).append(" ");
		
		queryPermutationAt(k % factorial);
	}
	
	static long queryOrder(int N, int permutationIndex) {
		if (permutationIndex == N - 1) {
			return 0;
		}
		
		int index = availableNums.indexOf(permutation.get(permutationIndex));
		long factorial = factorials[N - permutationIndex - 1];
		
		availableNums.remove(index);
		
		return index * factorial + queryOrder(N, permutationIndex + 1);
	}
}