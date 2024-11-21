import java.util.*;

class Solution {
    public int[] solution(int[] num_list, int n) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (int idx = 0; idx < num_list.length; idx += n) {
            answer.add(num_list[idx]);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}