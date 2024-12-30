import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scovilles, int K) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int mixCount = 0; // 섞은 횟수
        
        for (int scoville : scovilles) {
            priorityQueue.offer(scoville);
        }
        
        while (priorityQueue.size() >= 2) {
            int minScoville1 = priorityQueue.poll(); // 가장 맵지 않은 음식의 스코빌 지수
            int minScoville2 = priorityQueue.poll(); // 두 번째로 맵지 않은 음식의 스코빌 지수
            
            if (minScoville1 >= K) {
                return mixCount;
            }
            
            priorityQueue.offer(minScoville1 + 2 * minScoville2); // 가장 맵지 않은 두 음식 혼합
            mixCount++; // 섞은 횟수 갱신
        }
        
        if (priorityQueue.poll() >= K) {
            return mixCount;
        } else {
            return -1;
        }
    }
}