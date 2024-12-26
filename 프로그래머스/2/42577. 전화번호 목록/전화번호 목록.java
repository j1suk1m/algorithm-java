import java.util.*;

class Solution {
    private static boolean isPrefix(String current, String next) {
        for  (int j = 0; j < current.length(); j++) {
            if (current.charAt(j) != next.charAt(j)) {
                return false;
            }                
        }

        return true;
    }
    
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        
        for (int i = 0; i < phone_book.length - 1; i++) {
            String current = phone_book[i];
            String next = phone_book[i + 1];
            
            if (current.length() >= next.length()) {
                continue;
            }
            
            if (isPrefix(current, next)) {
                return false;
            }
        }
        
        return true;
    }
}