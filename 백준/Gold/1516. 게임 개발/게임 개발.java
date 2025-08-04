import java.util.*;
import java.io.*;

class Main {
    static int[] indegrees;
    static int[] times; // 건물을 건설하는 데 걸리는 시간
    static int[] completionTimes; // 건물 건설이 완료된 시간
    static final List<List<Integer>> graph = new ArrayList<>();
    static final Deque<Integer> queue = new ArrayDeque<>();
    static final StringBuilder output = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 건물의 종류 수

        indegrees = new int[N + 1];
        times = new int[N + 1];
        completionTimes = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int node = 1; node <= N; node++) {
            st = new StringTokenizer(br.readLine());

            times[node] = Integer.parseInt(st.nextToken());
            int predecessor = 0;

            // 진입 차수 및 연결 관계 저장
            while (st.hasMoreTokens() && (predecessor = Integer.parseInt(st.nextToken())) != -1) {
                indegrees[node]++;
                graph.get(predecessor).add(node);
            }
        }

        // 진입 차수가 0인 노드를 큐에 저장
        for (int node = 1; node <= N; node++) {
            if (indegrees[node] == 0) {
                completionTimes[node] = times[node];
                queue.offer(node);
            }
        }

        // 위상 정렬
        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int next : graph.get(node)) {
                completionTimes[next] = Math.max(completionTimes[next], completionTimes[node] + times[next]); // 모든 선행 작업이 종료된 후 시간 누적
                
                if (--indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // 건물 건설이 완료된 시간 출력
        for (int node = 1; node <= N; node++) {
            output.append(completionTimes[node]).append("\n");
        }

        System.out.println(output);
    }
}