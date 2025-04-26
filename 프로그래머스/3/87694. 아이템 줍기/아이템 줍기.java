import java.util.*;

class Node {
    int x;
    int y;
    int distance;
    
    Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

class Solution {
    private static final int OUTSIDE = -1;
    private static final int INSIDE = 0;
    private static final int EDGE = 1;
    private static final int VISITED = 0;
    private int[][] graph = new int[102][102];
    private Queue<Node> queue = new LinkedList<>();
    private final int[] dx = { -1, 1, 0, 0 };
    private final int[] dy = { 0, 0, -1, 1 };
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        for (int i = 0; i < 102; i++) {
            for (int j = 0; j < 102; j++) {
                graph[i][j] = OUTSIDE;
            }
        }
        
        for (int k = 0; k < rectangle.length; k++) {
            int x1 = rectangle[k][0] * 2;
            int x2 = rectangle[k][2] * 2;
            int y1 = rectangle[k][1] * 2;
            int y2 = rectangle[k][3] * 2;
            
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (x1 < i && i < x2 && y1 < j && j < y2) {
                        graph[i][j] = INSIDE;
                    }
                    
                    if (graph[i][j] != INSIDE) {
                        graph[i][j] = EDGE;
                    }
                }
            }
        }
        
        graph[characterX * 2][characterY * 2] = VISITED;
        queue.add(new Node(characterX * 2, characterY * 2, 0));
        
        int answer = 0;
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            if (current.x == itemX * 2 && current.y == itemY * 2) {
                answer = current.distance / 2;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                if (1 <= nx && nx <= 100 && 1 <= ny && ny <= 100 && graph[nx][ny] == EDGE) {
                    graph[nx][ny] = VISITED;
                    queue.add(new Node(nx, ny, current.distance + 1));
                }
            }
        }
        
        return answer;
    }
}