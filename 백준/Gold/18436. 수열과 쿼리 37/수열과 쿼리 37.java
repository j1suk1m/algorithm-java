import java.util.*;
import java.io.*;

class Main {
    static int[] nums;
    static int[] tree; // 특정 구간의 홀수의 개수 저장
    static final int ROOT = 1;
    static final int UPDATE = 1;
    static final int QUERY_EVEN = 2;
    static final int QUERY_ODD = 3;
    static final StringBuilder result = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine()); // 수열의 크기

        nums = new int[N + 1];
        tree = new int[getTreeSize(N + 1)];
        st = new StringTokenizer(br.readLine());

        // 수열 입력
        for (int i = 1; i <= N; i++) { // 1-based 인덱싱
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 특정 구간의 홀수의 개수를 저장하는 세그먼트 트리 생성
        buildTree(ROOT, 1, N);

        int M = Integer.parseInt(br.readLine()); // 쿼리의 개수

        // 쿼리 수행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == UPDATE) {
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(ROOT, 1, N, index, value);
            } else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());   
                int oddCount = queryOddCount(ROOT, 1, N, left, right);

                if (command == QUERY_EVEN) {
                    result.append(right - left + 1 - oddCount).append("\n");
                } else {
                    result.append(oddCount).append("\n");
                }
            } 
        }

        System.out.println(result);
    }

    // 리프 노드 N개를 포함할 수 있는 세그먼트 트리 배열 크기 계산
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
            tree[node] = nums[start] % 2; // 홀수의 개수 저장
            return;
        }

        int middle = getMiddle(start, end);

        buildTree(node * 2, start, middle);
        buildTree(node * 2 + 1, middle + 1, end);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value % 2; // 홀수의 개수 저장
            return;
        }

        int middle = getMiddle(start, end);

        if (index <= middle) {
            update(node * 2, start, middle, index, value);
        } else {
            update(node * 2 + 1, middle + 1, end, index, value);
        }

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static int queryOddCount(int node, int start, int end, int left, int right) {
        if (start > right || end < left) return 0;
        if (left <= start && end <= right) return tree[node];

        int middle = getMiddle(start, end);

        int leftOddCount = queryOddCount(node * 2, start, middle, left, right);
        int rightOddCount = queryOddCount(node * 2 + 1, middle + 1, end, left, right);

        return leftOddCount + rightOddCount;
    }
}