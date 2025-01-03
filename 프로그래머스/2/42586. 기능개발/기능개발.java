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
    private static Queue<Task> queue = new LinkedList<>();
    private static List<Integer> answer = new ArrayList<>();

    public int[] solution(int[] progresses, int[] speeds) {
        int deploymentOrder = -1; // 배포 순서 // answer 리스트의 인덱스로 사용
        int passedDays = 0; // 지난 날짜

        init(progresses, speeds); // 초기화

        while (!queue.isEmpty()) {
            Task task = queue.poll();
            int progress = task.getProgress();
            int speed = task.getSpeed();

            if (!isDeployable(progress, speed, passedDays)) {
                deploymentOrder++; // 다음 배포 순서에 배포
                passedDays = calculatePassedDays(progress, speed); // 배포 가능한 날짜 계산
            }

            deploy(deploymentOrder); // 배포 
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    // 큐 초기화
    private void init(int[] progresses, int[] speeds) {
        int taskCount = progresses.length;

        for (int i = 0; i < taskCount; i++) {
            queue.offer(new Task(progresses[i], speeds[i]));
        }
    }

    // 배포 가능한지 확인
    private boolean isDeployable(int progress, int speed, int passedDays) {
        return progress + (speed * passedDays) >= 100;
    }

    // 배포가 가능하려면 며칠이 지나야 하는지 계산
    private int calculatePassedDays(int progress, int speed) {
        return (int) Math.ceil((double) (100 - progress) / speed);
    }

    // 배포
    private void deploy(int deploymentOrder) {
        if (deploymentOrder < answer.size()) {
            answer.set(deploymentOrder, answer.get(deploymentOrder) + 1);
        } else {
            answer.add(1);
        }
    }
}