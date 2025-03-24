// N의 사용 횟수(1 ~ 8)를 이용해 다이나믹 프로그래밍을 수행한다.
// List<Set<Integer>>에서 i번째 인덱스에 존재하는 집합은 N을 i번 사용해서 만들 수 있는 숫자의 집합을 뜻한다.
// {N을 i번 연결한 숫자} 또는 {j번째 집합에 속한 숫자와 (i - j)번째 집합에 속한 숫자의 사칙 연산 결과} 중에 number가 있는지 확인한다.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private static final int MAX_USE_COUNT = 8; // N의 최대 사용 횟수를 저장한다.
    private final List<Set<Integer>> dp = new ArrayList<>();
    
    public int solution(int N, int number) {
        
        // 리스트를 초기화한다.
        init(N);
        
        // N의 사용 횟수인 useCount를 1부터 MAX_USE_COUNT까지 증가시킨다.
        for (int useCount = 1; useCount <= MAX_USE_COUNT; useCount++) {
            
            // N을 총 useCount번 사용하는 모든 조합에 대해 사칙 연산을 수행한다.
            calculateOperationsForAllPairs(useCount);
 
            // N을 useCount번 사용해 number를 만들 수 있는 경우, useCount를 반환한다.
            if (containsTargetNumber(dp.get(useCount), number)) {
                return useCount;
            }
            
        }
        
        // 이 시점에는 최솟값이 8보다 커지므로 문제의 조건에 맞게 -1을 반환한다.
        return -1;
    }
    
    // 리스트를 초기화한다.
    private void init(int N) {
        
        // N의 사용 횟수를 인덱스로 사용하기 위해 빈 HashSet을 맨 앞에 추가한다.
        dp.add(new HashSet<Integer>());
        
        for (int useCount = 1; useCount <= MAX_USE_COUNT; useCount++) {
            
            // N을 연속해서 useCount번 연결한 숫자를 저장한다.
            int initialValue = Integer.parseInt(String.valueOf(N).repeat(useCount));
            dp.add(new HashSet<Integer>(List.of(initialValue)));
            
        }
        
    }
    
    // 사칙 연산을 수행하고 결과를 저장한다.
    private void calculateOperationsForAllPairs(int useCount) {
        for (int subUseCount = 1; subUseCount <= useCount - 1; subUseCount++) {
            for (int subNumber1 : dp.get(subUseCount)) {
                for (int subNumber2 : dp.get(useCount - subUseCount)) {
                    
                    // N을 subUseCount번, (useCount - subUseCount)번 사용해서 만들 수 있는 숫자를 하나씩 모아 사칙 연산한다.
                    dp.get(useCount).add(subNumber1 + subNumber2);
                    dp.get(useCount).add(subNumber1 - subNumber2);
                    dp.get(useCount).add(subNumber1 * subNumber2);
                        
                    if (subNumber2 != 0) {
                         dp.get(useCount).add(subNumber1 / subNumber2);
                    }
                        
                }
            }
        }
    }
    
    private boolean containsTargetNumber(Set<Integer> numbers, int targetNumber) {
        for (int number : numbers) {
            if (number == targetNumber) {
                return true;
            }
        }
        
        return false;
    }

}