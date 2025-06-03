import java.util.*;

class Solution {
    private final Map<Character, Character> matchingBrackets = Map.of(')', '(', '}', '{', ']', '[');

    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            if (isValidBrackets(s)) { // 올바른 괄호 문자열의 개수를 누적한다.
                answer += 1;
            }

            s = rotate(s); // 괄호 문자열을 왼쪽으로 한 칸 회전시킨다.
        }

        return answer;
    }

    // 올바른 괄호 문자열인지 확인한다.
    private boolean isValidBrackets(String s) {
        Stack<Character> stack = new Stack<>();

        for (char currentChar : s.toCharArray()) {
            if (isOpeningBracket(currentChar)) { // 여는 괄호인 경우 스택에 추가한다.
                stack.push(currentChar);
            } else { // 닫는 괄호인 경우 조건을 확인한다. 
                if (stack.isEmpty() || stack.pop() != matchingBrackets.get(currentChar)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // 여는 괄호인지 확인한다.
    private boolean isOpeningBracket(char ch) {
        return matchingBrackets.containsValue(ch);
    }

    // 괄호 문자열을 왼쪽으로 한 칸 회전시킨다.
    private String rotate(String s) {
        return s.substring(1) + s.charAt(0);
    }
}