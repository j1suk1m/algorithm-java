import java.util.PriorityQueue;

class Solution {
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>((num1, num2) -> num2 - num1);
    private int heapSize = 0;

    public int[] solution(String[] operations) {
        for (int i = 0; i < operations.length; i++) {
            String[] operation = operations[i].split(" ");
            char command = operation[0].charAt(0);
            int data = Integer.parseInt(operation[1]);

            if (command == 'I') {
                insert(data);
            } else if (heapSize > 0 && data == 1) {
                deleteMax();
                clear();
            } else if (heapSize > 0 && data == -1) {
                deleteMin();
                clear();
            }
        }

        if (heapSize == 0) {
            return new int[]{0, 0};
        }

        return new int[]{maxHeap.poll(), minHeap.poll()};
    }

    private void insert(int data) {
        minHeap.offer(data);
        maxHeap.offer(data);
        heapSize++;
    }

    private void deleteMin() {
        maxHeap.remove(minHeap.poll());
        heapSize--;
    }

    private void deleteMax() {
        minHeap.remove(maxHeap.poll());
        heapSize--;
    }

    private void clear() {
        if (heapSize == 0) {
            minHeap.clear();
            maxHeap.clear();
        }
    }
}