import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isFirstLetter = true;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (Character.isWhitespace(ch)) {
                isFirstLetter = true;
                sb.append(ch);
            } else if (isFirstLetter) {
                isFirstLetter = false;
                sb.append(Character.isLowerCase(ch) ? Character.toUpperCase(ch) : ch);
            } else {
                sb.append(Character.isUpperCase(ch) ? Character.toLowerCase(ch) : ch);
            }
        }

        return sb.toString();
    }
}