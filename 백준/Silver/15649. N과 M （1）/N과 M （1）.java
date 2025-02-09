import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    private static final List<Integer> permutation = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[N + 1];

        backtrack(N, M);
    }

    private static void backtrack(int N, int M) {
        
        // 수열의 길이가 M이 된 경우
        if (permutation.size() == M) {
            permutation.forEach(number -> System.out.print(number + " "));
            System.out.println();
            return;
        }

        for (int number = 1; number <= N; number++) {
            if (!visited[number]) {
                visited[number] = true;
                permutation.add(number);
                backtrack(N, M);
                permutation.remove(permutation.size() - 1);
                visited[number] = false;
            }
        }
    }
}