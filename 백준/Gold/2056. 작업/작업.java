import java.util.*;
import java.io.*;

class Main {
    static int N; // 작업 개수, [3, 1e4]
    static int[] times; // 작업마다 걸리는 시간
    static int[] indegrees; // 진입 차수
    static final List<List<Integer>> graph = new ArrayList<>();
    static final Deque<Integer> queue = new ArrayDeque<>();
    static int[] completionTimes; // 작업을 완료하기 위한 최소 시간
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        times = new int[N + 1];
        indegrees = new int[N + 1];
        completionTimes = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 작업 정보 저장 
        for (int currTask = 1; currTask <= N; currTask++) {
            st = new StringTokenizer(br.readLine());

            times[currTask] = Integer.parseInt(st.nextToken());
            int prevTaskCount = Integer.parseInt(st.nextToken());

            // 선행 작업 저장
            while (prevTaskCount-- > 0) {
                int prevTask = Integer.parseInt(st.nextToken());
                indegrees[currTask]++;
                graph.get(prevTask).add(currTask);
            }
        }

        // 진입 차수가 0인 작업을 큐에 저장
        for (int i = 1; i <= N; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
                completionTimes[i] = times[i];
            }
        }

        // 위상 정렬
        while (!queue.isEmpty()) {
            int currTask = queue.poll();

            for (int nextTask : graph.get(currTask)) {
                completionTimes[nextTask] = Math.max(completionTimes[nextTask], completionTimes[currTask] + times[nextTask]);
                indegrees[nextTask]--;

                if (indegrees[nextTask] == 0) queue.add(nextTask);
            }
        }

        int answer = 0;
        
        // 모든 작업을 완료하기 위한 최소 시간 계산
        for (int completionTime : completionTimes) {
            answer = Math.max(answer, completionTime);
        }

        System.out.println(answer);
    }
}