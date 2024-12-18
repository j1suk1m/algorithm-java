import java.util.*;

class Solution {
    private static final int max = 1000;
    private static final boolean[] included = new boolean[max + 1];
    
    public int[] solution(int[] arr, int[] delete_list) {
        ArrayList<Integer> answer = new ArrayList<>();
        Arrays.fill(included, true);
        
        for (int deletedNumber : delete_list) {
            included[deletedNumber] = false;
        }
        
        for (int targetNumber : arr) {
            if (included[targetNumber]) {
                answer.add(targetNumber);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}