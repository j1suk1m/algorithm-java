import java.util.*;
import java.io.*;

class Children {
    String left;
    String right;

    Children(String left, String right) {
        this.left = left;
        this.right = right;
    }
}

class Main {
    static final String NONE = ".";
    static final String ROOT = "A";
    static final Map<String, Children> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 이진 트리 노드의 개수

        // 이진 트리 노드 저장
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            String key = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.put(key, new Children(left, right));
        }

        StringBuilder preorderTraversalResult = new StringBuilder();
        StringBuilder inorderTraversalResult = new StringBuilder();
        StringBuilder postorderTraversalResult = new StringBuilder();

        // 트리 순회
        runPreorderTraversal(ROOT, preorderTraversalResult);
        runInorderTraversal(ROOT, inorderTraversalResult);
        runPostorderTraversal(ROOT, postorderTraversalResult);

        System.out.println(preorderTraversalResult.toString());
        System.out.println(inorderTraversalResult.toString());
        System.out.println(postorderTraversalResult.toString());
    }

    // 전위 순회
    static void runPreorderTraversal(String node, StringBuilder result) {
        if (node.equals(NONE)) return;
            
        result.append(node); // 루트 노드 방문

        Children children = tree.get(node);

        runPreorderTraversal(children.left, result); // 왼쪽 자식 노드 방문
        runPreorderTraversal(children.right, result); // 오른쪽 자식 노드 방문
    }

    // 중위 순회
    static void runInorderTraversal(String node, StringBuilder result) {
        if (node.equals(NONE)) return;
            
        Children children = tree.get(node);

        runInorderTraversal(children.left, result); // 왼쪽 자식 노드 방문
        result.append(node); // 루트 노드 방문
        runInorderTraversal(children.right, result); // 오른쪽 자식 노드 방문
    }

    // 후위 순회
    static void runPostorderTraversal(String node, StringBuilder result) {
        if (node.equals(NONE)) return;
            
        Children children = tree.get(node);

        runPostorderTraversal(children.left, result); // 왼쪽 자식 노드 방문
        runPostorderTraversal(children.right, result); // 오른쪽 자식 노드 방문
        result.append(node); // 루트 노드 방문
    }
}