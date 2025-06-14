import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {        
        Arrays.sort(times); // 이분 탐색을 위한 오름차순 정렬
        
        long left = 0; 
        long right = (long) n * times[times.length - 1]; // 최대 시간
        
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            long totalCount = 0; // mid분 내에 심사할 수 있는 사람의 수
            
            for (int time : times) {
                totalCount += mid / time; 
            }
            
            if (totalCount >= n) { // 충분히 심사할 수 있으면, 더 짧은 시간도 가능한지 탐색
                right = mid; 
            } else { // 시간이 부족한 경우 더 긴 시간으로 탐색
                left = mid;
            }
        }
        
        return right;
    }
}