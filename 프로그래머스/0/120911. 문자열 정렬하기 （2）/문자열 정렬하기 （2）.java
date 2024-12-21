import java.util.*;

class Solution {
    public String solution(String myString) {
        char[] answer = myString.toLowerCase().toCharArray();
        Arrays.sort(answer);
        
        return new String(answer);
    }
}