class Solution {
    public String solution(String s) {
        int alphabetCount = 26;
        int[] counts = new int[alphabetCount]; // 각 알파벳의 등장 개수
        StringBuilder answer = new StringBuilder();
        
        // 각 알파벳의 등장 개수 저장
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < alphabetCount; i++) {
            if (counts[i] == 1) {
                answer.append((char)(i + 'a'));
            }
        }
        
        return answer.toString();
    }
}