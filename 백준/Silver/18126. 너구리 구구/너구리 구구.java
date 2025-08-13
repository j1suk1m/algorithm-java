import java.util.*;
import java.io.*;

class Edge {
    int dest;
    int cost;

    Edge(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }
}

class Main {
    static int N;
    static final List<List<Edge>> edges = new ArrayList<>();
    static final int ROOT = 1;
    static long maxDistance;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];

        // 인접 리스트 초기화
        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }

        // 인접 리스트 저장
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.get(v1).add(new Edge(v2, cost));
            edges.get(v2).add(new Edge(v1, cost));
        }

        // 깊이 우선 탐색
        visitRoom(ROOT, 0L);

        System.out.println(maxDistance);
        
        br.close();
    }

    static void visitRoom(int v, long distance) {
        visited[v] = true;
        maxDistance = Math.max(maxDistance, distance);

        for (Edge edge : edges.get(v)) {
            if (!visited[edge.dest]) {
                visitRoom(edge.dest, distance + edge.cost);
            }
        }
    }
}