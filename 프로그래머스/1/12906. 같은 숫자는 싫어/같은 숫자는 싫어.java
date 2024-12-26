import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int[] solution(int[] arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            
            if (!deque.isEmpty() && deque.peekLast() == current) {
                deque.offerLast(deque.pollLast());
            } else {
                deque.offerLast(current);
            }
        }
        
        int[] answer = new int[deque.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = deque.pollFirst();
        }

        return answer;
    }
}