import java.util.*;

class Node {
    int key;
    int distance;
    
    Node(int key, int distance) {
        this.key = key;
        this.distance = distance;
    }
}

class Solution {
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); 
    private static int[] nodeCount; // 인덱스 값과 최단 경로의 거리가 같은 노드의 개수
    private static boolean[] visited;
    private static Queue<Node> queue = new LinkedList<>();

    private static int bfs(int startNodeKey) {
        queue.add(new Node(startNodeKey, 0));
        visited[startNodeKey] = true;
        int maxDistance = 0; // 가장 멀리 떨어진 거리
        
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            
            maxDistance = Math.max(maxDistance, currentNode.distance);
            nodeCount[currentNode.distance]++;
            
            for (int nextNodeKey : graph.get(currentNode.key)) {
                if (!visited[nextNodeKey]) {
                    queue.add(new Node(nextNodeKey, currentNode.distance + 1));
                    visited[nextNodeKey] = true;
                }
            }
        }
        
        return maxDistance;
    }
    
    public int solution(int n, int[][] edge) {
        int startNodeKey = 1;
        int maxDistance;
        
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        nodeCount = new int[n];
        visited = new boolean[n + 1];
        
        // 그래프 저장
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        maxDistance = bfs(startNodeKey);
        
        // 가장 멀리 떨어진 노드의 개수 반환
        return nodeCount[maxDistance];
    }
}