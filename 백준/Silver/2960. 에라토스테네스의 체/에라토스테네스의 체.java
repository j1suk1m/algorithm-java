import java.util.*;
import java.io.*;

public class Main {
	static final int REMOVED = -1;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		
		init();
		
        // 숫자 지우기
		loop: for (int i = 2; i <= N; i++) {
			if (nums[i] == REMOVED) continue;
			
			nums[i] = REMOVED;
			K--;
						
			if (K == 0) {
				System.out.println(i);
				break;
			}
			
			for (int j = i; j <= N; j += i) {
				if (nums[j] == REMOVED) continue;
				
				nums[j] = REMOVED;
				K--;
								
				if (K == 0) {
					System.out.println(j);
					break loop;
				}
			}
		}
	}
	
	static void init() {
		for (int i = 2; i < nums.length; i++) {
			nums[i] = i;
		}
	}
}