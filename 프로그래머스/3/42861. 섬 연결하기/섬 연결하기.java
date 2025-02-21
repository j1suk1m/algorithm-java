import java.util.PriorityQueue;

class Solution {
    
    private int[] parent;
    private final PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );
    
    public int solution(int n, int[][] costs) {
        int answer = 0; // 건설 비용
        int linkCount = 0; // 연결 횟수
        
        parent = new int[n];
        initParentNodes(n);
        
        initPriorityQueue(costs);

        while (linkCount < n - 1) {
            int[] cost = priorityQueue.poll();
            int node1 = cost[0];
            int node2 = cost[1];
            
            // 사이클을 만들지 않는 경우
            if (find(node1) != find(node2)) {
                union(node1, node2);
                answer += cost[2];
                linkCount++;
            }
        }
        
        return answer;
    }
    
    // 부모 노드를 자기 자신으로 초기화
    private void initParentNodes(int n) {
        for (int node = 0; node < n; node++) {
            parent[node] = node;
        }
    }
    
    // 우선순위 큐 초기화
    private void initPriorityQueue(int[][] costs) {
        for (int[] cost : costs) {
            priorityQueue.offer(cost);
        }
    }
    
    private int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        
        return parent[node];
    }
    
    private void union(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);
        
        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }
    
}