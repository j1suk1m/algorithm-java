import java.util.*;
import java.io.*;

public class Main {
    static int N, M, P, C, D;
    static int rx, ry;
    static int[][] board;
    static Santa[] santas;
    static final int RUDOLPH = -1;
    static final int EMPTY = 0;
    static final int X = 0;
    static final int Y = 1;
    static final int[][] dr = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1},
                                           {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    static int turn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N + 1][N + 1];
        santas = new Santa[P + 1];
        st = new StringTokenizer(br.readLine());

        rx = Integer.parseInt(st.nextToken());
        ry = Integer.parseInt(st.nextToken());

        board[rx][ry] = RUDOLPH;

        // 산타 정보 저장
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int santaNumber = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            santas[santaNumber] = new Santa(sx, sy, santaNumber);
            board[sx][sy] = santaNumber;
        }

        for (turn = 1; turn <= M; turn++) {
            // 1. 루돌프가 가장 가까운 산타 방향으로 한 칸 이동
            moveToNearestSanta();

            // 2. 산타가 루돌프와 가까워지는 방향으로 한 칸 이동
            moveToRudolph();

            boolean isAllEliminated = true;

            // 3. 생존한 산타에게 1점 부여
            for (int i = 1; i <= P; i++) {
                Santa santa = santas[i];

                if (santa.isEliminated) continue;

                santa.score++;
                isAllEliminated = false;
            }

            if (isAllEliminated) break;
        }

        // 4. 결과 출력
        printResult();
    }

    // 루돌프가 가장 가까운 산타 방향으로 한 칸 이동
    static void moveToNearestSanta() {
        Santa nearestSanta = null;
        int minDistance = Integer.MAX_VALUE;

        // 가장 가까운 산타 찾기
        for (int i = 1; i <= P; i++) {
            Santa santa = santas[i];

            if (santa.isEliminated) continue;

            int distance = getDistanceBetween(rx, ry, santa.x, santa.y);

            if (nearestSanta == null 
                || distance < minDistance
                || (distance == minDistance && santa.compareTo(nearestSanta) < 0)) {
                nearestSanta = santa;
                minDistance = distance;
            }
        }

        int finalDrIdx = 0;

        // 가장 가까운 산타와 가장 가까워지는 방향 찾기
        for (int i = 0; i < dr.length; i++) {
            int nrx = rx + dr[i][X];
            int nry = ry + dr[i][Y];

            if (isOutOfRange(nrx, nry)) continue;

            int distance = getDistanceBetween(nrx, nry, nearestSanta.x, nearestSanta.y);

            if (distance < minDistance) {
                minDistance = distance;
                finalDrIdx = i;
            }
        }

        // 루돌프의 이동 좌표 결정
        int finalRx = rx + dr[finalDrIdx][X];
        int finalRy = ry + dr[finalDrIdx][Y];

        // 루돌프가 이동할 곳에 산타가 있어 충돌이 발생하는 경우
        if (board[finalRx][finalRy] > 0) {
            int santaNumber = board[finalRx][finalRy];
            Santa santa = santas[santaNumber];
            santa.lastStunnedTurn = turn + 1;
            santa.score += C;
            santa.move(finalDrIdx, C);
        }

        board[rx][ry] = EMPTY;
        board[finalRx][finalRy] = RUDOLPH;

        rx = finalRx;
        ry = finalRy;
    }

    // 산타들이 루돌프와 가까워지는 방향으로 한 칸 이동
    static void moveToRudolph() {
        for (int santaNumber = 1; santaNumber <= P; santaNumber++) {
            Santa santa = santas[santaNumber];

            if (santa.isEliminated || santa.isStunned()) continue;

            int finalDrIdx = -1;
            int minDistance = getDistanceBetween(rx, ry, santa.x, santa.y);

            for (int i = 0; i < 4; i++) {
                int nsx = santa.x + dr[i][X];
                int nsy = santa.y + dr[i][Y];

                if (isOutOfRange(nsx, nsy)) continue;
                if (board[nsx][nsy] > 0) continue;

                int distance = getDistanceBetween(rx, ry, nsx, nsy);

                if (distance < minDistance) {
                    minDistance = distance;
                    finalDrIdx = i;
                }
            }

            // 이동할 방향이 없거나 루돌프와 가까워질 수 없으면 이동 스킵
            if (finalDrIdx == -1) continue;

            // 산타의 이동 좌표 결정
            int finalSx = santa.x + dr[finalDrIdx][X];
            int finalSy = santa.y + dr[finalDrIdx][Y];

            // 산타가 이동할 곳에 루돌프가 있어 충돌이 발생하는 경우
            if (board[finalSx][finalSy] == RUDOLPH) {
                santa.lastStunnedTurn = turn + 1;
                santa.score += D;
                santa.move(getOppositeDrIdx(finalDrIdx), D - 1);
            } else {
                santa.move(finalDrIdx, 1);
            }
        }
    }

    // 결과 출력
    static void printResult() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= P; i++) {
            Santa santa = santas[i];

            sb.append(santa.score)
              .append(i < P ? " " : "\n");
        }

        System.out.print(sb);
    }

    static boolean isOutOfRange(int x, int y) {
        return x < 1 || x > N || y < 1 || y > N;
    }

    static int getDistanceBetween(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;

        return dx * dx + dy * dy;
    }

    static int getOppositeDrIdx(int drIdx) {
        return (drIdx + 2) % 4;
    }

    static class Santa implements Comparable<Santa> {
        int x;
        int y;
        int score; // 얻은 점수
        int number; // 산타 번호
        boolean isEliminated; // 탈락 여부
        int lastStunnedTurn; // 마지막 기절 턴 번호

        Santa(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
            score = 0;
            isEliminated = false;
            lastStunnedTurn = 0;
        }

        @Override
        public int compareTo(Santa other) {
            if (this.x != other.x) {
                return Integer.compare(other.x, this.x);
            }

            return Integer.compare(other.y, this.y);
        }

        boolean isStunned() {
            return lastStunnedTurn >= turn;
        }

        void move(int drIdx, int distance) {
            if (distance == 0) return;
            
            // 산타의 이동 좌표 결정
            int nx = x + dr[drIdx][X] * distance;
            int ny = y + dr[drIdx][Y] * distance;

            // 이동 후 격자 밖으로 나가게 될 경우 탈락
            if (isOutOfRange(nx, ny)) {
                this.isEliminated = true;
                board[x][y] = EMPTY;
                return;
            }

            // 이동할 좌표에 다른 산타가 있는 경우 연쇄 작용
            if (board[nx][ny] > 0) {
                int santaNumber = board[nx][ny];
                Santa santa = santas[santaNumber];
                santa.move(drIdx, 1); // 재귀 호출
            }

            // 산타 이동
            board[x][y] = EMPTY;
            board[nx][ny] = this.number;

            x = nx;
            y = ny;
        }
    }
}
