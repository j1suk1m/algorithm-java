import java.util.*;

class Solution {
    public int[] solution(int[] num_list) {
        int size = 5;
        int[] answer = new int[size];
        
        Arrays.sort(num_list);
        
        for (int idx = 0; idx < size; idx++) {
            answer[idx] = num_list[idx];
        }
        
        return answer;
    }
}