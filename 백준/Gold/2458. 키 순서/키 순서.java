import java.util.*;
import java.io.*;

class Main {    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> reverseGraph = new ArrayList<>();
        int answer = 0;
        
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 키 비교 횟수

        // 리스트 초기화
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // 연결 관계 저장
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int smaller = Integer.parseInt(st.nextToken()); // 키가 더 작은 학생의 번호
            int taller = Integer.parseInt(st.nextToken()); // 키가 더 큰 학생의 번호

            graph.get(smaller).add(taller); // smaller -> taller 간선 추가
            reverseGraph.get(taller).add(smaller); // taller -> smaller 간선 추가
        }

        for (int node = 1; node < N + 1; node++) {
            int tallerCount = dfs(graph, new boolean[N + 1], node) - 1;
            int smallerCount = dfs(reverseGraph, new boolean[N + 1], node) - 1;

            if (tallerCount + smallerCount == N - 1) answer++;
        }

        System.out.println(answer);
    }

    static int dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true;
        int count = 0;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                count += dfs(graph, visited, next);
            }
        }

        return count + 1;
    }
}