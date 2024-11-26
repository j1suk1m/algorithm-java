import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;

    private static int bfs(int start) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    count++;
                }
            }
        }

        return count;
    }

    private static int dfs(int current) {
        int count = 1;
        visited[current] = true;

        for (int next : graph.get(current)) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numOfComputers = Integer.parseInt(br.readLine());
        int numOfPairs = Integer.parseInt(br.readLine());

        for (int i = 0; i < numOfComputers + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numOfPairs; i++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input, " ");

            int computer1 = Integer.parseInt(st.nextToken());
            int computer2 = Integer.parseInt(st.nextToken());

            graph.get(computer1).add(computer2);
            graph.get(computer2).add(computer1);
        }

        visited = new boolean[numOfComputers + 1];

        System.out.println(dfs(1) - 1);
    }
}