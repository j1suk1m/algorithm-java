class Solution {    
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int totalArea = brown + yellow;
        int width = totalArea;
        
        while (true) {
            int height = totalArea / width; // 가로 * 세로 == 전체 넓이
            
            if (totalArea % height == 0 && isValid(width, height, brown, yellow)) {
                answer[0] = width;
                answer[1] = height;
                break;
            }
            
            width--;
        }            
        
        return answer;
    }
    
    // 가로, 세로의 유효성 검사
    private boolean isValid(int width, int height, int brown, int yellow) {
           return (2 * width + 2 * height - 4 == brown) && ((width - 2) * (height - 2) == yellow);
    }
}