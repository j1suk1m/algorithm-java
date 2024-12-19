class Solution {
    private static boolean isOdd(int n) {
        return n % 2 == 1;
    }
    
    public int solution(int n) {
        int answer = 0;
        
        if (isOdd(n)) {
            for (int i = n; i >= 1; i -= 2) {
                answer += i;
            }
        } else {
            for (int i = n; i >= 0; i -= 2) {
                answer += (i * i);
            }
        }
        
        return answer;
    }
}