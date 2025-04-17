import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static final List<Integer> nums = new ArrayList<>();
    private static int targetSum = 0; // 문제 본문의 n
    private static int targetOrder = 0; // 문제 본문의 k
    private static int order = 0;
    private static String answer = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        targetSum = Integer.parseInt(tokenizer.nextToken());
        targetOrder = Integer.parseInt(tokenizer.nextToken());

        backtrack(0);

        System.out.println(answer);
    }

    private static void backtrack(int sum) {
        if (sum == targetSum) {
            order += 1;

            if (order == targetOrder) {
                answer = generateAnswer("+");
            }

            return;
        }

        for (int num = 1; num <= 3; num++) { // 반복문에서 num이 오름차순으로 증가
            if (sum + num > targetSum) { // 이 조건을 만족한 분기는 해가 될 수 없음
                return; 
            }

            nums.add(num);
            backtrack(sum + num);
            nums.remove(nums.size() - 1);
        }
    }

    private static String generateAnswer(String delimiter) {
        return nums.stream().map(String::valueOf).collect(Collectors.joining(delimiter));
    }
}