import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue pq = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
        
        int N = Integer.parseInt(br.readLine()); // 연산의 개수

        while (N-- > 0) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) { // 힙에서 최댓값 제거 후 출력
                System.out.println(pq.isEmpty() ? 0 : pq.poll());
            } else { // 힙에 추가
                pq.offer(x);
            }
        }
    }
}