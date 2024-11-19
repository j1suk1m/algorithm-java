import java.util.*;
import java.io.*;

class Main {
    public static int dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int current) {
        int count = 1;
        visited[current] = true;

        // 직접 연결된 컴퓨터 중 방문하지 않은 노드 방문
        for (int next : graph.get(current)) {
            if (visited[next] == false) {
                count += dfs(graph, visited, next);
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numOfComputers = Integer.parseInt(br.readLine());
        int numOfPairs = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[numOfComputers + 1];

        for (int i = 0; i < numOfComputers + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numOfPairs; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int computer1 = Integer.parseInt(st.nextToken());
            int computer2 = Integer.parseInt(st.nextToken());

            // 양방향 연결 관계 저장
            graph.get(computer1).add(computer2);
            graph.get(computer2).add(computer1);
        }

        int answer = dfs(graph, visited, 1) - 1; // 자기 자신 제외

        System.out.println(answer);
    }
}