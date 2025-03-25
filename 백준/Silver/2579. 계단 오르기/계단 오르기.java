import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // 총 계단 개수를 저장한다.
        int stairCount = Integer.parseInt(reader.readLine());
        
        // 각 계단에 쓰여 있는 점수를 저장한다.
        int[] stairScores = new int[stairCount + 1];
        saveStairScores(stairScores, stairCount, reader);
        
        // 특이 케이스를 우선 처리한다.
        // stairCount가 1일 때 아래의 기저 조건에서 인덱스 범위를 초과할 수 있기 때문이다.
        if (stairCount <= 2) {
            int answer = 0;
            
            for (int score : stairScores) {
                answer += score;
            }
            
            System.out.println(answer);
            return;
        }

        // i번째 계단을 밟았다고 가정했을 때 그 시점까지 얻을 수 있는 최대 점수를 저장한다.
        int[] dp = new int[stairCount + 1];

        // 기저 조건을 명시한다.
        dp[1] = stairScores[1];
        dp[2] = stairScores[1] + stairScores[2];
        
        // 다이나믹 프로로그래밍을 이용해 i번째 계단을 밟았을 때의 최대 점수를 계산한다.
        for (int i = 3; i <= stairCount; i++) {
            dp[i] = Math.max(dp[i - 2], stairScores[i - 1] + dp[i - 3]) + stairScores[i];
        }
        
        System.out.println(dp[stairCount]);
    }
    
    // 각 계단의 점수를 저장한다.
    private static void saveStairScores(
        int[] stairScores, 
        int stairCount, 
        BufferedReader reader
    ) throws IOException {
        for (int i = 1; i <= stairCount; i++) {
            stairScores[i] = Integer.parseInt(reader.readLine());
        }
    }
    
}