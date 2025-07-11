import java.util.Arrays;

class Solution {
    public String[] solution(String[] str_list) {        
        for (int i = 0; i < str_list.length; i++) {
            String current = str_list[i];
            
            if (current.equals("l")) {
                return Arrays.copyOfRange(str_list, 0, i);
            } else if (current.equals("r")) {
                return Arrays.copyOfRange(str_list, i + 1, str_list.length);
            }
        }
        
        return new String[] {};
    }
}