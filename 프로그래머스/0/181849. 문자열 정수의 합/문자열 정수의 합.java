import java.util.*;

class Solution {
    public int solution(String num_str) {
        int answer = 0;
        String[] digits = num_str.split("");
        
        answer = Arrays.stream(digits).mapToInt(Integer::parseInt).sum();
        
        return answer;
    }
}