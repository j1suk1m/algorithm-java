import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();
        int answerLength = number.length() - k;
        
        for (int i = 0; i < number.length(); i++) {
            int currentNumber = number.charAt(i) - '0'; // char -> int
            
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
        
        StringBuilder answer = new StringBuilder();
        
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
        
        return answer.reverse().substring(0, answerLength);
    }
}