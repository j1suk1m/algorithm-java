import java.util.*;

class Solution {
    public int[] solution(int n, int[] numList) {
        return Arrays.stream(numList).filter(number -> number % n == 0).toArray();
    }
}