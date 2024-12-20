class Solution {
    public int solution(int n) {
        int startNumber = n;
        int answer = 0;
        
        // n이 짝수인 경우 n부터, 홀수인 경우 n - 1부터 시작
        if (n % 2 == 1) {
            startNumber = n - 1;
        }
        
        for (int number = startNumber; number >= 2; number -= 2) {
            answer += number;
        }
        
        return answer;
    }
}