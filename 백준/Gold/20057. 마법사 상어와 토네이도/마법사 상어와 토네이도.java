import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] A;
    static final int[] di = new int[] {0, 1, 0, -1}; // 왼쪽, 아래, 오른쪽, 위
    static final int[] dj = new int[] {-1, 0, 1, 0}; // 왼쪽, 아래, 오른쪽, 위
    static final int[] percent = new int[] {1, 1, 2, 2, 5, 7, 7, 10, 10, 0};
    static final int[][] ai = new int[][] {{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, 
                                           {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1}, 
                                           {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, 
                                           {1, 1, 0, 0, -2, 0, 0, -1, -1, -1}};
    static final int[][] aj = new int[][] {{1, 1, 0, 0, -2, 0, 0, -1, -1, -1}, 
                                           {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, 
                                           {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1}, 
                                           {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}};
    static final int LEFT = 0;
    static final int DOWN = 1;
    static final int RIGHT = 2;
    static final int UP = 3;
    static final int REST = ai[0].length - 1;
    static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        A = new int[N][N];

        // 모래의 양 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ci = N / 2;
        int cj = N / 2;
        int count = 0;
        int maxCount = 1;
        boolean isValidMaxCount = true;
        int dr = LEFT;

        while (ci > 0 || cj > 0) {
            // 다음 칸으로 이동
            ci = ci + di[dr];
            cj = cj + dj[dr];
            count++;

            // 모래 흩날리기
            spread(ci, cj, dr);

            // 현재 방향에서 이동해야 할 모든 칸 수만큼 이동한 경우
            if (count == maxCount) {
                count = 0;
                dr = changeDirection(dr); // 방향 변환

                if (isValidMaxCount) {
                    isValidMaxCount = false;
                } else {
                    isValidMaxCount = true;
                    maxCount++;
                }
            }
        }

        System.out.println(answer);
    }

    static int changeDirection(int curr) {
        if (curr == LEFT) return DOWN;
        if (curr == DOWN) return RIGHT;
        if (curr == RIGHT) return UP;
        return LEFT;
    }

    // ci: 현재 행 좌표
    // cj: 현재 열 좌표
    // dr: 현재 방향 (LEFT = 0, DOWN = 1, RIGHT = 2, UP = 3)
    static void spread(int ci, int cj, int dr) {
        // 흩날릴 모래가 없으면 종료
        if (A[ci][cj] == 0) return;
        
        int rest = A[ci][cj];
        
        for (int i = 0; i < ai[0].length; i++) {
            int ni = ci + ai[dr][i];
            int nj = cj + aj[dr][i];

            // 비율 칸 여부에 따라 분기
            int amount = (i == REST) ? rest : percent[i] * A[ci][cj] / 100;
            
            // 격자 범위를 벗어난 모래의 양 별도로 누적
            if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
                answer += amount;
            } else {  // 흩날린 모래의 양 누적
                A[ni][nj] += amount;
            }

            // 남은 모래의 양 갱신
            rest -= amount;
        }

        A[ci][cj] = 0;
    }
}