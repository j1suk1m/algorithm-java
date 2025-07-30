import java.util.*;
import java.io.*;

class Main {
    // 중간값 이하를 저장하는 최대 힙
    static final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); 

    // 중간값 초과를 저장하는 최소 힙
    static final PriorityQueue<Integer> minHeap = new PriorityQueue<>(); 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 정수의 개수

        while (N-- > 0) {
            int number = Integer.parseInt(br.readLine());

            // 두 힙의 크기 차이가 0 또는 1이 되도록 유지
            if (minHeap.size() == maxHeap.size()) { 
                maxHeap.offer(number);
            } else {
                minHeap.offer(number);
            }

            rebalance(); // 힙 속성 위반 시 조정
            
            System.out.println(maxHeap.peek()); // 항상 최대 힙의 루트가 중간값이 됨
        }
    }

    // 두 힙의 루트를 비교해 올바른 위치에 재배치
    static void rebalance() {
        if (maxHeap.isEmpty() || minHeap.isEmpty()) return;

        // 최대 힙의 루트가 최소 힙의 루트보다 크면 서로 교환
        if (maxHeap.peek() > minHeap.peek()) {
            maxHeap.offer(minHeap.poll());
            minHeap.offer(maxHeap.poll());
        }
    }
}