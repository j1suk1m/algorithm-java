import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int COLUMN = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[][] numbers = new int[N][COLUMN];

        // 전체 숫자표를 입력받는다.
        for (int i = 0; i < N; i++) {
            String[] row = reader.readLine().split(" ");
            
            for (int j = 0; j < COLUMN; j++) {
                numbers[i][j] = Integer.parseInt(row[j]);
            }
        }

        int[] prevMax = Arrays.copyOf(numbers[0], COLUMN);
        int[] prevMin = Arrays.copyOf(numbers[0], COLUMN);

        // 두 번째 줄부터 출발해 최대 점수와 최소 점수를 계산한다.
        for (int i = 1; i < N; i++) {
            int left = numbers[i][0];
            int center = numbers[i][1];
            int right = numbers[i][2];

            int[] currMax = new int[] {
                Math.max(prevMax[0], prevMax[1]) + left,
                Math.max(Math.max(prevMax[0], prevMax[1]), prevMax[2]) + center,
                Math.max(prevMax[1], prevMax[2]) + right
            };

            int[] currMin = new int[] {
                Math.min(prevMin[0], prevMin[1]) + left,
                Math.min(Math.min(prevMin[0], prevMin[1]), prevMin[2]) + center,
                Math.min(prevMin[1], prevMin[2]) + right
            };

            prevMax = currMax;
            prevMin = currMin;
        }

        int maxScore = Math.max(Math.max(prevMax[0], prevMax[1]), prevMax[2]);
        int minScore = Math.min(Math.min(prevMin[0], prevMin[1]), prevMin[2]);

        System.out.println(maxScore + " " + minScore);
    }
}