class Solution {
    private boolean isOdd(int num) {
        return num % 2 == 1 || num % 2 == -1;
    }
    
    public String solution(int num) {
        if (isOdd(num)) {
            return "Odd";
        }
        
        return "Even";
    }
}