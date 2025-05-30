import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n) {
        List<Integer> answer = new ArrayList<>(List.of(n));
        
        while (n > 1) {
            if (isOddNumber(n)) { // 홀수인 경우
                n = 3 * n + 1;
            } else { // 짝수인 경우
                n /= 2;
            }
            
            answer.add(n);
        }
        
        // List<Integer> -> int[] 변환
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    private boolean isOddNumber(int n) {
        return n % 2 == 1;
    }
}