import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            int N = Integer.parseInt(reader.readLine()); // 주가가 주어진 전체 날짜 수
            int[] prices = Stream.of(reader.readLine().split(" ")) // N일 동안의 주가 배열
                               .mapToInt(Integer::parseInt)
                               .toArray();
            
            long maximumProfit = 0L; // 최대 이익
            int sellingPrice = prices[N - 1]; // 매도할 가격

            // 주가 배열을 역으로 순회
            for (int i = N - 2; i >= 0; i--) {
                int currentPrice = prices[i];
                
                if (sellingPrice >= currentPrice) {
                    maximumProfit += sellingPrice - currentPrice; // 최대 이익 누적
                } else {
                    sellingPrice = currentPrice; // 매도할 가격 갱신
                }
            }

            System.out.println(maximumProfit);
        }
    }
}