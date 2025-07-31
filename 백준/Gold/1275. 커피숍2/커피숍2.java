import java.util.*;
import java.io.*;

public class Main {
	static long[] nums;
	static long[] tree;
	static final int ROOT = 1;
	static final String SEPARATOR = "\n";
	static final StringBuilder answer = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int Q = Integer.parseInt(st.nextToken()); // 턴의 개수
		
		nums = new long[N + 1];
		tree = new long[getTreeSize(N)];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i < N + 1; i++) { // 1 - based 인덱싱
			nums[i] = Long.parseLong(st.nextToken());
		}
		
		buildTree(ROOT, 1, N);
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			answer.append(query(ROOT, 1, N, Math.min(x, y), Math.max(x, y)))
				  .append(SEPARATOR);
			
			if (i == Q - 1) break;
			
			update(ROOT, 1, N, a, b);
		}
		
		System.out.println(answer);
	}

	static int getTreeSize(int N) {
		int height = (int) Math.ceil(Math.log(N) / Math.log(2));
		int treeSize = (1 << (height + 1));
		return treeSize;
	}
	
	static int getLeftChild(int node) {
		return node * 2;
	}
	
	static int getRightChild(int node) {
		return node * 2 + 1;
	}
	
	static int getMiddle(int start, int end) {
		return start + (end - start) / 2;
	}
	
	static void buildTree(int node, int start, int end) {
		if (start == end) {
			tree[node] = nums[start];
			return;
		}
		
		int leftChild = getLeftChild(node);
		int rightChild = getRightChild(node);
		int middle = getMiddle(start, end);
		
		buildTree(leftChild, start, middle);
		buildTree(rightChild, middle + 1, end);
		
		tree[node] = tree[leftChild] + tree[rightChild];
	}
	
	static long query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) return 0L;
		if (left <= start && end <= right) return tree[node];
		
		int leftChild = getLeftChild(node);
		int rightChild = getRightChild(node);
		int middle = getMiddle(start, end);
		
		long leftSum = query(leftChild, start, middle, left, right);
		long rightSum = query(rightChild, middle + 1, end, left, right);
		
		return leftSum + rightSum;
	}
	
	static void update(int node, int start, int end, int index, long value) {
		if (index < start || end < index) return;
		
		if (start == end) {
			nums[index] = value;
			tree[node] = value;
			return;
		}
		
		int leftChild = getLeftChild(node);
		int rightChild = getRightChild(node);
		int middle = getMiddle(start, end);
		
		update(leftChild, start, middle, index, value);
		update(rightChild, middle + 1, end, index, value);
		
		tree[node] = tree[leftChild] + tree[rightChild];	
	}
}