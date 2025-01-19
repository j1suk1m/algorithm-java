import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int citationCount = citations.length;
        
        Arrays.sort(citations);
        
        for (int i = citationCount - 1; i >= 1; i--) {
            int h = citationCount - i;
            
            if (citations[i - 1] <= h && h <= citations[i]) {
                return h;
            }
        }
        
        return Math.min(citationCount, citations[0]);
    }
}