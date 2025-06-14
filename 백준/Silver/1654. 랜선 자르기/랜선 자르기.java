import java.util.*;
import java.io.*;

class Main {
    private static int[] lengths;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        int K = Integer.parseInt(tokenizer.nextToken()); // 이미 가지고 있는 랜선의 개수
        int N = Integer.parseInt(tokenizer.nextToken()); // 필요한 랜선의 개수 

        lengths = new int[K];
            
        for (int i = 0; i < K; i++) { // 이미 가지고 있는 각 랜선의 길이 (cm)
            lengths[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(lengths);

        // check(low) != check(high)를 만족하는 low, high의 초깃값 설정
        long low = 1; // check(low) == true
        long high = (long) lengths[K - 1] + 1; // check(high) == false // int는 오버플로우 발생 가능

        while (low + 1 < high) {
            long mid = low + (high - low) / 2;

            if (check(K, N, mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }

        System.out.println(low);
    }

    /**
    랜선의 단위 길이가 mid(cm)와 같도록 자를 때, 적어도 N개 이상의 랜선을 만들 수 있는지 확인
    **/
    private static boolean check(int K, int N, long mid) {
        long totalCount = 0L;

        for (int i = K - 1; i >= 0; i--) {
            int length = lengths[i];

            if (length < mid) { // 조기 종료 조건
                break;
            }

            totalCount += (long) length / mid;
        }

        return totalCount >= (long) N;
    }
}