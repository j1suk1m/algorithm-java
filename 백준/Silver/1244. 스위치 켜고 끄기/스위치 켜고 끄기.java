import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    // 스위치 전원 상태
    private static int[] switches;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int switchCount = Integer.parseInt(reader.readLine());
        switches = new int[switchCount];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        // 스위치 전원 상태 저장
        for (int i = 0; i < switchCount; i++) {
            switches[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int studentCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < studentCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int gender = Integer.parseInt(tokenizer.nextToken()); // 학생 성별
            int number = Integer.parseInt(tokenizer.nextToken()); // 학생이 받은 숫자

            if (gender == 1) { // 남학생
                performBoysTask(number, switchCount);
            } else { // 여학생
                performGirlsTask(number, switchCount);
            }
        }

        // 결과 출력
        printResult(switchCount);
    }

    private static void performBoysTask(int start, int end) {
        for (int i = start; i <= end; i += start) {
            switches[i - 1] = 1 - switches[i - 1];
        }
    }

    private static void performGirlsTask(int number, int switchCount) {
        int leftIndex = number - 2;
        int rightIndex = number;

        while (leftIndex >= 0 && rightIndex < switchCount 
               && switches[leftIndex] == switches[rightIndex]) {
            leftIndex--;
            rightIndex++;
        }

        for (int i = leftIndex + 1; i <= rightIndex - 1; i++) {
            switches[i] = 1 - switches[i];
        }
    }

    private static void printResult(int switchCount) {
        StringBuilder result = new StringBuilder();
        int numberCountPerLine = 20;

        for (int i = 0; i < switchCount; i++) {
            result.append(switches[i]);

            // 20개가 될 때마다 개행
            if ((i + 1) % numberCountPerLine == 0) {
                result.append("\n");
            } else {
                result.append(" ");
            }
        }

        System.out.println(result.toString());
    }

}