import java.util.*;
import java.io.*;

class Main {
    static final String EOF = "0 0";
    static final String MEASURE = "!";
    static final String QUERY = "?";
    static final String UNKNOWN = "UNKNOWN";
    static final StringBuilder output = new StringBuilder();
    static int[] parent;
    static int[] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        String line = null;

        while (!(line = br.readLine()).equals(EOF)) {
            st = new StringTokenizer(line);
            
            int N = Integer.parseInt(st.nextToken()); // 샘플의 종류의 개수
            int M = Integer.parseInt(st.nextToken()); // 일의 수

            parent = new int[N + 1];
            dp = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i; // 자기 자신으로 초기화
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (command.equals(MEASURE)) {
                    int w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                } else if (command.equals(QUERY)) {
                    if (find(a) != find(b)) output.append(UNKNOWN).append("\n");
                    else output.append(dp[b] - dp[a]).append("\n");
                } else {
                    continue; // 이외의 명령 무시
                }
            }
        }

        System.out.println(output);
    }

    static int find(int node) {
        if (parent[node] != node) {
            int origin = parent[node];
            parent[node] = find(parent[node]);
            dp[node] += dp[origin];
        }

        return parent[node];
    }

    static void union(int node1, int node2, int weight) {
        int root1 = find(node1);
        int root2 = find(node2);
        
        if (root1 == root2) {
            return;
        } 

        parent[root2] = root1;
        dp[root2] = dp[node1] + weight - dp[node2];
    }
}