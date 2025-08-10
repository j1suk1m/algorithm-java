import java.util.*;
import java.io.*;

class Main {
    static int n; // 전체 연산 횟수, [1, 1e5]
    static final int QUERY = 1; // 사탕 상자에서 특정 순위의 사탕을 꺼내는 연산
    static final int UPDATE = 2; // 사탕 상자에 특정 맛의 사탕 몇 개를 넣거나 빼는 연산
    static final int ROOT = 1;
    static final int MIN_TASTE = 1;
    static final int MAX_TASTE = 1_000_000;
    static int[] tree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 전체 연산 횟수

        tree = new int[4 * (MAX_TASTE + 1)]; // 세그먼트 트리 생성

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int taste = 0;
            int count = -1;

            int command = Integer.parseInt(st.nextToken()); // 연산

            if (command == QUERY) {
                int rank = Integer.parseInt(st.nextToken());
                taste = findTasteWithRank(ROOT, MIN_TASTE, MAX_TASTE, rank); // 해당 순위의 사탕의 맛 계산
                sb.append(taste).append("\n");
            } else if (command == UPDATE) {
                taste = Integer.parseInt(st.nextToken());
                count = Integer.parseInt(st.nextToken());
            }

            update(ROOT, MIN_TASTE, MAX_TASTE, taste, count);
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    static int getMiddle(int start, int end) {
        return start + (end - start) / 2;
    }

    static int findTasteWithRank(int node, int start, int end, int rank) {
        if (start == end) return start;

        int middle = getMiddle(start, end);

        if (rank <= tree[node * 2]) {
            return findTasteWithRank(node * 2, start, middle, rank);
        } else {
            return findTasteWithRank(node * 2 + 1, middle + 1, end, rank - tree[node * 2]);
        }
    }

    static void update(int node, int start, int end, int taste, int count) {
        if (start == end) {
            tree[node] += count;
            return;
        }
       
        int middle = getMiddle(start, end);

        if (taste <= middle) {
            update(node * 2, start, middle, taste, count);
        } else {
            update(node * 2 + 1, middle + 1, end, taste, count);
        }

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}