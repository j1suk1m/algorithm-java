class Solution {
    public int solution(int n) {
        int factorialValue = 1;
        int number = 1;
        
        while (factorialValue <= n) {
            factorialValue *= number;
            number++;
        }
        
        return number - 2;
    }
}