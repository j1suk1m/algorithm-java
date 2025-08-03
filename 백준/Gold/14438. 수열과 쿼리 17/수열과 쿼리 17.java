import java.util.*;
import java.io.*;

class Main {
    static int[] nums;
    static int[] tree;
    static final StringBuilder answer = new StringBuilder();
    static final int ROOT = 1;
    static final int UPDATE = 1;
    static final int QUERY = 2;
    static final int INF = (int) 1e9 + 1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 수열의 크기

        nums = new int[N + 1];
        tree = new int[getTreeSize(N + 1)];
        st = new StringTokenizer(br.readLine());

        // 수열 입력
        for (int i = 1; i < N + 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 생성
        buildTree(ROOT, 1, N);

        int M = Integer.parseInt(br.readLine()); // 쿼리의 개수

        // 쿼리 입력
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());

            if (command == UPDATE) {
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(ROOT, 1, N, index, value);
            } else if (command == QUERY) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());  
                answer.append(query(ROOT, 1, N, left, right)).append("\n");
            } else {
                continue; // 이외의 명령 무시
            }
        }

        System.out.println(answer);
    }

    static int getTreeSize(int N) {
        int treeSize = Integer.highestOneBit(N);

        if (treeSize != N) {
            treeSize <<= 1;
        }

        return treeSize <<= 1;
    }

    static int getMiddle(int start, int end) {
        return start + (end - start) / 2;
    }

    static void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
            return;
        }

        int middle = getMiddle(start, end);
        
        buildTree(node * 2, start, middle);
        buildTree(node * 2 + 1, middle + 1, end);

        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    static void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }

        int middle = getMiddle(start, end);

        if (index <= middle) {
            update(node * 2, start, middle, index, value);
        } else {
            update(node * 2 + 1, middle + 1, end, index, value);
        }

        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    static int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return INF;
        if (left <= start && end <= right) return tree[node];

        int middle = getMiddle(start, end);

        int leftMin = query(node * 2, start, middle, left, right);
        int rightMin = query(node * 2 + 1, middle + 1, end, left, right);

        return Math.min(leftMin, rightMin);
    }
}