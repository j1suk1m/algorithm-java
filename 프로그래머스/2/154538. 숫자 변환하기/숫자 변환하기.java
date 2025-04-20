import java.util.LinkedList;
import java.util.Queue;

class Node {
    private int value; // 숫자 값
    private int operationCount; // 연산 횟수
    
    Node(int value, int operationCount) {
        this.value = value;
        this.operationCount = operationCount;
    }
    
    public int getValue() {
        return value;
    }
    
    public int getOperationCount() {
        return operationCount;
    }
}

class Solution {
    private static final int INF = 1000001;
    private final Queue<Node> queue = new LinkedList<>();
    private boolean[] visited = new boolean[INF];
    
    public int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }
    
    // bfs를 이용한 최소 연산 횟수 계산
    private int bfs(int x, int y, int n) {
        visited[x] = true;
        queue.add(new Node(x, 0));
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int value = current.getValue();
            int operationCount = current.getOperationCount();
            
            if (value == y) {
                return operationCount;
            } 
            
            if (value * 3 < INF && !visited[value * 3]) {
                visited[value * 3] = true;
                queue.add(new Node(value * 3, operationCount + 1));
            }
                        
            if (value * 2 < INF && !visited[value * 2]) {
                visited[value * 2] = true;
                queue.add(new Node(value * 2, operationCount + 1));
            }   
            
            if (value + n < INF && !visited[value + n]) {
                visited[value + n] = true;
                queue.add(new Node(value + n, operationCount + 1));
            }
        }
        
        return -1;
    }
}