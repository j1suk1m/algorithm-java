class Solution {
    public int solution(int[] sides) {
        int max = 0;
        int sumExceptForMax = 0;
        
        for (int side : sides) {
            if (max <= side) {
                sumExceptForMax += max;
                max = side;
            } else {
                sumExceptForMax += side;
            }
        }
        
        return max < sumExceptForMax ? 1 : 2;
    }
}