class Solution {
    public int tribonacci(int n) {
        if (n <= 1) return n;
        if (n == 2) return 1;

        int[] table = new int[n + 1];

        table[1] = 1;
        table[2] = 1;

        for (int i = 3; i <= n; i++) {
            table[i] = table[i - 3] + table[i - 2] + table[i - 1];
        }

        return table[n];
    }
}