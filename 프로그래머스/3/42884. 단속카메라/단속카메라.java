import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0; // 설치한 카메라의 개수
        int cameraPosition = -30001; // 마지막으로 설치한 카메라의 위치
        
        // 진출 지점에 대해 오름차순 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        for (int[] route : routes) {
            
            // 차량의 진입 지점이 마지막 카메라 설치 지점을 지나지 않음
            // 카메라 추가 설치 필요
            if (route[0] > cameraPosition) {
                answer++;
                
                // 최대한 많은 차량이 카메라를 지날 수 있도록 진출 지점에 설치
                cameraPosition = route[1]; 
                
            }
        }
        
        return answer;
    }
}