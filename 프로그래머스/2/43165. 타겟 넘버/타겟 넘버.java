class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    // 백트래킹을 이용해 target을 만드는 경우의 수를 계산
    private int dfs(int[] numbers, int target, int result, int index) {
        
        // 모든 숫자를 다 사용한 경우
        if (index == numbers.length) {
            
            // 누적 결과가 target과 같으면 1(경우의 수에 포함) 아니면 0
            return result == target ? 1 : 0;
        }

        // 현재 숫자를 더하거나 뺐을 때의 경우의 수를 각각 재귀 호출로 탐색
        int wayCount = dfs(numbers, target, result + numbers[index], index + 1);
        wayCount += dfs(numbers, target, result - numbers[index], index + 1);

        return wayCount;
    }
}