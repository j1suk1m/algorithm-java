class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        char[] answer = my_string.toCharArray();
        
        for (int i = s; i < s + overwrite_string.length(); i++) {
            answer[i] = overwrite_string.charAt(i - s);       
        }
        
        return new String(answer);
    }
}