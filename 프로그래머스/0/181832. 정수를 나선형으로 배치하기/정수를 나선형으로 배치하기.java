class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        int start = 0;
        int end = n - 1;
        int number = 1;
        
        while (number <= n * n) {
            // 오른쪽 방향 채우기
            for (int y = start; y <= end; y++) {
                answer[start][y] = number++;
            }
            
            // 아래쪽 방향 채우기
            for (int x = start + 1; x <= end; x++) {
                answer[x][end] = number++;
            }
            
            // 왼쪽 방향 채우기
            for (int y = end - 1; y >= start; y--) {
                answer[end][y] = number++;
            }
            
            // 위쪽 방향 채우기
            for (int x = end - 1; x > start; x--) {
                answer[x][start] = number++;
            }
            
            start++;
            end--;
        }
        
        return answer;
    }
}