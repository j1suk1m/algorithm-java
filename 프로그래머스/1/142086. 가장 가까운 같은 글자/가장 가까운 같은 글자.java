import java.util.*;

class Solution {
    static final int DEFAULT = -1;
    static final int TOTAL = 26; // 알파벳 개수
    
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] last = new int[TOTAL]; // 마지막으로 등장한 인덱스
        
        // 배열 초기화
        Arrays.fill(last, DEFAULT);
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int alphabetIndex = ch - 'a'; // 'a' -> 0, 'b' -> 1 매핑
            
            if (last[alphabetIndex] == DEFAULT) {
                answer[i] = DEFAULT;
            } else {
                answer[i] = i - last[alphabetIndex];
            }            
            
            // 가장 최근에 등장한 인덱스 갱신
            last[alphabetIndex] = i;
        }
        
        return answer;
    }
}