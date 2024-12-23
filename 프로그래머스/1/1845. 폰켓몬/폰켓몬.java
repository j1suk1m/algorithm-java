import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> uniqueNums = Arrays.stream(nums)
                                        .boxed()
                                        .collect(Collectors.toSet());
        
        return Math.min(uniqueNums.size(), nums.length / 2);
    }
}