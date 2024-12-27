import java.util.Arrays;
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
    private static final int[] processCountsAtPriority = new int[10];

    private static void init(int[] priorities) {
        for (int i = 0; i < priorities.length; i++) {
            int priority = priorities[i];
            processCountsAtPriority[priority]++;
            queue.offer(new Process(priority, i));
        }
    }
    
    private static boolean existsHigherPriority(int priority) {
        for (int i = priority + 1; i < processCountsAtPriority.length; i++) {
            if (processCountsAtPriority[i] > 0) {
                return true;
            }
        }
        
        return false;
    }
    
    public int solution(int[] priorities, int location) {
        int order = 1;
        
        init(priorities);
        
        while (!queue.isEmpty()) {
            Process process = queue.poll();
            int priority = process.getPriority();

            if (existsHigherPriority(priority)) {
                queue.offer(process);
            } else if (process.getLocation() == location) {
                break;
            } else {
                processCountsAtPriority[priority]--;
                order++;
            }
        }
        
        return order;
    }
}