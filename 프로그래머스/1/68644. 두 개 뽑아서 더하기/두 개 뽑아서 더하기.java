import java.util.ArrayList;
import java.util.List;

class Solution {
    private final boolean[] visited = new boolean[201]; // 두 수의 합의 최댓값은 100 + 100 = 200이므로, 인덱스 0 ~ 200까지 사용
    
    public int[] solution(int[] numbers) {
        List<Integer> answer = new ArrayList<>();
        
        // 가능한 모든 두 수의 합을 계산하여 visited 배열에 표시
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int sum = numbers[i] + numbers[j];
            
                if (!visited[sum]) {
                    visited[sum] = true;
                }
            }
        }
        
        // visited 배열에서 true로 표시된 인덱스를 answer 리스트에 추가
        for (int sum = 0; sum < visited.length; sum++) {
            if (visited[sum]) {
                answer.add(sum);
            }
        }
        
        // List<Integer> -> int[] 변환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}