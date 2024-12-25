import java.util.Map;
import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> counts = new HashMap<>();
        String answer = "";
        
        for (String player : participant) {
            counts.put(player, counts.getOrDefault(player, 0) + 1);
        }
        
        for (String player : completion) {
            counts.put(player, counts.get(player) - 1);
        }
        
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                answer = entry.getKey();
                break;
            }
        }
        
        return answer;
    }
}