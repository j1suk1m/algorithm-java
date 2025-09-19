import java.util.*;

class Solution {
    static final int INF = 101;
    static int[] firstIndex = new int[26]; // keymap에서 알파벳이 등장한 가장 빠른 위치
    
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        Arrays.fill(firstIndex, INF);
        
        // 알파벳의 최소 등장 위치 저장
        for (String str : keymap) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                firstIndex[ch - 'A'] = Math.min(firstIndex[ch - 'A'], i + 1);
            }
        }
        
        int result = 0;
        
        // 최소 연산 개수 계산
        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            result = 0;
            
            for (int j = 0; j < target.length(); j++) {
                char ch = target.charAt(j);
                
                if (firstIndex[ch - 'A'] < INF) {
                    result += firstIndex[ch - 'A'];
                } else {
                    result = -1;
                    break;
                }
            }
            
            answer[i] = result;
        }
        
        return answer;
    }
}