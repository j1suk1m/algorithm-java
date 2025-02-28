import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < number.length(); i++) {
            int currentNumber = Character.getNumericValue(number.charAt(i));
            
            if (stack.isEmpty() || (stack.peek() >= currentNumber)) {
                stack.push(currentNumber);
            } else {
                while (k > 0 && !stack.isEmpty() && stack.peek() < currentNumber) {
                    stack.pop();
                    k--;
                }
                
                stack.push(currentNumber);
            }
        }
        
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder answer = new StringBuilder();
        
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
        
        return answer.reverse().toString();
    }
}