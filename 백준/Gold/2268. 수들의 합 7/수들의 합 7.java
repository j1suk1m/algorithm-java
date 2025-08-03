import java.util.*;
import java.io.*;

class Main {
    static final int SUM = 0;
    static final int MODIFY = 1;
    static final int ROOT = 1;
    static long[] tree;
    static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 크기
        int M = Integer.parseInt(st.nextToken()); // 쿼리의 개수

        tree = new long[getTreeSize(N + 1)];
        
        // 쿼리 입력
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            
            int command = Integer.parseInt(st.nextToken());

            if (command == SUM) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                answer.append(sum(ROOT, 1, N, Math.min(left, right), Math.max(left, right))).append("\n");
            } else if (command == MODIFY) {
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                modify(ROOT, 1, N, index, value);
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

    // [left, right] 구간의 합 조회
    static long sum(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0L;
        if (left <= start && end <= right) return tree[node];

        long leftSum = sum(getLeftChild(node), start, getMiddle(start, end), left, right);
        long rightSum = sum(getRightChild(node), getMiddle(start, end) + 1, end, left, right);

        return leftSum + rightSum;
    }

    // 배열에서 index에 위치한 값을 value로 변경
    static void modify(int node, int start, int end, int index, int value) {
        if (index < start || end < index) return;
        
        if (start == end) { // 리프 노드
            tree[node] = value;
            return;
        }

        modify(getLeftChild(node), start, getMiddle(start, end), index, value);
        modify(getRightChild(node), getMiddle(start, end) + 1, end, index, value);

        tree[node] = tree[getLeftChild(node)] + tree[getRightChild(node)];   
    }
}