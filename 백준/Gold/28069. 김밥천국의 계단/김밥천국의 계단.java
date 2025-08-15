import java.util.*;
import java.io.*;

class Main {
    static int N; // 계단 개수, [1, 1e6]
    static int K; // 계단을 오르는 횟수, [1, 1e6]
    static int[] dp; // dp[i] -> i번째 계단에 도착하기 위한 최소 이동 횟수
    static final int START = 0;
    static final String SUCCESS = "minigimbob";
    static final String FAILURE = "water";
    static final int INF = 1_000_000 + 1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];

        // 초깃값 설정
        Arrays.fill(dp, INF);
        dp[START] = 0;

        int next;

        // 다이나믹 프로그래밍
        for (int now = START; now < N; now++) {
            next = now + 1;

            if (next <= N) {
                dp[next] = Math.min(dp[next], dp[now] + 1);
            }

            next = now + now / 2;

            if (next <= N) {
                dp[next] = Math.min(dp[next], dp[now] + 1);
            }
        }

        // 결과 출력
        System.out.println(dp[N] <= K ? SUCCESS : FAILURE);
        
        br.close();
    }
}