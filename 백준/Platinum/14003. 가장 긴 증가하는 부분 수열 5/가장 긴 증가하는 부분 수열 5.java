import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Pair {
	int value;
	int index;
	
	Pair(int value, int index) {
		this.value = value;
		this.index = index;
	}
}

class Node {
	int length; // LIS 길이
	int index; // LIS에서 가장 마지막에 등장하는 숫자의 배열 인덱스
	
	Node(int length, int index) {
		this.length = length;
		this.index = index;
	}
}

public class Main {
	static final int ROOT = 1;
	static final int NONE = -1;
	static int N; // 수열의 크기
	static int[] A; // 수열
	static Pair[] pairs; // 수열에서의 인덱스를 정렬 후에도 유지하기 위해 사용
	static Node[] tree; // 인덱스 트리
	static int[] dp; // 수열에 동일한 값이 있을 경우 서로 영향을 주지 않고 쿼리하기 위해 사용
	static int[] prev; // prev[i] -> A[i]로 끝나는 LIS에서, A[i] 직전에 등장하는 값의 인덱스
	static final List<Integer> reversedSequence = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); // 수열의 크기
		
		A = new int[N];
		pairs = new Pair[N];
		tree = new Node[getTreeSize(N)];
		dp = new int[N];
		prev = new int[N];
		st = new StringTokenizer(br.readLine());
		
		// 수열 저장
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
			A[i] = value;
			pairs[i] = new Pair(value, i);
		}
		
		// 값에 대해 오름차순 정렬
		Arrays.sort(pairs, (p1, p2) -> (Integer.compare(p1.value, p2.value)));
		
        // 직전 인덱스 배열 초기화
		Arrays.fill(prev, NONE);

        // 인덱스 트리 초기화
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node(0, NONE);
        }
        
		int i = 0;
		
		while (i < N) {
			int j = getUpperBound(pairs[i].value); // 현재 값을 초과하는 값이 처음 발견되는 인덱스
			
			// 동일한 값에 대해 쿼리
            // 바로 트리에 업데이트할 경우, LIS를 구하는 데 영향을 받음
			for (int k = i; k < j; k++) {
				int index = pairs[k].index;
				Node result = query(ROOT, 0, N - 1, 0, index - 1);
				dp[index] = result.length + 1;
				prev[index] = result.index;
			}
			
			// 트리 업데이트
			for (int k = i; k < j; k++) {
				int index = pairs[k].index;
				update(ROOT, 0, N - 1, index, new Node(dp[index], index));
			}
			
			i = j;
		}
		
        // 전체 구간에 대한 LIS 질의
		Node result = query(ROOT, 0, N - 1, 0, N - 1);
        
        int length = result.length;
		int index = result.index;
        
        bw.write(String.valueOf(length));
        bw.write("\n");
        
		reversedSequence.add(A[index]); // LIS의 마지막 숫자
		
        // 직전 인덱스 추적
		while (prev[index] != NONE) {
			reversedSequence.add(A[prev[index]]);
			index = prev[index];
		}
		
        // LIS를 앞에서부터 출력하기 위해 뒤집기
		Collections.reverse(reversedSequence);
        String output = reversedSequence.stream()
                                        .map(String::valueOf)
                                        .collect(Collectors.joining(" "));
        
		bw.write(output);
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static int getTreeSize(int N) {
		int height = (int) Math.ceil(Math.log(N) / Math.log(2));
		int treeSize = (1 << (height + 1));
		return treeSize;
	}
	
	static int getMiddle(int start, int end) {
		return start + (end - start) / 2;
	}
	
	// 정렬된 배열에서 처음으로 target보다 큰 값을 갖는 인덱스 반환
	static int getUpperBound(int target) {
		int left = 0;
		int right = N;
		
		while (left < right) {
			int middle = getMiddle(left, right);
			
			if (pairs[middle].value <= target) {
				left = middle + 1;
			} else {
				right = middle;
			}
		}
		
		return left;
	}
	
	static Node query(int node, int start, int end, int left, int right) {
		if (end < left || right < start) return new Node(0, NONE);
		if (left <= start && end <= right) return tree[node];
		
		int middle = getMiddle(start, end);
		
		Node leftResult = query(node * 2, start, middle, left, right);
		Node rightResult = query(node * 2 + 1, middle + 1, end, left, right);
		
		return leftResult.length > rightResult.length ? leftResult : rightResult;
	}
	
	static void update(int node, int start, int end, int index, Node value) {
		if (index < start || end < index) return;
		
		if (start == end && tree[node].length < value.length) {
			tree[node] = value;
			return;
		}
		
		int middle = getMiddle(start, end);
		
		update(node * 2, start, middle, index, value);
		update(node * 2 + 1, middle + 1, end, index, value);
		
		tree[node] = tree[node * 2].length > tree[node * 2 + 1].length ? tree[node * 2] : tree[node * 2 + 1];
	}
}