import java.io.*;
import java.util.*;

class Main {
    private static int[] apples; // 각 정점에 대한 사과 개수
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 인접 리스트

    private static int dfs(int current, int depth, int k) {
        if (depth > k) {
            return 0;
        }

        int count = apples[current];

        for (int next : graph.get(current)) {
            count += dfs(next, depth + 1, k);
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph.get(parent).add(child);
        }

        apples = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(dfs(0, 0, k));
    }
}