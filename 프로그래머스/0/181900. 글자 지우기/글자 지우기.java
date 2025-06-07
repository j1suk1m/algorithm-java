import java.util.HashSet;
import java.util.Set;

class Solution {
    public String solution(String my_string, int[] indices) {
        Set<Integer> blackList = new HashSet<>();
        
        // 제거할 글자의 인덱스를 집합에 저장한다.
        for (int index : indices) {
            blackList.add(index);
        }
        
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < my_string.length(); i++) {
            if (!blackList.contains(i)) { // 제거 대상이 아닌 경우에만 문자열에 이어 붙인다.
                answer.append(my_string.charAt(i));
            }
        }
        
        return answer.toString();
    }
}