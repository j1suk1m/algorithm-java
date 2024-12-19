import java.util.*;

class Solution {
    public String[] solution(String[] strArr) {
        ArrayList<String> answer = new ArrayList<>();
        String deletedTarget = "ad";
        
        for (String string : strArr) {
            if (!string.contains(deletedTarget)) {
                answer.add(string);
            }
        }
        
        return answer.toArray(new String[0]);
    }
}