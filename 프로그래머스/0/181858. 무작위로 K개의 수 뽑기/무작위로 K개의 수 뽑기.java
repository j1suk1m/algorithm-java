import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    private int[] result;
    private final Set<Integer> set = new HashSet<>();
    
    public int[] solution(int[] arr, int k) {
        int initValue = -1;
        result = new int[k]; // 최대 길이는 k
        
        Arrays.fill(result, initValue); // 배열 초기화
        
        for (int num : arr) {
            if (set.size() == k) { // 종료 조건
                break;
            }
            
            if (!set.contains(num)) { // 등장하지 않은 숫자인 경우
                result[set.size()] = num; // 배열에 덮어쓰기
                set.add(num); // 등장 여부를 확인하기 위해 집합에도 추가
            }     
        }
        
        return result;
    }
}