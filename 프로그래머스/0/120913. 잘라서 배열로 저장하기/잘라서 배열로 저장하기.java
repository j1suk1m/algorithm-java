import java.util.*;

class Solution {
    public String[] solution(String input, int n) {
        ArrayList<String> answer = new ArrayList<>();
        int startIndex = 0;
        
        while (startIndex < input.length()) {
            answer.add(input.substring(startIndex, Math.min(startIndex + n, input.length())));
            startIndex += n;
        }
        
        return answer.toArray(String[]::new);
    }
}