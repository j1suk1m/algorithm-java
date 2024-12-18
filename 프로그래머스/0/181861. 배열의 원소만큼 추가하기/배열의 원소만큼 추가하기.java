import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (int number : arr) {
            for (int count = 0; count < number; count++) {
                answer.add(number);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}