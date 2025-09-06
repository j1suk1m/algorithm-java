import java.util.*;
import java.io.*;

class Main {
    static int N; // 행 개수
    static int M; // 열 개수
    static int[][] arr; // 이차원 배열
    static int minTime; // 빙산이 두 덩어리 이상으로 분리되는 최초 시간
    static int chunkCount; // 덩어리 개수
    static final int SEA = 0; // 바다가 있는 칸
    static int[][] diff; // 빙산을 녹일 양
    static final int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 이동 변화량
    static boolean[][] visited; // 방문 여부 배열
    static Deque<Point> queue;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        // 이차원 배열 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minTime = 0;
        
        while (true) {
            melt(); // 빙산 녹이기
            minTime++;
            chunkCount = getChunkCount(); // 덩어리 개수 계산

            if (chunkCount == 0) {
                minTime = 0;
                break;
            } else if (chunkCount >= 2) {
                break;
            }
        }

        System.out.println(minTime);
    }

    // 빙산 녹이기
    static void melt() {
        diff = new int[N][M];

        // 빙산과 인접한 바다 칸의 개수 저장
        // 순차적으로 뺄셈을 수행하면 바다로 변한 인접한 칸이 현재 턴에 영향을 줌
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (arr[x][y] != SEA) {
                    for (int[] move : moves) {
                        int nx = x + move[0];
                        int ny = y + move[1];

                        // 맨 바깥 행, 열이 바다로 패딩되어 있으므로 유효한 범위인지 확인 불필요
                        if (arr[nx][ny] != SEA) continue;

                        diff[x][y]++; // 녹일 양 누적
                    }
                }
            }
        }

        // 빙산 녹이기
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                arr[x][y] -= Math.min(arr[x][y], diff[x][y]); // 음수 방지를 위해 min 사용
            }
        }
    }

    // 너비 우선 탐색을 이용한 덩어리 개수 계산
    static int getChunkCount() {
        visited = new boolean[N][M];
        queue = new ArrayDeque<>();
        int chunkCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && arr[i][j] != SEA) {
                    Point start = new Point(i, j);
                    visited[i][j] = true;
                    queue.add(start);
                    visit(start);
                    chunkCount++;
                }

                if (chunkCount >= 2) { 
                    return chunkCount;
                }
            }
        }

        return chunkCount;
    }

    // 너비 우선 탐색
    static void visit(Point start) {
        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            for (int[] move : moves) {
                int nx = curr.x + move[0];
                int ny = curr.y + move[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (arr[nx][ny] == SEA || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}