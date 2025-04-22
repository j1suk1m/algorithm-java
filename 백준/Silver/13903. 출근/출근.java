import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Rule {
    int dx;
    int dy;

    Rule(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}

class Block {
    int x;
    int y;
    int count;

    Block(int x, int y) {
        this(x, y, 0);
    }

    Block(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokenizer;
    private static int R; // 보도 블록의 세로 크기를 저장한다.
    private static int C; // 보도 블록의 가로 크기를 저장한다.
    private static int N; // 이동 규칙의 개수를 저장한다.
    private static int[][] graph; // 전체 보도 블록을 저장한다.
    private static final Queue<Block> queue = new LinkedList<>();
    private static final List<Rule> rules = new ArrayList<>(); // 이동 규칙을 저장한다.
    private static final int VERTICAL_BLOCK = 1;
    private static final int VISITED = 0;
    private static int answer = -1;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());

        // 보도 블록의 크기를 저장한다.
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        // 그래프를 초기화한다.
        graph = new int[R][C];
        initGraph();

        if (!queue.isEmpty()) {

            // 이동 규칙 리스트를 초기화한다.
            N = Integer.parseInt(reader.readLine());
            initRules();

            // 너비 우선 탐색을 통해 최소 이동 횟수를 계산한다.
            bfs();

        }

        System.out.println(answer);
    }

    /**
     * 그래프를 초기화한다.
     */
    private static void initGraph() throws IOException {
        for (int x = 0; x < R; x++) {
            String[] row = reader.readLine().split(" ");

            for (int y = 0; y < C; y++) {
                graph[x][y] = Integer.parseInt(row[y]);

                // 첫째 줄에 세로 블록이 있다면 큐에 저장한다.
                if (x == 0 && graph[x][y] == VERTICAL_BLOCK) {
                    graph[x][y] = VISITED; // 방문 처리한다.
                    queue.add(new Block(x, y));
                }
            }
        }
    }

    /**
     * 이동 규칙 리스트를 초기화한다.
     */
    private static void initRules() throws IOException {
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int dx = Integer.parseInt(tokenizer.nextToken());
            int dy = Integer.parseInt(tokenizer.nextToken());

            rules.add(new Rule(dx, dy));
        }
    }

    /**
     * 너비 우선 탐색을 통해 최소 이동 횟수를 계산한다.
     */
    private static void bfs() {
        while (!queue.isEmpty()) {
            Block current = queue.poll();

            // 마지막 줄의 블록에 도착하면 반복문을 종료한다.
            if (current.x == R - 1) {
                answer = current.count;
                break;
            }

            // 이동 규칙을 차례로 적용하며 다음 블록을 방문한다.
            for (Rule rule : rules) {
                int nx = current.x + rule.dx;
                int ny = current.y + rule.dy;

                if (canMoveTo(nx, ny)) {
                    graph[nx][ny] = VISITED; // 방문 처리한다.
                    queue.add(new Block(nx, ny, current.count + 1));
                }
            }
        }
    }

    /**
     * 다음 블록을 방문할 수 있는지 확인한다.
     * @param nx 다음 블록의 행 좌표
     * @param ny 다음 블록의 열 좌표
     * @return 방문 가능 여부
     */
    private static boolean canMoveTo(int nx, int ny) {
        return 0 <= nx && nx < R && 0 <= ny && ny < C && graph[nx][ny] == VERTICAL_BLOCK;
    }
}