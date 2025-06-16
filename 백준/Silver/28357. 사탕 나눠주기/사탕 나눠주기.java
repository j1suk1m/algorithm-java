import java.util.*;
import java.io.*;

class Main {
    private static long[] scores; // 점수 배열
    private static int N; // 학생의 수
    private static long K; // 사탕의 최대 개수
    
    public static void main(String[] args) throws IOException {
        // 1. 입력
        input();

        // 2. 오름차순 정렬
        Arrays.sort(scores);

        // 3. 기준 점수 X의 최솟값 계산
        System.out.println(calculateMinScore());
    }

    // 문제 풀이에 필요한 값 입력받기
    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        N = Integer.parseInt(tokenizer.nextToken()); // 학생의 수
        K = Long.parseLong(tokenizer.nextToken()); // 사탕의 최대 개수

        scores = new long[N];
        tokenizer = new StringTokenizer(reader.readLine(), " ");

        for (int i = 0; i < N; i++) {
            scores[i] = Long.parseLong(tokenizer.nextToken()); // 점수 배열
        }
    }

    private static long calculateMinScore() {
        long low = -1L;
        long high = scores[N - 1];

        while (low + 1 < high) {
            long mid = low + (high - low) / 2; // (low + high) / 2와 동일한 표현식

            if (check(mid)) { 
                high = mid; // 조건 만족 -> 더 낮은 기준 점수로 탐색
            } else {
                low = mid; // 조건 불만족 -> 더 높은 기준 점수로 탐색
            }
        }

        return high;
    }

    private static boolean check(long mid) {
        long sum = 0L; // 학생들에게 나누어줄 사탕의 개수
        
        for (int i = N - 1; i >= 0; i--) { // 최대 점수부터 거꾸로 탐색
            long score = scores[i];

            if (score <= mid) { // 이 시점부터는 사탕을 받지 못함
                break; // 반복문 조기 종료
            }
            
            sum += (score - mid); // 기준 점수 mid보다 점수가 높은 만큼 사탕을 받음
            
            if (sum > K) {
                return false;
            }
        }

        return sum <= K;
    }
}