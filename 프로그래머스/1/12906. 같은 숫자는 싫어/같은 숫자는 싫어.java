import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            
            if (stack.isEmpty() || stack.peek() != current) {
                stack.push(current);
            }
        }
        
        List<Integer> answer = new ArrayList<>(stack);

        return answer.stream().mapToInt(i -> i).toArray();
    }
}