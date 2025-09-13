import java.util.*;

class Solution {
    public String solution(String my_string) {
        StringBuilder answer = new StringBuilder();
        Set<Character> chars = new HashSet<>();
        
        for (int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i);
            
            if (!chars.contains(ch)) {
                answer.append(ch);
                chars.add(ch);
            }
        }
        
        return answer.toString();
    }
}