import java.util.*;
import java.io.*;

class Main {
    static long[] nums;
    static long[] tree;
    static final int UPDATE = 1;
    static final int QUERY = 2;
    static final String SEPARATOR = "\n";
    static final StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        nums = new long[N];
        tree = new long[getTreeSize(N)];

        // 전체 수 입력
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        initTree(1, 0, N - 1);

        // 명령 입력 후 수행
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            
            if (command == UPDATE) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                long num = Long.parseLong(st.nextToken());
                update(1, 0, N - 1, index, num); // index에 위치한 수를 num으로 변경
            } else if (command == QUERY) {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                answer.append(query(1, 0, N - 1, left, right)) // left에 위치한 수부터 right에 위치한 수까지의 합 조회
                      .append(SEPARATOR); 
            } else {
                continue; // 이외의 명령 무시
            }
        }

        System.out.println(answer.toString());
    }

    // 세그먼트 트리 크기 계산
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

    // 세그먼트 트리 생성
    static void initTree(int node, int start, int end) {
        if (start == end) { // 리프 노드인 경우
            tree[node] = nums[start];
        } else { // 리프 노드가 아닌 경우
            initTree(getLeftChild(node), start, getMiddle(start, end));
            initTree(getRightChild(node), getMiddle(start, end) + 1, end);

            // 양쪽 자식 노드의 합 저장
            tree[node] = tree[getLeftChild(node)] + tree[getRightChild(node)];
        }
    }

    // index에 위치한 수를 num으로 변경
    static void update(int node, int start, int end, int index, long num) {
        if (index < start || index > end) return;

        if (start == end) {
            nums[index] = num;
            tree[node] = num;
            return;
        }

        update(getLeftChild(node), start, getMiddle(start, end), index, num);
        update(getRightChild(node), getMiddle(start, end) + 1, end, index, num);
            
        // 양쪽 자식 노드의 합 저장
        tree[node] = tree[getLeftChild(node)] + tree[getRightChild(node)];
    }

    // left에 위치한 수부터 right에 위치한 수까지의 합 조회
    static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0L; // 조회할 구간과 관련 없는 경우
        if (left <= start && end <= right) return tree[node]; // 조회할 구간의 일부 또는 전체인 경우

        long leftSum = query(getLeftChild(node), start, getMiddle(start, end), left, right);
        long rightSum = query(getRightChild(node), getMiddle(start, end) + 1, end, left, right);
        
        return leftSum + rightSum;
    }
}