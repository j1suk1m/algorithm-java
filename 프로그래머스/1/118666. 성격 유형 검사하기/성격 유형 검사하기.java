import java.util.*;

class Solution {
    private final char[][] types = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
    private final Map<Character, Integer> scores = new HashMap<>(); // key: 유형, value: 누적 점수

    public String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();

        // 0. 맵을 초기화한다.
        init();

        // 1. survey와 choices를 순회하며 각 유형별 점수를 누적한다.
        for (int i = 0; i < survey.length; i++) {
            char disagree = survey[i].charAt(0);
            char agree = survey[i].charAt(1);
            int choice = choices[i];

            if (choice < 4) { // 매우 비동의, 비동의, 약간 비동의를 선택한 경우
                scores.put(disagree, scores.get(disagree) + (4 - choice));
            } else if (choice > 4) { // 약간 동의, 동의, 매우 동의를 선택한 경우
                scores.put(agree, scores.get(agree) + (choice - 4));
            }
        }

        // 2. 이차원 배열을 순회하며 각 지표에서의 유형을 결정한다.
        for (char[] indicator : types) {
            char first = indicator[0];
            char second = indicator[1];
            int firstScore = scores.get(first);
            int secondScore = scores.get(second);

            // 점수가 더 높은 유형을 선택한다.
            // 점수가 같다면 사전순으로 빠른 첫번째 유형을 선택한다.
            answer.append(firstScore >= secondScore ? first : second);
        }

        // 3. 결정된 성격 유형을 반환한다.
        return answer.toString();
    }

    private void init() {
        for (char[] indicator : types) {
            for (char type : indicator) {
                scores.put(type, 0); // 0으로 초기화한다.
            }
        }
    }
}