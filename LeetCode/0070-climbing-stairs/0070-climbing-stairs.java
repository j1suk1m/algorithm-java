class Solution {
    private static int[] table;

    private static void init(int n) {
        table = new int[n + 1];

        table[1] = 1;
        table[2] = 2;
        table[3] = 3;
    }

    private static void dp(int n) {
        for (int i = 4; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
    }

    public int climbStairs(int n) {
        if (n <= 3) {
            return n;
        }

        init(n);
        dp(n);

        return table[n];
    }
}