import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int citationCount = citations.length;
        
        Arrays.sort(citations);
        
        for (int i = citationCount - 1; i >= 1; i--) {
            int rank = citationCount - i;
            
            if (citations[i - 1] <= rank && rank <= citations[i]) {
                return rank;
            }
        }
        
        return Math.min(citationCount, citations[0]);
    }
}