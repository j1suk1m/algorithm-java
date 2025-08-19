class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        long target = Long.parseLong(p);
        int left = 0;
        int right = left + p.length() - 1;
        
        while (right < t.length()) {
            long curr = Long.parseLong(t.substring(left, right + 1));
            
            if (curr <= target) answer++;
            
            left++;
            right++;
        }
        
        return answer;
    }
}