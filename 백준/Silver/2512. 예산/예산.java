import java.util.*;
import java.io.*;

class Main {
    private static int N; // 지방의 수
    private static int M; // 총 예산
    private static int[] budgetRequests; // 예산 요청 배열
    
    public static void main(String[] args) throws IOException {
        // 1. 입력
        input();
        Arrays.sort(budgetRequests); // 예산 요청의 최댓값을 찾기 위한 오름차순 정렬

        // 2. 모든 예산 요청이 배정될 수 있는지 확인
        if (canAllocate()) {
            System.out.println(budgetRequests[N - 1]);
        } else { // 3. 이분 탐색 수행
            int low = 1;
            int high = budgetRequests[N - 1] + 1;

            while(low + 1 < high) {
                int mid = low + (high - low) / 2;
    
                if (check(mid)) {
                    low = mid;
                } else {
                    high = mid;
                }
            }

            System.out.println(low);
        }
    }

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine()); // 지방의 수
        budgetRequests = new int[N];
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        // 각 지방의 예산 요청 저장
        for (int i = 0; i < N; i++) {
            budgetRequests[i] = Integer.parseInt(tokenizer.nextToken());
        }

        M = Integer.parseInt(reader.readLine()); // 총 예산
    }

    // 모든 예산 요청을 배정할 수 있는지 확인
    // `예산 요청의 합 <= M(총 예산)`이면 true, 아니면 false 반환
    private static boolean canAllocate() {
        int sum = 0;

        for (int budget : budgetRequests) {
            sum += budget;

            if (sum > M) { // 조기 종료 조건
                return false;
            }
        }

        return true;
    }

    private static boolean check(int mid) {
        int i = 0;
        int sum = 0;

        // mid(상한)보다 적은 예산 요청의 합 계산
        while (budgetRequests[i] < mid) {
            sum += budgetRequests[i];
            i++;
        }

        sum += (N - i) * mid; // mid(상한)보다 큰 예산 요청은 mid로 배정
        
        return sum <= M;
    }
}