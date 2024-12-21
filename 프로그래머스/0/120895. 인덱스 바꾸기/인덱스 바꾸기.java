class Solution {
    public String solution(String myString, int num1, int num2) {
        StringBuilder answer = new StringBuilder(myString);
        
        char temp = answer.charAt(num1);
        answer.setCharAt(num1, answer.charAt(num2));
        answer.setCharAt(num2, temp);
        
        return answer.toString();
    }
}