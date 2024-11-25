import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] score = new int[3];
        int[] number1 = {1, 2, 3, 4, 5};
        int[] number2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] number3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int index = 0;
        int highest = -1;
        
        while (index < answers.length) {
            if (number1[index % 5] == answers[index]) {
                score[0]++;
            }
            
            if (number2[index % 8] == answers[index]) {
                score[1]++;
            }
            
            if (number3[index % 10] == answers[index]) {
                score[2]++;
            }
            
            index += 1;
        }
        
        for (int i = 0; i < 3; i++) {
            if (highest < score[i]) {
                highest = score[i];
                answer.clear();
                answer.add(i + 1);
            } else if (highest == score[i]) {
                answer.add(i + 1);
            }
        }
        
        return answer.stream().mapToInt(num -> num).toArray();
    }
}