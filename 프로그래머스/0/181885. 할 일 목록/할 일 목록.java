import java.util.*;

class Solution {
    public String[] solution(String[] todo_list, boolean[] finished) {
        ArrayList<String> answer = new ArrayList<>();
        
        for (int idx = 0; idx < todo_list.length; idx++) {
            if (!finished[idx]) {
                answer.add(todo_list[idx]);
            }
        }
        
        return answer.toArray(String[]::new);
    }
}