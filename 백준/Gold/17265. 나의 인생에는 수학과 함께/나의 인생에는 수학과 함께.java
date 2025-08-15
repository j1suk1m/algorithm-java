import java.util.*;
import java.io.*;

class Main {
    static char[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 지도의 크기

        map = new char[N + 1][N + 1];
        int[][] minValue = new int[N + 1][N + 1];
        int[][] maxValue = new int[N + 1][N + 1];
        int[][] moves = new int[][] {{0, -1}, {-1, 0}};
        
        // 지도 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        // 배열 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(minValue[i], Integer.MAX_VALUE);
            Arrays.fill(maxValue[i], Integer.MIN_VALUE);
        }

        // 초깃값 설정
        minValue[1][1] = map[1][1] - '0';
        maxValue[1][1] = map[1][1] - '0';

        // 다이나믹 프로그래밍
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 && j == 1) continue;

                for (int[] move : moves) {
                    int prevX = i + move[0];
                    int prevY = j + move[1];

                    if (prevX < 1 || prevX > N || prevY < 1 || prevY > N) continue;

                    if (Character.isDigit(map[i][j])) { // 현재 칸이 숫자인 경우
                        minValue[i][j] = Math.min(minValue[i][j], calculate(i, j, prevX, prevY, minValue[prevX][prevY]));
                        maxValue[i][j] = Math.max(maxValue[i][j], calculate(i, j, prevX, prevY, maxValue[prevX][prevY]));                            
                    } else { // 현재 칸이 기호인 경우
                        minValue[i][j] = Math.min(minValue[i][j], minValue[prevX][prevY]);
                        maxValue[i][j] = Math.max(maxValue[i][j], maxValue[prevX][prevY]);
                    }
                }
            }
        }

        sb.append(maxValue[N][N]).append(" ").append(minValue[N][N]);
        System.out.println(sb);
        
        br.close();
    }

    static int calculate(int x, int y, int prevX, int prevY, int totalPrevValue) {
        if (map[prevX][prevY] == '+') {
            return totalPrevValue + (map[x][y] - '0');
        } else if (map[prevX][prevY] == '-') {
            return totalPrevValue - (map[x][y] - '0');
        } else {
            return totalPrevValue * (map[x][y] - '0');
        }
    }
}