import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static final List<List<Integer>> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        for (int v = 0; v <= N; v++) {
            edges.add(new ArrayList<>());
        }

        // 인접 리스트 정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 양방향 연결
            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        int connectedComponentCount = 0;
        int doubledCycleCount = 0; 

        boolean[] visited = new boolean[N + 1];

        for (int v = 1; v <= N; v++) {
            if (!visited[v]) {
                // DFS 한 번으로 현재 정점이 속한 연결 요소의 모든 정점 방문
                doubledCycleCount += getCycleCount(v, -1, visited); 
                connectedComponentCount++;
            }
        }

        int connectCount = connectedComponentCount - 1; // 연결 요소 k개를 하나로 연결 -> k - 1개의 간선 필요
        int disconnectCount = doubledCycleCount / 2; // 양방향 연결 -> 사이클이 2번씩 카운트 -> 2로 나누기

        bw.write(String.valueOf(connectCount + disconnectCount));

        bw.flush();
        bw.close();
    }

    private static int getCycleCount(int curr, int parent, boolean[] visited) {
        visited[curr] = true;

        int cycleCount = 0;

        for (int next : edges.get(curr)) {
            if (!visited[next]) {
                cycleCount += getCycleCount(next, curr, visited);
            } else if (next != parent) {
                cycleCount++;
            }
        }

        return cycleCount;
    }
}