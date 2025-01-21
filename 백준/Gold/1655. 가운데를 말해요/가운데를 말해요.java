import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {
    private static final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private static final PriorityQueue<Integer> maxHeap = new PriorityQueue<>((number1, number2) -> number2 - number1);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        while (N-- > 0) {
            int number = Integer.parseInt(reader.readLine());

            if (minHeap.size() == maxHeap.size()) {
                maxHeap.offer(number);
            } else {
                minHeap.offer(number);
            }

            swapRoots();

            System.out.println(maxHeap.peek());
        }
    }

    private static void swapRoots() {
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
            minHeap.offer(maxHeap.poll());
            maxHeap.offer(minHeap.poll());
        }
    }
}