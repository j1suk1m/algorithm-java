import java.io.*;
import java.util.Arrays;

class Main {
    private static int[][] graph;
    private static final int INF = (int)1e7 + 1;

    // 그래프를 무한 비용으로 초기화
    private static void init(int n) {
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(graph[i], INF);
        }
    }

    // 플로이드 워셜 알고리즘
    private static void floydWarshall(int n) {
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (i == j || j == k || k == i) {
                        continue;
                    }

                    // k를 경유하는 비용이 더 적을 경우 비용 갱신
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    private static void print(int n) {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (graph[i][j] < INF) {
                    System.out.print(graph[i][j] + " ");
                } else {
                    System.out.print("0 ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new int[n + 1][n + 1];

        init(n);

        // 그래프 저장
        for (int i = 0; i < m; i++) {
            String[] busInfo = br.readLine().split(" ");

            int a = Integer.parseInt(busInfo[0]);
            int b = Integer.parseInt(busInfo[1]);
            int c = Integer.parseInt(busInfo[2]);

            if (graph[a][b] > c) {
                graph[a][b] = c;
            }
        }

        floydWarshall(n);
        print(n);
    }
}