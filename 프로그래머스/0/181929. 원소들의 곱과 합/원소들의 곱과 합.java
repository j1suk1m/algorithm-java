class Solution {
    public int solution(int[] num_list) {
        int multiplication = 1;
        int squareOfSum = 0;
        
        for (int i = 0; i < num_list.length; i++) {
            multiplication *= num_list[i];
            squareOfSum += num_list[i];
        }
        
        squareOfSum *= squareOfSum;
    
        if (multiplication < squareOfSum) {
            return 1;
        }
        
        return 0;
    }
}