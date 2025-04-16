import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

enum Operator {
    PLUS {
        public int apply(int a, int b) {
            return a + b;
        }
    },
    MINUS {
        public int apply(int a, int b) {
            return a - b;
        }
    },
    MULTIPLY {
        public int apply(int a, int b) {
            return a * b;
        }
    },
    DIVIDE {
        public int apply(int a, int b) {
            return a / b;
        }
    };

    public abstract int apply(int a, int b);
}

public class Main {
    private static int max = Integer.MIN_VALUE; // 연산 결과의 최댓값
    private static int min = Integer.MAX_VALUE; // 연산 결과의 최솟값
    private static int numberCount;
    private static int[] numbers;
    private static int[] operatorCount; // 연산자 개수

    public static void main(String[] args) throws IOException {
        init(); // 변수 초기화
        backtrack(1, numbers[0]); // 백트래킹을 이용한 연산 결과의 최댓값, 최솟값 계산

        System.out.println(max);
        System.out.println(min);
    }

    private static void backtrack(int index, int result) {
        if (index == numberCount) { // 종료 조건
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (Operator operator : Operator.values()) {
            int i = operator.ordinal();

            if (operatorCount[i] > 0) {
                operatorCount[i]--;
                backtrack(index + 1, operator.apply(result, numbers[index]));
                operatorCount[i]++;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        numberCount = Integer.parseInt(reader.readLine());
        numbers = convertStringToIntArray(reader.readLine());
        operatorCount = convertStringToIntArray(reader.readLine());
    }

    private static int[] convertStringToIntArray(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}