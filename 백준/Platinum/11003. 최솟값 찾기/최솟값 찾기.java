import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int value;
        int index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] D = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        Deque<Pair> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            // 영향 범위를 벗어난 값 제거
            while (!queue.isEmpty() && queue.getFirst().index <= i - L) {
                queue.removeFirst();
            }

            // 큐 내에서 오름차순을 유지하기 위해 뒤쪽 제거
            while (!queue.isEmpty() && queue.getLast().value > A[i]) {
                queue.removeLast();
            }

            // 현재 값 추가
            queue.addLast(new Pair(A[i], i));

            // 현재 구간의 최솟값 저장
            D[i] = queue.getFirst().value;
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            sb.append(D[i]);
            
            if (i < N - 1) {
                sb.append(" ");
            }
        }

        System.out.println(sb);
    }
}