class Solution {
    public int solution(String myString, String pat) {
        int answer = 0;
        int targetLength = pat.length();
        
        for (int i = 0; i <= myString.length() - targetLength; i++) {
            if (pat.equals(myString.substring(i, i + targetLength))) {
                answer++;
            }
        }
        
        return answer;
    }
}