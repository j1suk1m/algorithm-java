class Solution {
    public int solution(String num_str) {
        int answer = 0;
        char[] digits = num_str.toCharArray();
        
        for (char digit : digits) {
            answer += (digit - '0');
        }
        
        return answer;
    }
}