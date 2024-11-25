import java.util.*;

class Pair {
    public int x;
    public int y;
    public int distance;

    Pair(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

class Solution {
    private static int n;
    private static int m;
    private static int wall = 0;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Queue<Pair> queue = new LinkedList<>();

    private static int bfs(int[][] maps) {
        int answer = -1;
        maps[0][0] = wall;
        queue.add(new Pair(0, 0, 1));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            // 상대 팀 진영에 도착
            if (pair.x == n - 1 && pair.y == m - 1) {
                answer = pair.distance;
                break;
            }

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                } else if (maps[nx][ny] == wall) {
                    continue;
                } else { // 맵을 벗어나지 않고 벽이 아닌 경우
                    maps[nx][ny] = wall;
                    queue.add(new Pair(nx, ny, pair.distance + 1));
                }
            }
        }
        
        return answer;
    }

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;

        return bfs(maps);
    }
}