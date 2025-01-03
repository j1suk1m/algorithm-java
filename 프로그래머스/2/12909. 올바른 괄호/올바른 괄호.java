import java.util.EmptyStackException;
import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
    
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
      
            try {
                if (current == '(') { 
                    stack.push(current); // 열린 괄호는 push
                } else {
                    stack.pop(); // 닫힌 괄호는 pop
                }
            } catch (EmptyStackException e) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}