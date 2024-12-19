import java.util.*;

class Solution {
    public int[] solution(String myString) {
        ArrayList<Integer> answer = new ArrayList<>();
        String[] splitedResults = myString.split("x", -1);
        
        for (String string : splitedResults) {
            answer.add(string.length());
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}