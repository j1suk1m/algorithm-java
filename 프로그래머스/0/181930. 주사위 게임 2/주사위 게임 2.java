class Solution {
    private static int getSquare(int number) {
        return number * number;
    }
    
    private static int getCubic(int number) {
        return getSquare(number) * number;
    }
    
    public int solution(int a, int b, int c) {
        int answer = 0;
        
        if (a == b && b == c) {
            return (a + b + c) * (getSquare(a) + getSquare(b) + getSquare(c)) * (getCubic(a) + getCubic(b) + getCubic(c)); 
        } else if (a != b && b != c && c != a) {
            return a + b + c;
        }
        
        return (a + b + c) * (getSquare(a) + getSquare(b) + getSquare(c));
    }
}