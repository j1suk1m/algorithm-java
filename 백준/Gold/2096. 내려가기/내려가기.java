import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int COLS = 3;
    private static int rows;
    private static int[][] numbers;
    private static int[] prevMax;
    private static int[] prevMin;

    public static void main(String[] args) throws IOException {
        rows = Integer.parseInt(reader.readLine());
        numbers = new int[rows][COLS];

        // 전체 숫자 테이블을 입력받아 초기화한다.
        initNumberTable();

        prevMax = numbers[0].clone();
        prevMin = numbers[0].clone();

        for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
            // 현재 행에서 왼쪽 숫자, 가운데 숫자, 오른쪽 숫자를 선택했을 때의 최대 및 최소 점수를 계산한다.
            int[] currMax = calculateCurrentMax(rowIndex);
            int[] currMin = calculateCurrentMin(rowIndex);

            // 다음 행으로 이동하기 위해 배열을 갱신한다.
            // 다음 루프에서 currMax, currMin이 초기화되므로 배열의 메모리 주소를 넘겨도 된다.
            prevMax = currMax;
            prevMin = currMin;
        }

        int maxScore = Math.max(Math.max(prevMax[0], prevMax[1]), prevMax[2]);
        int minScore = Math.min(Math.min(prevMin[0], prevMin[1]), prevMin[2]);

        System.out.println(maxScore + " " + minScore);
    }

    // 전체 숫자 테이블을 입력받아 초기화한다.
    private static void initNumberTable() throws IOException {
        for (int i = 0; i < rows; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());

            for (int j = 0; j < COLS; j++) {
                numbers[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 현재 행에서 왼쪽 숫자, 가운데 숫자, 오른쪽 숫자를 선택했을 때의 최대 점수를 각각 계산한다.
    private static int[] calculateCurrentMax(int rowIndex) {
        int left = numbers[rowIndex][0];
        int center = numbers[rowIndex][1];
        int right = numbers[rowIndex][2];

        return new int[] {
            Math.max(prevMax[0], prevMax[1]) + left,
            Math.max(Math.max(prevMax[0], prevMax[1]), prevMax[2]) + center,
            Math.max(prevMax[1], prevMax[2]) + right
        };
    }

    // 현재 행에서 왼쪽 숫자, 가운데 숫자, 오른쪽 숫자를 선택했을 때의 최소 점수를 각각 계산한다.
    private static int[] calculateCurrentMin(int rowIndex) {
        int left = numbers[rowIndex][0];
        int center = numbers[rowIndex][1];
        int right = numbers[rowIndex][2];

        return new int[] {
            Math.min(prevMin[0], prevMin[1]) + left,
            Math.min(Math.min(prevMin[0], prevMin[1]), prevMin[2]) + center,
            Math.min(prevMin[1], prevMin[2]) + right
        };
    }
}