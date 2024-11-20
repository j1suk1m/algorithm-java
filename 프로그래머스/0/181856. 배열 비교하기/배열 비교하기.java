import java.util.*;

class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int answer = 0;
        
        // 두 배열의 길이가 다르다면, 긴 쪽이 큼
        if (arr1.length > arr2.length) {
            answer = 1;
        } else if (arr1.length < arr2.length) {
            answer = -1;
        } else { // 두 배열의 길이가 같다면, 모든 원소의 합이 큰 쪽이 큼
            int sumOfArr1 = Arrays.stream(arr1).sum();
            int sumOfArr2 = Arrays.stream(arr2).sum();
            
            if (sumOfArr1 > sumOfArr2) {
                answer = 1;
            } else if (sumOfArr1 < sumOfArr2) {
                answer = -1;
            } 
        }
        
        return answer;
    }
}