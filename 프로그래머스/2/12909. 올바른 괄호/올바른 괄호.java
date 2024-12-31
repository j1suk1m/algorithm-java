import java.util.EmptyStackException;
import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
    
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
        
            if (current == '(') {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                
                stack.pop();
            }
        }
    
        return stack.isEmpty();
    }
}