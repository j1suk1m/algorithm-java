import java.util.*;
import java.io.*;

public class Main {
	static int[] nums;
	static int[] tree;
	static final int ROOT = 1;
	static final String SEPARATOR = "\n";
	static final int INF = (int) 1e9 + 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 정수의 개수
		int M = Integer.parseInt(st.nextToken()); // 쿼리의 개수
		
		nums = new int[N + 1];
		tree = new int[getTreeSize(N)];
		
		for (int i = 1; i < N + 1; i++) { // 1-based 인덱싱
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		buildTree(ROOT, 1, N);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			answer.append(query(ROOT, 1, N, Math.min(a, b), Math.max(a, b)))
			      .append(SEPARATOR);
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
		
		tree[node] = Math.min(tree[leftChild], tree[rightChild]);
	}
	
	static int query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) return INF;
		if (left <= start && end <= right) return tree[node];

		int leftChild = getLeftChild(node);
		int rightChild = getRightChild(node);
		int middle = getMiddle(start, end);
		
		int leftMin = query(leftChild, start, middle, left, right);
		int rightMin = query(rightChild, middle + 1, end, left, right);
		
		return Math.min(leftMin, rightMin);
	}
}