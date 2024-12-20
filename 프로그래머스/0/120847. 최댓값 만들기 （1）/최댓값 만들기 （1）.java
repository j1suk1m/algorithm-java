import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int length = numbers.length;
        
        Arrays.sort(numbers);
        
        return numbers[length - 1] * numbers[length -2];
    }
}