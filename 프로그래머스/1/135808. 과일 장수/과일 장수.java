import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        // 이익이 발생하지 않는 경우 early return
        if (score.length < m) return 0;
        
        // 내림차순 정렬
        int[] sortedScore = Arrays.stream(score)
                                  .boxed()
                                  .sorted((a, b) -> b - a)
                                  .mapToInt(Integer::intValue)
                                  .toArray();
                                          
        int i = 0;
        int answer = 0;
        
        while (sortedScore.length - i >= m) {
            int minScore = sortedScore[i + m - 1]; // 현재 상자의 최하점
            answer += minScore * m; // 점수 누적
            i += m;
        }
        
        return answer;
    }
}