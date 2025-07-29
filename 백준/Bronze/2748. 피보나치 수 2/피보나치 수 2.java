import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		if (n <= 2) {
			System.out.println("1");
		} else {
			long[] fibonacci = new long[n + 1]; // 피보나치 수가 int 범위를 넘어설 수 있으므로 long 사용
			
			// 초깃값 설정
			fibonacci[0] = 0;
			fibonacci[1] = 1;
			fibonacci[2] = 1;
			
			for (int i = 3; i <= n; i++) {
				fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
			}
			
			System.out.println(fibonacci[n]);
		}
	}
}