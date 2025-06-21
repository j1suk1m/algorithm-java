import java.util.*;
import java.io.*;

public class Main {

    // 알파벳의 마지막 등장 인덱스를 저장할 배열
    // lastIndices[i]는 알파벳 (char)(i + 'a')의 마지막 등장 위치
    private static int[] lastIndices;
    private static final int NONE = -1; // 해당 문자가 아직 등장하지 않았음을 의미

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // 단어의 개수
        int groupWordCount = 0; // 그룹 단어의 개수

        for (int i = 0; i < N; i++) {
            initLastIndices(); // 매 단어마다 초기화
            String word = reader.readLine();

            if (isGroupWord(word)) { // 그룹 단어 여부 판단
                groupWordCount++;
            }
        }

        System.out.println(groupWordCount);
    }

    // 알파벳 등장 인덱스 배열 초기화
    private static void initLastIndices() {
        lastIndices = new int[26]; // 알파벳 소문자 개수: 26개
        Arrays.fill(lastIndices, NONE); // 모두 NONE(-1)으로 초기화
    }

    // 그룹 단어 여부 판단
    // 그룹 단어: 같은 문자가 연속해서 등장하거나, 중복 없이 순차적으로만 등장
    private static boolean isGroupWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            int charIndex = currentChar - 'a';

            // 이전에 등장한 적 있고, 이전 등장 위치가 현재 위치 - 1이 아니라면
            if (lastIndices[charIndex] != NONE && lastIndices[charIndex] + 1 != i) {
                return false; // 연속되지 않고 떨어진 상태로 다시 등장 -> 그룹 단어 아님
            }

            // 현재 문자의 등장 위치 갱신
            lastIndices[charIndex] = i;
        }

        return true;
    }
}