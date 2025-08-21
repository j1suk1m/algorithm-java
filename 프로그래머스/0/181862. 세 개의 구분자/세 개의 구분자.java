import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        String[] splittedStr = myStr.split("[a|b|c]");
        
        if (splittedStr.length == 0) return new String[] {"EMPTY"};
        
        List<String> answer = new ArrayList<>();
        
        for (String str : splittedStr) {
            if (str.length() > 0) answer.add(str);
        }
        
        // List<String> -> String[] 변환
        return answer.stream().toArray(String[]::new);
    }
}