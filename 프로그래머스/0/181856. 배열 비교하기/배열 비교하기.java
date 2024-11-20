import java.util.*;

class Solution {
    public int solution(int[] arr1, int[] arr2) {
        
        // Integer.compare() -> 첫번째가 크면 1, 두번째가 크면 -1 반환
        int answer = Integer.compare(arr1.length, arr2.length);
        
        if (answer == 0) {
            int sumOfArr1 = Arrays.stream(arr1).sum();
            int sumOfArr2 = Arrays.stream(arr2).sum();
            
            answer = Integer.compare(sumOfArr1, sumOfArr2);
        }
       
        return answer;
    }
}