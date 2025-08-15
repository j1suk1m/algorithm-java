import java.util.*;
import java.io.*;

class Main {
    static int N; // 계단 개수, [1, 1e6]
    static int K; // 계단을 오르는 횟수, [1, 1e6]
    static boolean[] visited; // 방문 여부
    static final int START = 0;
    static final String SUCCESS = "minigimbob";
    static final String FAILURE = "water";
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        System.out.println(climbStairs());
        
        br.close();
    }

    static String climbStairs() {
        Deque<Stair> queue = new ArrayDeque<>();

        queue.add(new Stair(START, 0));
        visited[START] = true; // 방문 처리

        while (!queue.isEmpty()) {
            Stair now = queue.poll();

            // 성공 조건
            // K번 이하로 N번째 계단에 도착할 수 있다면, 남은 이동 횟수는 0번째 계단에서 제자리 이동으로 채울 수 있음
            if (now.number == N && now.count <= K) return SUCCESS;

            // 실패 조건
            if (now.count > K) break;

            // 이동 유형 1: 한 칸 위로 이동
            if (now.number + 1 <= N && !visited[now.number + 1]) {
                queue.add(new Stair(now.number + 1, now.count + 1));
                visited[now.number + 1] = true; // 방문 처리
            }

            // 이동 유형 2: i번째 계단에서 i + floor(i / 2)번째 계단으로 이동
            if (now.number + now.number / 2 <= N && !visited[now.number + now.number / 2]) {
                queue.add(new Stair(now.number + now.number / 2, now.count + 1));
                visited[now.number + now.number / 2] = true; // 방문 처리
            }
        }

        return FAILURE;
    }

    static class Stair {
        int number; // 계단 번호
        int count; // 이동 횟수

        Stair(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }
}