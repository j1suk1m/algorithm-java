import java.util.*;
import java.io.*;

class Main {
    static int N, M; // 지도의 세로, 가로 크기
    static int x, y; // 주사위의 좌표
    static int K; // 명령의 개수
    static int[][] map;
    static int[] dice = new int[6]; // 주사위의 눈
    static final int EAST = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static final int SOUTH = 4;
    static final int BOTTOM = 5; // 주사위의 바닥면
    static final int TOP = 0; // 주사위의 윗면
    static final int RESET = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 지도 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int nx, ny, direction, nextSpace;
        st = new StringTokenizer(br.readLine());

        // 명령 수행
        for (int i = 0; i < K; i++) {
            direction = Integer.parseInt(st.nextToken());

            // 주사위의 다음 좌표 결정
            if (direction == EAST) {
                nx = x;
                ny = y + 1;
            } else if (direction == WEST) {
                nx = x;
                ny = y - 1;
            } else if (direction == NORTH) {
                nx = x - 1;
                ny = y;
            } else {
                nx = x + 1;
                ny = y;
            }

            // 다음 좌표가 지도를 벗어나는 경우 명령 무시
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            dice = roll(direction);
            nextSpace = map[nx][ny];

            // 이동한 칸에 쓰인 수에 따라 분기
            if (nextSpace == 0) {
                map[nx][ny] = dice[BOTTOM];
            } else {
                dice[BOTTOM] = map[nx][ny];
                map[nx][ny] = RESET;
            }

            sb.append(dice[TOP]).append("\n");

            x = nx;
            y = ny;
        }

        System.out.println(sb);
    }

    static int[] roll(int direction) {
        if (direction == EAST) {
            return new int[] {dice[3], dice[1], dice[0], dice[5], dice[4], dice[2]};
        } else if (direction == WEST) {
            return new int[] {dice[2], dice[1], dice[5], dice[0], dice[4], dice[3]};
        } else if (direction == NORTH) {
            return new int[] {dice[4], dice[0], dice[2], dice[3], dice[5], dice[1]};            
        }
        
        return new int[] {dice[1], dice[5], dice[2], dice[3], dice[0], dice[4]};
    }
}