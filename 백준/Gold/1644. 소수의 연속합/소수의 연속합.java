import java.util.*;
import java.io.*;

public class Main {
	static int[] nums;
	static final List<Integer> primes = new ArrayList<>();
	static final int REMOVED = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
        
        if (N == 1) {
            System.out.println(0);
            return;
        }
		
		nums = new int[N + 1];
		
		initNums();
		initPrimes(N);
		
		int left = 0;
		int right = 0;
		long sum = primes.get(left);
		int answer = 0;
		
		// 투 포인터를 이용한 연속된 소수의 합 계산
		while (left < primes.size()) {
			if (sum == N) {
				answer++;
				sum -= primes.get(left);
				left++;
			} else if (sum > N) {
				sum -= primes.get(left);
				left++;
			} else {
				right++;
				
				if (right >= primes.size()) break;
				
				sum += primes.get(right);
			}
		}
		
		System.out.println(answer);
	}
	
	static void initNums() {
		for (int i = 2; i < nums.length; i++) {
			nums[i] = i;
		}
	}
	
	static void initPrimes(int N) {
		for (int i = 2; i * i <= N; i++) {
			if (nums[i] == REMOVED) continue;
			
			for (int j = i * i; j <= N; j += i) {
				nums[j] = REMOVED;
			}
		}
        
        for (int i = 2; i <= N; i++) {
            if (nums[i] == REMOVED) continue;
            primes.add(i);
        }
	}
}