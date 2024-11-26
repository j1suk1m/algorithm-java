class Solution {
    private static int dfs(int[] numbers, int depth, int result, int target) {
        int count = 0;

        if (depth == numbers.length) {
            if (result == target) {
                return 1;
            } else {
                return 0;
            }
        }

        count += dfs(numbers, depth + 1, result + numbers[depth], target);
        count += dfs(numbers, depth + 1, result - numbers[depth], target);

        return count;
    }

    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }
}