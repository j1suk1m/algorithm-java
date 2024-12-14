import java.io.*;
import java.util.ArrayList;

class Main {
    private static final int alphabetCount = 26;
    private static final boolean[][] graph = new boolean[2 * alphabetCount][2 * alphabetCount];

    // 대소문자 알파벳을 0 ~ 51 사이의 인덱스로 변환
    private static int getIndexFromAlphabet(char alphabet) {
        if (Character.isLowerCase(alphabet)) {
            return alphabet - 'a' + alphabetCount;
        } else {
            return alphabet - 'A';
        }
    }

    // 0 ~ 51 사이의 인덱스를 대소문자 알파벳으로 변환
    private static char getAlphabetFromIndex(int index) {
        if (index <= alphabetCount - 1) { // 대문자에 해당하는 경우
            return (char)('A' + index);
        } else { // 소문자에 해당하는 경우
            return (char)('a' + index - alphabetCount);
        }
    }

    // 플로이드 워셜 알고리즘
    private static void floydWarshall() {
        for (int k = 0; k < 2 * alphabetCount; k++) {
            for (int i = 0; i < 2 * alphabetCount; i++) {
                for (int j = 0; j < 2 * alphabetCount; j++) {
                    if (i == j || j == k || k == i) {
                        continue;
                    } else if (!graph[i][j] && graph[i][k] && graph[k][j]) {
                        graph[i][j] = true; // i => k이고 k => j이면 i => j
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> answers = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 그래프 저장
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" => ");

            char P = input[0].charAt(0);
            char Q = input[1].charAt(0);

            if (P == Q) { // 전건과 후건이 동일한 경우 무시
                continue;
            }

            graph[getIndexFromAlphabet(P)][getIndexFromAlphabet(Q)] = true;
        }

        floydWarshall();

        for (int i = 0; i < 2 * alphabetCount; i++) {
            for (int j = 0; j < 2 * alphabetCount; j++) {
                if (graph[i][j]) {
                    answers.add(getAlphabetFromIndex(i) + " => " + getAlphabetFromIndex(j));
                }
            }
        }

        System.out.println(answers.size());
        answers.forEach(System.out::println);
    }
}