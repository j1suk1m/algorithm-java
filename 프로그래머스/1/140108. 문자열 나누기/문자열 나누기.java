class Solution {
    public int solution(String s) {
        int answer = 0;
        int index = 0;
        
        while (index < s.length()) {
            char x = s.charAt(index);
            int xCount = 1;
            index++;
            
            while (index < s.length() && xCount != 0) {               
                if (x == s.charAt(index)) {
                    xCount++;
                } else {
                    xCount--;
                }
                
                index++;
            }
            
            answer++;
        }
        
        return answer;
    }
}