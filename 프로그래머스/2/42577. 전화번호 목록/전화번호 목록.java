import java.util.Set;
import java.util.HashSet;

class Solution {
    public boolean solution(String[] phoneBook) {
        Set<String> set = new HashSet<>();
        
        for (String phoneNumber : phoneBook) {
             set.add(phoneNumber);
        }
        
        for (String phoneNumber : phoneBook) {
            for (int i = 1; i < phoneNumber.length(); i++) {
                if (set.contains(phoneNumber.substring(0, i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}