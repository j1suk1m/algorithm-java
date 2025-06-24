class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    
    private int dfs(int[] numbers, int target, int count, int sum) {
        if (count == numbers.length) { // 종료 조건
            return sum == target ? 1 : 0; // 타겟 넘버를 만들 수 있으면 방법의 개수에 포함되어야 하므로 1 반환
        }
        
        // 현재 숫자를 더한 경우와 뺀 경우의 모든 경로를 재귀적으로 탐색하고, 그 결과(방법의 개수)를 합산하여 반환
        return dfs(numbers, target, count + 1, sum + numbers[count])
            + dfs(numbers, target, count + 1, sum - numbers[count]);
    }
}