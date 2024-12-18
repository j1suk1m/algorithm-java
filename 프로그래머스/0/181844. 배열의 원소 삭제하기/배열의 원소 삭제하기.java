import java.util.*;

class Solution {
    private static final int max = 1000;
    private static final boolean[] deleted = new boolean[max + 1];
    
    public int[] solution(int[] arr, int[] delete_list) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (int deletedNumber : delete_list) {
            deleted[deletedNumber] = true;
        }
        
        for (int targetNumber : arr) {
            if (!deleted[targetNumber]) {
                answer.add(targetNumber);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}