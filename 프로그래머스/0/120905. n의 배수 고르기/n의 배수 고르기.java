import java.util.*;

class Solution {
    public int[] solution(int n, int[] numList) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < numList.length; i++) {
            if (numList[i] % n == 0) {
                answer.add(numList[i]);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}