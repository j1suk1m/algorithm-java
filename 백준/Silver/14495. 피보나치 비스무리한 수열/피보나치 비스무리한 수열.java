import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        if (n <= 3) {
            sb.append(1);
        } else {
            long[] table = new long[n + 1];

            // 초깃값 설정
            table[1] = 1L;
            table[2] = 1L;
            table[3] = 1L;

            // 다이나믹 프로그래밍
            for (int i = 4; i <= n; i++) {
                table[i] = table[i - 1] + table[i - 3]; // 점화
            }

            sb.append(table[n]);
        }
        
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }
}