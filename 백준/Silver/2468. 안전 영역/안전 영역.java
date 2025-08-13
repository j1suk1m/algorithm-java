import java.util.*;
import java.io.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int N; // 지역 크기 [2, 100]
    static int[][] map;
    static int maxCount = 0; // 안전한 영역의 최대 개수
    static int maxHeight = 0; // 지점의 최대 높이
    static final int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        findMaxSafeAreaCount();

        bw.write(String.valueOf(maxCount));
        bw.flush();

        br.close();
        bw.close();
    }

    static void findMaxSafeAreaCount() {
        for (int waterHeight = 0; waterHeight < maxHeight; waterHeight++) {
            int count = 0;
            boolean[][] visited = new boolean[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > waterHeight) {
                        visitArea(new Point(i, j), waterHeight, visited);
                        count++;
                    }
                    
                }
            }

            maxCount = Math.max(maxCount, count);
        }
    }

    static void visitArea(Point start, int waterHeight, boolean[][] visited) {
        Deque<Point> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int[] move : moves) {
                int nx = now.x + move[0];
                int ny = now.y + move[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map[nx][ny] <= waterHeight) continue;
                if (visited[nx][ny]) continue;

                queue.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}