import java.util.*;
import java.io.*;

class Node {
    int key;
    Node leftChild;
    Node rightChild;

    Node(int key) {
        this(key, null, null);
    }

    Node(int key, Node leftChild, Node rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}

class Main {
    static final List<Integer> preorderTraversal = new ArrayList<>();
    static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        // 전위 순회 결과 입력
        while ((input = br.readLine()) != null) {
            int number = Integer.parseInt(input);
            preorderTraversal.add(number);
        }

        Node root = buildTree(0, preorderTraversal.size() - 1);
        traversePostorder(root);

        System.out.println(answer);
    }

    // 전위 순회 결과로부터 이진 검색 트리 생성
    static Node buildTree(int start, int end) {
        if (start > end) return null; 
        if (start == end) return new Node(preorderTraversal.get(start));

        int key = preorderTraversal.get(start);
        int curr = start + 1;

        // 전위 순회 결과의 (start, end] 구간에서 현재 노드의 키보다 처음으로 더 큰 값을 갖는 노드 찾기
        while (curr <= end && preorderTraversal.get(curr) <= key) {
            curr++;
        }
    
        Node leftChild = buildTree(start + 1, curr - 1);
        Node rightChild = buildTree(curr, end);
    
        return new Node(key, leftChild, rightChild);
    }

    // 트리 후위 순회
    // 왼쪽 자식 노드 -> 오른쪽 자식 노드 -> 루트
    static void traversePostorder(Node node) {
        if (node == null) return;
        
        traversePostorder(node.leftChild);
        traversePostorder(node.rightChild);
        
        answer.append(node.key).append("\n");
    }
}