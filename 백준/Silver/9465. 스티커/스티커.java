import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] sticker;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            
            sticker = new int[2][N];

            // 스티커 저장
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = 0;

            if (N == 1) {
                result = Math.max(sticker[0][0], sticker[1][0]);
            } else if (N == 2) {
                result = Math.max(sticker[0][0] + sticker[1][1], 
                                  sticker[1][0] + sticker[0][1]);
            } else {
                int[][] dp = new int[2][N];

                dp[0][0] = sticker[0][0];
                dp[1][0] = sticker[1][0];
                dp[0][1] = sticker[1][0] + sticker[0][1];
                dp[1][1] = sticker[0][0] + sticker[1][1];

                for (int col = 2; col < N; col++) {
                    dp[0][col] = Math.max(dp[1][col - 1], dp[1][col - 2]) + sticker[0][col];
                    dp[1][col] = Math.max(dp[0][col - 1], dp[0][col - 2]) + sticker[1][col];
                }

                result = Math.max(dp[0][N - 1], dp[1][N - 1]);
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}