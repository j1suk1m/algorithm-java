import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int x; // 행 // 0부터 시작
    public int y; // 열 // 0부터 시작
    public int distance; // 지나온 칸의 개수 // 1부터 시작
    
    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

class Solution {
    private static final int WALL = 0; // 벽이 있는 자리
    private static final int SPACE = 1; // 벽이 없는 자리
    private static final int DIRECTION_COUNT = 4; // 이동 가능한 방향 개수
    private final int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    private final int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우
    private final Queue<Node> queue = new LinkedList<>();
    
    public int solution(int[][] maps) {
        int startX = 0;
        int startY = 0;
        int endX = maps.length - 1;
        int endY = maps[0].length - 1;
        
        return bfs(maps, startX, startY, endX, endY);
    }
    
    private int bfs(int[][] maps, int startX, int startY, int endX, int endY) {
        int answer = -1;
        
        maps[startX][startY] = WALL; // 방문 표시 // 벽이 있는 자리로 변경
        queue.add(new Node(startX, startY, 1)); // 큐에 추가
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            if (current.x == endX && current.y == endY) { // 종료 조건
                answer = current.distance;
                break;
            }
            
            for (int i = 0; i < DIRECTION_COUNT; i++) { // 상하좌우 탐색
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                if (nx < 0 || nx > endX || ny < 0 || ny > endY) { // 범위를 벗어나는 경우
                    continue;
                } else if (maps[nx][ny] == WALL) { // 벽이 있는 자리인 경우
                    continue;
                } else {
                    maps[nx][ny] = WALL; // 방문 표시 // 벽이 있는 자리로 변경
                    queue.add(new Node(nx, ny, current.distance + 1)); // 큐에 추가
                }
            }
        }
        
        return answer;
    }
}