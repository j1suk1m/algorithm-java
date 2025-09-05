import java.util.*;
import java.io.*;

class Main {
    static int N; // 연구소의 크기, [4, 50]
    static int M; // 활성화할 바이러스의 개수, [1, 10]
    static int[][] lab; // 연구소
    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static int blankCount = 0; // 빈 칸의 개수
    static final List<Point> viruses = new ArrayList<>(); // 바이러스 좌표 리스트
    static final int INF = Integer.MAX_VALUE;
    static int minTime = INF; // 모든 빈 칸에 바이러스를 확산시키는 최소 시간
    static final int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 이동
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][N];

        // 연구소 상태 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());

                if (lab[i][j] == BLANK) {
                    blankCount++; // 빈 칸의 개수 누적
                } else if (lab[i][j] == VIRUS) {
                    viruses.add(new Point(i, j, 0)); // 바이러스 좌표 저장
                }
            }
        }

        if (blankCount == 0) { 
            minTime = 0;
        } else if (viruses.size() == M) {
            minTime = spread(viruses);
        } else {
            activate(0, new ArrayList<>());
        }

        System.out.println(minTime < INF ? minTime : -1);
    }

    // 바이러스 M개 활성화 후 확산
    static void activate(int start, List<Point> selectedViruses) {
        if (selectedViruses.size() == M) { // 종료 조건
            int result = spread(selectedViruses); // 확산
            minTime = Math.min(minTime, result); // 최소 시간 갱신
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            selectedViruses.add(viruses.get(i));
            activate(i + 1, selectedViruses); // 백트래킹
            selectedViruses.remove(selectedViruses.size() - 1);
        }
    }

    // 너비 우선 탐색을 이용한 바이러스 확산
    static int spread(List<Point> selectedViruses) {
        boolean[][] visited = new boolean[N][N];
        Deque<Point> queue = new ArrayDeque<>();
        int restBlankCount = blankCount; // 바이러스가 확산되지 않은 빈 칸의 개수

        for (Point virus : selectedViruses) {
            visited[virus.x][virus.y] = true;
            queue.add(virus);
        }

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            if (lab[curr.x][curr.y] == BLANK) {
                restBlankCount--;

                if (restBlankCount == 0) {
                    return curr.time;
                }
            }

            for (int[] move : moves) {
                int nx = curr.x + move[0];
                int ny = curr.y + move[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (lab[nx][ny] == WALL) continue;

                visited[nx][ny] = true;
                queue.add(new Point(nx, ny, curr.time + 1));
            }
        }

        return INF;
    }

    static class Point {
        int x;
        int y;
        int time;

        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}