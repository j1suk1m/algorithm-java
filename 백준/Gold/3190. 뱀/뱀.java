import java.util.*;
import java.io.*;

enum Direction {
    RIGHT(0, 0, 1),
    DOWN(1, 1, 0),
    LEFT(2, 0, -1),
    UP(3, -1, 0);

    private final int code;
    private final int dr; // delta of row // 행 방향의 변화량
    private final int dc; // delta of col // 열 방향의 변화량

    Direction(int code, int dr, int dc) {
        this.code = code;
        this.dr = dr;
        this.dc = dc;
    }

    public int getDr() {
        return dr;
    }

    public int getDc() {
        return dc;
    }

    public Direction turnRight() {
        return Direction.values()[(this.code + 1) % 4];
    }

    public Direction turnLeft() {
        return Direction.values()[(this.code + 3) % 4];
    }
}

class Position {
    private final int row; // 행 
    private final int col; // 열 

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Position getNextPosition(Direction direction) {
        return new Position(this.row + direction.getDr(), this.col + direction.getDc());
    }
}

class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokenizer;
    private static int[][] board; // 전체 보드 
    private static final Map<Integer, Character> directionChanges = new HashMap<>(); // 방향 변환 정보
    private static final Deque<Position> snakePositions = new ArrayDeque<>(); // 뱀을 구성하는 위치 정보
    private static int N; // 보드 크기
    private static int K; // 사과 개수
    private static int L; // 방향 변환 횟수
    private static final int WALL = -1; // 벽
    private static final int EMPTY = 0; // 칸
    private static final int APPLE = 1; // 사과
    private static final int SNAKE = 2; // 뱀
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine()); // 보드 크기
        K = Integer.parseInt(reader.readLine()); // 사과 개수

        board = new int[N + 2][N + 2]; // 편의를 위해 N * N 크기 보드의 바깥에 패딩 추가

        // 벽 위치 표시
        markWalls();

        // 사과 위치 표시
        markApples();

        L = Integer.parseInt(reader.readLine()); // 방향 변환 횟수

        // 방향 변환 정보 저장
        initDirectionChanges();

        // 초깃값 설정
        int time = 1;
        Direction direction = Direction.RIGHT;
        Position head = new Position(1, 1);

        // 시작점으로 이동
        move(head);

        while (true) {           
            // 다음 머리 위치 계산
            Position nextHeadPosition = head.getNextPosition(direction);

            // 다음 머리 위치가 유효한지 확인
            if (!isValidPosition(nextHeadPosition)) {
                break;
            }
            
            // 다음 머리 위치에 사과가 없는 경우 꼬리 제거
            if (!hasApple(nextHeadPosition)) {
                removeTail();
            }

            // 다음 머리 위치로 이동
            // 이동 시 해당 칸이 SNAKE(2)로 저장되어 APPLE(1)이 덮어 쓰일 수 있으므로 사과 존재 여부 확인 후 이동
            head = nextHeadPosition;
            move(head);
            
            // 방향을 변환해야 할 시점인 경우 변환
            if (directionChanges.containsKey(time)) {
                char nextDirection = directionChanges.get(time);
                direction = (nextDirection == 'L') ? direction.turnLeft() : direction.turnRight();
            }

            time++;
        }

        System.out.println(time);
    }

    // 벽이 있는 위치에 WALL(-1) 저장
    private static void markWalls() {
        for (int row = 0; row < N + 2; row++) {
            for (int col = 0; col < N + 2; col++) {
                if (row == 0 || row == N + 1 || col == 0 || col == N + 1) {
                    board[row][col] = WALL;
                }
            }
        }
    }

    // 사과가 놓인 위치에 APPLE(1) 저장
    private static void markApples() throws IOException {
        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int row = Integer.parseInt(tokenizer.nextToken());
            int col = Integer.parseInt(tokenizer.nextToken());

            board[row][col] = APPLE;
        }
    }

    // directionChanges 맵에 방향 변환 정보 저장
    // key = 방향 변환 시점, value = 변환될 방향
    private static void initDirectionChanges() throws IOException {
        for (int i = 0; i < L; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int time = Integer.parseInt(tokenizer.nextToken());
            char nextDirection = tokenizer.nextToken().charAt(0);

            directionChanges.put(time, nextDirection);
        }
    }

    // 뱀 머리 이동
    private static void move(Position head) {
        board[head.getRow()][head.getCol()] = SNAKE;
        snakePositions.addFirst(head);
    }

    // 전달된 position이 유효한 위치인지 확인
    // 벽이나 몸에 닿은 경우 false, 그 외의 경우 true 반환
    private static boolean isValidPosition(Position position) {
        int value = board[position.getRow()][position.getCol()];
        return value != WALL && value != SNAKE;
    }

    // 전달된 position에 사과가 놓여 있는지 확인
    private static boolean hasApple(Position position) {
        return board[position.getRow()][position.getCol()] == APPLE;
    }

    // 뱀 꼬리 제거
    private static void removeTail() {
        Position tail = snakePositions.removeLast();
        board[tail.getRow()][tail.getCol()] = EMPTY;
    }
}