import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static Queue<Integer> bridge = new LinkedList<>(); // 다리
    private static int totalWeightOnBridge = 0; // 현재 다리를 건너고 있는 트럭들의 전체 무게
    
    public int solution(int bridgeLength, int maxWeight, int[] truckWeights) {
        int answer = 0;
                
        for (int currentWeight : truckWeights) {
            while (!(isPassable(currentWeight, bridgeLength, maxWeight))) { // 다리를 건널 수 있을 때까지                
                if (bridge.size() == bridgeLength) { // 다리에 올라갈 자리가 없는 경우
                    totalWeightOnBridge -= bridge.poll(); // 다리를 완전히 지난 트럭을 제거
                } else { // 무게 제한으로 건널 수 없는 경우
                    bridge.offer(0); // 무게가 0인 트럭 추가
                    answer++;
                }
            }
            
            bridge.offer(currentWeight); // 다리 위에 오르기
            totalWeightOnBridge += currentWeight; // 다리 위의 전체 무게 추가
            answer++;       
        }
            
        return answer + bridgeLength; // 마지막 트럭이 다리를 건너는 데 걸리는 시간 추가
    } 
    
    // 다리를 건널 수 있는지 확인
    private static boolean isPassable(int currentWeight, int bridgeLength, int maxWeight) {
        return (bridge.size() + 1 <= bridgeLength && totalWeightOnBridge + currentWeight <= maxWeight);
    }
}