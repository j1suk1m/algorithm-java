import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited; // visited[BROKEN][x][y] = 이미 벽을 한 번 부수고 (x, y)에 도달한 경우
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int NORMAL = 0;
    static final int BROKEN = 1;
    static final int START_X = 1;
    static final int START_Y = 1;

    static class Space {
        int x;
        int y;
        int distance;
        boolean isBreakable;

        Space(int x, int y, int distance, boolean isBreakable) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.isBreakable = isBreakable;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visited = new boolean[2][N + 1][M + 1];

        // 맵 저장
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        // 최단 경로 출력
        System.out.println(findShortestDistance(START_X, START_Y));
    }

    static int findShortestDistance(int x, int y) {
        Deque<Space> queue = new ArrayDeque<>();
        queue.add(new Space(x, y, 1, true));
        visited[x][y][NORMAL] = true;

        while (!queue.isEmpty()) {
            Space curr = queue.poll();

            if (curr.x == N && curr.y == M) { // 종료 조건
                return curr.distance;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (isOutOfRange(nx, ny)) continue;

                if (map[nx][ny] == EMPTY) { // 다음 칸이 빈칸인 경우
                    if (curr.isBreakable && !visited[NORMAL][nx][ny]) {
                        queue.add(new Space(nx, ny, curr.distance + 1, true));
                        visited[NORMAL][nx][ny] = true;
                    }
                    if (!curr.isBreakable && !visited[BROKEN][nx][ny]) {
                        queue.add(new Space(nx, ny, curr.distance + 1, false));
                        visited[BROKEN][nx][ny] = true;
                    }
                } else { // 다음 칸이 벽인 경우
                    if (curr.isBreakable && !visited[BROKEN][nx][ny]) {
                        queue.add(new Space(nx, ny, curr.distance + 1, false));
                        visited[BROKEN][nx][ny] = true;
                    }
                }
            }
        }

        return -1;
    }

    static boolean isOutOfRange(int x, int y) {
        return x < 1 || x > N || y < 1 || y > M;
    }
}