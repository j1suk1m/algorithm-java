import java.util.*;

class Solution {
    public int[] solution(int l, int r) {
        List<Integer> answer = new ArrayList<>();
        
        for (int i = l; i <= r; i++) {
            if (i % 5 != 0) continue;
            if (isValid(i)) {
                answer.add(i);
            }
        }
        
        if (answer.isEmpty()) {
            return new int[] {-1};
        } else {
            return answer.stream().mapToInt(i -> i).toArray();       
        }        
    }
    
    // 정수 n이 0 또는 5로만 이루어진 숫자인지 확인
    boolean isValid(int n) {
        for (char ch : String.valueOf(n).toCharArray()) {
            if (ch != '0' && ch != '5') return false;
        }
        
        return true;
    }
}