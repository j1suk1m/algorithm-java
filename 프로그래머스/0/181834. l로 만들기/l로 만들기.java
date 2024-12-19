class Solution {
    public String solution(String myString) {
        StringBuilder answer = new StringBuilder();
        char target = 'l';
        
        for (int i = 0; i < myString.length(); i++) {
            char current = myString.charAt(i);
            
            if (current < target) {
                answer.append(target);
            } else {
                answer.append(current);
            }
        }
        
        return answer.toString();
    }
}