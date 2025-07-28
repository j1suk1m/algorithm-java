import java.util.*;
import java.io.*;

class Position {
	int x; // 행 좌표 // 0-based
	int y; // 열 좌표 // 0-based
	int time; // 분 단위
	char type;
	
	Position(int x, int y, int time, char type) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.type = type;
	}
	
	public boolean equals(Position o) {
		return o.x == this.x && o.y == this.y;
	}
}

public class Main {
	private static final char EMPTY = '.'; // 비어있는 곳
	private static final char WATER = '*'; // 물이 차있는 곳
	private static final char ROCK = 'X'; // 돌
	private static final char CAVE = 'D'; // 비버의 굴 // 목적지
	private static final char HEDGEHOG = 'S'; // 고슴도치 위치 // 시작점
	private static char[][] forest; 
	private static final Deque<Position> queue = new ArrayDeque<>();
	private static final List<Position> waters = new ArrayList<>();
	private static final int[] dx = {-1, 1, 0, 0};
	private static final int[] dy = {0, 0, -1, 1};
    private static Position start = null;
	private static Position cave = null;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
		
		int R = Integer.parseInt(tokenizer.nextToken());
		int C = Integer.parseInt(tokenizer.nextToken());
		
        // 숲 그래프 초기화
		initForest(reader, R, C);
		
		if (!canArrive(R, C)) {
			System.out.println("KAKTUS");
		}
	}
	
	private static void initForest(BufferedReader reader, int R, int C) throws IOException {
		forest = new char[R][C];

		for (int i = 0; i < R; i++) {
			String row = reader.readLine();
			
			for (int j = 0; j < C; j++) {
				forest[i][j] = row.charAt(j);
				
				if (start == null && row.charAt(j) == HEDGEHOG) {
					start = new Position(i, j, 0, HEDGEHOG);
				} else if (row.charAt(j) == WATER) {
					waters.add(new Position(i, j, 0, WATER));
				} else if (cave == null && row.charAt(j) == CAVE) {
					cave = new Position(i, j, 0, CAVE);
				}
			}
		}	
	}
	
	private static boolean canArrive(int R, int C) {
		waters.forEach(water -> queue.add(water));
		
		queue.add(start);
		forest[start.x][start.y] = ROCK; // 방문 처리
		
		while (!queue.isEmpty()) {
			Position current = queue.poll();
			
            // 고슴도치가 굴에 도착
			if (current.equals(cave) && current.type == HEDGEHOG) {
				System.out.println(current.time);
				return true;
			}
			
            // 인접한 칸의 유효성 확인 후 이동
			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				
				char next = forest[nx][ny];
				
				if (current.type == HEDGEHOG && next != ROCK && next != WATER) {
					queue.add(new Position(nx, ny, current.time + 1, current.type));
					forest[nx][ny] = ROCK; // 방문 처리
				} else if (current.type == WATER && next == EMPTY) {
					queue.add(new Position(nx, ny, current.time + 1, current.type));
					forest[nx][ny] = WATER; // 방문 처리
				}
			}
		}
		
		return false;
	}
}