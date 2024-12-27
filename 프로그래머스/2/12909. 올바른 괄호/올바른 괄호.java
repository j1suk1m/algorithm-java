import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == '(') {
                stack.push(current);
            } else {
                if (stack.empty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        
        if (stack.empty()) {
            return true;
        }
        
        return false;
    }
}