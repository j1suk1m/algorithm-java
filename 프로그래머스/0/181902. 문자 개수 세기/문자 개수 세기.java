class Solution {
    public int[] solution(String my_string) {
        int[] answer = new int[52];
        
        for (int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                answer[ch - 'A']++;
            } else {
                answer[ch - 'a' + 26]++;
            }
        }
        
        return answer;
    }
}