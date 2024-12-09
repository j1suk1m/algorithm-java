import java.util.*;
import java.io.*;

class Position {
    private final int x;
    private final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Main {
    private static int[][] graph;
    private static int N;
    private static int M;
    private static int space = 0;
    private static int cabbage = 1;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static int bfs(int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        graph[x][y] = space; // 방문 처리
        queue.add(new Position(x, y));

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.getX() + dx[i];
                int ny = current.getY() + dy[i];

                if (!(0 <= nx && nx < N && 0 <= ny && ny < M)) {
                    continue;
                } else if (graph[nx][ny] == space) {
                    continue;
                } else { // 배추가 있는 칸인 경우
                    graph[nx][ny] = space; // 방문 처리
                    queue.add(new Position(nx, ny));
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int answer = 0;
            graph = new int[N][M];

            // 배추 심기
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int Y = Integer.parseInt(st.nextToken());
                int X = Integer.parseInt(st.nextToken());

                graph[X][Y] = cabbage;
            }

            // 배추가 있는 칸에서 BFS 실행
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (graph[x][y] == cabbage) {
                        answer += bfs(x, y);
                    }
                }
            }

            System.out.println(answer);
        }
    }
}