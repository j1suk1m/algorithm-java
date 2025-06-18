import java.util.*;
import java.io.*;

class Main {
    private static int N; // 총 기간 (단위: 일)
    private static int M; // 인출 횟수
    private static int[] budgets; // 각 날짜에 필요한 금액 배열

    public static void main(String[] args) throws IOException {
        input();

        int low = getMaxBudget() - 1;        
        int high = getTotalBudget();

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;

            if (check(mid)) {
                high = mid; // 조건 만족 -> 인출 금액을 더 줄일 수 있는지 탐색
            } else {
                low = mid; // 조건 불만족 -> 인출 금액을 늘려야 함
            }
        }

        System.out.println(high);
    }

    // 데이터 입력
    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        budgets = new int[N];

        for (int i = 0; i < N; i++) {
            budgets[i] = Integer.parseInt(reader.readLine());
        }
    }

    // 가장 큰 하루 지출 계산
    private static int getMaxBudget() {
        int max = 0;
        
        for (int budget : budgets) {
            max = Math.max(max, budget);
        }
        
        return max;
    }

    // 전체 지출 합 계산
    private static int getTotalBudget() {
        int sum = 0;
        
        for (int budget : budgets) {
            sum += budget;
        }
        
        return sum;
    }

    // mid원을 인출했을 때, 인출 횟수가 M 이하인지 확인
    private static boolean check(int mid) {
        int count = 1; // 인출 횟수 // 첫 인출 포함
        int current = mid; // 현재 사용 가능한 금액

        for (int budget : budgets) {
            if (current < budget) {
                count++;
                current = mid;
            }

            if (count > M) { // 조기 종료 조건
                break;
            }

            current -= budget;
        }

        return count <= M;
    }
}