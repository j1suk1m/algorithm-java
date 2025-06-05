import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final boolean[] visited = new boolean[5];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int testCaseCount = Integer.parseInt(reader.readLine());
        List<String> testCases = new ArrayList<>();

        // 전체 테스트 케이스를 입력받는다.
        for (int i = 0; i < testCaseCount; i++) {
            testCases.add(reader.readLine());
        }

        // 백트래킹을 이용해 문자열 순열의 결과를 출력한다.
        for (int i = 0; i < testCaseCount; i++) {
            String testCase = testCases.get(i);
            System.out.println("Case # " + (i + 1) + ":");
            permutate(testCase, new StringBuilder(testCase.length()));
        }
    }
    
    private static void permutate(String testCase, StringBuilder permutation) {
        if (testCase.length() == permutation.length()) { // 종료 조건
            System.out.println(permutation);
            return;
        }

        for (int i = 0; i < testCase.length(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            permutation.append(testCase.charAt(i));
            permutate(testCase, permutation); 
            permutation.deleteCharAt(permutation.length() - 1);
            visited[i] = false;
        }
    }
}