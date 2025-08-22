class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int[] prev = arr;
        int[] curr;
        
        while (true) {
            curr = convert(prev);
            
            if (isSame(prev, curr)) {
                answer--;
                break;
            }
            
            answer++;
            prev = curr;
        }
        
        return answer;
    }
    
    int[] convert(int[] prev) {
        int[] curr = new int[prev.length];
        
        for (int i = 0; i < prev.length; i++) {
            int number = prev[i];
            
            if (number >= 50 && number % 2 == 0) {
                curr[i] = number / 2;
            } else if (number < 50 && number % 2 == 1) {
                curr[i] = number * 2 + 1;
            }
        }
        
        return curr;
    }
    
    boolean isSame(int[] prev, int[] curr) {
        if (prev.length != curr.length) return false;
        
        for (int i = 0; i < prev.length; i++) {
            if (prev[i] != curr[i]) return false;
        }
        
        return true;
    }
}