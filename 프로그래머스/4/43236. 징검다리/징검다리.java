import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    private final List<Integer> rockPositions = new ArrayList<>(); // 바위가 위치하는 지점들의 리스트
    
    public int solution(int distance, int[] rocks, int n) {
        init(rocks, distance); // 리스트 초기화
        
        int low = 0;
        int high = distance + 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2; // (low + high) / 2와 동일한 표현식
            
            if (check(mid, n)) {
                low = mid; // 조건 만족 -> 거리의 최솟값을 늘려도 조건을 만족하는지 확인
            } else {
                high = mid; // 조건 불만족 -> 거리의 최솟값을 줄이면 조건을 만족하는지 확인
            }
        }
        
        return low;
    }
    
    private void init(int[] rocks, int distance) {
        for (int rock : rocks) {
            rockPositions.add(rock);
        }      
        
        rockPositions.add(distance); // 편리한 계산을 위해 도착 지점 추가
        rockPositions.sort(Comparator.naturalOrder()); // 오름차순 정렬        
    }
    
    private boolean check(int mid, int n) {
        int prevRockPos = 0; // 직전 바위의 지점
        int removedRockCnt = 0; // 제거할 바위의 개수
        
        for (int currentRockPos : rockPositions) {
            if (currentRockPos - prevRockPos < mid) { // mid보다 거리가 작은 경우
                removedRockCnt++; // 바위 제거
            } else {
                prevRockPos = currentRockPos; // 직전 바위의 지점 갱신
            }
        }
        
        return removedRockCnt <= n;
    }
}