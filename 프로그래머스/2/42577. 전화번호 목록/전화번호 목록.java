import java.util.Map;
import java.util.HashMap;

class Solution {
    public boolean solution(String[] phoneBook) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String phoneNumber : phoneBook) {
             map.put(phoneNumber, 0);
        }
        
        for (String phoneNumber : phoneBook) {
            for (int i = 1; i < phoneNumber.length(); i++) {
                if (map.containsKey(phoneNumber.substring(0, i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}