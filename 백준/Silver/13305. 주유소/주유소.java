import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine()); // 도시의 개수
        
        long[] distances = new long[N - 1];
        st = new StringTokenizer(br.readLine());

        // 인접한 두 도시를 연결하는 도로의 길이 저장
        for (int i = 0; i < distances.length; i++) {
             distances[i] = Long.parseLong(st.nextToken());   
        }

        int[] prices = new int[N];
        st = new StringTokenizer(br.readLine());

        // 주유소의 리터당 가격 저장
        for (int i = 0; i < prices.length; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        long minCost = 0L; // 처음 도시에서 마지막 도시로 가는 최소 비용
        int currMinPrice = (int) 1e9 + 1; // 현재까지의 리터당 가격 최솟값

        for (int i = 0; i < distances.length; i++) {
            if (prices[i] < currMinPrice) {
                currMinPrice = prices[i]; // 최솟값 갱신
            }

            minCost += currMinPrice * distances[i]; // int형 오버플로우 발생 가능
        }

        bw.write(String.valueOf(minCost));
        bw.flush();

        br.close();
        bw.close();
    }
}