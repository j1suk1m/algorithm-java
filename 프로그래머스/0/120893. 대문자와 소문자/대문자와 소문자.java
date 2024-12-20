class Solution {
    public String solution(String my_string) {
        char[] answer = my_string.toCharArray();
        
        for (int i = 0; i < answer.length; i++) {
            char current = answer[i];
            
            if (Character.isLowerCase(current)) {
                answer[i] = Character.toUpperCase(current);
            } else {
                answer[i] = Character.toLowerCase(current);
            }
        }
        
        return new String(answer);
    }
}