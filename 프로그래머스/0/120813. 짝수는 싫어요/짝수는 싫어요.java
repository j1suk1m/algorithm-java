import java.util.*;

class Solution {
    public int[] solution(int n) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (int number = 1; number <= n; number += 2) {
            answer.add(number);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}