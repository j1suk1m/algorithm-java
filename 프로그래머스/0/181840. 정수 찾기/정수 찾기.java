class Solution {
    private static boolean contain(int[] numbers, int target) {
        for (int number : numbers) {
            if (number == target) {
                return true;
            }
        }
        
        return false;
    }
    
    public int solution(int[] num_list, int n) {
        if (contain(num_list, n)) {
            return 1;
        }
        
        return 0;
    }
}