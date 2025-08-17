import java.util.*;
import java.io.*;

class Main {
    static int N; // 도시의 개수, [2, 3 * 1e5]
    static int M; // 도로의 개수, [1, 1e6]
    static int K; // 거리 정보, [1, 3 * 1e5]
    static int X; // 출발 도시의 번호, [1, N]
    static final List<List<Integer>> graph = new ArrayList<>(); // 인접 리스트
    static int[] distances;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        distances = new int[N + 1];
        Arrays.fill(distances, K + 1);

        // 단방향 연결 관계 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            graph.get(src).add(dest);
        }

        runDijkstra(X);

        for (int i = 1 ; i <= N; i++) {
            if (distances[i] == K) {
                sb.append(i).append("\n");
            }
        }
        
        bw.write(sb.length() == 0 ? "-1" : sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    static void runDijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.dist, v2.dist));

        distances[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (distances[curr.value] < curr.dist) continue;

            for (int next : graph.get(curr.value)) {
                if (distances[next] > distances[curr.value] + 1) {
                    distances[next] = distances[curr.value] + 1;
                    pq.add(new Node(next, distances[next]));
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