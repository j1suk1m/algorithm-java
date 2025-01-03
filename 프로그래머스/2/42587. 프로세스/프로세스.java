import java.util.LinkedList;
import java.util.Queue;

class Process {
    private final int priority;
    private final int location;
    
    Process(int priority, int location) {
        this.priority = priority;
        this.location = location;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public int getLocation() {
        return location;
    }
}

class Solution {
    private static final Queue<Process> queue = new LinkedList<>();
    
    // 인덱스: 우선순위
    // 값: 아직 실행되지 않은 프로세스 중 해당 우선순위를 가지는 프로세스의 개수
    private static final int[] processCountsAtPriority = new int[10]; 
    
    public int solution(int[] priorities, int location) {
        int order = 1; // 실행 순서
        
        init(priorities); // 초기화
        
        while (!queue.isEmpty()) {
            Process process = queue.poll();
            int priority = process.getPriority();

            if (hasHigherPriority(priority)) { // 우선순위가 더 높은 프로세스가 큐에 존재하는 경우
                queue.offer(process); // 현재 프로세스를 실행하지 않고 큐에 다시 추가
            } else if (process.getLocation() == location) { // 타겟 프로세스를 발견한 경우
                break;
            } else { // 현재 프로세스를 실행할 수 있는 경우
                processCountsAtPriority[priority]--;
                order++;
            }
        }
        
        return order;
    }

    // 배열 및 큐 초기화
    private static void init(int[] priorities) {
        for (int i = 0; i < priorities.length; i++) {
            int priority = priorities[i];
            processCountsAtPriority[priority]++;
            queue.offer(new Process(priority, i));
        }
    }

    // 인자로 전달된 우선순위보다 높은 우선순위를 가지는 프로세스가 있는지 확인
    private static boolean hasHigherPriority(int priority) {
        for (int i = priority + 1; i < processCountsAtPriority.length; i++) {
            if (processCountsAtPriority[i] > 0) {
                return true;
            }
        }

        return false;
    }
}