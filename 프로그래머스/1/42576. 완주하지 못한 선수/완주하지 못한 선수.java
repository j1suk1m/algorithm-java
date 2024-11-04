import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        int index;
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for (index = 0; index < completion.length; index++) {
            if (!(participant[index].equals(completion[index]))) {
                break;
            }
        }
        
        return participant[index];
    }
}