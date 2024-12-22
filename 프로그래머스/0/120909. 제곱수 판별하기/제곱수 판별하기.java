class Solution {
    public int solution(int n) {
        int squareNumber = 0;
        int number = 0;
        
        while (squareNumber < n) {
            number++;
            squareNumber = number * number;
        }
        
        return squareNumber == n ? 1 : 2;
    }
}