import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        int row = triangle.length;
        
        for (int i = 1; i < row; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    triangle[i][j] += triangle[i - 1][j];
                } else if (j == i) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
        }
        
        return Arrays.stream(triangle[row - 1]).max().getAsInt(); // 배열의 마지막 행의 최댓값 계산
    }
}