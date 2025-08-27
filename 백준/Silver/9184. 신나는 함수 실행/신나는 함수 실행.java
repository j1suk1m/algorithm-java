import java.util.*;
import java.io.*;

class Main {
    static final String EOF = "-1 -1 -1";
    static int[][][] dp = new int[21][21][21];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        String line = null;
        int a, b, c, result;

        while (!(line = br.readLine()).equals(EOF)) {
            st = new StringTokenizer(line);

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            result = w(a, b, c);

            sb.append("w(").append(a).append(", ")
                .append(b).append(", ")
                .append(c).append(") = ")
                .append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    static int w(int a, int b, int c) {
        if (isValidRange(a, b, c) && dp[a][b][c] != 0) return dp[a][b][c];
        if (a <= 0 || b <= 0 || c <= 0) return 1;
        if (a > 20 || b > 20 || c > 20) return dp[20][20][20] = w(20, 20, 20);
        if (a < b && b < c) return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    static boolean isValidRange(int a, int b, int c) {
        return 0 <= a && a <= 20 && 0 <= b && b <= 20 && 0 <= c && c <= 20; 
    }
}