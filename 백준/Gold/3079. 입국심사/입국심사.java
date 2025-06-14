import java.util.*;
import java.io.*;

class Main {
    private static int[] T;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        int N = Integer.parseInt(tokenizer.nextToken()); // 입국 심사대 개수
        int M = Integer.parseInt(tokenizer.nextToken()); // 사람 수
        
        T = new int[N];

        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(reader.readLine()); // 심사하는 데 걸리는 시간
        }

        // check(low) != check(high)가 되도록 low, high의 초깃값 설정
        long low = 0L; // check(low) == false
        long high = (long) M * T[N - 1]; // check(high) == true

        while (low + 1 < high) {
            long mid = (low + high) / 2; // 모든 심사가 완료되기까지 걸리는 시간

            if (check(M, mid)) { // M명을 모두 심사할 수 있는 경우
                high = mid; // 시간을 줄여도 모두 심사할 수 있는지 확인
            } else {
                low = mid; // 시간을 늘림
            }
        }

        System.out.println(high);
    }

    private static boolean check(int M, long mid) {
        long totalPeople = 0L;
        
        for (int time : T) {
            totalPeople += mid / time;

            if (totalPeople >= M) { // 조기 종료 조건
                break;
            }
        }

        return totalPeople >= M;
    }
}