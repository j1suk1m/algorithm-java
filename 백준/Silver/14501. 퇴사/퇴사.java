import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] T;
    static int[] P;
    static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        T = new int[N + 1];
        P = new int[N + 1];

        for (int day = 1; day <= N; day++) {
            st = new StringTokenizer(br.readLine());

            T[day] = Integer.parseInt(st.nextToken());
            P[day] = Integer.parseInt(st.nextToken());
        }

        backtrack(1, 0);

        System.out.println(answer);
    }

    static void backtrack(int day, int profit) {
        if (day > N) {            
            answer = Math.max(answer, profit);
            return;
        }

        backtrack(day + 1, profit); // 현재 날짜에 상담을 진행하지 않는 경우
        
        if (day + T[day] <= N + 1) { // 현재 날짜에 상담을 진행하는 경우
            backtrack(day + T[day], profit + P[day]); 
        }
    }
}