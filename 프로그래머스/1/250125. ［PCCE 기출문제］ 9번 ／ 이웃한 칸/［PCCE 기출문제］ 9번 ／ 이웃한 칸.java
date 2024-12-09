class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int N = board.length;
        String color = board[h][w];
        int[] dh = {-1, 1, 0, 0};
        int[] dw = {0, 0, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            int nh = h + dh[i];
            int nw = w + dw[i];
            
            if (!(0 <= nh && nh < N && 0 <= nw && nw < N)) {
                continue;
            } else if (color.equals(board[nh][nw])) {
                answer++;
            }
        }
        
        return answer;
    }
}