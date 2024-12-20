class Solution {
    private static boolean isOdd(int number) {
        return number % 2 == 1;
    }
    
    private static int getPowerOfTwo(int exponent) {
        if (exponent == 1) {
            return 2;
        }
        
        int value = getPowerOfTwo(exponent / 2);

        if (isOdd(exponent)) {
            return value * value * 2;
        } else {
            return value * value;
        }
    }
    
    public int solution(int n, int t) {
        return n * getPowerOfTwo(t);
    }
}