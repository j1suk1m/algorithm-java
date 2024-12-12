import java.io.*;

class Main {
    private static final int alphabetCount = 26;
    private static boolean[][] graph = new boolean[alphabetCount][alphabetCount];

    // 플로이드 워셜 알고리즘
    private static void floydWarshall() {
        for (int k = 0; k < alphabetCount; k++) {
            for (int i = 0; i < alphabetCount; i++) {
                for (int j = 0; j < alphabetCount; j++) {
                    if (i == k || k == j || i == j) {
                        continue;
                    } else if (!graph[i][j] && graph[i][k] && graph[k][j]) {
                        graph[i][j] = true; // i -> k이고 k -> j이면 i -> j
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");

            int a = input[0].charAt(0) - 'a';
            int b = input[2].charAt(0) - 'a';

            graph[a][b] = true;
        }

        floydWarshall();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");

            int p = input[0].charAt(0) - 'a';
            int q = input[2].charAt(0) - 'a';

            if (graph[p][q]) {
                System.out.println("T");
            } else {
                System.out.println("F");
            }
        }
    }
}