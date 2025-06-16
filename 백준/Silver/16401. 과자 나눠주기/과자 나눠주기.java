import java.util.*;
import java.io.*;

class Main {
    private static int[] lengths;
    private static int M; // 조카의 수
    private static int N; // 과자의 수
    
    public static void main(String[] args) throws IOException {
        // 1. 입력
        input();

        // 2. 모든 조카에게 같은 길이의 막대 과자를 나눠줄 수 있는지 확인
        if (canDistribute(1)) {
            // 3. 나눠줄 수 있는 최대 길이 계산
            System.out.println(calculateMaxLength());
        } else {
            System.out.println(0);
        }
    }

    // 문제 풀이에 필요한 값 입력받기
    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        M = Integer.parseInt(tokenizer.nextToken()); // 조카의 수
        N = Integer.parseInt(tokenizer.nextToken()); // 과자의 수

        lengths = new int[N];
        tokenizer = new StringTokenizer(reader.readLine(), " ");

        for (int i = 0; i < N; i++) {
            lengths[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    // 모든 조카(총 M명)에게 같은 길이(mid)의 막대 과자를 나눠줄 수 있는지 확인
    private static boolean canDistribute(int mid) {
        long sum = 0L;
        
        for (int length : lengths) {
            sum += length / mid;

            if (sum >= M) { // 조기 종료 조건
                return true;
            }
        }

        return false;        
    }

    // 모든 조카에게 똑같이 나눠줄 수 있는 막대 과자의 최대 길이 계산
    private static int calculateMaxLength() {
        Arrays.sort(lengths); // 현재 최대 길이를 계산하기 위한 오름차순 정렬
        
        int low = 1;
        int high = lengths[N - 1] + 1;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2; // (low + high) / 2와 동일한 표현식

            if (canDistribute(mid)) { // mid 길이로 똑같이 나눠줄 수 있다면
                low = mid; // 더 긴 길이로 나눠줄 수 있는지 확인
            } else {
                high = mid; // 길이를 줄인 뒤 나눠줄 수 있는지 확인
            }
        }

        return low;
    }
}