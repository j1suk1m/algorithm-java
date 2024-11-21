import java.util.*;

class Solution {
    public int solution(int[] num_list) {
        int[] sum = new int[2]; // 짝수 번째 합, 홀수 번째 합
        
        for (int idx = 0; idx < num_list.length; idx++) {
            sum[(idx + 1) % 2] += num_list[idx];
        }
        
        return Arrays.stream(sum).max().getAsInt();
    }
}