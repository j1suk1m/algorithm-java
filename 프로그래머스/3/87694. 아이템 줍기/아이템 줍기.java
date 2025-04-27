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
    private static final int MAX_SIZE = 102;
    private static final int GRID_SIZE = 100;
    
    private int[][] graph = new int[MAX_SIZE][MAX_SIZE];
    private Queue<Node> queue = new LinkedList<>();
    private final int[] dx = { -1, 1, 0, 0 };
    private final int[] dy = { 0, 0, -1, 1 };

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        initializeGraph();
        fillGraphWithRectangle(rectangle);
        
        return bfs(characterX, characterY, itemX, itemY);
    }
    
    private void initializeGraph() {
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                graph[i][j] = OUTSIDE;
            }
        }
    }

    private void fillGraphWithRectangle(int[][] rectangle) {
        for (int[] rec : rectangle) {
            int x1 = rec[0] * 2;
            int x2 = rec[2] * 2;
            int y1 = rec[1] * 2;
            int y2 = rec[3] * 2;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (x1 < i && i < x2 && y1 < j && j < y2) {
                        graph[i][j] = INSIDE; // 내부 구역
                    }

                    if (graph[i][j] != INSIDE) {
                        graph[i][j] = EDGE; // 외부 구역
                    }
                }
            }
        }
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY) {
        graph[characterX * 2][characterY * 2] = VISITED;
        queue.add(new Node(characterX * 2, characterY * 2, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.x == itemX * 2 && current.y == itemY * 2) {
                return current.distance / 2; // 2배된 거리 값을 원래 크기로 나누어 반환
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isValid(nx, ny)) {
                    graph[nx][ny] = VISITED;
                    queue.add(new Node(nx, ny, current.distance + 1));
                }
            }
        }

        return -1; // 경로를 찾을 수 없는 경우
    }

    private boolean isValid(int nx, int ny) {
        return 1 <= nx && nx <= GRID_SIZE && 1 <= ny && ny <= GRID_SIZE && graph[nx][ny] == EDGE;
    }
}