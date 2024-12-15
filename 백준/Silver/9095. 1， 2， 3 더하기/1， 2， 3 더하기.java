import java.io.*;

class Main {
    private static int[] table;
    
    private static void init() {
        table[1] = 1; // 1을 1, 2, 3의 합으로 나타내는 방법의 수
        table[2] = 2; // 2를 1, 2, 3의 합으로 나타내는 방법의 수
        table[3] = 4; // 3을 1, 2, 3의 합으로 나타내는 방법의 수
    }

    // 다이나믹 프로그래밍
    private static void dp(int n) {
        for (int i = 4; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2] + table[i - 3];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            if (n <= 2) {
                System.out.println(n);
            } else if (n == 3) {
                System.out.println(4);
            } else {
                table = new int[n + 1];

                init();
                dp(n);

                System.out.println(table[n]);
            }
        }
    }
}