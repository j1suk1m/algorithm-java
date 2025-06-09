class Solution {
    public String solution(String my_string, int s, int e) {
        StringBuilder answer = new StringBuilder(my_string);
        
        while (s < e) {
            char left = answer.charAt(s);
            char right = answer.charAt(e);
            
            answer.setCharAt(s, right);
            answer.setCharAt(e, left);
            
            s++;
            e--;
        }
        
        return answer.toString();
    }
}