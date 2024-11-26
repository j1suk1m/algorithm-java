class Solution {    
    private static int dfs(int[] numbers, int depth, int result, int target) {
        if (depth == numbers.length) {
            return result == target ? 1 : 0;
        }

        return dfs(numbers, depth + 1, result + numbers[depth], target) + dfs(numbers, depth + 1, result - numbers[depth], target);
    }

    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }
}