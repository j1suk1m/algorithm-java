import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static int[] permutation;
    private static boolean[] visited;
    private static final StringBuilder answer = new StringBuilder();
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        permutation = new int[M];
        visited = new boolean[N + 1];

        // 백트래킹
        backtrack(0);
        
        System.out.println(answer);
    }

    private static void backtrack(int depth) {
        
        // 수열의 길이가 M이 된 경우
        if (depth == M) {
            for (int number : permutation) {
                answer.append(number).append(" ");
            }
            
            answer.append("\n");
            return;
        }

        for (int number = 1; number <= N; number++) {
            if (!visited[number]) {
                visited[number] = true;
                permutation[depth] = number;
                backtrack(depth + 1);
                visited[number] = false;
            }
        }
        
    }
}