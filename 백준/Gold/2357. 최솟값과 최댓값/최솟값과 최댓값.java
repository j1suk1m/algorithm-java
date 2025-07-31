import java.util.*;
import java.io.*;

class Node {
	int min;
	int max;
	
	Node(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	static Node merge(Node leftChild, Node rightChild) {
		int min = Math.min(leftChild.min, rightChild.min);
		int max = Math.max(leftChild.max, rightChild.max);
		
		return new Node(min, max);
	}
	
	static Node generateDummy() {
		return new Node((int) 1e9 + 1, 0);
	}
}

public class Main {
	static int[] nums;
	static Node[] tree;
	static final int ROOT = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 정수의 개수
		int M = Integer.parseInt(st.nextToken()); // 쿼리의 개수
		
		nums = new int[N + 1];
		tree = new Node[getTreeSize(N)];
		
		for (int i = 1; i < N + 1; i++) { // 1-based 인덱싱
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		buildTree(ROOT, 1, N);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			Node result = query(ROOT, 1, N, Math.min(a, b), Math.max(a, b));
			
			answer.append(result.min)
				  .append(" ")
				  .append(result.max)
			      .append("\n");
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
			tree[node] = new Node(nums[start], nums[start]);
			return;
		}
		
		int leftChild = getLeftChild(node);
		int rightChild = getRightChild(node);
		int middle = getMiddle(start, end);
		
		buildTree(leftChild, start, middle);
		buildTree(rightChild, middle + 1, end);
		
		tree[node] = Node.merge(tree[leftChild], tree[rightChild]);
	}
	
	static Node query(int node, int start, int end, int left, int right) {
		if (end < left || right < start) return Node.generateDummy();
		if (left <= start && end <= right) return tree[node];
		
		int leftChild = getLeftChild(node);
		int rightChild = getRightChild(node);
		int middle = getMiddle(start, end);
		
		Node leftChildNode = query(leftChild, start, middle, left, right);
		Node rightChildNode = query(rightChild, middle + 1, end, left, right);
		
		return Node.merge(leftChildNode, rightChildNode);
	}
}