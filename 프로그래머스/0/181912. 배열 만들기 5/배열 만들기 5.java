import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        List<Integer> answer = new ArrayList<>();
        
        for (String str : intStrs) {
            int parsedValue = Integer.parseInt(str.substring(s, s + l));
            
            if (parsedValue > k) {
                answer.add(parsedValue);
            }
        }
        
        // List<Integer> -> int[] 변환
        return answer.stream().mapToInt(i -> i).toArray();
    }
}