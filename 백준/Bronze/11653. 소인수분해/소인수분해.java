import java.util.*;
import java.io.*;

public class Main {
	static final List<Integer> primes = new ArrayList<>();
	static int[] nums;
	static final int REMOVED = -1;
	static final StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 소인수분해할 정수
		
		if (N == 1) {
			System.out.println();
		} else {
			nums = new int[N + 1];
			
			initNums(N);
			initPrimes(N);
			calculatePrimeFactors(N);
			
			System.out.println(answer);
		}
	}
	
	static void initNums(int N) {
		for (int i = 2; i <= N; i++) {
			nums[i] = i;
		}
	}
	
	static void initPrimes(int N) {
		for (int i = 2; i <= N; i++) {
			if (nums[i] == REMOVED) continue;
			primes.add(i);
			
			for (int j = i; j <= N; j += i) {
				if (nums[j] == REMOVED) continue;
				nums[j] = REMOVED;
			}
		}
	}
	
	static void calculatePrimeFactors(int N) {
		for (int prime : primes) {
			if (N == 1) break;
			
			while (N % prime == 0) {
				N /= prime;
				answer.append(prime)
				      .append("\n");
			}
		}
	}
}