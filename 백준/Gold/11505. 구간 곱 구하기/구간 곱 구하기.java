import java.util.*;
import java.io.*;

class Main {
    static long[] nums;
    static long[] tree;
    static final int MOD = (int) 1e9 + 7;
    static final int ROOT = 1;
    static final int UPDATE = 1;
    static final int QUERY = 2;
    static final String SEPARATOR = "\n";
    static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 곱 조회 횟수

        nums = new long[N + 1];
        tree = new long[getTreeSize(N)];
        
        for (int i = 1; i < N + 1; i++) { // 1-based 인덱싱
            nums[i] = Long.parseLong(br.readLine());
        }

        buildTree(ROOT, 1, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());

            if (command == UPDATE) {
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(ROOT, 1, N, index, value);
            } else if (command == QUERY) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                answer.append(query(ROOT, 1, N, left, right))
                      .append(SEPARATOR);
            } else {
                continue; // 이외의 명령 무시
            }
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
        if (start == end) { // 리프 노드인 경우
            tree[node] = nums[start];
        } else {
            buildTree(getLeftChild(node), start, getMiddle(start, end));
            buildTree(getRightChild(node), getMiddle(start, end) + 1, end);

            tree[node] = ((tree[getLeftChild(node)] % MOD) * (tree[getRightChild(node)] % MOD)) % MOD; 
        }
    }

    static void update(int node, int start, int end, int index, int value) {
        if (index < start || index > end) return;
        
        if (start == end) {
            nums[index] = value;
            tree[node] = value;
            return;
        }

        update(getLeftChild(node), start, getMiddle(start, end), index, value);
        update(getRightChild(node), getMiddle(start, end) + 1, end, index, value);

        tree[node] = ((tree[getLeftChild(node)] % MOD) * (tree[getRightChild(node)] % MOD)) % MOD;         
    }

    static long query(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 1L;
        if (left <= start && end <= right) return tree[node];

        long leftMul = (query(getLeftChild(node), start, getMiddle(start, end), left, right)) % MOD;
        long rightMul = (query(getRightChild(node), getMiddle(start, end) + 1, end, left, right)) % MOD;

        return leftMul * rightMul % MOD;
    }
}