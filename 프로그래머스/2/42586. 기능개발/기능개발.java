import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Task {
    private final int progress;
    private final int speed;
    
    Task(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
    }
    
    public int getProgress() {
        return progress;
    }
    
    public int getSpeed() {
        return speed;
    }
}

class Solution {
    private static Queue<Task> queue = new LinkedList();
    private static List<Integer> answer = new ArrayList<>();

    public int[] solution(int[] progresses, int[] speeds) {
        int deploymentOrder = -1;
        int day = 0;
        
        init(progresses, speeds);
        
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            int progress = task.getProgress();
            int speed = task.getSpeed();
            
            if (!isDeployable(progress, speed, day)) {
                deploymentOrder++;
                day = (int) Math.ceil((double) (100 - progress) / speed);
            }
            
            deploy(deploymentOrder);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    private void init(int[] progresses, int[] speeds) {
        int taskCount = progresses.length;
        
        for (int i = 0; i < taskCount; i++) {
            queue.offer(new Task(progresses[i], speeds[i]));
        }
    }
    
    private boolean isDeployable(int progress, int speed, int day) {
        return progress + (speed * day) >= 100;
    }
    
    private void deploy(int deploymentOrder) {
        if (deploymentOrder < answer.size()) {
            answer.set(deploymentOrder, answer.get(deploymentOrder) + 1);
        } else {
            answer.add(1);
        }
    }
}