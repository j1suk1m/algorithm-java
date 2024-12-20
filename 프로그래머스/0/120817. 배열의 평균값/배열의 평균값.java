import java.util.*;

class Solution {
    public double solution(int[] numbers) {
        int sum = Arrays.stream(numbers).sum();
        int count = numbers.length;
        
        return (double)sum / count;
    }
}