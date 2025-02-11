import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static BufferedReader reader;
    private static StringTokenizer tokenizer;
    private static int N;
    private static int M;
    private static int[] permutation;
    private static int[] numbers;
    private static boolean[] visited;
    private static final StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        // 배열 초기화
        init();

        // 오름차순 정렬
        Arrays.sort(numbers);

        // 백트래킹 수행
        backtrack(0, 0);

        // 결과 출력
        System.out.print(answer);
    }

    /**
     * 배열 초기화
     * @throws IOException
     */
    private static void init() throws IOException {
        permutation = new int[M];
        numbers = new int[N];
        visited = new boolean[N];

        tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    /**
     * 백트래킹 수행
     * @param depth permutation 배열에서의 인덱스
     * @param startIndex numbers 배열에서의 인덱스
     */
    private static void backtrack(int depth, int startIndex) {
        if (depth == M) {
            for (int number : permutation) {
                answer.append(number).append(" ");
            }

            answer.append("\n");
            return;
        }

        // 오름차순 수열을 위해 startIndex부터 시작
        for (int index = startIndex; index < N; index++) {
            if (!visited[index]) {
                visited[index] = true;
                permutation[depth] = numbers[index];
                backtrack(depth + 1, index + 1);
                visited[index] = false;
            }
        }
    }
}