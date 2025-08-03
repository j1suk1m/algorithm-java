import java.util.*;
import java.io.*;

class Main {
    static long[] tree;
    static final int UPDATE = 1;
    static final int QUERY = 2;
    static final int ROOT = 1;
    static final StringBuilder result = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 전체 날짜 수 
        int Q = Integer.parseInt(st.nextToken()); // 쿼리의 개수

        tree = new long[getTreeSize(N + 1)];

        // 쿼리 수행
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == UPDATE) {
                int day = Integer.parseInt(st.nextToken());
                int delta = Integer.parseInt(st.nextToken());
                update(ROOT, 1, N, day, delta);
            } else if (command == QUERY) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());                
                result.append(query(ROOT, 1, N, left, right)).append("\n");
            } else {
                continue; // 이외의 명령 무시
            }
        }

        System.out.println(result);
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

    static void update(int node, int start, int end, int day, int delta) {
        if (start == end) {
            tree[node] += delta;
            return;
        }

        int middle = getMiddle(start, end);

        if (day <= middle) {
            update(node * 2, start, middle, day, delta);
        } else {
            update(node * 2 + 1, middle + 1, end, day, delta);
        }

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) return 0L;
        if (left <= start && end <= right) return tree[node];

        int middle = getMiddle(start, end);

        long leftSum = query(node * 2, start, middle, left, right);
        long rightSum = query(node * 2 + 1, middle + 1, end, left, right);

        return leftSum + rightSum;
    }
}