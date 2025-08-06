import java.util.*;
import java.io.*;

class Edge {
	int src;
	int dest;
	int time;
	
	Edge(int src, int dest, int time) {
		this.src = src;
		this.dest = dest;
		this.time = time;
	}
	
	Edge(int W, int srcX, int srcY, int destX, int destY, int time) {
		this.src = W * srcY + srcX;
		this.dest = W * destY + destX;
		this.time = time;
	}
	
	int getSrcX(int W) {
		return getX(W, src);
	}
	
	int getSrcY(int W) {
		return getY(W, src);
	}
	
	int getDestX(int W) {
		return getX(W, dest);
	}
	
	int getDestY(int W) {
		return getY(W, dest);
	}
	
	int getX(int W, int node) {
		return node % W;
	}
	
	int getY(int W, int node) {
		return node / W;
	}
}

public class Main {
	static int W; // 묘지의 너비
	static int H; // 묘지의 높이
	static int G; // 묘비의 개수
	static int E; // 귀신 구멍의 개수
	static int[][] grave; // 묘지
	static long[][] times;
	static final int GRAVESTONE = -1; // 묘비
	static final int GHOST_HOLE = -2; // 귀신 구멍
	static final int GRASS = 0; // 잔디
	static final long INF = Long.MAX_VALUE;
	static List<Edge> edges; // 간선 리스트
	static final int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 
	static final boolean HAS_NEGATIVE_CYCLE = true;
	static final String EOF = "0 0";
	static final StringBuilder output = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		String line = null;
		
		while (!(line = br.readLine()).equals(EOF)) {
			st = new StringTokenizer(line);
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(br.readLine());
			grave = new int[H][W];
			edges = new ArrayList<>();
			
			// 묘지에 묘비 위치 저장
			for (int i = 0; i < G; i++) {
				st = new StringTokenizer(br.readLine());
				
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				grave[Y][X] = GRAVESTONE;
			}
			
			E = Integer.parseInt(br.readLine());
			
			// 묘지에 귀신 구멍 위치 저장
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int X1 = Integer.parseInt(st.nextToken());
				int Y1 = Integer.parseInt(st.nextToken());
				int X2 = Integer.parseInt(st.nextToken());
				int Y2 = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				grave[Y1][X1] = GHOST_HOLE;
				edges.add(new Edge(W, X1, Y1, X2, Y2, T)); // 간선 추가
			}
			
			// 간선 리스트에 간선 저장
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (i == H - 1 && j == W - 1) continue;
					if (grave[i][j] == GRASS) {
						for (int[] move : moves) {
							int ni = i + move[0];
							int nj = j + move[1];
							
							if (ni < 0 || ni >= H || nj < 0 || nj >= W) continue;
							if (grave[ni][nj] == GRAVESTONE) continue;

							edges.add(new Edge(W, j, i, nj, ni, 1));
						}
					}
				}
			}
		
			times = new long[H][W];

			// 시간을 무한으로 초기화
			for (int i = 0; i < H; i++) {
				Arrays.fill(times[i], INF);
			}
			
			if (runBellmanFord() == HAS_NEGATIVE_CYCLE) {
				output.append("Never");
			} else if (times[H - 1][W - 1] == INF) {
				output.append("Impossible");
			} else {
				output.append(times[H - 1][W - 1]);
			}
			
			output.append("\n");	
		}
		
		bw.write(output.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static boolean runBellmanFord() {
		times[0][0] = 0;
		
		for (int i = 0; i < W * H - 1; i++) {
			for (Edge edge : edges) {
				if (times[edge.getSrcY(W)][edge.getSrcX(W)] == INF) continue;
				times[edge.getDestY(W)][edge.getDestX(W)] = Math.min(times[edge.getDestY(W)][edge.getDestX(W)], 
																	 times[edge.getSrcY(W)][edge.getSrcX(W)] + edge.time);
			}
		}
		
		for (Edge edge : edges) {
			if (times[edge.getSrcY(W)][edge.getSrcX(W)] == INF) continue;
			if (times[edge.getDestY(W)][edge.getDestX(W)] > times[edge.getSrcY(W)][edge.getSrcX(W)] + edge.time) return HAS_NEGATIVE_CYCLE;
		}
		
		return !HAS_NEGATIVE_CYCLE;
	}
}