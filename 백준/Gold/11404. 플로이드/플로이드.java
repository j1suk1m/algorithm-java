import java.util.*;
import java.io.*;

public class Main {
	static int n; // 도시의 개수
	static int m; // 버스의 개수
	static int[][] costs; // costs[s][d] == s에서 d로 가는 데 필요한 최소 비용
	static final int INF = (int) 1e7;
	static final StringBuilder output = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		costs = new int[n + 1][n + 1];
		
		// 비용 초기화
		for (int i = 1; i <= n; i++) {
			Arrays.fill(costs[i], INF);
			costs[i][i] = 0;
		}
		
		// 인접 행렬 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			costs[src][dest] = Math.min(costs[src][dest], cost);
		}
		
		// 플로이드 워셜 알고리즘을 이용한 최소 비용 계산
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == j || j == k || k == i) continue;
					
					// 정점 k를 경유하는 것이 더 비용이 적을 경우 비용 갱신
					if (costs[i][j] > costs[i][k] + costs[k][j]) {
						costs[i][j] = costs[i][k] + costs[k][j];
					}
				}
			}
		}
		
		// 출력 생성
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				output.append(costs[i][j] < INF ? costs[i][j] : 0).append(" ");
			}
			
			output.append("\n");
		}
		
		bw.write(output.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}