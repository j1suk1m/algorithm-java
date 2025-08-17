import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Main {
    static int N; // 도시의 개수, [2, 3 * 1e5]
    static int M; // 도로의 개수, [1, 1e6]
    static int K; // 거리 정보, [1, 3 * 1e5]
    static int X; // 출발 도시의 번호, [1, N]
    static final List<List<Integer>> graph = new ArrayList<>(); // 인접 리스트
    static final List<Integer> answer = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String output = null;
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 단방향 연결 관계 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            graph.get(src).add(dest);
        }

        runBfs(X);

        answer.sort((a, b) -> Integer.compare(a, b));

        if (answer.size() > 0) {
            output = answer.stream()
                           .map(String::valueOf)
                           .collect(Collectors.joining("\n"));
        } else {
            output = "-1";
        }
        
        bw.write(output);
        bw.flush();

        br.close();
        bw.close();
    }

    static void runBfs(int start) {
        Deque<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        
        queue.add(new Node(start, 0));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.dist == K) answer.add(curr.value);
            if (curr.dist > K) break;
        
            for (int next : graph.get(curr.value)) {
                if (!visited[next]) {
                    queue.add(new Node(next, curr.dist + 1));
                    visited[next] = true;
                }
            }
        }
    }

    static class Node {
        int value;
        int dist;

        Node(int value, int dist) {
            this.value = value;
            this.dist = dist;
        }
    }
}