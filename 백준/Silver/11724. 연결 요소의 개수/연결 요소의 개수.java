import java.util.*;
import java.io.*;

class Main {
    private static int N;
    private static int M;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static int answer;

    private static int dfs(int node) {
        visited[node] = true; // 방문 처리

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next);
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];

        // 그래프 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 양방향 연결
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int node = 1; node < N + 1; node++) {
            if (!visited[node]) {
                answer += dfs(node);
            }
        }

        System.out.println(answer);
    }
}