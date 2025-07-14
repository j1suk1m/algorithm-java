import java.util.*;
import java.io.*;

class Main {
    private static boolean[] canEatHamburger; // i번째 위치에 햄버거가 있고, 아직 먹을 수 있는 상태인지 저장
    private static final char HAMBURGER = 'H';
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        String input = reader.readLine();

        int answer = 0;
        canEatHamburger = new boolean[N]; // false로 초기화

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == HAMBURGER) { // 햄버거 위치 표시
                canEatHamburger[i] = true;
            } else { // 전체 사람 수 계산
                answer++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == HAMBURGER) continue;
            
            int left = i - K;
            int right = i + K;

            if (!eatHamburger(N, left, right)) { // 햄버거를 먹지 못한 경우
                answer--;
            }
        }

        System.out.println(answer);
    }

    // index가 유효한 범위의 인덱스인지 확인
    private static boolean isValidIndex(int N, int index) {
        return 0 <= index && index < N;
    }

    // left부터 right까지 탐색
    private static boolean eatHamburger(int N, int left, int right) {
        for (int j = left; j <= right; j++) {
            if (isValidIndex(N, j) && canEatHamburger[j]) {
                canEatHamburger[j] = false;
                return true;
            }
        }
        
        return false;
    }
}