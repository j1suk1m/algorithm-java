import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        return Arrays.stream(numbers).map(number -> number * 2).toArray();
    }
}