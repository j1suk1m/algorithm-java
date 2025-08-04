import java.util.*;
import java.io.*;

class Main {
    static int[][] edges; // 간선 리스트
    static int[] parent;
    static int selectedEdgeCount; // 최소 신장 트리를 만들기 위해 선택된 간선의 개수 // 정점 개수 - 1과 같아야 함
    static int totalCost = 0; // 최소 신장 트리의 전체 가중치
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int M = Integer.parseInt(br.readLine()); // 컴퓨터를 연결하는 선의 수

        edges = new int[M][3];
        parent = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i; // 자기 자신으로 초기화
        }

        // 연결 관계 저장 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                edges[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가중치에 대해 오름차순 정렬
        Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));

        // 최소 신장 트리 생성
        makeMST(N);

        System.out.println(totalCost);        
    }

    // 전체 노드가 N개일 때, 최소 신장 트리 생성
    static void makeMST(int N) {
        for (int[] edge : edges) {
            if (selectedEdgeCount == N - 1) break; // 종료 조건
            
            int node1 = edge[0];
            int node2 = edge[1];
            int cost = edge[2];

            if (node1 == node2) continue;

            int root1 = find(node1);
            int root2 = find(node2);

            if (root1 == root2) continue; // node1과 node2를 연결하는 경로가 이미 존재

            union(root1, root2);
            
            selectedEdgeCount++;
            totalCost += cost;
        }        
    }

    // node의 루트 저장 및 반환
    static int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    // 합집합
    static void union(int root1, int root2) {
        parent[Math.max(root1, root2)] = Math.min(root1, root2);
    }
}