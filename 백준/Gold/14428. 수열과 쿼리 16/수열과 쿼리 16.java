import java.util.*;
import java.io.*;

class Main {
    static int[] nums;
    static int[] tree;
    static final int ROOT = 1;
    static final int UPDATE = 1;
    static final int QUERY = 2;
    static final int DUMMY = 0;
    static final int INF = (int) 1e9 + 1;
    static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 수열의 크기

        nums = new int[N + 1];
        tree = new int[getTreeSize(N + 1)];
        st = new StringTokenizer(br.readLine());

        nums[DUMMY] = INF;
        
        // 수열 입력
        for (int i = 1; i < N + 1; i++) { // 1-based 인덱싱
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

    static void mergeAndSave(int node, int leftChildValue, int rightChildValue) {
        if (nums[leftChildValue] < nums[rightChildValue]) {
            tree[node] = leftChildValue;
        } else if (nums[leftChildValue] > nums[rightChildValue]) {
            tree[node] = rightChildValue;
        } else {
            tree[node] = Math.min(leftChildValue, rightChildValue);
        }
    }

    static int compareAndReturn(int node, int leftChildValue, int rightChildValue) {
        if (nums[leftChildValue] < nums[rightChildValue]) {
            return leftChildValue;
        } else if (nums[leftChildValue] > nums[rightChildValue]) {
            return rightChildValue;
        } else {
            return Math.min(leftChildValue, rightChildValue);
        }
    }

    static void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }

        int leftChild = getLeftChild(node);
        int rightChild = getRightChild(node);
        int middle = getMiddle(start, end);

        buildTree(leftChild, start, middle);
        buildTree(rightChild, middle + 1, end);

        mergeAndSave(node, tree[leftChild], tree[rightChild]);
    }

    static void update(int node, int start, int end, int index, int value) {
        if (index < start || end < index) return;

        if (start == end) {
            nums[index] = value;
            tree[node] = index;
            return;
        }
        
        int leftChild = getLeftChild(node);
        int rightChild = getRightChild(node);
        int middle = getMiddle(start, end);

        update(leftChild, start, middle, index, value);
        update(rightChild, middle + 1, end, index, value);

        mergeAndSave(node, tree[leftChild], tree[rightChild]);
    }

    static int query(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return DUMMY;
        if (left <= start && end <= right) return tree[node];
        
        int leftChild = getLeftChild(node);
        int rightChild = getRightChild(node);
        int middle = getMiddle(start, end);

        int leftChildValue = query(leftChild, start, middle, left, right);
        int rightChildValue = query(rightChild, middle + 1, end, left, right);

        return compareAndReturn(node, leftChildValue, rightChildValue);    
    }
}