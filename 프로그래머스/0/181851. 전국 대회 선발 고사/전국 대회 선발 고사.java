import java.util.*;

class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        int answer = 0;
        int selectedCount = 0; // 선발된 학생 수
        Map<Integer, Integer> rankToOriginalIdx = new HashMap<>(); // 키: 등수, 값: 학생 번호
        
        for (int i = 0; i < rank.length; i++) {
            rankToOriginalIdx.put(rank[i], i);
        }
        
        for (int i = 1; i <= rank.length; i++) {
            int originalIdx = rankToOriginalIdx.get(i);
            
            if (selectedCount == 3) break; // 이미 3명을 선발한 경우 종료
            if (!attendance[originalIdx]) continue;
            
            answer += Math.pow(10, 4 - 2 * selectedCount) * originalIdx;
            selectedCount++;
        }
        
        return answer;
    }
}