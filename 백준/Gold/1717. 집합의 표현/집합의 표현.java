import java.util.*;
import java.io.*;

public class Main {
    static int[] parent;
	static final int UNION = 0;
	static final int FIND = 1;
	static final String SAME_SET = "YES";
	static final String NOT_SAME_SET = "NO";
	static final StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); 
		int m = Integer.parseInt(st.nextToken()); // 연산의 개수
		
		initParent(n);

        // 연산 수행
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int operation = Integer.parseInt(st.nextToken());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			if (operation == UNION) {
				union(node1, node2);
			} else if (operation == FIND) {
				if (node1 == node2 || find(node1) == find(node2)) output.append(SAME_SET).append("\n");
				else output.append(NOT_SAME_SET).append("\n");
			} else {
				continue; // 이외의 연산 무시
			}
		}
		
		System.out.println(output);
	}
	
	static void initParent(int n) {
        parent = new int[n + 1];
        
		for (int node = 0; node < n + 1; node++) {
			parent[node] = node; // 자기 자신으로 초기화
		}
	}
	
    // node가 속한 집합의 대표 노드 번호 반환
	static int find(int node) {
		if (parent[node] != node) {
			return parent[node] = find(parent[node]);
		}
		
		return node;
	}
	 
    // node1이 속한 집합과 node2가 속한 집합을 하나로 합침
	static void union(int node1, int node2) {
        if (node1 == node2) return;
        
		int root1 = find(node1);
		int root2 = find(node2);
		
        if (root1 == root2) return;
        
		parent[Math.max(root1, root2)] = Math.min(root1, root2);
	}
}