class Solution {
    static final int RIGHT = 0;
    static final int BOTTOM = 1;
    static final int LEFT = 2;
    static final int TOP = 3;
    
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        int number = 1;
        int direction = RIGHT;
        int x = 0;
        int y = 0;
        
        while (number <= n * n) {
            answer[x][y] = number;
            
            if (direction == RIGHT) {
                if (y == n - 1 || answer[x][y + 1] != 0) { // 방향 변환 필요
                    direction = BOTTOM;
                    x++;
                } else {
                    y++;
                }               
            } else if (direction == BOTTOM) {
                if (x == n - 1 || answer[x + 1][y] != 0) {
                    direction = LEFT;
                    y--;
                } else {
                    x++;
                }
            } else if (direction == LEFT) {
                if (y == 0 || answer[x][y - 1] != 0) {
                    direction = TOP;
                    x--;
                } else {
                    y--;
                }
            } else {
                if (x == 0 || answer[x - 1][y] != 0) {
                    direction = RIGHT;
                    y++;
                } else {
                    x--;
                }
            }
            
            number++;
        }
        
        return answer;
    }
}