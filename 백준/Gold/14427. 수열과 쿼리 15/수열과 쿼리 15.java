import java.util.*;
import java.io.*;

class Node {
    int min; // 구간 최솟값
    int index; // 수열에서 구간 최솟값이 위치한 인덱스 (1-based)

    Node(int min, int index) {
        this.min = min;
        this.index = index;
    }

    Node(Node other) {
        this.min = other.min;
        this.index = other.index;
    }
}

class Main {
    static int[] A; // 수열
    static Node[] tree; // 세그먼트 트리
    static final int UPDATE = 1;
    static final int QUERY = 2;
    static final String SEPARATOR = "\n";
    static final int ROOT = 1;
    static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 수열의 크기

        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        tree = new Node[getTreeSize(N)];

        // 수열 저장
        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

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
            } else if (command == QUERY) {
                answer.append(query(ROOT))
                      .append(SEPARATOR);
            } else {
                continue; // 이외의 명령 무시
            }
        }

        System.out.println(answer.toString());
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
            tree[node] = new Node(A[start], start);
        } else {
            buildTree(getLeftChild(node), start, getMiddle(start, end));
            buildTree(getRightChild(node), getMiddle(start, end) + 1, end);

            if (tree[getLeftChild(node)].min == tree[getRightChild(node)].min) {
                tree[node] = new Node(
                    tree[getLeftChild(node)].min, 
                    Math.min(tree[getLeftChild(node)].index, tree[getRightChild(node)].index)
                );
            } else if (tree[getLeftChild(node)].min < tree[getRightChild(node)].min) {
                tree[node] = new Node(tree[getLeftChild(node)]);                
            } else {
                tree[node] = new Node(tree[getRightChild(node)]);
            }
        }
    }

    static void update(int node, int start, int end, int index, int value) {
        if (index < start || index > end) return;

        if (start == end) {
            A[index] = value;
            tree[node] = new Node(value, index);
            return;
        }

        update(getLeftChild(node), start, getMiddle(start, end), index, value);
        update(getRightChild(node), getMiddle(start, end) + 1, end, index, value);

        if (tree[getLeftChild(node)].min == tree[getRightChild(node)].min) {
            tree[node] = new Node(
                tree[getLeftChild(node)].min, 
                Math.min(tree[getLeftChild(node)].index, tree[getRightChild(node)].index)
            );
        } else if (tree[getLeftChild(node)].min < tree[getRightChild(node)].min) {
            tree[node] = new Node(tree[getLeftChild(node)]);                
        } else {
            tree[node] = new Node(tree[getRightChild(node)]);
        }        
    }

    static int query(int node) {
        return tree[ROOT].index;
    }
}