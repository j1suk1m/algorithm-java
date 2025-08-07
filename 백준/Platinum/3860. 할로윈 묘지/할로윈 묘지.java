import java.util.*;
import java.io.*;

// 후보 1: 다익스트라 -> 가중치(시간)가 음수인 경우가 존재하므로 사용 불가
// 후보 2: 벨만 포드 -> 음의 가중치에도 동작하고, 음의 사이클을 감지할 수 있으므로 적합
// 후보 3: 플로이드 워셜 -> 정점의 개수가 최대 900개(W * H)이므로 O(V^3)에서 시간 초과

// 벨만 포드 알고즘을 위해 간선 리스트에 저장할 간선 객체
class Edge {
	int srcX;
	int srcY;
	int destX;
	int destY;
	int time; // (srcX, srcY) -> (destX, destY) 간선의 가중치
	 
	Edge(int srcX, int srcY, int destX, int destY, int time) {
		this.srcX = srcX;
		this.srcY = srcY;
		this.destX = destX;
		this.destY = destY;
		this.time = time;
	}
}

public class Main {
	static int W; // 묘지의 너비
	static int H; // 묘지의 높이
	static int G; // 묘비의 개수
	static int E; // 귀신 구멍의 개수
	static final int GRAVESTONE = -2; // 묘비가 있는 칸
	static final int GHOST_HOLE = -1; // 귀신 구멍이 있는 칸
	static final int GRASS = 0; // 잔디가 있는 칸
	static final long INF = Long.MAX_VALUE;
	static final String EOF = "0 0"; // 입력의 마지막 줄
	static final boolean HAS_NEGATIVE_CYCLE = true; // 음의 사이클 포함 여부
	static int[][] grave; // 묘지
	static long[][] times; // 최소 시간
	static final int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static List<Edge> edges; // 간선 리스트
	static final StringBuilder output = new StringBuilder(); // 결과 출력
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		String input = null;
		
		while (!(input = br.readLine()).equals(EOF)) {
			st = new StringTokenizer(input);
		
			W = Integer.parseInt(st.nextToken()); // 묘지의 너비
			H = Integer.parseInt(st.nextToken()); // 묘지의 높이
			
			grave = new int[W][H]; // 문제를 해석하면 new int[H][W]이지만, 편의를 위해 변경
			times = new long[W][H]; // 최소 시간
			edges = new ArrayList<>(); // 간선 리스트
			
			G = Integer.parseInt(br.readLine()); // 묘비의 개수
			
			// 묘지에 묘비 위치 저장
			for (int i = 0; i < G; i++) {
				st = new StringTokenizer(br.readLine());
				
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				grave[X][Y] = GRAVESTONE;
			}
			
			E = Integer.parseInt(br.readLine()); // 귀신 구멍의 개수
			
			// 묘지에 귀신 구멍 위치 저장
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int X1 = Integer.parseInt(st.nextToken());
				int Y1 = Integer.parseInt(st.nextToken());
				int X2 = Integer.parseInt(st.nextToken());
				int Y2 = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				grave[X1][Y1] = GHOST_HOLE;
				
				edges.add(new Edge(X1, Y1, X2, Y2, T)); // 간선 리스트에 '귀신 구멍 -> 잔디' 간선 추가
			}
			
			// 간선 리스트에 간선 추가
			// 유형 1: 잔디 -> 상하좌우 인접한 잔디
			// 유형 2: 잔디 -> 상하좌우 인접한 귀신 구멍
			for (int x = 0; x < W; x++) {
				for (int y = 0; y < H; y++) {
					if (grave[x][y] != GRASS) continue;
					if (x == W - 1 && y == H - 1) continue; // 출구에서는 간선을 만들지 않음
					
					// 상하좌우 이동
					for (int[] move : moves) {
						int nx = x + move[0];
						int ny = y + move[1];
						
						if (nx < 0 || nx >= W || ny < 0 || ny >= H) continue;
						if (grave[nx][ny] == GRAVESTONE) continue; // 묘비로는 갈 수 없음
						
						edges.add(new Edge(x, y, nx, ny, 1));
					}
				}
			}
			
			// 최소 시간을 무한으로 초기화
			for (int i = 0; i < W; i++) {
				Arrays.fill(times[i], INF);				
			}

			// 결과 출력
			// 유형 1: 음의 사이클을 포함하는 경우 Never
			// 유형 2: 출구에 도달할 수 없는 경우 Impossible
			// 유형 3: 이외의 경우 입구부터 출구까지의 최소 시간
			if (runBellmanFord() == HAS_NEGATIVE_CYCLE) {
				output.append("Never").append("\n");
			} else if (times[W - 1][H - 1] == INF) {
				output.append("Impossible").append("\n");
			} else {
				output.append(times[W - 1][H - 1]).append("\n");
			}
		}
		
		bw.write(output.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	// 벨만 포드 알고리즘을 이용한 최소 시간 계산
	static boolean runBellmanFord() {
		times[0][0] = 0L;
		
		// 최소 시간 갱신
		for (int i = 0; i < W * H - 1; i++) {
			for (Edge edge : edges) {
				if (times[edge.srcX][edge.srcY] == INF) continue;
				if (times[edge.destX][edge.destY] > times[edge.srcX][edge.srcY] + edge.time) {
					times[edge.destX][edge.destY] = times[edge.srcX][edge.srcY] + edge.time;
				}
			}
		}
		
		// 최대 간선의 개수(W * H - 1)를 사용했는데도 시간이 갱신됨 -> 음의 사이클 존재
		for (Edge edge : edges) {
			if (times[edge.srcX][edge.srcY] == INF) continue;
			if (times[edge.destX][edge.destY] > times[edge.srcX][edge.srcY] + edge.time) {
				return HAS_NEGATIVE_CYCLE;
			}			
		}
		
		return !HAS_NEGATIVE_CYCLE;
	}
}