import java.util.*;

class Solution {
    public String[] solution(String myString) {
        
        // 하나 이상의 연속된 x를 기준으로 분할
        String[] answer = myString.split("x+");
        
        // 빈 문자열 필터링 후 정렬
        answer = Arrays.stream(answer)
                       .filter(str -> !str.isEmpty())
                       .sorted()
                       .toArray(String[]::new);
        
        return answer;
    }
}