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
        
        int[] answer = new int[stack.size()];
        
        for (int i = answer.length - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}