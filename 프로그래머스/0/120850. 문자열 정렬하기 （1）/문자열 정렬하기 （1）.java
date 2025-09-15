import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        List<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i);
            
            if (Character.isDigit(ch)) {
                answer.add(ch - '0');
            }
        }
        
        answer.sort(Comparator.naturalOrder());

        return answer.stream().mapToInt(i -> i).toArray();
    }
}