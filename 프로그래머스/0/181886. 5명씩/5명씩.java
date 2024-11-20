import java.util.*;

class Solution {
    public String[] solution(String[] names) {
        ArrayList<String> answer = new ArrayList<>();
        
        for (int idx = 0; idx < names.length; idx += 5) {
            answer.add(names[idx]);
        }
        
        return answer.toArray(String[]::new);
    }
}