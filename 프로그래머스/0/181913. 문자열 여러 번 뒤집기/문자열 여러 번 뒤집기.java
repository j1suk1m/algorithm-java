class Solution {
    public String solution(String my_string, int[][] queries) {
        String currStr = my_string;
        
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            
            currStr = reverse(currStr, start, end);
        }
        
        return currStr;
    }
    
    String reverse(String currStr, int start, int end) {
        StringBuilder result = new StringBuilder();
        StringBuilder reversedPart = new StringBuilder(currStr.substring(start, end + 1)).reverse();
        
        result.append(currStr.substring(0, start))
            .append(reversedPart)
            .append(currStr.substring(end + 1, currStr.length()));
        
        return result.toString();
    }
}