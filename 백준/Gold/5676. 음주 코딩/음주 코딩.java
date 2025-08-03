import java.util.*;
import java.io.*;

class Main {
    static int[] nums;
    static int[] tree; // 실제 곱셈 결과가 아닌, NEGATIVE, ZERO, POSITIVE 중 하나를 저장
    static final int NEGATIVE = -1;
    static final int ZERO = 0;
    static final int POSITIVE = 1;
    static final int ROOT = 1;
    static final String UPDATE = "C";
    static final String QUERY = "P";
    static final String PLUS = "+";
    static final String MINUS = "-";
    static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        String line = null;

        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);

            int N = Integer.parseInt(st.nextToken()); // 수열의 크기
            int K = Integer.parseInt(st.nextToken()); // 게임의 라운드 수

            nums = new int[N + 1];
            tree = new int[getTreeSize(N + 1)];
            st = new StringTokenizer(br.readLine());

            // 수열 입력
            for (int i = 1; i <= N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            // 세그먼트 트리 생성
            buildTree(ROOT, 1, N);

            // 게임 라운드 진행
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();

                if (command.equals(UPDATE)) {
                    int index = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    update(ROOT, 1, N, index, value);
                } else if (command.equals(QUERY)) {
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());
                    int result = query(ROOT, 1, N, left, right);
                    answer.append(getSign(result));
                } else {
                    continue; // 이외의 명령 무시
                }
            }

            answer.append("\n");
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

    static int getSignType(int value) {
        if (value < ZERO) return NEGATIVE;
        if (value > ZERO) return POSITIVE;
        return ZERO;
    }

    static String getSign(int value) {
        if (value < ZERO) return MINUS;
        if (value > ZERO) return PLUS;
        return String.valueOf(ZERO);
    }

    static void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = getSignType(nums[start]);
            return;
        }

        int middle = getMiddle(start, end);

        buildTree(node * 2, start, middle);
        buildTree(node * 2 + 1, middle + 1, end);

        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }

    static void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = getSignType(value);
            return;
        }

        int middle = getMiddle(start, end);

        if (index <= middle) {
            update(node * 2, start, middle, index, value);
        } else {
            update(node * 2 + 1, middle + 1, end, index, value);
        }

        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }

    static int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return POSITIVE;
        if (left <= start && end <= right) return tree[node];

        int middle = getMiddle(start, end);

        int leftMul = query(node * 2, start, middle, left, right);
        int rightMul = query(node * 2 + 1, middle + 1, end, left, right);

        return leftMul * rightMul;
    }
}