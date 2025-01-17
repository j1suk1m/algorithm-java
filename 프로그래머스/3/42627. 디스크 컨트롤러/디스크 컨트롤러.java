import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {   
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((job1, job2) -> job1[1] - job2[1]); // 소요 시간이 짧은 순서대로 정렬
        int currentTime = 0;
        int sumOfTurnaroundTime = 0;
        int finishedJobCount = 0; // 완료된 작업의 개수
        int previousStartTime = -1; // 직전에 수행된 작업의 수행 시작 시간
        
        // 요청 시간이 빠른 순서대로 정렬
        Arrays.sort(jobs, (job1, job2) -> job1[0] - job2[0]);
        
        // 전체 작업을 수행할 동안 반복
        while (finishedJobCount < jobs.length) {
            
            // 현재 시점에 수행할 수 있는 작업을 힙에 추가
            for (int[] job : jobs) {
                if (previousStartTime < job[0] && job[0] <= currentTime) {
                    heap.offer(job);
                }
            }
            
            if (heap.isEmpty()) {
                currentTime++;
            } else {
                int[] job = heap.poll();
                previousStartTime = currentTime;
                currentTime += job[1];
                sumOfTurnaroundTime += (currentTime - job[0]);
                finishedJobCount++;
            }
        }
        
        return (int) sumOfTurnaroundTime / jobs.length;
    }
}