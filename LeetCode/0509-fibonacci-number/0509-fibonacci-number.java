class Solution {
    private static int[] table;
    
    private static void init(int n) {
        table = new int[n + 1];
        table[0] = 0;
        table[1] = 1;
    }
    
    private static void dp(int n) {
        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
    }
    
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        
        init(n);
        dp(n);
        
        return table[n];
    }
}