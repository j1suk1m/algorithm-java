class Solution {
    private static int sum(int a, int b) {
        return Integer.parseInt(String.valueOf(a) + String.valueOf(b));
    }
    
    public int solution(int a, int b) {
        return Math.max(sum(a, b), sum(b, a));
    }
}