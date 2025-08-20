class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for (int x = 0; x <= d; x += k) {
            long maxY = (long) Math.sqrt((long) d * d - (long) x * x); // 피타고라스의 정리 변형 
            answer += (maxY / k) + 1;
        }
        
        return answer;
    }
}