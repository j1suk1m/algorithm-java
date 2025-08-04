import java.util.*;
import java.io.*;

class Main {
    static int[] indegree;
    static final List<List<Integer>> graph = new ArrayList<>();
    static final Deque<Integer> queue = new ArrayDeque<>();
    static final StringBuilder output = new StringBuilder();
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생의 수
        int M = Integer.parseInt(st.nextToken()); // 키 비교 횟수

        indegree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 연결 관계 저장
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B); // A -> B 간선 추가
            indegree[B]++; 
        }

        // 진입 차수가 0인 노드들을 큐에 추가
        for (int node = 1; node <= N; node++) {
            if (indegree[node] == 0) queue.offer(node);
        }

        // 위상 정렬 수행
        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int next : graph.get(node)) {
                indegree[next]--;

                if (indegree[next] == 0) queue.offer(next);
            }

            output.append(node).append(" ");
        }

        System.out.println(output);
    }
}