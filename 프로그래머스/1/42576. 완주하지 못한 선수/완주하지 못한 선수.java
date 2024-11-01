import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for (int index = 0; index < completion.length; index++) {
            if (!(participant[index].equals(completion[index]))) {
                return participant[index];
            }
        }
        
        return participant[participant.length - 1];
    }
}