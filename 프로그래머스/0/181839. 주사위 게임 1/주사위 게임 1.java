class Solution {
    private static boolean isOdd(int number) {
        return number % 2 == 1;
    }
    
    public int solution(int a, int b) {
        if (isOdd(a) && isOdd(b)) {
            return a * a + b * b;
        } else if (isOdd(a) || isOdd(b)) {
            return 2 * (a + b);
        }
        
        return Math.max(a, b) - Math.min(a, b);
    }
}