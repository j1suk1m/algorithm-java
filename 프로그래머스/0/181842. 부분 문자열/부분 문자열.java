class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        for (int i = 0; i <= str2.length() - str1.length(); i++) {
            if (str1.charAt(0) == str2.charAt(i)) {
                if (str1.equals(str2.substring(i, i + str1.length()))) {
                    answer = 1;
                    break;
                }
            }
        }
        
        return answer;
    }
}