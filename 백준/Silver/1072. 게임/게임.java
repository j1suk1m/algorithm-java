import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long X = Long.parseLong(st.nextToken()); // 전체 게임 횟수
		long Y = Long.parseLong(st.nextToken()); // 이긴 게임 횟수
		
		long Z = Y * 100 / X; // 승률(%)
		
		// 승률이 절대 변하지 않는 경우
		// Z == 99 -> 이전 패배 기록을 삭제할 수 없기 때문에, 절대 100이 될 수 없음
		// Z == 100 -> 이미 최대 승률이기 때문에, 절대 변하지 않음
		if (Z >= 99) { 
			System.out.println(-1);
		} else {
			long left = 0L;
		    long right = X;
			
			while (left + 1 < right) {
				long mid = left + (right - left) / 2;
				
				if (check(X, Y, Z, mid)) { // 승률이 Z보다 커진 경우
					right = mid; // 게임을 덜 해도 승률이 Z보다 큰지 탐색
				} else { // 승률이 Z보다 작은 경우
					left = mid; // 게임을 더 해봄
				}
			}
			
			System.out.println(right);
		}
	}
	
	// 게임을 mid번 더 했을 때, 승률이 기존 승률 Z보다 커지는지 확인
	static boolean check(long X, long Y, long Z, long mid) {
		return (Y + mid) * 100 / (X + mid) > Z;
	}
}