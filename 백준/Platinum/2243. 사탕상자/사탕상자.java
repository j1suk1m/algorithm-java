import java.util.*;
import java.io.*;

class Main {
    static final int TAKE_OUT = 1;
    static final int MIN_TASTE = 1;
    static final int MAX_TASTE = (int) 1e6;
    static final int ROOT = 1;
    static int[] tree;
    static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine()); // 쿼리 횟수
       
        initTree();
        buildTree(ROOT, MIN_TASTE, MAX_TASTE);

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int taste = 0;
            int count = 0;

            if (command == TAKE_OUT) {
                int rank = Integer.parseInt(st.nextToken()); // 꺼낼 사탕의 순위
                taste = findKthTaste(ROOT, MIN_TASTE, MAX_TASTE, rank); // 해당 순위의 사탕의 맛 계산
                count = -1;
                answer.append(taste).append("\n");
            } else { 
                taste = Integer.parseInt(st.nextToken()); // 사탕의 맛
                count = Integer.parseInt(st.nextToken()); // 사탕의 개수
            } 
            
            updateCount(ROOT, MIN_TASTE, MAX_TASTE, taste, count);
        }

        System.out.println(answer);
    }

    static int getTreeSize() {
        int height = (int) Math.ceil(Math.log(MAX_TASTE + 1) / Math.log(2));
        int treeSize = (1 << (height + 1));
        return treeSize;
    }

    static void initTree() {
         tree = new int[getTreeSize()];
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

    static void buildTree(int node, int startTaste, int endTaste) {
        if (startTaste == endTaste) {
            tree[node] = 0; // 초기 사탕 개수
            return;
        }

        int leftChild = getLeftChild(node);
        int rightChild = getRightChild(node);
        int middle = getMiddle(startTaste, endTaste);

        buildTree(leftChild, startTaste, middle);
        buildTree(rightChild, middle + 1, endTaste);

        tree[node] = tree[leftChild] + tree[rightChild];
    }

    static int findKthTaste(int node, int startTaste, int endTaste, int K) {
        if (startTaste == endTaste) {
            return startTaste;
        }

        int leftChild = getLeftChild(node);
        int rightChild = getRightChild(node);
        int middle = getMiddle(startTaste, endTaste);
        
        if (K <= tree[leftChild]) {
            return findKthTaste(leftChild, startTaste, middle, K);
        } else {
            return findKthTaste(rightChild, middle + 1, endTaste, K - tree[leftChild]);
        }
    }

    static void updateCount(int node, int startTaste, int endTaste, int targetTaste, int deltaCount) {
        if (startTaste == endTaste) {
            tree[node] += deltaCount;
            return;
        }
    
        int leftChild = getLeftChild(node);
        int rightChild = getRightChild(node);
        int middle = getMiddle(startTaste, endTaste);
    
        if (targetTaste <= middle) {
            updateCount(leftChild, startTaste, middle, targetTaste, deltaCount);
        } else {
            updateCount(rightChild, middle + 1, endTaste, targetTaste, deltaCount);
        }
    
        tree[node] = tree[leftChild] + tree[rightChild]; 
    }
}