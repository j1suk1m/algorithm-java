import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine()); // 숫자 카드 묶음 개수
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선 순위 큐

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(reader.readLine()));
        }

        int answer = 0; // 최소 비교 개수

        while (pq.size() >= 2) {
            int sum = pq.poll() + pq.poll(); // 하나의 묶음으로 합치기
            pq.add(sum);
            answer += sum;
        }

        System.out.println(answer);
    }
}