import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine()); // 시험장의 개수

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());

        // 각 시험장에 있는 응시자의 수 입력
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        
        int B = Integer.parseInt(st.nextToken()); // 총감독관이 한 시험장에서 감시할 수 있는 응시자의 수
        int C = Integer.parseInt(st.nextToken()); // 부감독관이 한 시험장에서 감시할 수 있는 응시자의 수

        long answer = (long) N; // 각 시험장마다 총감독관 한 명씩 미리 배치

        // 각 시험장마다 필요한 부감독관의 최소 수 계산
        for (int i = 0; i < N; i++) {
            if (A[i] <= B) continue;

            answer += (A[i] - B) / C;

            if ((A[i] - B) % C > 0) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}