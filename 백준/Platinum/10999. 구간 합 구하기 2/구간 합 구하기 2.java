import java.util.*;
import java.io.*;

public class Main {
	static int N; // 수의 개수, [1, 1e6]
	static int M; // 수의 변경 횟수, [1, 1e4]
	static int K; // 구간 합 질의 횟수 [1, 1e4]
	static long[] arr;
	static long[] tree; // 느리게 갱신되는 세그먼트 트리
	static long[] lazy;
	static final int ROOT = 1;
	static final int UPDATE_RANGE = 1;
	static final int QUERY = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N + 1];
		tree = new long[getTreeSize()];
		lazy = new long[tree.length];
		
		// 전체 수 저장
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		// 세그먼트 트리 생성
		buildTree(ROOT, 1, N);
		
		// 변경 또는 질의
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int command = Integer.parseInt(st.nextToken());
			
			if (command == UPDATE_RANGE) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				long diff = Long.parseLong(st.nextToken());
				updateRange(ROOT, 1, N, left, right, diff);
			} else if (command == QUERY) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				sb.append(query(ROOT, 1, N, left, right)).append("\n");
			} else {
				continue; // 이외의 명령 무시
			}
		}
		
		// 결과 출력
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static int getTreeSize() {
		int height = (int) Math.ceil(Math.log(N) / Math.log(2));
		return (1 << (height + 1));
	}
	
	static int getMiddle(int start, int end) {
		return start + (end - start) / 2;
	}
	
	static void buildTree(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}
		
		int middle = getMiddle(start, end);
		
		buildTree(node * 2, start, middle);
		buildTree(node * 2 + 1, middle + 1, end);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	static void updateLazy(int node, int start, int end) {
		if (lazy[node] == 0L) return; // 지연 갱신할 값이 없으면 종료
		
		tree[node] += (end - start + 1) * lazy[node]; // 하위 노드의 개수만큼 갱신
		
		if (start != end) { // 리프 노드가 아니라면 자식 노드들에게 지연 갱신 예약
			lazy[node * 2] += lazy[node];
			lazy[node * 2 + 1] += lazy[node];
		}
		
		lazy[node] = 0L; // 갱신 완료
	}
	
	static void updateRange(int node, int start, int end, int left, int right, long diff) {
		updateLazy(node, start, end); // 지연 갱신 처리
		
		if (end < left || right < start) return; // 갱신 범위와 관계 없는 노드일 경우 종료
		
		if (left <= start && end <= right) { // 노드가 대표하는 구간이 갱신 범위에 완전히 포함될 경우
			tree[node] += (end - start + 1) * diff; // 하위 노드의 개수만큼 갱신
			
			if (start != end) { // 리프 노드가 아니라면 자식 노드들에게 지연 갱신 예약
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			
			return;
		}
		
		int middle = getMiddle(start, end);
		
		updateRange(node * 2, start, middle, left, right, diff);
		updateRange(node * 2 + 1, middle + 1, end, left, right, diff);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	static long query(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end); // 지연 갱신 처리
		
		if (end < left || right < start) return 0L;
		if (left <= start && end <= right) return tree[node];
		
		int middle = getMiddle(start, end);
		
		long leftSum = query(node * 2, start, middle, left, right);
		long rightSum = query(node * 2 + 1, middle + 1, end, left, right);
		
		return leftSum + rightSum;
	}
 }