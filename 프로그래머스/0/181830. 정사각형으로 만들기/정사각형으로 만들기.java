class Solution {
    public int[][] solution(int[][] arr) {
        // 이미 정사각형 배열이면 바로 반환
        if (arr.length == arr[0].length) return arr;
        
        int length = Math.max(arr.length, arr[0].length);
        int[][] answer = new int[length][length];
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                answer[i][j] = arr[i][j];
            }
        }
        
        return answer;
    }
}