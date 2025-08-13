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
    static final String EOF = "0 0";
    static int w; // 지도의 너비, [1, 50]
    static int h; // 지도의 높이, [1, 50]
    static int[][] map; // 지도
    static final int SEA = 0; // 바다
    static final int LAND = 1; // 땅  
    static final int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, 
                                  {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        String line = null;

        while (!(line = br.readLine()).equals(EOF)) {
            st = new StringTokenizer(line);
            
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[h][w];

            // 지도 입력
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 섬의 개수 출력
            sb.append(getIslandCount()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    static int getIslandCount() {
        int count = 0;
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == LAND) {
                    visitIsland(new Point(i, j));
                    count++;
                }
            }
        }

        return count;
    }

    static void visitIsland(Point start) {
        Deque<Point> queue = new ArrayDeque<>();

        queue.add(start);
        map[start.x][start.y] = SEA; // 방문 처리

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int[] move : moves) {
                int nx = now.x + move[0];
                int ny = now.y + move[1];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if (map[nx][ny] == SEA) continue;

                queue.add(new Point(nx, ny));
                map[nx][ny] = SEA; // 방문 처리
            }
        }
    }
}