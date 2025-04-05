import java.util.Arrays;

class Solution {
    private int[] nums;  // 입력 배열을 복사해 저장한다.
    private int targetNum; // 타겟 넘버를 저장한다.
    
    public int solution(int[] numbers, int target) {
        
        // 인자로 전달된 정수 배열 numbers를 nums로 복사한다.
        // dfs 메서드를 호출할 때마다 numbers를 인자로 전달하지 않기 위함이다.
        nums = Arrays.copyOf(numbers, numbers.length);
        
        // nums와 동일한 이유로 target의 값을 targetNum에 저장한다.
        targetNum = target;
        
        // dfs를 수행한 뒤 결과를 반환한다.
        return dfs(0, 0);
        
    }
    
    // dfs를 수행해 타겟 넘버를 만드는 방법의 수를 계산한다.
    // @param index nums 배열에서의 인덱스이자 dfs의 깊이
    // @param result 현재까지의 연산 결과
    private int dfs(int index, int result) {
        
        // 종료 조건이다.
        // 배열의 모든 숫자를 사용했으므로 연산 결과가 타겟 넘버와 같은지 확인한다.
        // 만약 같다면, 타겟 넘버를 만드는 방법의 수에 포함되므로 1을 반환한다.
        if (index == nums.length) {
            return result == targetNum ? 1 : 0;
        }
        
        // 현재 인덱스의 숫자를 더하는 경우와 빼는 경우 각각의 방법의 수를 계산한다.
        int plusCaseResult = dfs(index + 1, result + nums[index]);
        int minusCaseResult = dfs(index + 1, result - nums[index]);
        
        return plusCaseResult + minusCaseResult;
        
    }
}