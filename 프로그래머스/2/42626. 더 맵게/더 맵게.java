import java.util.PriorityQueue;

class Solution {
    private static final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
    public int solution(int[] scovilles, int K) {
        int answer = 0;
        
        init(scovilles);
        
        while (minHeap.size() > 1) {
            if (minHeap.peek() >= K) {
                return answer;
            }
            
            int minScoville1 = minHeap.poll();
            int minScoville2 = minHeap.poll();

            int mixedScoville = minScoville1 + 2 * minScoville2;
            minHeap.offer(mixedScoville);
            answer++;
        }
        
        if (minHeap.peek() >= K) {
            return answer;
        }
        
        return -1;
    }
    
    private void init(int[] scovilles) {
        for (int scoville : scovilles) {
            minHeap.offer(scoville);
        }
    }
}