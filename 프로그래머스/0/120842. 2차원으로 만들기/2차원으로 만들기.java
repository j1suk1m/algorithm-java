class Solution {
    public int[][] solution(int[] numbers, int n) {
        int[][] answer = new int[numbers.length / n][n];
        
        for (int i = 0; i < numbers.length / n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = numbers[n * i + j];
            }
        }
        
        return answer;
    }
}