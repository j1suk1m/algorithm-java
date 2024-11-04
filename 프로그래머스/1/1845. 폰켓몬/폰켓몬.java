import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = nums.length / 2;
        Set<Integer> uniqueNums = new HashSet<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            uniqueNums.add(nums[i]);
        }
        
        if (uniqueNums.size() < nums.length / 2) {
            answer = uniqueNums.size();
        }
        
        return answer;
    }
}