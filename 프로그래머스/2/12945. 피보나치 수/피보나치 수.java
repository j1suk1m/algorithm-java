class Solution {
    public int solution(int n) {
        int[] memo = new int [n + 1];
        int mod = 1234567;
        
        // 기저 조건
        memo[0] = 0;
        memo[1] = 1;
        
        // 다이나믹 프로그래밍
        for (int number = 2; number < n + 1; number++) {
            memo[number] = ((memo[number - 1] % mod) + (memo[number - 2] % mod)) % mod;
        }
        
        return memo[n];
    }
}