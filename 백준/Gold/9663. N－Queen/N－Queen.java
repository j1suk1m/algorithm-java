import java.util.*;
import java.io.*;

public class Main {
	private static int answer = 0;
	private static int N;
	private static int[] board; // board[row] == col -> (row, col)에 퀸이 놓여 있음
	private static boolean[] put; // put[row] == true -> row에 퀸이 놓여 있음
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(reader.readLine()); // 체스판의 가로, 세로 크기 (N * N)
		
		board = new int[N];
		put = new boolean[N];
		
        // 백트래킹을 이용한 경우의 수 계산
		backtrack(0);
		
		System.out.println(answer);
	}
	
	private static void backtrack(int row) {
		if (row == N) { // 재귀 종료 조건
			answer++;
			return;
		}
		
		for (int col = 0; col < N; col++) {
			if (!put[row]) {
				board[row] = col; // (row, col)에 퀸을 놓음
				
				if (canPut(row)) {
					put[row] = true;
					backtrack(row + 1); // 다음 행으로 넘어감
					put[row] = false;
				}
			}
		}
	}
	
	private static boolean canPut(int row) {
		for (int i = 0; i < row; i++) {
			if (board[row] == board[i] || (row - board[row]) == (i - board[i]) || (row + board[row]) == (i + board[i])) {
				return false;
			}
		}
		
		return true;
	}
}