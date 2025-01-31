class Solution {
    public int solution(int[][] sizes) {
        int maxOfShortLength = 0;
        int maxOfLongLength = 0;
        
        for (int[] size : sizes) {  
            int shortLength = Math.min(size[0], size[1]);
            int longLength = Math.max(size[0], size[1]);
            
            // 최댓값 갱신
            maxOfShortLength = Math.max(maxOfShortLength, shortLength); 
            maxOfLongLength = Math.max(maxOfLongLength, longLength); 
        }
        
        return maxOfShortLength * maxOfLongLength;
    }
}