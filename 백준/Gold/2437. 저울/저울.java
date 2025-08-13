import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] weights;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        weights = new int[N];
        st = new StringTokenizer(br.readLine());

        // 추 무게 저장
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        // 추 무게 오름차순 정렬
        Arrays.sort(weights);

        int maxMeasure = 0; // 현재까지 측정 가능한 총 무게
        
        for (int weight : weights) {
            if (maxMeasure + 1 < weight) break;
            maxMeasure += weight;
        }

        System.out.println(maxMeasure + 1);
        
        br.close();
    }
}