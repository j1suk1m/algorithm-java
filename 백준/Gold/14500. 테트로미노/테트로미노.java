import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] paper;
    static int answer = 0;
    static int[][][] patterns = new int[][][] {{{0, 1}, {0, 2}, {0, 3}}, {{1, 0}, {2, 0}, {3, 0}},
                                               {{0, 1}, {1, 0}, {1, 1}},
                                               {{1, 0}, {2, 0}, {2, 1}}, {{0, 1}, {0, 2}, {-1, 2}}, {{0, 1}, {1, 1}, {2, 1}}, {{1, 0}, {0, 1}, {0, 2}},
                                               {{1, 0}, {2, 0}, {2, -1}}, {{0, 1}, {0, 2}, {1, 2}}, {{0, 1}, {1, 0}, {2, 0}}, {{1, 0}, {1, 1}, {1, 2}},
                                               {{1, 0}, {1, 1}, {2, 1}}, {{0, 1}, {1, 0}, {1, -1}}, {{1, 0}, {1, -1}, {2, -1}}, {{0, 1}, {1, 1}, {1, 2}},
                                               {{0, 1}, {0, 2}, {1, 1}}, {{1, 0}, {1, 1}, {2, 0}}, {{1, -1}, {1, 0}, {1, 1}}, {{1, -1}, {1, 0}, {2, 0}}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];

        // 종이에 쓰여 있는 수 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[][] pattern : patterns) {
                    put(i, j, pattern);
                }
            }
        }

        System.out.println(answer);
    }

    static void put(int x, int y, int[][] pattern) {
        int sum = paper[x][y];
        
        for (int i = 0; i < pattern.length; i++) {
            int nx = x + pattern[i][0];
            int ny = y + pattern[i][1];

            // 범위를 벗어나는 경우 이 패턴 자체를 사용할 수 없음 -> 메서드 종료
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) return;

            // 테트로미노 위의 수 누적
            sum += paper[nx][ny];
        }

        // 최대 합 갱신
        answer = Math.max(answer, sum);
    }
}