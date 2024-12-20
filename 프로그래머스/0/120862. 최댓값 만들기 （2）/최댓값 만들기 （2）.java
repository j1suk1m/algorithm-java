import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);
        
        int candidate1 = numbers[0] * numbers[1];
        int candidate2 = numbers[numbers.length - 1] * numbers[numbers.length - 2];
        
        return Math.max(candidate1, candidate2);
    }
}