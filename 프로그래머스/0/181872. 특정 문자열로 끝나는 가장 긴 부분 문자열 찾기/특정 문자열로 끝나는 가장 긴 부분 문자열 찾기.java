class Solution {
    public String solution(String myString, String pat) {
        String answer = "";
        int start = myString.length() - pat.length();
        
        for (int i = start; i >= 0; i--) {
            String substring = myString.substring(i, i + pat.length());
            
            if (substring.equals(pat)) {
                answer = myString.substring(0, i) + substring;
                break;
            }
        }
        
        return answer;
    }

}